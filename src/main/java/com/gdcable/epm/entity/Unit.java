package com.gdcable.epm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gdcable.epm.publics.Constants;


/**
 * 单位实体，设计初衷：尽量减少有用字段对码表的依赖。
 * @author chen
 */
@Entity
@Table(name = "gn_base_unit",schema=Constants.GD_NET)
@Cacheable 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "GN_BASE_UNIT", sequenceName = "GN_BASE_UNIT_SEQ", allocationSize = 1)
public class Unit{		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 100007L;
	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="GN_BASE_UNIT")
	@Column(name="ut_unit_id")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	@Transient
	public static String SHENG = "sheng";
	@Transient
	public static String CITY = "city";
	@Transient
	public static String COUNTY = "county";
	@Transient
	public static String TOWN = "town";

	/**
	 * 单位名称
	 */
	private String unitName;
	/**
	 * 上级单位
	 */
	private	Unit unit;
	
	/**
	 * 下级单位集合
	 */
	private List<Unit> unitList= new ArrayList<Unit>(); // 有序的关联对象集合
	/**=============以下为基础库字段，备用============**/
	/**
	 * 单位级别码(字段类型参照基础库说明)
	 */
	private String unitLevel;
	/**
	 * 地区码
	 */
	private String unitCode;
	/**
	 * 单位代码
	 */
	private String departmentCode;	
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮编
	 */
	private String postCode;
	/**
	 * 电话
	 */
	private String telphone;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 管理单位代码
	 */
	private String managerUnitCode;
	
	/**
	 * 国家单位代码
	 */
	private String countryUnitCode;


	
	/**
	 * 状态 1为正常  0为禁用
	 */
	private int status;
	
	
	@Column(name="ut_unit_name",nullable = false, length=200)
	public String getUnitName()
	{
		return unitName;
	}
	public void setUnitName(String unitName)
	{
		this.unitName = unitName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ut_up_unit_id")
	public Unit getUnit()
	{
		return unit;
	}
	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ut_up_unit_id")
	public List<Unit> getUnitList()
	{
		return unitList;
	}

	public void setUnitList(List<Unit> unitList)
	{
		this.unitList = unitList;
	}
	
	@Column(name="ut_unit_level",length=100)
	public String getUnitLevel()
	{
		return unitLevel;
	}

	public void setUnitLevel(String unitLevel)
	{
		this.unitLevel = unitLevel;
	}

	@Column(name="ut_unit_code",length=100)
	public String getUnitCode()
	{
		return unitCode;
	}

	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}

	@Column(name="ut_department_code",length=100)
	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	@Column(name="ut_address",length=100)
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column(name="ut_post_code",length=6)
	public String getPostCode()
	{
		return postCode;
	}

	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}

	@Column(name="ut_telphone",length=15)
	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	@Column(name="ut_email",length=255)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name="ut_manager_unit_code",length=100)
	public String getManagerUnitCode()
	{
		return managerUnitCode;
	}

	public void setManagerUnitCode(String managerUnitCode)
	{
		this.managerUnitCode = managerUnitCode;
	}

	@Column(name="ut_country_unit_code",length=100)
	public String getCountryUnitCode()
	{
		return countryUnitCode;
	}

	public void setCountryUnitCode(String countryUnitCode)
	{
		this.countryUnitCode = countryUnitCode;
	}

	@Column(name="ut_status",nullable=true,length=10)
	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
	
}
