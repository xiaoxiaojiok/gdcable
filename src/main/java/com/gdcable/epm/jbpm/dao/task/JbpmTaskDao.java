package com.gdcable.epm.jbpm.dao.task;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.api.identity.Group;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
import org.jbpm.pvm.internal.task.ParticipationImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdcable.epm.dao.base.BaseDaoImpl;
import com.gdcable.epm.jbpm.dao.identity.IJbpmIdUser;
import com.gdcable.epm.jbpm.vo.PageForm;
import com.gdcable.epm.jbpm.vo.common.TwoTuple;

@Repository
public class JbpmTaskDao extends BaseDaoImpl implements IJbpmTask{
	private Logger  log = Logger.getLogger(JbpmTaskDao.class);
	
	@Autowired
	private  IJbpmIdUser iJbpmIdUser;
	
	public List listAllPerson(Map map){
		String hql = inistallHql(map);
		Query query = getSession().createQuery(hql);
		if (map.containsKey("assignee")) {
	      query.setString("assignee", map.get("assignee")+"");
	    }
	    
	    if (map.containsKey("candidate")) {
	      query.setString("candidateUserId", map.get("candidate")+"");
	       if (map.containsKey("groupIds")) {
	        query.setParameterList("candidateGroupIds", (List)map.get("groupIds"));
	      }
	    }
	    List result = query.list();
	    if((!map.containsKey("candidate")) || map.containsKey("count")){
	    	return query.list();
	    }
	      List<?> list = (List<?>) result;
	      if (list.isEmpty()) return Collections.EMPTY_LIST;

	      StringBuilder hql2 = new StringBuilder();
	      hql2.append("from ").append(TaskImpl.class.getName()).append(" as task ");
	      boolean isWhereAdded = false;
	      hql2.append(" where task.id in (:identifiers) ");
	      hql2.append(" order by createTime desc");
	      
	      return getSession().createQuery(hql2.toString())
	        .setParameterList("identifiers", list)
	        .list();
		
	}
	private String inistallHql(Map map){
		StringBuilder hql = new StringBuilder();
	  	hql.append("select ");
	  	boolean isWhereAdd = false;
	    List<String> groupIds; 
	    // participations
	    if (map.containsKey("candidate")) {
	      if (map.containsKey("count")) {
	        hql.append("count(distinct task.id) ");
	      } else {
	        hql.append("distinct task.id ");
	      }
	      
	      hql.append("from ");
	      hql.append(ParticipationImpl.class.getName());
	      hql.append(" as participant join participant.task as task ");

	      isWhereAdd=appendWhereClause("participant.type = 'candidate' ", hql,isWhereAdd);


	      List<Group> groups = iJbpmIdUser.findGroupsByUser((String)map.get("candidate"));
	      if (groups.isEmpty()) {
	        groupIds = null;
	        isWhereAdd=appendWhereClause("participant.userId = :candidateUserId ", hql,isWhereAdd);
	      }
	      else {
	        groupIds = new ArrayList<String>();
	        for (Group group: groups) {
	          groupIds.add(group.getId());
	        }  
	        map.put("groupIds", groupIds);
	        isWhereAdd=appendWhereClause("(participant.userId = :candidateUserId or participant.groupId in (:candidateGroupIds))", hql,isWhereAdd);
	      }
	    }
	    else {
	      if (map.containsKey("count")) {
	        hql.append("count(task) ");
	      } else {
	        hql.append("task ");
	      }
	      
	      hql.append("from ");
	      hql.append(TaskImpl.class.getName());
	      hql.append(" as task ");
	    }


	    if (map.containsKey("processDefinitionId")) {
	    	isWhereAdd=appendWhereClause("task.processInstance.processDefinitionId = '"+map.get("processDefinitionId")+"' ", hql,isWhereAdd);
	    }
	    if (map.containsKey("like_processDefinitionId")) {
	    	isWhereAdd=appendWhereClause("task.processInstance.processDefinitionId like '"+map.get("like_processDefinitionId").toString()+"-%' ", hql,isWhereAdd);
		    }

	    if (map.containsKey("assignee")) {
	    	isWhereAdd=appendWhereClause("task.assignee = :assignee ", hql,isWhereAdd);
	    }
	    else if(map.containsKey("candidate")){
	    	isWhereAdd=appendWhereClause("task.assignee is null ", hql,isWhereAdd);
	    }
//	    if(map.containsKey("order"))
	    if(map.containsKey("assignee") && !map.containsKey("candidate") && !map.containsKey("count")){
	    	hql.append("  order by task.createTime desc");
	    }
	    

	    String hqlQuery = hql.toString();
	    return hqlQuery;
	}
//	用户任务分页
	public TwoTuple<Long, List> getUserTaskByDefinitionPage(String key,String user,int page,int everypage){
		List<Group> groups = iJbpmIdUser.findGroupsByUser(user);
		List groupIds ;
		 if (groups.isEmpty()) {
		        groupIds = null;
		      
		 }
		else {
		        groupIds = new ArrayList<String>();
		        for (Group group: groups) {
		            groupIds.add(group.getId());
		        }  
		      
		}
		 String candidate = "";
		 if(groupIds==null || groupIds.size()==0){
			 candidate = "  p.userId = :assignee ";
		 }
		 else candidate = " (p.userId = :assignee or p.groupId in (:groupIds)) ";
		String hql = "select t from "+TaskImpl.class.getName()+" t where "+
			"(t.assignee = :assignee and t.executionId like '"+key+"%') or "+
			"( exists (select 1 from "+ParticipationImpl.class.getName()+" p "+
             " where p.task = t and p.type='candidate' and t.assignee is null "+
                   " and "+candidate+")) ";
		System.out.println("\n\n\nhql------------------"+hql);
		log.debug(hql);
		Session session = getSession();
		String counthql = hql.replace("select t", "select count(t)");
		log.debug(counthql);
		Query query1 = session.createQuery(counthql);
		query1.setParameter("assignee", user);
		 if(groupIds!=null && groupIds.size()>0){
			 query1.setParameterList("groupIds", groupIds);
		 }
		 Object count = query1.uniqueResult();
		 
		hql = hql+" order by t.createTime desc";
		Query query = session.createQuery(hql);
		query.setParameter("assignee", user);
		 if(groupIds!=null && groupIds.size()>0){
			 query.setParameterList("groupIds", groupIds);
		 }
		 int begin = (page-1)*everypage;
		 query.setFirstResult(begin);
		 query.setMaxResults(everypage);
		 List result =  query.list();
		 
		 return new TwoTuple<Long, List>((Long)count, result);
	}
	
	public TwoTuple<Long, List> getUserTaskByDefinitionPage(String key,PageForm form,String user) throws Exception{
		List<Group> groups = iJbpmIdUser.findGroupsByUser(user);
		List groupIds ;
		 if (groups.isEmpty()) {
		        groupIds = null;
		      
		 }
		else {
		        groupIds = new ArrayList<String>();
		        for (Group group: groups) {
		            groupIds.add(group.getId());
		        }  
		      
		}
		 String candidate = "";
		 if(groupIds==null || groupIds.size()==0){
			 candidate = "  p.userId = :assignee ";
		 }
		 else candidate = " (p.userId = :assignee or p.groupId in (:groupIds)) ";
		String hql = "select t,i,w from "+TaskImpl.class.getName()+" t,JbInstance i,"+form.getTarget().getName()+" w where "+
		"t.executionId = i.instance and i.service = w.id and t.executionId like '"+key+"%' and " +	
		"((t.assignee = :assignee ) or "+
			"( exists (select 1 from "+ParticipationImpl.class.getName()+" p "+
             " where p.task = t and p.type='candidate' and t.assignee is null "+
                   " and "+candidate+")))";
		Class clazz = form.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Map param = new HashMap();
		for(int i=0;i<fields.length;i++){
			Field f = fields[i];
			if(f.getName().equals("page")){
				continue;
			}
			else if(f.getName().equals("rows")){
				continue;
			}
			else if(f.getName().equals("target")){
				continue;
			}
			String method ="get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
			Method m = clazz.getDeclaredMethod(method, new Class[]{});
			Object o = m.invoke(form, new Object[]{});
			if(o!=null && !o.toString().equals("")){
				hql = hql + "and w."+f.getName()+" = :"+f.getName();
				param.put(f.getName(), o);
			}
		}
		log.debug(hql);
		Session session =getSession();
		String counthql = hql.replace("select t,i,w", "select count(t)");
		log.debug(counthql);
		 Query query1 = session.createQuery(counthql);
		 query1.setParameter("assignee", user);
		 if(groupIds!=null && groupIds.size()>0){
			 query1.setParameterList("groupIds", groupIds);
		 }
		 Iterator it= param.keySet().iterator();
		 while(it.hasNext()){
			 String para = (String) it.next();
			 query1.setParameter(para, param.get(para));
		 }
		 Object count = query1.uniqueResult();
		 
		hql = hql+" order by t.createTime desc";
		Query query = session.createQuery(hql);
		query.setParameter("assignee", user);
		 if(groupIds!=null && groupIds.size()>0){
			 query.setParameterList("groupIds", groupIds);
		 }
		 Iterator it1= param.keySet().iterator();
		 while(it1.hasNext()){
			 String para = (String) it1.next();
			 query.setParameter(para, param.get(para));
		 }
		 
		 int begin = (form.getPage()-1)*(form.getRows());
		 query.setFirstResult(begin);
		 query.setMaxResults(form.getRows());
		 List result =  query.list();
		 
		 return new TwoTuple<Long, List>((Long)count, result);
	}
	
	public List getLastTaskByTaskName(String taskname,String executionId){
		StringBuffer hql = new StringBuffer("from "+HistoryTaskInstanceImpl.class.getName());
		hql.append(" t where ");
		hql.append(" t.activityName = :taskname");
		hql.append(" and t.executionId = :executionId");
		hql.append(" order by t.endTime desc");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("taskname", taskname);
		query.setParameter("executionId", executionId);
		return query.list();
	}
	
	private boolean appendWhereClause(String add,StringBuilder hql,boolean isWhereAdd){
		if(isWhereAdd){
			hql.append("  and ");
		}
		else {
			hql.append("  where ");
		}
		hql.append(add);
		return true;
	}
	
	
	
	public IJbpmIdUser getiJbpmIdUser() {
		return iJbpmIdUser;
	}
	public void setiJbpmIdUser(IJbpmIdUser iJbpmIdUser) {
		this.iJbpmIdUser = iJbpmIdUser;
	}
	
	

}
