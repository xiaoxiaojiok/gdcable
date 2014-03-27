package com.gdcable.epm.servicre.permission;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.permission.PermissionDao;
import com.gdcable.epm.entity.Permission;
import com.gdcable.epm.entity.Role;
import com.gdcable.epm.servicre.base.BaseService;

/**
 * 
 * <pre>
 * 权限业务
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-9
 */
@Component
@Transactional(readOnly = true)
public class PermissionService extends BaseService
{
	@Autowired
	private PermissionDao permissionDao;
	
	/**
	 * <pre>
	 * 根据菜单名查找权限(分页)
	 * </pre>
	 * @param menuName 菜单名
	 * @param pageable 分页组件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<Permission> findByAll(final String menuName,Pageable pageable)
	{
		if(StringUtils.isEmpty(menuName))
		{
			 List<Permission> perlist = (List<Permission>) this.permissionDao.findAll(Permission.class);
			 Object t = perlist.size();
			return new PageImpl(perlist, pageable, t!=null?Long.parseLong(t.toString()):0);
		}
		else
		{
			return this.permissionDao.queryByMenuName(menuName, pageable);
		}
	}
	
	/**
	 * <pre>
	 * 保存权限
	 * </pre>
	 * @param permission
	 */
	@Transactional(readOnly = false)
	public void save(Permission permission)
	{
		if(permission.getId()==null || permission.getId()==0){
			permissionDao.save(permission);
		}else{
			permissionDao.update(permission);
		}
	}
	
	/**
	 * <pre>
	 * 批量删除
	 * </pre>
	 * @param ids
	 */
	@Transactional(readOnly = false)
	public void deleteByIds(String ids)
	{
		permissionDao.deleteByIds(ids);
	}
	
	public Permission getById(Long id)
	{
		return (Permission) findById(id, Permission.class);
	}
	
	/**
	 * <pre>
	 * 根据菜单id查找该菜单的权限集
	 * </pre>
	 * @param menuId
	 * @return
	 */
	public List<Permission> getPermissionsByMenuId(Long menuId)
	{
		return permissionDao.getPermissionsByMenuId(menuId);
	}
	
	/**
	 * <pre>
	 *  根据菜单ID和角色ID查找权限集
	 * </pre>
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public List<Permission> getPermissionByMenuIdAndRoleId(Long menuId,Long roleId)
	{
		return permissionDao.getPermissionsByMenuIdAndRoleId(menuId, roleId);
	}
	/**
	 * <pre>
	 * 根据权限ID集合查找权限集
	 * </pre>
	 * @param permList
	 * @return
	 */
	public List<Permission> getPermissionByPermIds(Collection<Permission> permList)
	{
		String permIds = "";
		for(Permission per:permList){
			permIds +=per.getId()+",";
		}
		if(permIds.length()>=2);
		permIds = permIds.substring(0, permIds.length()-1);
		return permissionDao.getPermissionByPermIds(permIds);
	}
	
	/**
	 * <pre>
	 * 根据角色ID集合查找菜单权限集
	 * </pre>
	 * @param roles
	 * @return
	 */
	public List<Permission> getPermissionByRoleIds(Collection<Role> roles)
	{
		String roleIds = "";
		for(Role role:roles){
			roleIds +=role.getId()+",";
		}
		if(roleIds.length()>=2);
		roleIds = roleIds.substring(0, roleIds.length()-1);
		return permissionDao.getPermissionByRoles(roleIds);
	}

	public List<Permission> getPermissionByRoles(String roleids)
	{
		return permissionDao.getPermissionByRoles(roleids);
	}

	public void findByAll(String menuName, Paging<Permission> p)
	{
		this.permissionDao.queryByMenuName(menuName, p);
	}
	
}
