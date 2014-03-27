package com.gdcable.epm.servicre.workflow;

import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.pvm.internal.identity.impl.UserImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gdcable.epm.dao.workflow.WorkDao;
import com.gdcable.epm.dao.workflow.WorkDaoImpl;
import com.gdcable.epm.entity.Work;
import com.gdcable.epm.jbpm.dao.opinion.IJbOption;
import com.gdcable.epm.jbpm.service.JBPMProcess;
import com.gdcable.epm.jbpm.service.JBPMTask;
import com.gdcable.epm.servicre.base.BaseService;

@Component
@Transactional(readOnly=true)
public class WorkServiceImpl extends BaseService {
	@Autowired
	private WorkDao workDao;
	@Autowired
	private JBPMTask jBPMTask;
	@Autowired
	private IJbOption iJbOption;
	@Autowired
	private JBPMProcess jBPMProcess;
	
	

	/**
	 * 
	 * <p>
	 *  这个方法作废
	 * </p>
	 * @param o
	 * @param user
	 * @param variable
	 * @see #saveAndCompleteWork(Work, String, Map)
	 */
	
	@Transactional(readOnly=false)
	@Deprecated
	public void saveAndCompleteWork(Work o,UserImpl user,Map variable){
		if(o.getId()!=null){
			 o = ((WorkDaoImpl)workDao).findById(o.getId(), Work.class);
		}
		else ((WorkDaoImpl)workDao).save(o);
		Long id = o.getId();
		ProcessInstance processInstance = jBPMProcess.saveProcessInstanceByKey("ConstructionDelegate",id,o.getClass(),user.getId());
		List task = jBPMTask.getTaskByIstance(processInstance.getId());
		TaskImpl defaulttask = (TaskImpl) task.get(0);
		jBPMTask.completeTask(defaulttask, user.getId(), null, variable);
	}
	
	
	/**
	 * 
	 * <p>
	 * 	  这个一般用于作用提交表单的时候，当提交一个表单的时,就需要启动一个流程并且自动完成第一个节点
	 * </p>
	 * @param o  业务po，根据id（这个id最好是封装类型）是否存在来判断这个po是否已经在库中有对应
	 * @param userId 用户的登录名，Login_name
	 * @param variable 往流程中添加的参数
	 */
	@Transactional(readOnly=false)
	public void saveAndCompleteWork(Work o,String userId,Map variable){
		if(o.getId()!=null){
			 o = ((WorkDaoImpl)workDao).findById(o.getId(), Work.class);
		}
		else ((WorkDaoImpl)workDao).save(o);
		Long id = o.getId();
		ProcessInstance processInstance = jBPMProcess.saveProcessInstanceByKey("ConstructionDelegate",id,o.getClass(),userId);
		List task = jBPMTask.getTaskByIstance(processInstance.getId());
		TaskImpl defaulttask = (TaskImpl) task.get(0);
		jBPMTask.completeTask(defaulttask, userId, null, variable);
	}
	
	
	/**
	 * 
	 * <p>
	 * 	根据主键找业务实体
	 * </p>
	 * @param id 主键
	 * @return 业务实体
	 */
	public Work findWorkById(Long id){
		return ((WorkDaoImpl)workDao).findById(id, Work.class);
	}
	

}
