package com.gdcable.epm.jbpm.service.imp;

import java.util.List;

import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gdcable.epm.jbpm.dao.identity.IJbpmIdUser;
import com.gdcable.epm.jbpm.service.JBPMIdentity;


/**
 * 
 * <pre>
 * 		jbpm身份service，与业务的身份service有区别
 * </pre>
 * @author dxq
 * @version 1.0, 2013年9月23日
 */
@Component
@Transactional(readOnly=true)
public class JBPMIdentityService extends JBPMCommon implements JBPMIdentity{
	
	@Autowired
	private IJbpmIdUser iJbpmIdUser;
	
	/**
	 * 
	 * <p>
	 *  这个方法作废
	 * </p>
	 * @param userid
	 * @param pwd
	 */
	
	@Transactional(readOnly=false)
	@Deprecated
	public User validate(String userid, String pwd) {
		// TODO Auto-generated method stub
		if(pwd==null || pwd.equals("") || userid==null ||userid.equals("")){
			return null;
		}
		UserImpl user =(UserImpl) identityService.findUserById(userid);
		if(user == null) {
			return null;
		}
		if(user.getPassword()==null || !user.getPassword().equals(pwd)){
			return null;
		}
		return user;
	}
	
	
	/**
	 * 
	 * <p>
	 *  针对用户id和角色来找到所有用户
	 * </p>
	 * @param user List<String> 存放用户id
	 * @param group	List<String> 存放角色id
	 * @return 返回UserImpl类
	 */
	public List getUserByIdAndGroup(List user, List group){
		return iJbpmIdUser.getUserByIdAndGroup(user, group);
	}

	public void setiJbpmIdUser(IJbpmIdUser iJbpmIdUser) {
		this.iJbpmIdUser = iJbpmIdUser;
	}
	
}
