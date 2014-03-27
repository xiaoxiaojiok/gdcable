package com.gdcable.epm.jbpm.service.imp;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <pre>
 *  对于公用service，初始化一些api接口
 * </pre>
 * @author dxq
 * @version 1.0, 2013年9月23日
 */
@Component
@Transactional(readOnly=true)
public class JBPMCommon implements ApplicationContextAware{
	
	protected ApplicationContext applicationContext ;
	
	protected RepositoryService repositoryService;
	protected ExecutionService executionService;
	protected IdentityService identityService;
	protected ManagementService managementService;
	protected TaskService taskService;
	protected HistoryService historyService;
	

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		this.repositoryService  = applicationContext.getBean(RepositoryService.class);
		this.executionService  = applicationContext.getBean(ExecutionService.class);
		this.identityService  = applicationContext.getBean(IdentityService.class);
		this.managementService  = applicationContext.getBean(ManagementService.class);
		this.taskService  = applicationContext.getBean(TaskService.class);
		this.historyService  = applicationContext.getBean(HistoryService.class);
	}



	
	
	

}
