package com.gdcable.epm.dao.unit;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.Paging;
import com.accentrue.gd_net.hibernate.PagingCallback;
import com.accentrue.gd_net.hibernate.QueryCallback;
import com.gdcable.epm.dao.base.BaseDaoImpl;
import com.gdcable.epm.entity.Unit;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.util.CodeUnit;

/**
 * 
 * <pre>
 * 单位管理dao
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
@Repository
public class UnitDaoImpl extends BaseDaoImpl implements UnitDao
{

	@SuppressWarnings("unchecked")
	@Override
	public Unit findByUnitCode(String unitCode)
	{
		List<Unit> ulist = getSession().createCriteria(Unit.class).add(Restrictions.eq("unitCode", unitCode)).list();
		if(ulist==null || ulist.size()==0)return null;
		else{
			return ulist.get(0);
		}
	}

	@Override
	public List<?> getDwList(String gldwdm)
	{
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> getTopUnitList()
	{
		return getSession().createCriteria(Unit.class).add(Restrictions.isNull("unit.id")).list();
	}

	@Override
	public Collection<Unit> getSubUnitListByPUnit(final Long pid)
	{
		return HibernateUtils.list(getSession(), Unit.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria cri) throws HibernateException
			{
				if(pid != -1 && pid != 0){
					cri.add(Restrictions.eq("unit.id", pid));
				}
			}
		},"unit");
	}

	@Override
	public void findAll(final String unitName,final String unitCode,final Long pUnit,final User user, Paging<Unit> e)
	{
		HibernateUtils.distinct(getSession(), Unit.class, e, new PagingCallback()
		{
			@Override
			public void createCriteria(Criteria cri) throws HibernateException
			{
				if(StringUtils.isNotEmpty(unitName)){
					cri.add(Restrictions.like("unitName", "%"+unitName+"%"));
				}
				if(StringUtils.isNotEmpty(unitCode)){
					cri.add(Restrictions.eq("unitCode", unitCode));
				}
				if(pUnit != -1 && pUnit != 0){
					Unit u = (Unit) findById(pUnit,Unit.class);
					String dq_m = u.getUnitCode();
					dq_m = dq_m.substring(0, CodeUnit.getGldwdmSubLength(dq_m));
					cri.add(Restrictions.like("unitCode", dq_m +"%"));
				}
				if(user!=null){
					if(user.getSuperManager() == 0){
						String dq_m = user.getUnitId().getUnitCode();
						dq_m = dq_m.substring(0, CodeUnit.getGldwdmSubLength(dq_m));
						cri.add(Restrictions.like("unitCode", dq_m +"%"));
					}
				}
			}
			
			@Override
			public void order(Criteria c) throws HibernateException, SQLException
			{
				c.addOrder(Order.asc("id"));
			}
		}, "unit");
	}

	@Override
	public Collection<Unit> getUnitByIds(final Collection<Long> unitids)
	{
		HibernateUtils.list(getSession(), Unit.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				c.add(Restrictions.in("id", unitids));
			}
		});
		
		return null;
	}

}
