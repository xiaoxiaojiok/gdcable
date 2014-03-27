package com.gdcable.epm.jbpm.helper;


/**
 * 
 * <pre>
 * 主要存放一些静态数据，与流程相关的
 * </pre>
 * @author dxq
 * @version 1.0, 2013年9月23日
 */
public class JBPMProcessData
{
	//当流向是下面几个的时候，返回节点的处理人是上次的处理人
	public static String PROCESS_BACK="";
	
	public static final String TASK_NAME = "task";
	
	static{
		PROCESS_BACK = "撤回,退回,返回,打回,不通过,审核不通过";
	}
}
