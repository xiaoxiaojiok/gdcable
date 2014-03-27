package com.gdcable.epm.dao.user;

import java.util.Collection;
import java.util.List;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.base.BaseDao;
import com.gdcable.epm.entity.User;

/**
 * 
 * <pre>
 * 用户管理dao
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
public interface UserDao extends BaseDao
{
	
	public Collection<User> findAllUser(int status);
	
	/**
	 * 根据unitId获得用户列表,20130516,eric
	 */
	public Collection<User> findUserListByUnitId(Long  unitId);
	
	/**
	 * 根据查询条件查询所有用户
	 * 
	 * @param unitId
	 * @param roleId
	 * @param username
	 * @param loginname
	 * @return
	 */
	public void getUserList(Long unitId,Long roleId,String username,String loginname,User user,Paging<User> p);
	
	/**
	 * 
	 * <pre>
	 * 检查登录名是否存在
	 * </pre>
	 * @param loginName
	 * @return
	 */
	public boolean checkLoginNameIsExits(String loginName);
	
	/**
	 * 
	 * <pre>
	 * 根据登录名获取用户
	 * </pre>
	 * @param loginName
	 * @return
	 */
	public User findUserByLoginName(String loginName);
	
	/**
	 * 
	 * <pre>
	 * 根据用户名称获取用户
	 * </pre>
	 * @param loginName
	 * @return
	 */
	public Collection<User> findUserByUserName(String userName);
	
	/**
	 * 
	 * <pre>
	 * 得到系统的超管
	 * </pre>
	 * @return
	 */
	public List<User> getSuperManager();
	
	/**
	 * 
	 * <pre>
	 * 是否存在用户和角色的对应关系
	 * </pre>
	 * @return
	 */
	public boolean isExitUserAndRole(long userId,long roleId);
	
	
}
