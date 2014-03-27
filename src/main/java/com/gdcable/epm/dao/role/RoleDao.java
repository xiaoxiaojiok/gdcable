package com.gdcable.epm.dao.role;

import java.util.Collection;
import java.util.List;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.base.BaseDao;
import com.gdcable.epm.entity.Role;
import com.gdcable.epm.entity.Unit;

/**
 * 
 * <pre>
 * 角色dao
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
public interface RoleDao extends BaseDao  {

	/**
	 * 
	 * <pre>
	 * 根据角色名称查询所有角色
	 * </pre>
	 * @return
	 */
	public void queryAllRole(String roleName,Paging<Role> p);
	
	/**
	 * <pre>
	 * 修改角色状态
	 * </pre>
	 * @param status
	 * @param id
	 */
	public void updateRoleByStatus(int status ,Long id);
	
	/**
	 * 
	 * <pre>
	 * 得到用户的所有业务角色
	 * </pre>
	 * @param roleId
	 * @return
	 */
	public Collection<Role> getBusinessRoleList(Collection<Long> roleId);
	
	/**
	 * 
	 * <pre>
	 * 得到用户的所有业务角色管辖的单位集合
	 * </pre>
	 * @param userId
	 * @return
	 */
	public List<Unit> getBusinessRoleUnitList(Collection<Long> roleId);

}
