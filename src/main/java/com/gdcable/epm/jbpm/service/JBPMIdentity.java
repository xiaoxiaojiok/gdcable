package com.gdcable.epm.jbpm.service;

import java.util.List;

import org.jbpm.api.identity.User;

public interface JBPMIdentity {
	/**
	 * 
	 * <p>
	 *  这个方法作废
	 * </p>
	 * @param userid
	 * @param pwd
	 */
	@Deprecated
	public User validate(String userid,String pwd);
	
	/**
	 * 
	 * <p>
	 *  针对用户id和角色来找到所有用户
	 * </p>
	 * @param user List<String> 存放用户id
	 * @param group	List<String> 存放角色id
	 * @return 返回UserImpl类
	 */
	public List getUserByIdAndGroup(List user, List group);

}
