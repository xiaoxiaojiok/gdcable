package com.gdcable.epm.jbpm.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.gdcable.epm.jbpm.dao.instance.IJbInstance;
import com.gdcable.epm.jbpm.dao.opinion.IJbOption;
import com.gdcable.epm.jbpm.dao.task.IJbpmTask;
import com.gdcable.epm.jbpm.po.instance.JbInstance;
import com.gdcable.epm.jbpm.po.opinion.JbOption;
import com.gdcable.epm.jbpm.service.JBPMProcess;
import com.gdcable.epm.jbpm.service.JBPMTask;

/**
 * 
 * <pre>
 * 	处理流程中有关任务的api
 * </pre>
 * @author dxq
 * @version 1.0, 2013年9月23日
 */
@Component
@Transactional(readOnly=true)
public class JBPMTaskService extends JBPMCommon implements JBPMTask {
	@Autowired
	private IJbpmTask iJbpmTask;
	@Autowired
	private JBPMProcess jBPMProcess;
	@Autowired
	private IJbInstance iJbInstance;
	@Autowired
	private IJbOption iJbOption ;
	private Logger log = Logger.getLogger(JBPMTaskService.class);
	
	
	/**
	 * <pre>
	 * 	 根据流程实例id，返回这个流程的所有正在审核的任务
	 * </pre>
	 * @param id 流程实例id
	 * @return 返回taskImp的list
	 */
	public List getTaskByIstance(String id){
		return taskService.createTaskQuery().processInstanceId(id).list();
	}


	public void setiJbpmTask(IJbpmTask iJbpmTask) {
		this.iJbpmTask = iJbpmTask;
	}
	
	/**
	 * <pre>
	 * 	 完成任务走下一步,作废
	 * </pre>
	 * @param taskId 任务id
	 * @param userId 用户id
	 * @return 是否完成
	 * @see #completeTask(String, String, String, Map)
	 */
	@Transactional(readOnly=false)
	@Deprecated
	public boolean completeTask(String taskId,String userId){
		TaskImpl  task =(TaskImpl) taskService.getTask(taskId);
		if(task.getAssignee()==null || task.getAssignee().equals("")){
			taskService.takeTask(taskId, userId);
		}
		HashMap map = new HashMap();
		map.put("suggestion", "同意");
		taskService.completeTask(taskId,map);
		return true;
	}
	
	/**
	 * <pre>
	 * 	 完成任务走下一步
	 * </pre>
	 * @param task 任务
	 * @param userId 用户id
	 * @param transition 流向name
	 * @param variables 参数hashmap
	 * @return 是否完成
	 */
	@Transactional(readOnly=false)
	public boolean completeTask(TaskImpl task,String userId,String transition,Map variables){
		
		return completeTask(task.getId(),userId,transition,variables);
	}
	
	
	/**
	 * <pre>
	 * 	 完成任务走下一步
	 * </pre>
	 * @param taskId 任务id
	 * @param userId 用户id
	 * @param transition 流向name
	 * @param variables 参数hashmap
	 * @return 是否完成
	 */
	@Transactional(readOnly=false)
	public boolean completeTask(String taskId,String userId,String transition,Map variables){
		//根据任务id获取任务
		TaskImpl  task =(TaskImpl) taskService.getTask(taskId);
		
		//如果variables中没有opinion 获取opinion为空 默认设置为同意
		String opinion = "同意";
		if(variables.containsKey("opinion") && StringUtils.hasText(variables.get("opinion")+"")){
			opinion = (String)variables.get("opinion");
		}
		
		//根据流程实例id 去int_jb_instance查找出对应的业务,
		HashMap inspar = new HashMap();
		inspar.put("instance", task.getProcessInstance().getId());
		List r = iJbInstance.getAll(JbInstance.class,inspar);
		JbInstance jb =(JbInstance) r.get(0);
		
		//保存意见
		JbOption po = new JbOption();
		po.setChecktime(new Date());
		po.setInstance(task.getProcessInstance().getId());
		po.setServiceid(jb.getService()); //根据上面查找
		po.setTaskid(Long.parseLong(taskId));
		po.setSuggestion(opinion);
		iJbOption.save(po);
		
		//查看任务是否有候选人，如果没 设置为userId
		if(task.getAssignee()==null || task.getAssignee().equals("")){
			taskService.takeTask(taskId, userId);
			log.debug(userId+ "获得任务"+ taskId);
		}
		
		if(log.isDebugEnabled()){
			JSONObject jo = JSONObject.fromObject(variables);
			log.debug(jo.toString());
		}
		
		//如果transition为空或者为null，先去流程定义中查找看是这个节点是否有为空的transition，否则就按第一条
		boolean hasNullTransition = true; //假设有空流向
		TransitionImpl firstTransition = null;
		if(!StringUtils.hasText(transition)){
			ProcessDefinitionImpl  pdi = jBPMProcess.getProcessDefinitionByTask(taskId);
			ActivityImpl ai = pdi.getActivity(task.getName());
			List out = ai.getOutgoingTransitions();
			Iterator oit = out.iterator();
					
			int i = 0;
			while(oit.hasNext()){
				TransitionImpl ti =(TransitionImpl) oit.next();
				if(i==0){
					firstTransition = ti;
					i++;
				}
						
				if(!StringUtils.hasText(ti.getName())){
					hasNullTransition = true;
					break;
				}
				else hasNullTransition = false;
			}
		}
		if(!hasNullTransition){
					//如果参数transition为空，但是这个节点没有空的流向，就设置为第一个
			transition = firstTransition.getName();
		}
		
		//完成任务走下一步
		if(StringUtils.hasText(transition)){
			taskService.completeTask(taskId,transition,variables);
		}
		else{
			taskService.completeTask(taskId, variables);
		}
		
		return true;
	}
	
	
	
	/**
	 * <pre>
	 * 	 根据taskname和taskid,去历史表中找到最近完成的task,
	 * </pre>
	 * @param taskname 节点name
	 * @param taskid 当前task节点，主要来确认流程实例id
	 * @return TaskImpl
	 */
	public HistoryTaskImpl getLastTaskByTaskName(String taskname,String taskId){
		TaskImpl task = (TaskImpl) taskService.getTask(taskId);
		List result = iJbpmTask.getLastTaskByTaskName(taskname, task.getExecutionId());
		if(result ==null){
			return null;
		}
		HistoryTaskInstanceImpl last = (HistoryTaskInstanceImpl) result.get(0);
		return last.getHistoryTask();
	}

	
	public Task getTaskById(String taskId){
		return taskService.getTask(taskId);
	}
	public void setjBPMProcess(JBPMProcess jBPMProcess) {
		this.jBPMProcess = jBPMProcess;
	}
	public void setiJbInstance(IJbInstance iJbInstance) {
		this.iJbInstance = iJbInstance;
	}
	public void setiJbOption(IJbOption iJbOption) {
		this.iJbOption = iJbOption;
	}
	
	
	
	
	
}
