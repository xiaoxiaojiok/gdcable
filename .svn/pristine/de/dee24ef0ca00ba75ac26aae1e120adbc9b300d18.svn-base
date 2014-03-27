
package com.gdcable.epm.jbpm.dao.identity;

import java.util.List;

import org.hibernate.Query;
import org.jbpm.api.identity.Group;
import org.jbpm.pvm.internal.identity.impl.UserImpl;
import org.jbpm.pvm.internal.util.CollectionUtil;
import org.springframework.stereotype.Repository;

import com.gdcable.epm.dao.base.BaseDaoImpl;

@Repository
public class JbpmIdUserDao extends BaseDaoImpl implements IJbpmIdUser{
	public List<Group> findGroupsByUser(String userId) {
		List<?> groups = getSession().getNamedQuery("findGroupsByUser")
	      .setParameter("userId", userId)
	      .list();
	    return CollectionUtil.checkList(groups, Group.class);
	  }

	public List<UserImpl> getUserByIdAndGroup(final List userid,final List groupid){
		
		StringBuffer hql =new StringBuffer("select a from UserImpl a,MembershipImpl b,GroupImpl c where a=b.user and b.group = c  ");
		hql.append(" and (");
		boolean isor= false;
		if(userid!=null && userid.size()!=0){
			hql.append(" a.id in (:userid) ");
			isor = true;
		}
		if(groupid!=null && groupid.size()!=0){
			if(isor){
				hql.append(" or ");
			}
			hql.append(" c.id in (:groupid)");

		}
		hql.append(" )");
		final String hqls = hql.toString();
		Query query = getSession().createQuery(hqls);
		if(userid!=null && userid.size()!=0){
			query.setParameterList("userid", userid);
		}
		if(groupid!=null && groupid.size()!=0){
			query.setParameterList("groupid", groupid);
		}
		return query.list();
	}

}
