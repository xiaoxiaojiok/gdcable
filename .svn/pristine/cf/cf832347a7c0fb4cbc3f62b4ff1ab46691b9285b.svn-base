package com.gdcable.epm.controller.workflow;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdcable.epm.entity.Work;
import com.gdcable.epm.jbpm.service.JBPMProcess;
import com.gdcable.epm.jbpm.service.JBPMTask;
import com.gdcable.epm.servicre.shiro.ShiroDbRealm.ShiroUser;
import com.gdcable.epm.servicre.workflow.WorkServiceImpl;

@Controller
@RequestMapping(value="/Workflow")
public class WorkInfoController{
	
	@Autowired
	private WorkServiceImpl workService;

	private final static String JBPM4_PREFIX ="jbpm4_";
	private static String DEFAULTTRANSITION = "default-transition";
	private static String PARAMETER_PREFIX = "jbpm4_";
	private static String DECISIONS = "decision";
	@Autowired
	private JBPMProcess jBPMProcess;
	@Autowired
	private JBPMTask jBPMTask;
	private Logger log = Logger.getLogger(WorkInfoController.class);
	
	
	//进入到添加页面
	@RequestMapping(value="/index")
	public String workIndex(HttpServletRequest request,Model model
			) throws Exception{
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("user", user);
		return "sigong/add";
	}
	
	//进入到待审核页面
	@RequestMapping(value="/list")
	public String workList(HttpServletRequest request,Model model
			) throws Exception{
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("user", user);
		return "sigong/projectList";
	}
	
	
	//进入到已审核页面
	@RequestMapping(value="/checkedlist")
	public String workCheckedList(HttpServletRequest request,Model model
			) throws Exception{
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("user", user);
		return "sigong/projectCheckedList";
	}
	

	@RequestMapping(value="/workSave")
	public String workSave(HttpServletRequest request,HttpServletResponse response,
			HttpSession session,Work work) throws Exception{
		workService.save(work);
		request.setAttribute("work", work);
		return "sigong/view";
	}
	
	//保存并且自动提交第一步
	@RequestMapping(value="/workComplete")
	public String workComplete(HttpServletRequest request,HttpServletResponse response,
			HttpSession session,Work work){
//		UserImpl user = (UserImpl)session.getAttribute("userinfo");
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		HashMap variable = new HashMap();
		Map req = request.getParameterMap();
		Iterator it = req.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			if(key.startsWith(JBPM4_PREFIX)){
				String[] t = (String[]) req.get(key);
				variable.put(key.substring(JBPM4_PREFIX.length()), t[0]);
				log.debug(key+ " "+t[0]);
			}
		}
		
		workService.saveAndCompleteWork(work,user.getLoginName(),variable);
		return "sigong/projectList";  
	}
	
	
	@RequestMapping(value="/workTaskComplete")
	public String workTaskComplete(HttpServletRequest request,HttpServletResponse response){
		String taskid = request.getParameter("taskid");
		if(!StringUtils.hasText(taskid)){
			request.setAttribute("requeststatus", "有问题");
//			return (String)result.get("projectList");
		}
//		User user = (User)request.getSession().getAttribute("userinfo");
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		
		String transition = request.getParameter("transitionName");
		if(!StringUtils.hasText(transition) || transition.equals(DEFAULTTRANSITION)){
			transition = null;
		}
		
	
		ProcessDefinitionImpl pdi = jBPMProcess.getProcessDefinitionByTask(taskid);
		TaskImpl task = (TaskImpl) jBPMTask.getTaskById(taskid);
		ActivityImpl ai = pdi.getActivity(task.getName());
		TransitionImpl tr ;
		if(StringUtils.hasText(transition)){
			tr= ai.getOutgoingTransition(transition);
		}
		else tr = ai.getDefaultOutgoingTransition();
		ActivityImpl destination =tr.getDestination();
		
		
		Map parameters = request.getParameterMap();
		HashMap variables =obtainVarFromRequest(parameters);
		if(destination.getType()!=null && destination.getType().equals(DECISIONS)){
			String executionid = task.getExecutionId();
			List servicevo = jBPMProcess.getServiceByProcessInstanceId(executionid);
			addServiceDate(variables,servicevo);
		}
		jBPMTask.completeTask(taskid, user.getLoginName(),transition,variables);
		request.setAttribute("requeststatus", "成功");
		return "sigong/projectList";
	}
	
	@RequestMapping(value="/workInfoView")
	public String workInfoView(HttpServletRequest request,HttpServletResponse response){
		String taskid = request.getParameter("taskid");
		String serviceid = request.getParameter("serviceid");
		Work info = workService.findWorkById(Long.parseLong(serviceid));
		List suggestion = jBPMProcess.getHistSuggestionByTaskId(taskid);
		request.setAttribute("work", info);
		request.setAttribute("suggestion", suggestion);
		request.setAttribute("taskid", taskid);
		
		ProcessDefinitionImpl pdi = jBPMProcess.getProcessDefinitionByTask(taskid);
		TaskImpl task = (TaskImpl) jBPMTask.getTaskById(taskid);
		ActivityImpl aci = pdi.getActivity(task.getActivityName());
		List out = aci.getOutgoingTransitions();
		
		request.setAttribute("outcome", out);
		return "sigong/viewDetail";
	}
	
	private HashMap obtainVarFromRequest(Map parameters){
		HashMap variables = null;
		Iterator it = parameters.keySet().iterator();
		while(it.hasNext()){
			String parametername = (String) it.next();
			String[] parametervalue = (String[]) parameters.get(parametername);
			if(parametername.indexOf(PARAMETER_PREFIX)>-1 && parametervalue.length >0){
				if(variables==null){
					variables = new HashMap();
				}
				parametername = parametername.substring(PARAMETER_PREFIX.length());
				variables.put(parametername, parametervalue[0]);
				log.debug(parametername+ " "+parametervalue[0]);
			}
		}
		return variables;
	}
	private void addServiceDate(Map parameter,List servicevo){
		Object service = servicevo.get(0);
		Class clazz = service.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if(parameter==null){
			parameter = new HashMap();
		}
		for(Field f : fields){
			if(f.getType().isAssignableFrom(Collection.class)){
				continue;
			}
			else if(f.getType().isAssignableFrom(Map.class)){
				continue;
			}
			else if(f.getType().isAssignableFrom(Set.class)){
				continue;
			}
			else if(f.getType().isArray()){
				continue;
			}
			else{
				try{
					String methodname ="get"+f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1);
					Method me = clazz.getDeclaredMethod(methodname, new Class[]{});
					Object result = me.invoke(service, new Object[]{});

					if(result instanceof String){
						parameter.put(f.getName(), result);
					}
					else if(result instanceof Number){
						parameter.put(f.getName(), result);
					}
					else if(result instanceof Boolean){
						parameter.put(f.getName(), result);
					}
					else if(result instanceof Character){
						parameter.put(f.getName(), result);
					}
				}catch(Exception e){
					
				}
			}
		}
	}

}
