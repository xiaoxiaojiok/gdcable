package com.gdcable.epm.dao.mcode;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.QueryCallback;
import com.gdcable.epm.dao.base.BaseDaoImpl;
import com.gdcable.epm.entity.Mcode;

/**
 * 
 * <pre>
 * 码表dao处理接口实现类
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-12
 */
@Repository
public class McodeDaoImpl extends BaseDaoImpl implements McodeDao
{

	@Override
	public Collection<Mcode> findByMtype(final String mtype)
	{
		return HibernateUtils.list(getSession(), Mcode.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				if(mtype!=null && !mtype.equals("")){
					c.add(Restrictions.eq("mtype", mtype));
				}
				c.add(Restrictions.ne("datelevel", 0));
			}
		});
	}

}
