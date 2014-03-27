package com.gdcable.epm.controller.workflow;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdcable.epm.jbpm.service.JBPMProcess;
import com.gdcable.epm.jbpm.service.JBPMTask;
import com.gdcable.epm.jbpm.vo.WorkVo;
import com.gdcable.epm.jbpm.vo.common.TwoTuple;
import com.gdcable.epm.servicre.shiro.ShiroDbRealm.ShiroUser;

/**
 * 
 * <pre>
 * 查询用户关于work业务的待办，参数通过反射来实现，</br>
 * 查询的字段的name要与po中的值对应起来。</br>
 * 封装参数的VO需要继承com.accentrue.gd_net.jbpm.vo.PageForm</br>
 * </pre>
 * @author dxq
 * @version 1.0, 2013年9月22日
 * 
 */
@Controller
@RequestMapping(value="/Workflow")
public class WorkViewHandlerController {
	private static String default_service = "Work";
	@Autowired
	private JBPMTask jBPMTask;
	@Autowired
	private JBPMProcess jBPMProcess;
	private String key = "ConstructionDelegate";
	
	
	@RequestMapping(value="/listHandlerall")
	@ResponseBody
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int page ;
		int rows ;
		String classname="com.ibaiyun.pojo.Work";
		WorkVo form = new WorkVo();
		
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		Class clazz = form.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try{
			for(int i=0;i<fields.length;i++){
				Field f = fields[i];
				System.out.println(f.getName());
				if(StringUtils.hasText(request.getParameter(f.getName()))){
					String methodname = "set"+f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1);
					Method method = clazz.getMethod(methodname, new Class[]{String.class});
					method.invoke(form, new Object[]{request.getParameter(f.getName())});
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String pagestr = request.getParameter("page");
		if(!StringUtils.hasText(pagestr)){
			page = 1;
		}
		else page=Integer.parseInt(pagestr);
		
		String rowsstr = request.getParameter("rows");
		if(!StringUtils.hasText(rowsstr)){
			rows = 10;
		}
		else rows=Integer.parseInt(rowsstr);
		
		form.setRows(rows);
		form.setPage(page);
		JSONObject json = new JSONObject();
		TwoTuple<Long, List> resultTwoTuple = null;
		try {
			resultTwoTuple = jBPMProcess.getServiceByProcessDefinitionKey(user.getLoginName(), key,form);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				json.put("total", 0);
				json.put("rows", "");
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
			return json.toString();
		}
		json.put("total", resultTwoTuple.a);
		json.put("rows", resultTwoTuple.b);
		return json.toString();
	}	

}
