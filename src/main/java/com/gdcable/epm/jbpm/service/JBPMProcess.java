package com.gdcable.epm.jbpm.service;

import java.io.File;
import java.util.List;

import org.jbpm.api.ProcessInstance;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;

import com.gdcable.epm.jbpm.vo.PageForm;
import com.gdcable.epm.jbpm.vo.common.TwoTuple;


/**
 * 
 * <pre>
 * 	关于流程启动和运行阶段api
 * </pre>
 * @author dxq
 * @version 1.0, 2013年9月23日
 */
public interface JBPMProcess {
	
	/**
	 * 
	 * <p>
	 *  发布流程,作废
	 * </p>
	 * @param url classpath路径
	 * @return 返回流程定义id
	 */
	@Deprecated
	public String saveProcessDefinition(String url);
	
	
	/**
	 * 
	 * <p>
	 *  发布流程,作废
	 * </p>
	 * @param File 路径
	 * @return 返回流程定义id
	 */
	@Deprecated
	public String saveProcessDefinition(File url);
	
	
	/**
	 * 
	 * <p>
	 *   提交一个表单的时候，启动一个流程
	 * </p>
	 * @param key 流程的key
	 * @param id 业务po的id
	 * @param po 业务po的class
	 * @param userId 第一个环节的办理人 默认设置为当前用户
	 * @return 返回 流程实例
	 */
	public ProcessInstance saveProcessInstanceByKey(String key,Long id,Class po,String userId);
	
	/**
	 * 
	 * <p>
	 *   启动流程根据key，如无特殊情况不使用
	 * </p>
	 * @param key 流程key
	 * @return 返回ProcessInstance
	 */
	@Deprecated
	public ProcessInstance saveProcessInstanceByKey(String key);
	
	
	/**
	 * 
	 * <p>
	 *   根据任务，返回流程定义
	 * </p>
	 * @param taskId 任务id
	 * @return 返回流程定义ProcessDefinitionImpl
	 */
	public ProcessDefinitionImpl getProcessDefinitionByTask(String taskId);
	
	/**
	 * 
	 * <p>
	 *   根据key，返回最新流程定义
	 * </p>
	 * @param key 流程Key
	 * @return 返回流程定义ProcessDefinitionImpl
	 */
	public ProcessDefinitionImpl getProcessDefinitionByKey(String key);
	
	
	/**
	 * 
	 * <p>
	 *   根据流程实例id找业务
	 * </p>
	 * @param executionid 流程实例id
	 * @return 返回 业务List  （没有意外情况都是一条）
	 */
	public List getServiceByProcessInstanceId(String executionid);
	
	/**
	 * 
	 * <p>
	 *   根据任务id，返回这个流程的历史环节（非任务）
	 * </p>
	 * @param taskId 任务id
	 * @return 返回List
	 */
	public List  getHistoryExecutionImpByTask(String taskId);
	
	/**
	 * 
	 * <p>
	 *   根据流程实例id，返回这个流程的历史环节（非任务）
	 * </p>
	 * @param processinstanceid 任务流程实例id
	 * @return 返回 List
	 */
	public List  getHistoryExecutionImpByProcessInstance(String processinstanceid);
	
	
	/**
	 * 
	 * <p>
	 *   启动流程实例id,获取所有意见
	 * </p>
	 * @param procid 流程实例id
	 * @return 返回List
	 */
	public List getHistSuggestionByProcId(String procid);
	
	/**
	 * 
	 * <p>
	 *   启动任务id,获取任务所在流程实例的所有意见
	 * </p>
	 * @param taskid 任务id
	 * @return 返回List
	 */
	public List getHistSuggestionByTaskId(String taskid);
	
	
	/**
	 * 
	 * <p>
	 *   分页查找用户的某个业务的待办任务
	 * </p>
	 * @param userid 用户id
	 * @param key 业务的key(流程定义的key)
	 * @param form 查询字段，分页字段 eg .{@link com.accentrue.gd_net.jbpm.vo.WorkVo}
	 * @return 返回 TwoTuple<Long,List>  a为总数，b为分页查询的TaskVo List
	 */
	public TwoTuple<Long,List> getServiceByProcessDefinitionKey(String userid,String key,PageForm form) throws Exception;
	
	
	/**
	 * 
	 * <p>
	 *   分页查找用户的某个业务已经处理过的任务
	 * </p>
	 * @param userid 用户id
	 * @param key 业务的key(流程定义的key)
	 * @param form 查询字段，分页字段 eg .{@link com.accentrue.gd_net.jbpm.vo.WorkVo}
	 * @return 返回 TwoTuple<Long,List>  a为总数，b为分页查询的TaskVo List
	 */
	public TwoTuple<Long,List> getServiceHandlerByProcessDefinitionKey(String userid,String key,PageForm form) throws Exception;
}
