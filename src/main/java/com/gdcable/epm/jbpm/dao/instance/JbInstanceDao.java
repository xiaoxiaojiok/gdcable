package com.gdcable.epm.jbpm.dao.instance;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gdcable.epm.dao.base.BaseDaoImpl;

@Repository
public class JbInstanceDao extends BaseDaoImpl implements IJbInstance{
	Logger log = Logger.getLogger(JbInstanceDao.class);
	
	public  List getServiceList(String classname,List ids){
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("from ").append(classname);
		hql.append(" where id  in (:ids)");
		log.debug(hql.toString());
		Query query = session.createQuery(hql.toString());
		query.setParameterList("ids", ids);
		return query.list();
	}
	
	public List getServiceByInstance(List instanceids,String classname){
		Session session = getSession();
		StringBuffer hql = new StringBuffer();
		hql.append("from ").append(" JbInstance j , ").append(classname).append(" s");
		hql.append(" where j.service=s.id and j.instance in (:instanceids)");
		String hqls = hql.toString();
		Query query = session.createQuery(hqls);
		query.setParameterList("instanceids", instanceids);
		return query.list();
	}

}
