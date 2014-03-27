package com.gdcable.epm.dao.base;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.QueryCallback;

/**
 * 
 * <pre>
 * 基础dao接口实现类
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-8
 */
@Repository
public class BaseDaoImpl implements BaseDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public boolean delete(Object obj)
	{
		try{
			getSession().delete(obj);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public  <T>T findById(long id,Class<T> cls)
	{
		try{
			Criteria cri = getSession().createCriteria(cls).add(Restrictions.eq("id", id));
			if(cri.list()!=null && cri.list().size()!=0){
				return (T) cri.list().get(0);
			}else{
				return null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T>List<T> findAll(Class<T> cls)
	{
		try{
			List<T> objList = getSession().createCriteria(cls).addOrder(Order.asc("id")).list();
			return objList;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(Object obj)
	{
		try{
			this.getSession().save(obj);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Object obj)
	{
		try{
			this.getSession().update(obj);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public <T>List<T> getAll(Class<T> cls,final Map map)
	{
		return (List) HibernateUtils.list(getSession(), cls, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				if(map!=null && map.keySet()!=null){
					Iterator it = map.keySet().iterator();
					while(it.hasNext()){
						String property = (String)it.next();
						String value =(String) map.get(property);
						if(!property.startsWith("_")){
							c.add(Restrictions.eq(property, value));
						}
						else if(property.startsWith("_order")){
							String[] str = property.split("_");
							if(str.length<=3 && str[3].equals("asc")){
								c.addOrder(Order.asc(str[2]));
							}else{
								c.addOrder(Order.desc(str[2]));
							}
						}
					}
				}
			}
		});
		
	}

}
