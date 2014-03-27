package com.gdcable.epm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gdcable.epm.entity.Menu;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.servicre.menu.MenuService;
import com.gdcable.epm.servicre.shiro.ShiroDbRealm.ShiroUser;
import com.gdcable.epm.servicre.user.UserService;

/**
 * 
 * <pre>
 * 系统主页入口
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-3
 */
@Controller
@RequestMapping(value = "/")
public class IndexController 
{
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	
	/**
	 * 进入首页
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request){
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		User currUser = userService.getUser(user.getId());
		List<Menu> menuList = this.menuService.getMenuListByCurrUser(currUser);
		long firstMenuId = 0l;
		if(menuList!=null && menuList.size()>0){
			firstMenuId = menuList.get(0).getId();
		}
		model.addAttribute("menuList", menuList);
		model.addAttribute("firstM", firstMenuId);
		return "frame/mainframe";
	}
	
	
	/**
	 * 系统头部
	 * 
	 * @return
	 */
	@RequestMapping(value = "top",method = RequestMethod.GET)
	public String top(HttpServletRequest request,Model model) {
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("name", user.getRealName());
		return "top";
	}
	/**
	 * 系统下部
	 * 
	 * @return
	 */
	@RequestMapping(value = "bottom",method = RequestMethod.GET)
	public String bottom() {
		return "bottom";
	}
	/**
	 * 系统主体
	 * 
	 * @return
	 */
	@RequestMapping(value = "main")
	public String main(HttpServletRequest request,Model model) {
		return "main";
	}
	/**
	 * 左边菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "left")
	public String left(HttpServletRequest request,Model model) {
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		User currUser = userService.getUser(user.getId());
		List<Menu> menuList = this.menuService.getMenuListByCurrUser(currUser);
		model.addAttribute("menuList", menuList);
		return "left";
	}
	/**
	 * 欢迎页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "welcome")
	public String welcome(HttpServletRequest request,Model model) {
		return "welcome";
	}

}
