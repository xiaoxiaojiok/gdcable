package com.gdcable.epm.servicre.shiro;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.gdcable.epm.entity.Role;
import com.gdcable.epm.entity.Unit;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.servicre.role.RoleService;
import com.gdcable.epm.servicre.unit.UnitService;
import com.gdcable.epm.servicre.user.UserService;
import com.gdcable.epm.util.Encrypt;

/**
 * <pre>
 * 自实现用户与权限查询.
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-11
 */
public class ShiroDbRealm extends AuthorizingRealm
{
	
	private UserService userService;
	private UnitService unitService;
	private RoleService roleService;
	@Autowired
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	@Autowired
	public void setUnitService(UnitService unitService)
	{
		this.unitService = unitService;
	}
	@Autowired
	public void setRoleService(RoleService roleService)
	{
		this.roleService = roleService;
	}

	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findUserByLoginName(token.getUsername());
		if (user == null)
		{
			return null;
		}
		else
		{
			//机构列表（ 有级别关系）
			List<Unit> list = unitService.getUnitList(user);
			//机构列表下拉框（ 有级别关系）
			String unitOptions = unitService.unitOptions(list,null,null,null);
			List<Role> rlist = user.getRoleList();
			List<Unit> runitList = roleService.getBusinessRoleUnitList(rlist);
			String roleUnitOptions = "";
			if(runitList!=null && runitList.size()>0){
				List<Unit> ulist = unitService.getUnitByIds(runitList);
				roleUnitOptions = unitService.roleUnitOptions(ulist);
			}
			return new SimpleAuthenticationInfo(new ShiroUser(user.getLoginName(), user.getPassword(), user.getId(),
					user.getRoleList(), user.getUnitId(), user.getUsername(),unitOptions,roleUnitOptions), user.getPassword(), getName());
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		User user = userService.findUserByLoginName(shiroUser.getLoginName());
		if (user != null)
		{
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : user.getRoleList())
			{
				// 基于Role的权限信息
				info.addRole(role.getName());
				// 基于Permission的权限信息
				info.addStringPermissions(role.getPermissions());
			}
			return info;
		}
		else
		{
			return null;
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal)
	{
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		principals.getRealmNames();
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo()
	{
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null)
		{
			for (Object key : cache.keys())
			{
				cache.remove(key);
			}
		}
	}

	public ShiroUser getShiroUser()
	{
		return new ShiroUser();
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public class ShiroUser implements Serializable, HttpSessionBindingListener
	{

		private static final long serialVersionUID = -1748602382963711884L;

		private String loginName;

		private String password;

		private long id;

		private List<Role> rolelist;

		private Unit unit;

		private String realName; // 真实名字
		
		private String userUnitOptions; // 当前用户直属管辖机构下拉列表
		
		private String userRoleUnitOptions; //当前用户所属业务角色管辖机构下拉列表
		
		public String getUserUnitOptions()
		{
			return userUnitOptions;
		}

		public void setUserUnitOptions(String userUnitOptions)
		{
			this.userUnitOptions = userUnitOptions;
		}

		public String getUserRoleUnitOptions()
		{
			return userRoleUnitOptions;
		}

		public void setUserRoleUnitOptions(String userRoleUnitOptions)
		{
			this.userRoleUnitOptions = userRoleUnitOptions;
		}

		public String getRealName()
		{
			return realName;
		}

		public void setRealName(String realName)
		{
			this.realName = realName;
		}

		public List<Role> getRolelist()
		{
			return rolelist;
		}

		public void setRolelist(List<Role> rolelist)
		{
			this.rolelist = rolelist;
		}

		public Unit getUnit()
		{
			return unit;
		}

		public void setUnit(Unit unit)
		{
			this.unit = unit;
		}

		public ShiroUser()
		{
		};

		public ShiroUser(String loginName, String password, long id, List<Role> rolelist, Unit unit, String realName,String unitOptions,String roleUnitOptions)
		{
			this.loginName = loginName;
			this.password = password;
			this.id = id;
			this.rolelist = rolelist;
			this.unit = unit;
			this.realName = realName;
			this.userUnitOptions = unitOptions;
			this.userRoleUnitOptions = roleUnitOptions;
		}

		public String getLoginName()
		{
			return loginName;
		}

		public String getPassword()
		{
			return password;
		}

		public void setPassword(String password)
		{
			this.password = password;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString()
		{
			return loginName;
		}

		public long getId()
		{
			return id;
		}

		public void setLoginName(String loginName)
		{
			this.loginName = loginName;
		}

		public void setId(long id)
		{
			this.id = id;
		}

		public void valueBound(HttpSessionBindingEvent event)
		{

		}

		public void valueUnbound(HttpSessionBindingEvent event)
		{
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 扩展用户登陆鉴权验证，针对数据库用户密码加密的问题
	 * </pre>
	 * @author Administrator
	 * @version 1.0, 2013-9-16
	 */
	public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
	    @Override
	    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
	        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	        Object tokenCredentials = Encrypt.MD5((String.valueOf(token.getPassword())));
	        Object accountCredentials = getCredentials(info);
	        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
	        return equals(tokenCredentials, accountCredentials);
	    }
	}

}
