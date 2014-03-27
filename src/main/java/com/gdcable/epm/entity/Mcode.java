package com.gdcable.epm.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gdcable.epm.publics.Constants;



/**
 *		码表实体
 *	设置规则：各实体中码值统一采用String类型
 * @author chen
 * @version 1.0, 2012-11-9
 */
@Entity
@Table(name="pm_base_mcode", schema=Constants.GD_NET)
@Cacheable 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_BASE_MCODE", sequenceName = "PM_BASE_MCODE_SEQ", allocationSize = 1)
public class Mcode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;
	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_BASE_MCODE")
	@Column(name="mcode_id")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 * 码类型
	 */
	private String mtype;
	/**
	 * 码名
	 */
	private String mkey;
	/**
	 * 码值
	 */
	private String mvalue;
	/**
	 * 码备注
	 */
	private String remark;
	/**
	 * 码排序
	 */
	private long orderNum;
	/**=============以下为基础库字段，备用============**/
	/**
	 * 未标注，估测为：创建日期
	 */
	private Date creatdate;
	/**
	 * 未标注，估测为：日期级别
	 */
	private int datelevel;
	
	@Column(name="mc_mtype",nullable = false, length=300)
	public String getMtype()
	{
		return mtype;
	}
	public void setMtype(String mtype)
	{
		this.mtype = mtype;
	}
	
	@Column(name="mc_mkey",nullable = false, length=100)
	public String getMkey()
	{
		return mkey;
	}
	public void setMkey(String mkey)
	{
		this.mkey = mkey;
	}
	
	@Column(name="mc_mvalue",nullable = false, length=50)
	public String getMvalue()
	{
		return mvalue;
	}
	public void setMvalue(String mvalue)
	{
		this.mvalue = mvalue;
	}
	
	@Column(name="mc_remark",nullable = true, length=300)
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	@Column(name="mc_order_num",nullable = true)
	public long getOrderNum()
	{
		return orderNum;
	}
	public void setOrderNum(long orderNum)
	{
		this.orderNum = orderNum;
	}
	
	@Column(name="mc_create_date",nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatdate()
	{
		return creatdate;
	}
	public void setCreatdate(Date creatdate)
	{
		this.creatdate = creatdate;
	}
	
	@Column(name="mc_date_level",nullable=true,columnDefinition="INTEGER default 0")
	public int getDatelevel()
	{
		return datelevel;
	}
	public void setDatelevel(int datelevel)
	{
		this.datelevel = datelevel;
	}
	
	
}

