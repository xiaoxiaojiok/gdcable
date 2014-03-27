package com.gdcable.epm.dao.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.gdcable.epm.entity.User;

/**
 * 
 * <pre>
 * 用户管理dao
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao
{

	@Override
	public Collection<User> findAllUser(final int status)
	{
		return HibernateUtils.list(getSession(),User.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria cri) throws HibernateException
			{
				if(status<2){
					cri.add(Restrictions.eq("status", status));
				}
			}
		}, "unitId");
	}

	@Override
	public Collection<User> findUserListByUnitId(final Long unitId)
	{
		return HibernateUtils.list(getSession(),User.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria cri) throws HibernateException
			{
				if(unitId !=-1 && unitId!=0){
					cri.add(Restrictions.eq("unitId.id", unitId));
				}
			}
		}, "unitId");
	}

	@Override
	public void getUserList(final Long unitId,final Long roleId,final String username,final String loginname,final User user, Paging<User> p)
	{
		HibernateUtils.distinct(getSession(), User.class, p, new PagingCallback()
		{
			@Override
			public void createCriteria(Criteria cri) throws HibernateException
			{
				if(unitId!=-1 && unitId!=0){
					cri.add(Restrictions.eq("unitId.id", unitId));
				}
				if(StringUtils.isNotEmpty(username)){
					cri.add(Restrictions.like("username", "%"+username+"%"));
				}
				if(StringUtils.isNotEmpty(loginname)){
					cri.add(Restrictions.like("loginName", "%"+loginname+"%"));
				}
				if(user!=null){
					if(user.getSuperManager() == 0){
						cri.add(Restrictions.like("superManager", "0"));
					}
				}
				if(roleId!=-1 && roleId!=0){
					cri.createAlias("Role", "role");
					cri.add(Restrictions.eq("role.id", roleId));
				}
			}
			@Override
			public void order(Criteria c) throws HibernateException, SQLException
			{
				c.addOrder(Order.asc("unitId.id"));
				c.addOrder(Order.asc("id"));
			}
		}, "unitId");
	}

	@Override
	public boolean checkLoginNameIsExits(final String loginName)
	{
		Collection<User> ulist = HibernateUtils.list(getSession(), User.class, new QueryCallback()
		{
			
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				c.add(Restrictions.eq("loginName", loginName));
			}
		});
		if(ulist!=null && ulist.size()>0){
			return true;
		}else
			return false;
	}

	@Override
	public User findUserByLoginName(final String loginName)
	{
		try{
			User user = HibernateUtils.get(getSession(), User.class, new QueryCallback()
			{
				@Override
				public void createCriteria(Criteria c) throws HibernateException
				{
					c.add(Restrictions.eq("loginName", loginName));
				}
			},"unitId");
			return user;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Collection<User> findUserByUserName(final String userName)
	{
		return HibernateUtils.list(getSession(), User.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				c.add(Restrictions.eq("username", userName));
			}
		});
	}

	@Override
	public List<User> getSuperManager()
	{
		Collection<User> userlist = HibernateUtils.list(getSession(), User.class, new QueryCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				c.add(Restrictions.eq("superManager", 1));
				c.add(Restrictions.eq("status", 0));
			}
		});
		List<User> relist = new ArrayList<User>();
		for(User u:userlist){
			relist.add(u);
		}
		return relist;
	}

	@Override
	public boolean isExitUserAndRole(long userId, long roleId)
	{
		StringBuffer sql = new StringBuffer("select * from gn_base_user_role where user_id=");
		sql.append(userId).append(" and role_id=").append(roleId);
		Query query  = getSession().createSQLQuery(sql.toString());
		if(query.list()!=null && query.list().size()>0){
			return true;
		}else{
			return false;
		}
	}

}
