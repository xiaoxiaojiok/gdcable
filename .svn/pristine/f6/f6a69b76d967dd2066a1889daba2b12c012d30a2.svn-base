package com.gdcable.epm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.gdcable.epm.publics.Constants;
import com.google.common.collect.Lists;


/**
 * 用户实体
 * @author 殷俊
 */
@Entity
@Table(name = "pm_user",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_USER", sequenceName = "PM_USER_SEQ", allocationSize = 1)
public class User{
	
	private static final long serialVersionUID = 100008L;
	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_USER")
	@Column(name="user_id")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	/**
	 * 登录名(不允许空值)
	 */
	private String loginName;
	/**
	 * 密码(不允许空值)
	 */
	private String password;
	/**
	 * 初始密码(不允许空值)
	 */
	private String initPassword;
	/**
	 * 真实姓名（或昵称，依系统而定）
	 */
	private String username;
	/**
	 * 用户状态（0：启用（默认）；1：禁用）
	 */
	private int status;	
	
	/**
	 * 机构ID
	 */
	private Unit unitId;
	
	private List<Role> roleList = Lists.newArrayList(); // 有序的关联对象集合
	
	/**=============以下为基础库字段，备用============**/
	/**
	 * 性别,1：男性；2：女性
	 */
	private int gender;
	private String mobile; // 手机号码
	private String email; // 用户电邮
	private Date addTime; // 用户添加时间
	private Date updateTime; //用户修改时间
	private int  superManager; // 是否超级管理员，默认为0
	private User creater;  //创建人
	private User updater;  //修改人
	
	@Column(name="u_login_name",nullable=false,length=200)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name="u_password",nullable=false,length=200)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="u_init_password",nullable=false,length=200)	
	public String getInitPassword()
	{
		return initPassword;
	}

	public void setInitPassword(String initPassword)
	{
		this.initPassword = initPassword;
	}

	@Column(name="u_user_name",nullable=false,length=200)
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
	
	

	@Column(name="u_status",nullable = true, columnDefinition="INTEGER default 0")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="u_unit_id",nullable=true)
	public Unit getUnitId()
	{
		return unitId;
	}

	public void setUnitId(Unit unit)
	{
		this.unitId = unit;
	}
	

	// 多对多定义
	@ManyToMany(targetEntity=Role.class,cascade = { CascadeType.PERSIST,CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "gn_base_user_role" ,schema=Constants.GD_NET, joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序
	@OrderBy("id ASC")
	// 缓存策略
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**=========备用字段原则：int、long、Date设置为可空，String按照文档指定长度================*/
	@Column(name="u_gender",nullable=true,columnDefinition="INTEGER DEFAULT 1")
	public int getGender()
	{
		return gender;
	}

	public void setGender(int gender)
	{
		this.gender = gender;
	}

	@Column(name="u_mobile")
	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	@Column(name="u_email")
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name="u_add_time",nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAddTime()
	{
		return addTime;
	}

	public void setAddTime(Date addTime)
	{
		this.addTime = addTime;
	}

	@Column(name="u_update_time",nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}

	@Column(name="u_super_manager",nullable=true,columnDefinition="INTEGER default 0")
	public int getSuperManager()
	{
		return superManager;
	}

	public void setSuperManager(int superManager)
	{
		this.superManager = superManager;
	}

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="u_creater_id",nullable=true)
	public User getCreater()
	{
		return creater;
	}

	public void setCreater(User creater)
	{
		this.creater = creater;
	}

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="u_updater_id",nullable=true)
	public User getUpdater()
	{
		return updater;
	}

	public void setUpdater(User updater)
	{
		this.updater = updater;
	}
	
	
	
}