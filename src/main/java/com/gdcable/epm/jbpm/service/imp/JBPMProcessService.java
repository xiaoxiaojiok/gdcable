package com.gdcable.epm.jbpm.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.api.history.HistoryActivityInstanceQuery;
import org.jbpm.jpdl.internal.activity.TaskActivity;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.task.TaskDefinitionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gdcable.epm.jbpm.dao.definition.IJbDefinition;
import com.gdcable.epm.jbpm.dao.instance.IJbInstance;
import com.gdcable.epm.jbpm.dao.opinion.IJbOption;
import com.gdcable.epm.jbpm.dao.task.IJbpmTask;
import com.gdcable.epm.jbpm.po.definition.JbDefinition;
import com.gdcable.epm.jbpm.po.instance.JbInstance;
import com.gdcable.epm.jbpm.service.JBPMProcess;
import com.gdcable.epm.jbpm.vo.PageForm;
import com.gdcable.epm.jbpm.vo.Transation;
import com.gdcable.epm.jbpm.vo.common.TwoTuple;
import com.gdcable.epm.jbpm.vo.task.TaskVo;

/**
 * 
 * <pre>
 * 	关于流程启动和运行阶段api
 * </pre>
 * @author dxq
 * @version 1.0, 2013年9月23日
 */
@Component
@Transactional(readOnly=true)
public class JBPMProcessService extends JBPMCommon implements JBPMProcess{
	
	private Logger log = Logger.getLogger(JBPMProcessService.class);
	@Autowired
	private IJbpmTask ijbpmtask;
	@Autowired
	private IJbDefinition iJbDefinition;
	@Autowired
	private IJbInstance iJbInstance;
//	上传流程
	@Autowired
	private IJbOption iJbOption;
	
	
	/**
	 * 
	 * <p>
	 *  发布流程,作废
	 * </p>
	 * @param url classpath路径
	 * @return 返回流程定义id
	 */
	@Transactional(readOnly=false)
	@Deprecated
	public String saveProcessDefinition(String url){
		return repositoryService.createDeployment().addResourceFromClasspath(url).deploy();
	}
	
	/**
	 * 
	 * <p>
	 *  发布流程,作废
	 * </p>
	 * @param File 路径
	 * @return 返回流程定义id
	 */
	@Transactional(readOnly=false)
	@Deprecated
	public String saveProcessDefinition(File url){
		return repositoryService.createDeployment().addResourceFromFile(url).deploy();
	}


	/**
	 * 
	 * <p>
	 *   根据任务，返回流程定义
	 * </p>
	 * @param taskId 任务id
	 * @return 返回流程定义ProcessDefinitionImpl
	 */
	public ProcessDefinitionImpl getProcessDefinitionByTask(String taskId){
		TaskImpl task = (TaskImpl) taskService.getTask(taskId);
		ExecutionImpl ei  = task.getExecution();

		ProcessDefinitionImpl pd =(ProcessDefinitionImpl) repositoryService.createProcessDefinitionQuery().processDefinitionId(ei.getProcessDefinitionId()).uniqueResult();
		return pd;
	}
	
	/**
	 * 
	 * <p>
	 *   根据key，返回最新流程定义
	 * </p>
	 * @param key 流程Key
	 * @return 返回流程定义ProcessDefinitionImpl
	 */
	public ProcessDefinitionImpl getProcessDefinitionByKey(String key){
		List li = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION).list();
		return (ProcessDefinitionImpl) li.get(0);
	}
	
	/**
	 * 
	 * <p>
	 *   根据任务id，返回这个流程的历史环节（非任务）
	 * </p>
	 * @param taskId 任务id
	 * @return 返回 List
	 */
	public List  getHistoryExecutionImpByTask(String taskId){
		TaskImpl task = (TaskImpl) taskService.getTask(taskId);
		ExecutionImpl ei  = task.getExecution();
		return getHistoryExecutionImpByProcessInstance(ei.getProcessInstance().getId());
	}
	
	/**
	 * 
	 * <p>
	 *   根据流程实例id，返回这个流程的历史环节（非任务）
	 * </p>
	 * @param processinstanceid 任务流程实例id
	 * @return 返回 List
	 */
	public List  getHistoryExecutionImpByProcessInstance(String processinstanceid){
		List li = historyService.createHistoryActivityInstanceQuery().processInstanceId(processinstanceid).orderDesc(HistoryActivityInstanceQuery.PROPERTY_STARTTIME).list();
		return li;
	}
//	启动流程实例（与业务挂钩）id业务主键		po业务类
	
	/**
	 * 
	 * <p>
	 *   提交一个表单的时候，启动一个流程
	 * </p>
	 * @param key 流程的key
	 * @param id 业务po的id
	 * @param po 业务po的class
	 * @param userId 第一个环节办理人，设置为当前用户
	 * @return 返回 流程实例
	 */
	@Transactional(readOnly=false)
	public ProcessInstance saveProcessInstanceByKey(String key,Long id,Class po,String userId){
		//启动流程，返回流程实例
		ProcessInstance instance = null;
		
		ProcessDefinitionImpl pdi = getProcessDefinitionByKey(key);
		ActivityImpl ai = (ActivityImpl) pdi.getInitial().getOutgoingTransitions().get(0).getDestination();
		ActivityBehaviour ab = ai.getActivityBehaviour();
		System.out.println(ab instanceof TaskActivity);
		//从开始到下一个环节  只可能有一个流向 ，并且这个流向为空。 这个流向的目标是task节点
		if(ab instanceof TaskActivity){
			TaskActivity a =(TaskActivity)ab;
			TaskDefinitionImpl taskDefinition = a.getTaskDefinition();
			if(taskDefinition.getAssigneeExpression()!=null){
				HashMap map = new HashMap();
				map.put("assignname", userId);
				instance = executionService.startProcessInstanceByKey(key,map);
			}
			instance = executionService.startProcessInstanceByKey(key);
		}

		
		//根据key去 int_jb_definition 查找出来唯一，如没有则报错
		Map parameter = new HashMap();
		parameter.put("key", key);
		List definition = iJbDefinition.getAll(JbDefinition.class, parameter);
		if(definition==null || definition.size()==0){
			throw new RuntimeException("报错");
		}
		JbDefinition jd = (JbDefinition) definition.get(0);
		
		JbInstance jb = new JbInstance();
		jb.setInstance(instance.getId());
		jb.setService(id);
		jb.setDefintion(jd.getId()); //上面那个id
		jb.setPoclass(po.getName());
		iJbInstance.save(jb);
		log.info(instance.getId()+"");
		return instance;
	}
	
	
	/**
	 * 
	 * <p>
	 *   根据流程实例id找业务
	 * </p>
	 * @param executionid 流程实例id
	 * @return 返回 业务List  （没有意外情况都是一条）
	 */
	public List getServiceByProcessInstanceId(String executionid){
		Map parameter = new HashMap();
		parameter.put("instance", executionid);
		List instance = iJbInstance.getAll(JbInstance.class, parameter);
		List ids = new ArrayList();
		Iterator it = instance.iterator();
		String classname = "";
		while(it.hasNext()){
			JbInstance jb = (JbInstance)it.next();
			ids.add(jb.getService());
			classname = jb.getPoclass();
		}
		List result = iJbInstance.getServiceList(classname, ids);
		log.debug("条数"+(result==null ? 0:result.size()));
		return result;
	}

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
	public TwoTuple<Long,List> getServiceByProcessDefinitionKey(String userid,String key,PageForm form) throws Exception{
		//查询 会返回result ，a为总数，b为List集合
		//这个b 是Objectp[3] object[0] 为TaskImpl ，object[1]为JbInstance，object[2]为实体对象
		TwoTuple<Long,List> result = ijbpmtask.getUserTaskByDefinitionPage(key,form,userid);
		List task = result.b;
		if(task==null){
			return new TwoTuple(0,new ArrayList());
		}
		
		
		//下面开始转换成 前台需求的json数据
		
		Iterator it = task.iterator();
		TaskImpl t = null;
		List resulttask = new ArrayList();
		List instanceids = new ArrayList();
		HashMap taskmap = new HashMap();
		while(it.hasNext()){
			Object[] o = (Object[]) it.next();
			
			t = (TaskImpl) o[0];
			JbInstance i = (JbInstance)o[1];
			Object service = o[2];
			
			TaskVo vo = new TaskVo();
			vo.setDbid(t.getDbid()+"");
			vo.setActivityName(t.getActivityName());
			vo.setExecutionId(t.getExecutionId());
			vo.setName(t.getName());
			resulttask.add(vo);
			
			ProcessDefinitionImpl pdi = getProcessDefinitionByTask(t.getId());
			ActivityImpl ai = pdi.getActivity(t.getActivityName());
			List out = ai.getOutgoingTransitions();
			Iterator outit = out.iterator();
			List liout = new ArrayList();
			Transation transation = null;
			while(outit.hasNext()){
				TransitionImpl outt = (TransitionImpl)outit.next();
				transation = new Transation();
				transation.setName(outt.getName());
				liout.add(transation);
			}
			vo.setTransation(liout);
			vo.setService(service);
		}
		return new TwoTuple(result.a,resulttask);
		
	}
	
	
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
	public TwoTuple<Long,List> getServiceHandlerByProcessDefinitionKey(String userid,String key,PageForm form) throws Exception{
		//查询 会返回result ，a为总数，b为List集合
		//这个b 是Objectp[3] object[0] 为TaskImpl ，object[1]为JbInstance，object[2]为实体对象
		TwoTuple<Long,List> result = ijbpmtask.getUserTaskByDefinitionPage(key,form,userid);
		List task = result.b;
		if(task==null){
			return new TwoTuple(0,new ArrayList());
		}
		
		
		//下面开始转换成 前台需求的json数据
		
		Iterator it = task.iterator();
		TaskImpl t = null;
		List resulttask = new ArrayList();
		List instanceids = new ArrayList();
		HashMap taskmap = new HashMap();
		while(it.hasNext()){
			Object[] o = (Object[]) it.next();
			
			t = (TaskImpl) o[0];
			JbInstance i = (JbInstance)o[1];
			Object service = o[2];
			
			TaskVo vo = new TaskVo();
			vo.setDbid(t.getDbid()+"");
			vo.setActivityName(t.getActivityName());
			vo.setExecutionId(t.getExecutionId());
			vo.setName(t.getName());
			resulttask.add(vo);
			
			ProcessDefinitionImpl pdi = getProcessDefinitionByTask(t.getId());
			ActivityImpl ai = pdi.getActivity(t.getActivityName());
			List out = ai.getOutgoingTransitions();
			Iterator outit = out.iterator();
			List liout = new ArrayList();
			Transation transation = null;
			while(outit.hasNext()){
				TransitionImpl outt = (TransitionImpl)outit.next();
				transation = new Transation();
				transation.setName(outt.getName());
				liout.add(transation);
			}
			vo.setTransation(liout);
			vo.setService(service);
		}
		return new TwoTuple(result.a,resulttask);
		
	}
	

	/**
	 * 
	 * <p>
	 *   启动流程根据key,如无特殊情况不使用
	 * </p>
	 * @param key 流程key
	 * @return 返回ProcessInstance
	 */
	@Transactional(readOnly=false)
	@Deprecated
	public ProcessInstance saveProcessInstanceByKey(String key){
		return executionService.startProcessInstanceByKey(key);
	}

	public void setiJbDefinition(IJbDefinition iJbDefinition) {
		this.iJbDefinition = iJbDefinition;
	}


	public void setiJbInstance(IJbInstance iJbInstance) {
		this.iJbInstance = iJbInstance;
	}



	public IJbpmTask getIjbpmtask() {
		return ijbpmtask;
	}



	public void setIjbpmtask(IJbpmTask ijbpmtask) {
		this.ijbpmtask = ijbpmtask;
	}


	/**
	 * 
	 * <p>
	 *   启动流程实例id,获取所有意见
	 * </p>
	 * @param procid 流程实例id
	 * @return 返回List
	 */
	public List getHistSuggestionByProcId(String procid) {
		List result  = iJbOption.getOpinionByInstanceId(procid);
		return result;
	}



	/**
	 * 
	 * <p>
	 *   启动任务id,获取任务所在流程实例的所有意见
	 * </p>
	 * @param taskid 任务id
	 * @return 返回List
	 */
	public List getHistSuggestionByTaskId(String taskid) {
		TaskImpl task = (TaskImpl) taskService.getTask(taskid);
		return getHistSuggestionByProcId(task.getProcessInstance().getId());
	}



	public void setiJbOption(IJbOption iJbOption) {
		this.iJbOption = iJbOption;
	}

	

	
	
	
	

}
