package com.gdcable.epm.dao.role;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.Paging;
import com.accentrue.gd_net.hibernate.PagingCallback;
import com.accentrue.gd_net.hibernate.QueryCallback;
import com.gdcable.epm.dao.base.BaseDaoImpl;
import com.gdcable.epm.entity.Role;
import com.gdcable.epm.entity.Unit;

/**
 * 
 * <pre>
 * 角色管理dao实现类
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-9
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao
{

	@Override
	public void queryAllRole(final String roleName,Paging<Role> p)
	{
		HibernateUtils.distinct(getSession(), Role.class, p, new PagingCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				if(roleName!=null &&!roleName.equals("")){
					c.add(Restrictions.like("name", "%"+roleName+"%"));
				}
			}
			@Override
			public void order(Criteria c) throws HibernateException, SQLException
			{
				c.addOrder(Order.asc("id"));
			}
		});
	}

	@Override
	public void updateRoleByStatus(int status, Long id)
	{
		try{
			Query query = getSession().createQuery("update Role set status=? where id=?");
			query.setParameter(0, status);
			query.setParameter(1, id);
			query.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public Collection<Role> getBusinessRoleList(final Collection<Long> roleId)
	{
		return HibernateUtils.list(getSession(), Role.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				if(!roleId.equals("")){
					c.add(Restrictions.in("id", roleId));
				}
				c.add(Restrictions.eq("roleType.id", 2L));
			}
		});
	}

	@Override
	public List<Unit> getBusinessRoleUnitList(final Collection<Long> roleId)
	{
		Collection<Role> rlist = HibernateUtils.list(getSession(), Role.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				if(!roleId.equals("")){
					c.add(Restrictions.in("id", roleId));
				}
				c.add(Restrictions.eq("roleType.id", 2L));
			}
		},"roleUnit");
		List<Unit> ulist = new ArrayList<Unit>();
		for(Role role:rlist){
			ulist.add(role.getRoleUnit());
		}
		return ulist;
	}

}
