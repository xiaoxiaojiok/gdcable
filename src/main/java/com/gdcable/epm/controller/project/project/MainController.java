package com.gdcable.epm.controller.project.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("project")
public class MainController
{
	@RequestMapping("index")
	public String index(){
		return "project/index";
	}
}
