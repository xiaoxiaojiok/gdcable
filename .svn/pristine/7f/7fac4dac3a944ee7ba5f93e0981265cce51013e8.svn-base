package com.gdcable.epm.controller.jbpm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.gdcable.epm.jbpm.helper.JBPMProcessData;
import com.gdcable.epm.jbpm.service.JBPMIdentity;
import com.gdcable.epm.jbpm.service.JBPMProcess;
import com.gdcable.epm.jbpm.service.JBPMTask;


@Controller
@RequestMapping(value="/jbpm")
public class NextPersonAction{
	
	@Autowired
	private JBPMProcess jBPMProcess;
	@Autowired
	private JBPMTask jBPMTask;
	@Autowired
	private JBPMIdentity jBPMIdentity;
	@Autowired
	private ProcessInfoHandler processInfoHandler;
	private Logger log = Logger.getLogger(NextPersonAction.class);
	
	
	@RequestMapping(value="nextPerson")
	@ResponseBody
	public void nextPerson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		String taskid = request.getParameter("taskid");
		String transitionName = request.getParameter("transitionName");
		String processkey = request.getParameter("processkey");
		JSONObject json = new JSONObject();
		
		ProcessDefinitionImpl pdi = null;
		TaskImpl task = null;
		ActivityImpl activity = null;
		if((!StringUtils.hasText(taskid)) && (!StringUtils.hasText(transitionName))){
			//默认为提交表单时候选人
			log.debug("从开始节点开始获取候选人");
			if(!StringUtils.hasText(processkey)){
				json.put("status", "-1");
				json.put("info", "获取用户失败,条件不足");
				response.setHeader("Cache-Control", "no-cache");  
				PrintWriter pw = response.getWriter();
				json.write(pw);
				pw.close();
				return ;
			}
			//建设流程begin节点只有一条流程，，难道会有2个？
			pdi = jBPMProcess.getProcessDefinitionByKey(processkey);
			activity =(ActivityImpl) pdi.getInitial().getOutgoingTransitions().get(0).getDestination();
			
		}
		
		if(pdi==null){
			pdi = jBPMProcess.getProcessDefinitionByTask(taskid);
			task = (TaskImpl) jBPMTask.getTaskById(taskid);
			activity = pdi.getActivity(task.getName());
		}
		
		HashMap result = new HashMap();
		
		//当流程为JBPMProcessData.PROCESS_BACK的某个值时候，assigne当上次这个环节中获取
		boolean isBackTransitionName = false;
		String[] back = JBPMProcessData.PROCESS_BACK.split(",");
		if(StringUtils.hasText(transitionName)){
			for(String b : back){
				if(b.equals(transitionName)){
					isBackTransitionName = true;
					break ;
				}
			}
		}
		if(isBackTransitionName){
			//为返回，打回类似意思的流向的时候
			TransitionImpl ti = activity.getOutgoingTransition(transitionName);
			ActivityImpl target = ti.getDestination();
			HistoryTaskImpl historyTask = jBPMTask.getLastTaskByTaskName(target.getName(), taskid);
			log.info(task.getName()+"  id  "+task.getId()+"  "+transitionName+"   "+historyTask.getAssignee());
			result.put("type", JBPMProcessData.TASK_NAME);
			List li = new ArrayList();
			result.put("data", li);
			HashMap fi = new HashMap();
			li.add(fi);
			fi.put("name", target.getName());
			HashMap info = new HashMap();
			fi.put("candidate", info);
			info.put("roleid", new ArrayList());
			List user = new ArrayList();
			user.add(historyTask.getAssignee());
			info.put("userid", user);
		}
		else{
			processInfoHandler.obtainNextPerson(pdi,activity,task,transitionName,result);
		}
		
		
		List user =new ArrayList();
		if(!result.containsKey("data")){
			//考虑end节点 后面没数据
		}
		else{
			List data = (List)result.get("data");
			Iterator it = data.iterator();
			while(it.hasNext()){
				Map userinfo =(Map) it.next();
				String taskname = (String) userinfo.get("name");
				Map info = (Map) userinfo.get("candidate");
				List userid = (List)info.get("userid");
				List roleid = (List)info.get("roleid");
				List users = jBPMIdentity.getUserByIdAndGroup(userid, roleid);
				userinfo.put("candidate", users);
			}
		}
		
		json = JSONObject.fromObject(result);
		response.setHeader("Cache-Control", "no-cache");  
		PrintWriter pw = response.getWriter();
		json.write(pw);
		pw.close();
		return ;
		
	}

}