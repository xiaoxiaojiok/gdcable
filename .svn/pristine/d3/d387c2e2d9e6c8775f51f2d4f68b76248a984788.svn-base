package com.gdcable.epm.controller.common.permission;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.entity.Menu;
import com.gdcable.epm.entity.Permission;
import com.gdcable.epm.publics.StringView;
import com.gdcable.epm.servicre.menu.MenuService;
import com.gdcable.epm.servicre.permission.PermissionService;

/**
 * 
 * <pre>
 * 权限管理
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-9
 */
@Controller
@RequestMapping(value = "/permission")
public class PermissionController
{
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 
	 * <pre>
	 * 查询权限列表
	 * </pre>
	 * @param pageNumber
	 * @param menuName
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("perm:view")
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request,String menuName, Model model)
	{
		Paging<Permission> p = HibernateUtils.createPaging(request);
		permissionService.findByAll(menuName, p);
		HibernateUtils.savePaging(request, p);
		model.addAttribute("menuName", menuName);
		return "permission/list";
	}
	
	/**
	 * <pre>
	 * 添加页面
	 * </pre>
	 * @param model
	 * @return
	 */
	//	@RequiresPermissions("perm:edit")
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(Model model)
	{
		List<Menu> list = (List<Menu>) menuService.findAll(Menu.class);
		model.addAttribute("permission", new Permission());
		model.addAttribute("mlist",list);
		return "permission/edit";
	}
	/**
	 * <pre>
	 * 保存
	 * </pre>
	 * @param role
	 * @param result
	 * @return
	 */
//	@RequiresPermissions("perm:edit")
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView  save(HttpServletRequest request,Long id,String permName,String descript,int permType,Long menuId, String permValue)
	{
		StringView view=new StringView();
		Permission per=null;
		if(id==0){
			per=new Permission();
		}else{
			per=(Permission) permissionService.findById(id, Permission.class);
		}
		per.setPermName(permName);
		per.setDescript(descript);
		per.setPermType(permType);
		per.setPermValue(permValue);
		per.setMenu((Menu)menuService.findById(menuId, Menu.class));
		try{
			permissionService.save(per);
			view.setContent("success");
		}catch (Exception e) {
			view.setContent("faild");
		}
		return new ModelAndView(view);
	}
	
	/**
	 * <pre>
	 * 批量删除
	 * </pre>
	 * @param ids
	 * @return
	 */
//	@RequiresPermissions("perm:delete")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public boolean batchDel(String ids)
	{
		try{
			if(StringUtils.isNotEmpty(ids.trim()))
			{
				permissionService.deleteByIds(ids);
			}
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
	}
}
