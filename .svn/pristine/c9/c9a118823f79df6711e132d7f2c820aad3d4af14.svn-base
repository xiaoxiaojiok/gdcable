package com.gdcable.epm.jbpm.dao.identity;

import java.util.List;

import org.jbpm.api.identity.Group;
import org.jbpm.pvm.internal.identity.impl.UserImpl;

import com.gdcable.epm.dao.base.BaseDao;

public interface IJbpmIdUser  extends BaseDao {
	
	public List<Group> findGroupsByUser(String userId);
	
	public List<UserImpl> getUserByIdAndGroup(List userid,List groupid);

}
