package com.gdcable.epm.controller.common.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.entity.Menu;
import com.gdcable.epm.entity.Permission;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.publics.StringView;
import com.gdcable.epm.servicre.menu.MenuService;
import com.gdcable.epm.servicre.permission.PermissionService;
import com.gdcable.epm.servicre.shiro.ShiroDbRealm.ShiroUser;
import com.gdcable.epm.servicre.user.UserService;

/**
 * 
 * <pre>
 * 菜单controller
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-8
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserService userService;
	
	/**
	 * 菜单管理  -- 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Menu> list = menuService.getMenuListByLevel(1);
		String menuOptions = menuService.menuOptions(list, null);
		map.put("menuOptions", menuOptions);
		return new ModelAndView("menu/index",map);
	}
	
	/**
	 * 菜单管理  -- 列表
	 * 
	 * @return
	 */
	//	@RequiresPermissions("menu:view")
	@RequestMapping(value = "list")
	public ModelAndView list(HttpServletRequest request,String menuName,int status,Long pMenu){
		System.out.println(11);
		Map<String,Object> map = new HashMap<String,Object>();
		Paging<Menu> p = HibernateUtils.createPaging(request);
		System.out.println(p.getCurrentPage()+"-------");
		menuService.findByParams(menuName,status,pMenu,p);
		HibernateUtils.savePaging(request, p);
		List<Menu> list = menuService.constructMenuList((List<Menu>) p.getData());
		map.put("list", list);
		return new ModelAndView("menu/list",map);
	}
	
	/**
	 * 菜单管理  -- 编辑
	 * 
	 * @param request
	 * @param id
	 * @param addType	正常添加0
	 * 					正常修改1
	 * 					添加下级2
	 * @return
	 */
//	@RequiresPermissions("menu:edit")
	@RequestMapping(value = "edit")
	public ModelAndView edit(HttpServletRequest request,Long id,Integer addType){
		Map<String,Object> map = new HashMap<String,Object>();
		Menu menu = (Menu) menuService.findById(id, Menu.class);
		List<Menu> list = menuService.getMenuListByLevel(1);
		String menuOptions = "";
		if(addType == 2){
			menuOptions = menuService.menuOptions(list, menu);
			menu = null;
		}else{
			menuOptions = menuService.menuOptions(list, menu == null ? null : menu.getMenu());
		}
		map.put("menuOptions", menuOptions);
		map.put("menu", menu);
		map.put("addType", addType);
		return new ModelAndView("menu/edit",map);
	}
	
	/**
	 * 菜单管理  -- 保存
	 * 
	 * @return
	 */
//	@RequiresPermissions("menu:save")
	@RequestMapping(value = "save")
	public ModelAndView save(HttpServletRequest request,Menu menu,Long top_menu){
		StringView view = new StringView();
		Menu pMenu = null;
		if(StringUtils.isEmpty(menu.getMenuUrl())){
			menu.setMenuUrl("/");
		}
		try{
			//当前所属系统标识
//			int intSys = CurrentSystemUtil.getCurrentSystem();
//			menu.setIntSys(intSys);
			if(top_menu != 0){
				pMenu = (Menu) menuService.findById(top_menu,Menu.class);
			}else{
				pMenu = menuService.findByMenuName("ROOT");
			}
			if(menu.getId() == null){	//新增菜单
				ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
				User user = userService.getUser(shiroUser.getId());
				menu.setMenu(pMenu);
				menu.setCreater(user);
				menuService.save(menu);
				if(menu.getMenuLevel() > 1){
					//新增菜单时，增加该菜单所属权限
					System.out.println(menu.getId());
					Permission p = new Permission();
					p.setPermName(menu.getMenuName() + "[菜单]");
					p.setDescript(menu.getMenuName());
					p.setPermType(-1);
					p.setPermValue(String.valueOf(menu.getId()));
					p.setMenu(menu);
					permissionService.save(p);
				}
			}else{	//修改菜单
				Menu m = (Menu) menuService.findById(menu.getId(),Menu.class);
				m.setMenuName(menu.getMenuName());
				m.setMenuUrl(menu.getMenuUrl());
				m.setOrderNum(menu.getOrderNum());
				m.setStatus(menu.getStatus());
				m.setMenuLevel(menu.getMenuLevel());
				m.setMenu(pMenu);
				menuService.update(m);
				
			}
			view.setContent("success");
		}catch(Exception ex){
			view.setContent("faild");
			ex.printStackTrace();
		}
		
		return new ModelAndView(view);
	}
	
	/**
	 * 菜单管理  -- 删除菜单及子菜单
	 * 
	 * @return
	 */
//	@RequiresPermissions("menu:delete")
	@RequestMapping(value = "delete")
	public ModelAndView delete(String ids){
		StringView view = new StringView();
		Menu m = null;
		try{
			if(StringUtils.isNotEmpty(ids)){
				String arr[] = ids.split(",");
				for (String string : arr)
				{
					m = (Menu) menuService.findById(new Long(string),Menu.class);
					//有外键关联需删除所有子菜单
					List<Menu> list = menuService.getSubMenuListByPMenu(m);
					menuService.deleteSubMenus(list);
					List<Permission> permlist = permissionService.getPermissionsByMenuId(m.getId());
					if(permlist!=null){
						for(Permission per:permlist){
							permissionService.delete(per);
						}
					}
					//删除菜单
					menuService.delete(m);
				}
 			}
			view.setContent("success");
		}catch(Exception ex){
			view.setContent("faild");
			ex.printStackTrace();
		}
		
		return new ModelAndView(view);
	}
	
	/**
	 * 菜单管理  -- 禁用 / 启用
	 * 
	 * @return
	 */
//	@RequiresPermissions("menu:setup")
	@RequestMapping(value = "setup")
	public ModelAndView setup(Long id,Integer status){
		StringView view = new StringView();
		Menu m = null;
		try{
			m = (Menu) menuService.findById(id,Menu.class);
			m.setStatus(status);
			menuService.update(m);
			view.setContent("success");
		}catch(Exception ex){
			view.setContent("faild");
			ex.printStackTrace();
		}
		return new ModelAndView(view);
	}
	
	/**
	 * 根据上级菜单获取层级信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getLayerInfo")
	public ModelAndView getLayerInfo(HttpServletRequest request,Long id){
		StringView view = new StringView();
		Menu menu = (Menu) menuService.findById(id,Menu.class);
		menu.setSubMenuList(menuService.getSubMenuListByPMenu(menu));
		int maxOrderNum = 0;
		for (int i = 0 ; i < menu.getSubMenuList().size(); i++){
			Menu m = menu.getSubMenuList().get(i);
			if(i == 0) maxOrderNum = m.getOrderNum();
			if(m.getOrderNum() > maxOrderNum){
				maxOrderNum = m.getOrderNum();
			}
		}
		String result =  maxOrderNum + "," + menu.getMenuLevel();
		view.setContent(result);
		return new ModelAndView(view);
	}
	
	@RequestMapping(value="leftmenu/{pMenuId}")
	public String leftMenu(@PathVariable long pMenuId,Model model){
		System.out.println("来到这里了");
		Menu menu = (Menu) menuService.findById(pMenuId,Menu.class);
		menu.setSubMenuList(menuService.getSubMenuListByPMenu(menu));
		model.addAttribute("menu", menu);
		return "/frame/west";
	}
}
