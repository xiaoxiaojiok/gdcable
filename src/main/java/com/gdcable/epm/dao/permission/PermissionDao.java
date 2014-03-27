package com.gdcable.epm.dao.permission;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.base.BaseDao;
import com.gdcable.epm.entity.Permission;

/**
 * 
 * <pre>
 * 权限Dao
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
public interface PermissionDao extends BaseDao {

	/**
	 * 
	 * <pre>
	 * 根据id字符串批量删除权限
	 * </pre>
	 * @param ids
	 */
	void deleteByIds(String ids);
	
	/**
	 * 
	 * <pre>
	 * 根据菜单id得到权限
	 * </pre>
	 * @param menuId
	 * @return
	 */
	List<Permission> getPermissionsByMenuId(Long menuId);
	
	/**
	 * <pre>
	 * 根据菜单ID和角色ID查询权限
	 * </pre>
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	List<Permission> getPermissionsByMenuIdAndRoleId(Long menuId,Long roleId);
	
	/**
	 * <pre>
	 * 根据角色集合查询菜单权限
	 * </pre>
	 * @param roleids
	 * @return
	 */
	List<Permission> getPermissionByRoles(String roleids);
	
	/**
	 * 
	 * <pre>
	 * 根据权限id集合得到权限集合
	 * </pre>
	 * @param permIds
	 * @return
	 */
	List<Permission> getPermissionByPermIds(String permIds);
	
	/**
	 * 
	 * <pre>
	 * 根据菜单名称，获取相关的所属权限集合
	 * </pre>
	 * @param menuName
	 * @return
	 */
	public Page<Permission> queryByMenuName(String menuName,Pageable pageable);
	
	/**
	 * 
	 * <pre>
	 * 根据菜单名称，获取相关的所属权限集合
	 * </pre>
	 * @param menuName
	 * @return
	 */
	void queryByMenuName(String menuName,Paging<Permission> p);
	
	
}
