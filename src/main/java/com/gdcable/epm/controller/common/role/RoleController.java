package com.gdcable.epm.controller.common.role;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.entity.Mcode;
import com.gdcable.epm.entity.Permission;
import com.gdcable.epm.entity.Role;
import com.gdcable.epm.entity.Unit;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.servicre.mcode.McodeService;
import com.gdcable.epm.servicre.permission.PermissionService;
import com.gdcable.epm.servicre.role.RoleService;
import com.gdcable.epm.servicre.shiro.ShiroDbRealm.ShiroUser;
import com.gdcable.epm.servicre.unit.UnitService;
import com.gdcable.epm.servicre.user.UserService;

/**
 * <pre>
 * 角色管理controller
 * 授权信息: role:view 角色查询，role:add 角色添加，role:save 角色保存，role:delete 角色删除
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController
{
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private McodeService mcodeService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PermissionListEditor permissionListEditor;

	@SuppressWarnings("unchecked")
//	@RequiresPermissions("role:view")
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, String roleName, Model model)
	{
		Paging<Role> p = HibernateUtils.createPaging(request);
		roleService.queryAllRole(roleName, p);
		HibernateUtils.savePaging(request, p);
		return "role/list";
	}
	
	/**
	 * <pre>
	 * 添加
	 * </pre>
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("role:add")
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String add(Model model)
	{
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String unitOptions = shiroUser.getUserUnitOptions();
		model.addAttribute("unitOptions", unitOptions);
		List<Mcode> mlist = mcodeService.findByMtye("ROLE_M");
		model.addAttribute("roleType", mlist);
		model.addAttribute("role", new Role());
		return "role/edit";
	}
	
	/**
	 * <pre>
	 * 保存角色
	 * </pre>
	 * @param role
	 * @param result
	 * @param redirectAttributes
	 * @return
	 */
//	@RequiresPermissions("role:save")
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@Valid Role role,Long roleTypeId,Long roleUnitId,BindingResult result, RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			return "role/edit";
		}
		else
		{
			try{
				Mcode mcode = this.mcodeService.findById(roleTypeId, Mcode.class);
				role.setRoleType(mcode);
				if(mcode.getMvalue().equals("2")){
					Unit unit = unitService.findById(roleUnitId);
					role.setRoleUnit(unit);
				}
				roleService.save(role);
				List<User> superUser = userService.getSuperManager();
				for(User u:superUser){
					if(userService.isExitUserAndRole(u.getId(), role.getId())){
						continue;
					}else{
						List<Role> rlist = u.getRoleList();
						rlist.add(role);
						userService.update(u);
					}
				}
				redirectAttributes.addFlashAttribute("message", "保存角色" + role.getName() + "成功");
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return "redirect:/role/list";
		}
	}
	
	/**
	 * <pre>
	 * 批量删除
	 * </pre>
	 * @param ids
	 * @return
	 */
//	@RequiresPermissions("role:delete")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public boolean delete(String ids)
	{
		boolean success = false;
		if(StringUtils.isNotEmpty(ids.trim()))
		{
			String[] roleids = ids.split(",");
			for(String id : roleids)
			{
				Role role = (Role) this.roleService.findById(Long.parseLong(id), Role.class);
				roleService.delete(role);
			}
			success=true;
		}
		return success;
	}
	
	/**
	 * <pre>
	 * 保存授权
	 * </pre>
	 * @param role
	 * @return
	 */
//	@RequiresPermissions("role:permission")
	@RequestMapping(value = "menu/save",method=RequestMethod.POST)
	public String accreditRole(Role role,Long menuId,RedirectAttributes redirectAttributes)
	{
		Role r = (Role) roleService.findById(role.getId(), Role.class);
		List<Permission> rolePermList = permissionService.getPermissionByRoles(r.getId().toString());
		if(role.getPermissionList()== null )
		{
			//原来有权限现在没有了。
			if(rolePermList !=null && rolePermList.size()>0)
			{
				r.getPermissionList().removeAll(rolePermList);
			}
		}
		else 
		{
			//当原来的权限为空，且现在有授权时
			if(rolePermList==null || rolePermList.size()==0)
			{
				r.getPermissionList().addAll(role.getPermissionList());
			}
			else 
			{ 
				//过滤权限添加
				for(Permission p : role.getPermissionList())
				{
					if(!rolePermList.contains(p))
					{
						r.getPermissionList().add(p);
					}
				}
			   //过滤权限删除
				for(Permission p : rolePermList)
				{
					if(!role.getPermissionList().contains(p))
					{
						r.getPermissionList().remove(p);
					}
				}
			}
		}
		roleService.save(r);
		redirectAttributes.addFlashAttribute("message", "授权给" + r.getName() + "成功");
		return "redirect:/role/accredit/"+role.getId();
	}
	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(List.class, "permissionList", permissionListEditor);
	}
}
