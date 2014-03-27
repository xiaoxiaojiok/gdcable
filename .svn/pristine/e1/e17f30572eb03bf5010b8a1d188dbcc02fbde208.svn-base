package com.gdcable.epm.controller;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 
 * <pre>
 * 登陆处理
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
@Controller
@RequestMapping("/")
public class LoginController
{
	 @RequestMapping(value = "/login", method = RequestMethod.POST)  
	 public String loginfail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		System.out.println("login");
		return "login";
	}
	 
	 @RequestMapping(value = "/login", method = RequestMethod.GET)  
	  public String login(){
	    return  "login";  
	  }  



}
