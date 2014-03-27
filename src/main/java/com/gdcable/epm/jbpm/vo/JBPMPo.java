package com.gdcable.epm.jbpm.vo;

import javax.persistence.Transient;

public class JBPMPo {
	
	private String taskid;
	
	private String transitionName;
	
	private String processdefinitonkey;

	@Transient
	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	@Transient
	public String getTransitionName() {
		return transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}
	@Transient
	public String getProcessdefinitonkey() {
		return processdefinitonkey;
	}

	public void setProcessdefinitonkey(String processdefinitonkey) {
		this.processdefinitonkey = processdefinitonkey;
	}
	
	

}
