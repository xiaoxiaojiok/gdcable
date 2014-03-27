package com.gdcable.epm.servicre.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.role.RoleDao;
import com.gdcable.epm.entity.Role;
import com.gdcable.epm.entity.Unit;
import com.gdcable.epm.servicre.base.BaseService;

/**
 * 
 * <pre>
 * 角色管理Service实现类
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-9
 */
@Component
@Transactional(readOnly=true)
public class RoleService extends BaseService
{
	@Autowired 
	private RoleDao roleDao;

	public void queryAllRole(String roleName,Paging<Role> p)
	{
		 this.roleDao.queryAllRole(roleName,p);
	}
	
	/**
	 * <pre>
	 * 修改角色状态
	 * </pre>
	 * @param status
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void updateRoleByStatus(int status ,Long id)
	{
		roleDao.updateRoleByStatus(status, id);
	}
	
	/**
	 * 
	 * <pre>
	 * 根据角色id，返回所有的业务角色
	 * </pre>
	 * @param roleId
	 * @return
	 */
	public List<Role> getBusinessRoleList(List<Role> roleList){
		Collection<Long> roleids = new ArrayList<Long>();
		for(Role r:roleList){
			roleids.add(r.getId());
		}
		Collection<Role> rlist =roleDao.getBusinessRoleList(roleids);
		List<Role> relist = new ArrayList<Role>();
		for(Role role:rlist){
			relist.add(role);
		}
		return relist; 
	}
	
	/**
	 * 
	 * <pre>
	 * 得到用户的所有业务角色管辖的单位集合
	 * </pre>
	 * @param userId
	 * @return
	 */
	public List<Unit> getBusinessRoleUnitList(List<Role> roleList){
		Collection<Long> roleids = new ArrayList<Long>();
		for(Role r:roleList){
			roleids.add(r.getId());
		}
		List<Unit> unitlist = this.roleDao.getBusinessRoleUnitList(roleids);
		return unitlist;
	}

}

