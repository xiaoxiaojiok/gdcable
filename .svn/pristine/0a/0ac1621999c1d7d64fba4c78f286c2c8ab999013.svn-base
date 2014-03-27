package com.gdcable.epm.controller.common.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.entity.Role;
import com.gdcable.epm.entity.Unit;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.publics.StringView;
import com.gdcable.epm.servicre.role.RoleService;
import com.gdcable.epm.servicre.unit.UnitService;
import com.gdcable.epm.servicre.user.UserService;
import com.gdcable.epm.util.Encrypt;
import com.gdcable.epm.util.RandomUtil;



/**
 * 用户管理控制器
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
@Controller
@RequestMapping(value = "/user")
public class UserController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UnitService unitService;
	/**
	 * 进入用户管理菜单
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(Model model){
		Object shiroUser = SecurityUtils.getSubject().getPrincipal();
		User user = userService.findUserByLoginName(shiroUser.toString());
		//机构列表（ 有级别关系）
		List<Unit> list = unitService.getUnitList(user);
		//机构列表下拉框（ 有级别关系）
		String unitOptions = unitService.unitOptions(list,null,null,null);
		model.addAttribute("unitOptions", unitOptions);
		model.addAttribute("user", user);
		return "account/index";
	}
	/**
	 * 用户的列表查询.
	 */
//	@RequiresPermissions("user:view")
	@RequestMapping(value = "query")
	public ModelAndView list(HttpServletRequest request,Long unit,Long roleId,String username,String loginname){
		Map<String,Object> map = new HashMap<String,Object>();
		Paging<User> p = HibernateUtils.createPaging(request);
		User user = userService.getUser(request);
		userService.getUserList(unit, roleId, username, loginname, user, p);
		HibernateUtils.savePaging(request, p);
		System.out.println(p.getData().size());
		map.put("curr_user", user);
		return new ModelAndView("account/list",map);
	}
	
	/**
	 * 删除用户，支持多个用户勾选批量删除
	 * @param ids(格式："1,2,3")
	 * @return
	 */
//	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request,String ids){		
		StringView view = new StringView();
		if(StringUtils.isNotEmpty(ids)){			
			String[] arr= ids.split(",");
			try{				
				for(int i=0;i<arr.length;i++){
					long id=Long.parseLong(arr[i]);
					userService.deleteUser(id);
				}
			}catch (Exception e) {
				view.setContent("false");
			}
			
		}
		view.setContent("success");	
		return new ModelAndView(view);
		
	}

	/**
	 *用户的新增与修改的界面初始化
	 * @param id
	 * @return
	 */
//	@RequiresPermissions("user:edit")
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(HttpServletRequest request,@PathVariable Long id){
		User curUser = userService.getUser(request); //获取当前用户
		User user = userService.getUser(id);				//编辑的用户，当为新增时，此为空
		Map<String,Object> map = new HashMap<String,Object>();
		if(user !=null){     //编辑用户时
				//机构列表（ 有级别关系）
			List<Unit> list = unitService.getUnitList(curUser);
			Unit unit = unitService.findById(user.getUnitId().getId());
			//机构列表下拉框（ 有级别关系）
			String unitOptions = unitService.unitOptions(list,unit,null,null);
			map.put("dw_select",unitOptions );
		}else{
			//机构列表（ 有级别关系）
			List<Unit> list = unitService.getUnitList(curUser);
			//机构列表下拉框（ 有级别关系）
			String unitOptions = unitService.unitOptions(list,null,null,null);
			map.put("dw_select",unitOptions);
		}
		
		List<Role> roles = new ArrayList<Role>();
		if (user != null){ // 编辑用户模式时
			roles = user.getRoleList();
		}
		//当前用户新建或者修改用户的基础权限集合应该为自身拥有的集合的子集
		List<Role> roleList = new ArrayList<Role>();
		for (Role role : curUser.getRoleList()){
			if(role.getStatus() == 1){
				boolean flag = true;
				for (Role role2 : roles){
					if(role.getId().equals(role2.getId())){
						flag = false;
						break;
					}
				}
				if(flag){
					roleList.add(role);
				}
			}
		}
		
		//当前用户新建或者修改用户的可登陆子系统集合应该为自身可登陆的集合的子集
		map.put("user", user);		//编辑用户，当为新增时，此变量为空
		map.put("curUser", curUser); //当前用户
		map.put("roleList", roleList);  //可供选择的角色
		map.put("roles", roles); //用户已有的角色
		map.put("key", id==0); 	//区分用户的修改和新增(key为true时：新增；key为false时：修改。）
		return new ModelAndView("account/edit",map);
	}
	
	/**
	 * 保存用户（新增、修改用户）
	 * 
	 * @return
	 */
//	@RequiresPermissions("user:save")
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request,User user,String birth,String roleIds){
		User DbUser = null;
		Unit unit = unitService.findById(Long.parseLong(request.getParameter("unit_id")));
		user.setUnitId(unit);
		boolean flag = false;		//flag为true时：编辑模式，flag为false时：新增模式
		if(user.getId() != null){		//编辑模式下的保存应该先获取数据库中用户实体
			DbUser=userService.getUser(user.getId());
			flag = true;
		}
		StringView view = new StringView();
		String[] roles=null;
		List<Role> roleList = new ArrayList<Role>();
		if(!"".equals(roleIds) && roleIds !=null){
			roles=roleIds.split(",");		//注意：空字符串经过split操作得到的数组长度为1，会产生异常
		}			
		if(roles !=null){
			for(String roleId:roles){
				Role role =(Role) roleService.findById(Long.parseLong(roleId), Role.class);
				roleList.add(role);
			}
		}
		if(flag){ //编辑模式保留之前的地段内容
			DbUser.setRoleList(roleList);	//编辑模式不涉及密码的处理
			DbUser.setUsername(user.getUsername());
			DbUser.setGender(user.getGender());
			DbUser.setMobile(user.getMobile());
			DbUser.setEmail(user.getEmail());
			DbUser.setUnitId(user.getUnitId());
		}else{
			user.setRoleList(roleList);	
			user.setPassword(Encrypt.MD5(user.getInitPassword()));
		}
		try{
			if(flag){
				userService.saveUser(DbUser);
			}else{
				userService.saveUser(user);
			}
			view.setContent("success");
		}catch(Exception ex){
			view.setContent("faild");
			ex.printStackTrace();
		}

		return new ModelAndView(view);
	}
	
	//登录名的唯一性检查
	@RequestMapping(value = "/check")
	public ModelAndView checkLoginName(HttpServletRequest request,String loginName){
		StringView view = new StringView();
		if(userService.findUserByLoginName(loginName) == null){
			view.setContent("true");
		}else{
			view.setContent("false");
		}
		return new ModelAndView(view);
		
	}
	/**
	 * 用户的停用与启用
	 * @param request
	 * @param userId
	 * @param status
	 * @return
	 */
//	@RequiresPermissions("user:setup")
	@RequestMapping(value = "/setup",method=RequestMethod.POST)
	public ModelAndView setup(HttpServletRequest request,Long userId,int sta){
		StringView view = new StringView();
		try{
			User user = userService.getUser(userId);
			user.setStatus(sta);
			userService.saveUser(user);
			view.setContent("success");
		}catch (Exception e){
			view.setContent("faild");
			e.printStackTrace();
		}
		return new ModelAndView(view);
	}

	/**
	 * 重置用户密码
	 * @param request
	 * @param userId
	 * @param status
	 * @return
	 */
//	@RequiresPermissions("user:resetPwd")
	@RequestMapping(value = "/resetPwd",method=RequestMethod.POST)
	public ModelAndView resetPwd(HttpServletRequest request,String ids){
		StringView view = new StringView();
		try{
			String [] arr = ids.split(",");
			for(String string : arr){
				User user = userService.getUser(new Long(string));
				user.setInitPassword("123456");
				user.setPassword(Encrypt.MD5(user.getInitPassword()));
				userService.saveUser(user);
			}
			view.setContent("success");
		}catch (Exception e){
			view.setContent("faild");
			e.printStackTrace();
		}
		return new ModelAndView(view);
	}
	
	/**
	 * 检查系统中是否已存在该用户名 如果存在加1
	 * 
	 * @return
	 */
	public String checkLoginName(String loginName){
		String result = "";
		if(!"".equals(loginName)){
			User u = userService.findUserByLoginName(loginName);
			if(u == null){
				result = loginName;
			}else{
				Pattern pattern = Pattern.compile("[0-9]*");
				if(pattern.matcher(loginName.substring(loginName.length() - 1)).matches()){
					loginName = loginName.substring(0,loginName.length() - 1) + (Integer.parseInt(loginName.substring(loginName.length() - 1)) + 1);
				}else{
					loginName = loginName + "1";
				}
				result = checkLoginName(loginName);
			}
		}
		return result;
	}
	
	
	
	public String setLoginName(String loginName){
		Pattern pattern = Pattern.compile("[0-9]*");
		if(pattern.matcher(loginName.substring(loginName.length() - 1)).matches()){
			loginName = loginName.substring(0,loginName.length() - 1) + (Integer.parseInt(loginName.substring(loginName.length() - 1)) + 1);
		}else{
			loginName = loginName + "1";
		}
		return loginName;
	}
	
	/**
	 * 随机生成图片名称
	 *
	 * @param originalFileName
	 * @return
	 */
	private String createRandomFileName(String originalFileName){
		return RandomUtil.getInstance().randNumberAndAlpha(15) + System.currentTimeMillis() + originalFileName.substring(originalFileName.lastIndexOf("."));
	}
}
