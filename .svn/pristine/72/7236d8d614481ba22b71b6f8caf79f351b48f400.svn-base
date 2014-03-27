package com.gdcable.epm.controller.jbpm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ResourceBundleELResolver;
import javax.el.ValueExpression;

import org.apache.log4j.Logger;
import org.jbpm.jpdl.internal.activity.TaskActivity;
import org.jbpm.pvm.internal.el.Expression;
import org.jbpm.pvm.internal.el.JbpmElContext;
import org.jbpm.pvm.internal.el.JbpmElFactory;
import org.jbpm.pvm.internal.el.JbpmFunctionMapper;
import org.jbpm.pvm.internal.el.JstlFunction;
import org.jbpm.pvm.internal.el.StaticTextExpression;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.Condition;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.task.TaskDefinitionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gdcable.epm.jbpm.helper.JVariableElResolver;
import com.gdcable.epm.jbpm.org.jbpm.pvm.internal.model.CustomerExpressionCondition;
import com.gdcable.epm.jbpm.service.JBPMProcess;

@Component
public class ProcessInfoHandler implements ApplicationContextAware {
	final static String TASK = "task";
	final static String DECISION = "decision";
	final static String FORK = "fork";
	final static String END = "end";
	
	private ApplicationContext applicationContext;
	private Logger  log = Logger.getLogger(ProcessInfoHandler.class);
	public  void obtainNextPerson(ProcessDefinitionImpl pdi,ActivityImpl ai,TaskImpl task,String transitionName,Map result){
		if(result==null){
			result = new HashMap();
		}
		if(ai==null){
			ai = pdi.getActivity(task.getName());
		}
		TransitionImpl tr ;
		if(StringUtils.hasText(transitionName)){
			tr= ai.getOutgoingTransition(transitionName);
		}
		else tr = ai.getDefaultOutgoingTransition();
		ActivityImpl destination =tr.getDestination();
//		List userid = new ArrayList();
//		List roleid  = new ArrayList();
		obtainNext(destination,result,task,pdi);
	}
	
	private void obtainNext(ActivityImpl destination,Map result,TaskImpl task,ProcessDefinitionImpl pdi){
		log.info("获得下一节点: "+destination.getType()+"   "+destination.getName());
		if(destination.getType().equals(TASK)){
			if(!result.containsKey("type")){
				result.put("type", TASK);
			}
			
			obtainTaskPerson(destination,result,task,pdi);
		}
		else if(destination.getType().equals(DECISION)){
			if(!result.containsKey("type")){
				result.put("type", DECISION);
			}
			obtainDecisionPerson(destination,result,task,pdi);
		}
		else if(destination.getType().equals(FORK)){
			if(!result.containsKey("type")){
				result.put("type", FORK);
			}
			obtainForkPerson(destination,result,task,pdi);
		}
		else if(destination.getType().equals(END)){
			if(!result.containsKey("type")){
				result.put("type", END);
			}
		}
		else{
			//其他情况在做考虑
		}
	}

	private void obtainForkPerson(ActivityImpl destination,Map result1,TaskImpl task,ProcessDefinitionImpl pdi){

		List<TransitionImpl> outgoingTransitions = (List) destination.getOutgoingTransitions();

		for (TransitionImpl transition : outgoingTransitions) {
			
			System.out.println("fork流向"+transition.getName());
			ActivityImpl next = transition.getDestination();
			obtainNext(next,result1,task,pdi);
		}
		
		
	}
				
	private void obtainDecisionPerson(ActivityImpl destination,Map result1,TaskImpl task,ProcessDefinitionImpl pdi){
		List<TransitionImpl> outgoingTransitions = (List) destination.getOutgoingTransitions();
		String executionid = task.getExecutionId();
		JBPMProcess process = applicationContext.getBean(JBPMProcess.class);
		List servicevo = process.getServiceByProcessInstanceId(executionid);
		Object service = servicevo.get(0);//假设只能找到一条
		Class clazz = service.getClass();
		Field[] fields = clazz.getDeclaredFields();
		HashMap map = new HashMap();
		for(Field f : fields){
			if(f.getType().isAssignableFrom(Collection.class)){
				continue;
			}
			else if(f.getType().isAssignableFrom(Map.class)){
				continue;
			}
			else if(f.getType().isAssignableFrom(Set.class)){
				continue;
			}
			else if(f.getType().isArray()){
				continue;
			}
			else{
				try{
					String methodname ="get"+f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1);
					Method me = clazz.getDeclaredMethod(methodname, new Class[]{});
					Object result = me.invoke(service, new Object[]{});
					if(result instanceof String){
						map.put(f.getName(), result);
					}
					else if(result instanceof Number){
						map.put(f.getName(), result);
					}
					else if(result instanceof Boolean){
						map.put(f.getName(), result);
					}
					else if(result instanceof Character){
						map.put(f.getName(), result);
					}
					
					
				}catch(Exception e){
					
				}
			}
		}
		
		for (TransitionImpl transition : outgoingTransitions) {		
		      Condition condition = transition.getCondition();
		      if(condition instanceof CustomerExpressionCondition){
		    	  try{
		    		  String el = ((CustomerExpressionCondition)condition).getExpression();
		    		  log.debug("判断表达式 "  +el);
		    		  JbpmElFactory jbpmElFactory = JbpmElFactory.getJbpmElFactory();
		    		  ExpressionFactory expressionFactory = jbpmElFactory.createExpressionFactory();
		    		  CompositeELResolver compositeELResolver = new JVariableElResolver(map);
		    		  compositeELResolver.add(new ResourceBundleELResolver());
		    		  compositeELResolver.add(new MapELResolver());
		    		  compositeELResolver.add(new ListELResolver());
		    		  compositeELResolver.add(new ArrayELResolver());
		    		  compositeELResolver.add(new BeanELResolver());
		    		  FunctionMapper functionMapper = new JbpmFunctionMapper(JstlFunction.class);
		    		  ELContext elContext = new JbpmElContext(compositeELResolver, functionMapper);
		    		  ValueExpression valueExpression = expressionFactory.createValueExpression(elContext, el, Object.class);
		    		  Object o = valueExpression.getValue(elContext);
		    		  boolean result = ((Boolean) o).booleanValue();
			    	  if  ( (condition==null) || result) {
			    		  	  log.info(" Decision: "+transition.getName()+"  符合要求 ");
					    	  ActivityImpl next = transition.getDestination();
					    	  obtainNext(next,result1,task,pdi);
					   }
			      }catch(Exception e){
			    	  e.printStackTrace();
			    	  log.info(e.getMessage());
//			    	  ActivityImpl target = transition.getDestination();
//			    	  obtainNext(types, target,userid,roleid,task);
			      }
		      }
		      else{
					ActivityImpl target = transition.getDestination();
					obtainNext(target,result1,task,pdi);
		      } 

		 }
		
	}

	private void obtainTaskPerson(ActivityImpl destination,Map result,TaskImpl task,ProcessDefinitionImpl pdi){
		TaskActivity taskActivity = (TaskActivity) destination.getActivityBehaviour();
		TaskDefinitionImpl taskDefinition = taskActivity.getTaskDefinition();
		Expression candidateUsersExpression = taskDefinition.getCandidateUsersExpression();
//默认juel
		List userid = new ArrayList();
		List roleid = new ArrayList();
		if(taskDefinition.getCandidateUsersExpression()!=null){
			if(candidateUsersExpression instanceof StaticTextExpression){
				String  candidateUsers =( (StaticTextExpression)candidateUsersExpression).getExpressionString();
				String[] str = candidateUsers.split(",");
				for(String s : str){
					userid.add(s);
				}
			}
		}
		Expression candidateGroupsExpression = taskDefinition.getCandidateGroupsExpression();
		if(taskDefinition.getCandidateGroupsExpression()!=null){
			if(candidateGroupsExpression instanceof StaticTextExpression){
				String  candidateGroups =( (StaticTextExpression)candidateGroupsExpression).getExpressionString();
				String[] str = candidateGroups.split(",");
				for(String s : str){
					roleid.add(s);
				}
			}
		}
		HashMap userinfo = new HashMap();
		userinfo.put("userid", userid);
		userinfo.put("roleid", roleid);
		Map activity = new HashMap();
		activity.put("name", destination.getName());
		activity.put("candidate", userinfo);
		if(!result.containsKey("data")){
			List li = new ArrayList();
			result.put("data", li);
		}
		List li = (List)result.get("data");
		li.add(activity);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	

}
