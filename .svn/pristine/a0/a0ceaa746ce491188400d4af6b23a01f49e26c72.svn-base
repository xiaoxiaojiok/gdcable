package com.gdcable.epm.servicre.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.user.UserDao;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.servicre.base.BaseService;

/**
 * 
 * <pre>
 * 用户管理service
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
@Component
@Transactional(readOnly = true)
public class UserService extends BaseService 
{
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;

	@Autowired
	private UserDao userDao;
	
	/**
	 * 获取所有的已启用用户
	 * 	@param pageable
	 * @return
	 */
	public List<User> getAllAbleUser() {
		 return (List<User>) userDao.findAllUser(1);
	}
	
	/**
	 * 根据unitId获得用户列表,20130516,eric
	 */
	public List<User> findUserListByUnitId(Long  unitId) {
		return  (List<User>) userDao.findUserListByUnitId(unitId);
	}
	
	/**
	 * 获取所有用户（包括已启用和已停用的用户）
	 * @param pageable
	 * @return
	 */
	public void getAllUsers() {
		 userDao.findAllUser(2);
	}
	
	/**
	 * 根据查询条件查询所有用户
	 * 
	 * @param unitId
	 * @param roleId
	 * @param username
	 * @param loginname
	 * @return
	 */
	public void getUserList(Long unitId,Long roleId,String username,String loginname,User user,Paging<User> p){
		userDao.getUserList(unitId,roleId,username,loginname,user,p);
	}
	
	/**
	 * 根据用户ID查找用户
	 * @param id
	 * @return
	 */
	public User getUser(Long id) {
		User user = (User) userDao.findById(id, User.class);
		return user;
	}
	
	/**
	 * 根据登录名查找用户（登录名控制唯一）
	 * @param loginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		User user = userDao.findUserByLoginName(loginName);
		return user;
	}
	
	
	/**
	 * 返回当前用户
	 * 
	 * @param request
	 * @return
	 */
	public User getUser(HttpServletRequest request){
		String loginName = request.getRemoteUser();
		User user = findUserByLoginName(loginName);
		return user;
	}
	
	/**
	 * 用户真实姓名可能不唯一
	 * 根据用户的真实姓名查找用户
	 * @param loginName
	 * @return
	 */
	public List<User> findByUsername(String username){
		List<User> list = (List<User>) userDao.findUserByUserName(username);
		return list;
	}

	/**
	 *保存用户
	 *用于用户的增加、修改
	 * @param user
	 */			
	@Transactional(readOnly=false)
	public void saveUser(User user){
		//基础信息保存不成功，不往下执行。
		if(user.getMobile() == null) user.setMobile("13800138000");
		if(user.getId()==null ||user.getId()==0){
			userDao.save(user);
		}else{
			userDao.update(user);
		}
	}
	
	/**
	 * 删除用户
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			return;
		}
		User user = this.userDao.findById(id, User.class);
		userDao.delete(user);
	}

	/**
	 * 判断是否超级管理员.
	 */
	public boolean isSupervisor(Long id) {
		return getUser(id).getSuperManager() == 1;
	}
	
	/**
	 * 
	 * <pre>
	 * 得到超级管理员
	 * </pre>
	 * @return
	 */
	public List<User> getSuperManager(){
		return this.userDao.getSuperManager();
	}
	
	/**
	 * 
	 * <pre>
	 * 是否存在用户和角色的对应关系
	 * </pre>
	 * @return
	 */
	public boolean isExitUserAndRole(long userId, long roleId){
		return this.userDao.isExitUserAndRole(userId, roleId);
	}
	
}
