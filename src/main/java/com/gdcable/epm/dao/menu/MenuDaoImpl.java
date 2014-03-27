package com.gdcable.epm.dao.menu;

import java.util.List;

import org.hibernate.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.base.BaseDaoImpl;
import com.gdcable.epm.entity.Menu;

/**
 * 
 * <pre>
 * 菜单管理DAO实现类
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
@Repository
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao 
{

	@Override
	public Menu findByMenuName(String menuName)
	{
		Query query = getSession().createQuery(" FROM Menu m WHERE m.menuName = ? ");  
		query.setParameter(0, menuName);  
		return (Menu)query.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void findByParams(final String menuName,final int status,final Long pMenu,Paging<Menu> p)
	{
		int startNum = (p.getCurrentPage() - 1)* p.getPageSize();
		int endNum = startNum+p.getPageSize();
		StringBuffer sql = new StringBuffer("select {m.*} from gn_base_menu m where 1=1 ");
		if(!menuName.equals("")){
			sql.append(" and m.m_menu_name like '%"+menuName+"%'");
		}
		if(status!=-1){
			sql.append(" and m.m_status = "+status);
		}
		if(pMenu!=0){
			sql.append(" and m_up_menu_id="+pMenu);
		}
		sql.append("start with m.m_up_menu_id is null connect by prior m.m_menu_id=m.m_up_menu_id order siblings by m.m_menu_id");
		int count = getSession().createSQLQuery(sql.toString()).addEntity("m",Menu.class).list().size(); 
		StringBuffer sql2 = new StringBuffer("select * from (").append(sql.toString()).append(") where rownum between ").append(startNum).append(" and ").append(endNum);
		List<Menu> mlist = (List<Menu>) getSession().createSQLQuery(sql2.toString()).addEntity("m", Menu.class).list();
		p.setData(mlist);
		p.setAmount(count);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Menu> getMenuListByLevel(int menuLevel, Pageable pageable)
	{
		Query query = getSession().createQuery("FROM Menu m WHERE m.menuLevel = ?");
		query.setParameter(0, menuLevel);
		List<Menu> menuList = (List<Menu>)query.list();
		Object t = menuList.size();
		return new PageImpl(menuList, pageable, t!=null?Long.parseLong(t.toString()):0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Menu> getSubMenuListByPMenu(Long pid, Pageable pageable)
	{
		Query query = getSession().createQuery("FROM Menu m WHERE m.menu.id = ?");
		query.setParameter(0, pid);
		List<Menu> menuList = (List<Menu>)query.list();
		Object t = menuList.size();
		return new PageImpl(menuList, pageable, t!=null?Long.parseLong(t.toString()):0);
	}

	@Override
	public Integer nextLevelCount(Long currMenuId)
	{
		Query query = getSession().createQuery("SELECT COUNT(m.id) FROM Menu m WHERE m.menu.id = ?");
		query.setParameter(0, currMenuId);
		Integer count =  (Integer)query.list().get(0);
		return count;
	}

	@Override
	public Menu FindById(long menuId)
	{
		Query query = getSession().createQuery(" FROM Menu m WHERE m.id= ? ");  
		query.setParameter(0, menuId);  
		return (Menu)query.list().get(0);
	}

}
