package com.gdcable.epm.jbpm.dao.opinion;

import java.util.List;

import org.hibernate.Session;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.springframework.stereotype.Repository;

import com.gdcable.epm.dao.base.BaseDaoImpl;
import com.gdcable.epm.jbpm.helper.opinion.JbOptionHelper;

@Repository
public class JbOptionDao extends BaseDaoImpl implements IJbOption{
	
	public List getOpinionByInstanceId(String instanceid){
		StringBuffer hql = new StringBuffer();
		hql.append("from ").append(HistoryTaskImpl.class.getName());
		hql.append(" a,JbOption b");
		hql.append(" where a.executionId=b.instance ");
		hql.append(" and b.instance = :instanceid ");
		hql.append(" and a.state='completed'");
		hql.append(" and a.dbid=b.taskid ");
		hql.append(" order by b.checktime desc");
		Session session = getSession();
		List li = session.createQuery(hql.toString()).setParameter("instanceid", instanceid).list();
		List result = JbOptionHelper.coverPoToVo(li);
		return result;
	}

}
