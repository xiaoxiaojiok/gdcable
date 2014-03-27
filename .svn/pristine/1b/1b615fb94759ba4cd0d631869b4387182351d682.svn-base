package com.gdcable.epm.dao.menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.base.BaseDao;
import com.gdcable.epm.entity.Menu;

/**
 * 
 * <pre>
 * 菜单管理DAO
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
public interface MenuDao extends BaseDao {
	
	/**
	 *  获取当前级别菜单
	 *  
	 * @param menuLevel
	 * @param intSys 系统代号
	 * @return
	 */
	Page<Menu> getMenuListByLevel(int menuLevel,Pageable pageable);
	
	/**
	 *  根据当前菜单获取其下一级别子菜单
	 * 
	 * @param pid
	 * @return
	 */
	Page<Menu> getSubMenuListByPMenu(Long pid,Pageable pageable);
	
	/**
	 * 获取当前菜单下一级别数量
	 * 
	 * @param currMenuId
	 * @return
	 */
	Integer nextLevelCount(Long currMenuId);
	
	/**
	 * 根据菜单名字查询
	 * 
	 * @param menuName
	 * @return
	 */
	Menu findByMenuName(String menuName);
	
	/**
	 * 根据条件查询
	 * 
	 * @param menuName
	 * @return
	 */
	void findByParams(String menuName,int status,Long pMenu,Paging<Menu> p);
	
	/**
	 * 
	 * <pre>
	 * 根据菜单id，得到一个菜单实体
	 * </pre>
	 * @param menuId
	 * @return
	 */
	Menu FindById(long menuId);
}
