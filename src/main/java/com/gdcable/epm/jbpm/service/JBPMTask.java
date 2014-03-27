package com.gdcable.epm.jbpm.service;

import java.util.List;
import java.util.Map;

import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.jbpm.pvm.internal.task.TaskImpl;


/**
 * 
 * <pre>
 * 	处理流程中有关任务的api
 * </pre>
 * @author dxq
 * @version 1.0, 2013年9月23日
 */
public interface JBPMTask {
	
	
	
	/**
	 * <pre>
	 * 	 根据流程实例id，返回这个流程的所有正在审核的任务
	 * </pre>
	 * @param id 流程实例id
	 * @return 返回taskImp的list
	 */
	public List getTaskByIstance(String id);
	
	
	public Task getTaskById(String taskId);
	
	/**
	 * <pre>
	 * 	 完成任务走下一步,作废
	 * </pre>
	 * @param taskId 任务id
	 * @param userId 用户id
	 * @return 是否完成
	 * @see #completeTask(String, String, String, Map)
	 */
	@Deprecated
	public boolean completeTask(String taskId,String userId);
	
	
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
	public boolean completeTask(String taskId,String userId,String transition,Map variables);
	
	
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
	public boolean completeTask(TaskImpl task,String userId,String transition,Map variables);
	
	/**
	 * <pre>
	 * 	 根据taskname和taskid,去历史表中找到最近完成的task,
	 * </pre>
	 * @param taskname 节点name
	 * @param taskid 当前task节点，主要来确认流程实例id
	 * @return TaskImpl
	 */
	public HistoryTaskImpl getLastTaskByTaskName(String taskname,String taskId);

}
