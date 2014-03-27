package com.gdcable.epm.jbpm.dao.task;

import java.util.List;
import java.util.Map;

import com.gdcable.epm.dao.base.BaseDao;
import com.gdcable.epm.jbpm.vo.PageForm;
import com.gdcable.epm.jbpm.vo.common.TwoTuple;

public interface IJbpmTask extends BaseDao{
	
	public List listAllPerson(Map map);
	
	public TwoTuple<Long, List> getUserTaskByDefinitionPage(String key,String user,int page,int everypage);
	
	public TwoTuple<Long, List> getUserTaskByDefinitionPage(String key,PageForm form,String user) throws Exception;
	
	public List getLastTaskByTaskName(String taskname,String executionId);
}
