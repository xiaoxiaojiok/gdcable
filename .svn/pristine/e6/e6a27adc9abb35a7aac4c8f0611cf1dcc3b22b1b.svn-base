package com.gdcable.epm.dao.permission;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.Paging;
import com.accentrue.gd_net.hibernate.PagingCallback;
import com.gdcable.epm.dao.base.BaseDaoImpl;
import com.gdcable.epm.entity.Permission;

/**
 * 
 * <pre>
 * 权限dao处理类
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-8
 */
@Repository
public class PermissionDaoImpl extends BaseDaoImpl implements PermissionDao 
{
	
	@Override
	public void deleteByIds(String ids)
	{
		String[] id = ids.split(",");
		for(String pid:id){
			Permission p = findById(Long.parseLong(pid), Permission.class);
			delete(p);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getPermissionByPermIds(String permIds)
	{
		Query query = getSession().createQuery("from Permission p where p.id in (?)");
		query.setParameter(0, permIds);
		List<Permission> perlist = (List<Permission>)query.list();
		return perlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getPermissionByRoles(String roleids)
	{
		Query query = getSession().createQuery("select p from Permission p join p.roleList r where p.permType=-1 and r.id in("+roleids+")");
		List<Permission> perlist = (List<Permission>)query.list();
		return perlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getPermissionsByMenuId(Long menuId)
	{
		Query query = getSession().createQuery("from Permission p where p.menu.id=(?)");
		query.setParameter(0, menuId);
		List<Permission> perlist = (List<Permission>)query.list();
		return perlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getPermissionsByMenuIdAndRoleId(Long menuId, Long roleId)
	{
		Query query = getSession().createQuery("select p from Permission p join p.roleList r where p.menu.id=(?) and r.id=(?)");
		query.setParameter(0, menuId);
		query.setParameter(1, roleId);
		List<Permission> perlist = (List<Permission>)query.list();
		return perlist;
	}
	
	@SuppressWarnings("unchecked")
	public Page<Permission> queryByMenuName(String menuName,Pageable pageable){
		List<Permission> perlist = getSession().createCriteria(Permission.class).add(Expression.like("menu.menuName", "%" + menuName + "%")).list();
		Object t = perlist.size();
		return new PageImpl(perlist, pageable, t!=null?Long.parseLong(t.toString()):0);
	}

	

	@Override
	public void queryByMenuName(final String menuName, Paging<Permission> p)
	{
		HibernateUtils.distinct(getSession(), Permission.class, p, new PagingCallback()
		{
			@Override
			public void createCriteria(Criteria c) throws HibernateException
			{
				if(menuName!=null &&!menuName.equals("")){
					c.createAlias("Menu", "menu");
					c.add(Restrictions.like("menu.menuName", "%"+menuName+"%"));
				}
			}
			@Override
			public void order(Criteria c) throws HibernateException, SQLException
			{
				c.addOrder(Order.asc("id"));
			}
		},"menu");
	}
}
