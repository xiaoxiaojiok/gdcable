package com.gdcable.epm.controller.jbpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jbpm.pvm.internal.history.model.HistoryActivityInstanceImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.gdcable.epm.jbpm.service.JBPMProcess;

@Controller
@RequestMapping(value="/Workflow")
public class ProcessInfoAction{
	@Autowired
	private JBPMProcess jBPMProcess;
	Logger log = Logger.getLogger(ProcessInfoAction.class);
	
	@RequestMapping(value="/processview")
	public String processview(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String taskid = request.getParameter("taskid");
		if(taskid==null){
			return "sigong/projectList";
		}
		ProcessDefinitionImpl pdi = jBPMProcess.getProcessDefinitionByTask(taskid);
		request.setAttribute("processdefinition", pdi);
		
		List historyExecution = jBPMProcess.getHistoryExecutionImpByTask(taskid);
		HashMap map = new HashMap();
		Iterator it = historyExecution.iterator();
		while(it.hasNext()){
			HistoryActivityInstanceImpl activity =(HistoryActivityInstanceImpl) it.next();
			if(map.containsKey(activity.getActivityName())){
				List li = (List)map.get(activity.getActivityName());
				li.add(activity);
			}
			else{
				List li = new ArrayList();
				li.add(activity);
				map.put(activity.getActivityName(), li);
			}
		}
		request.setAttribute("historyexecution", map);
		return "sigong/processinfo";
	}

	public void setjBPMProcess(JBPMProcess jBPMProcess) {
		this.jBPMProcess = jBPMProcess;
	}
	
}
