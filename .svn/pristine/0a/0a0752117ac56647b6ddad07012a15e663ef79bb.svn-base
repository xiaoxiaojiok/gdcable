package com.gdcable.epm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gdcable.epm.publics.Constants;

@Entity
@Table(name = "PM_BUDGET_CATEGORY",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_BUDGET_CATEGORY", sequenceName = "PM_BUDGET_CATEGORY_SEQ", allocationSize = 1)
public class BudgetCategory implements Serializable{
	
	private Integer categoryid;//预算分类编号
	private String categoryName;//预算分类名称
	private String categoryYear;//年份
	private Date starDate;//开始日期
	private Date endDate;//结束日期
	private Mcode status;//状态
	private Date releasedDate;//发布日期
	private User createBy;//录入人员编号
	private Date creationDate;//录入日期
	private User lastUpdateBy;//最后更新人编号
	private Date lastUpdateDate;//最后更新日期
	private Integer workFlowId;// 业务唯一id
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_BUDGET_CATEGORY")
	@Column(name="CATEGORY_ID")
	public Integer getCategoryid()
	{
		return categoryid;
	}
	public void setCategoryid(Integer categoryid)
	{
		this.categoryid = categoryid;
	}
	
	
	
	@Column(name="NAME",length=250)
	public String getCategoryName()
	{
		return categoryName;
	}
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
	
	@Column(name="YEAR",length=10)
	public String getCategoryYear()
	{
		return categoryYear;
	}
	public void setCategoryYear(String categoryYear)
	{
		this.categoryYear = categoryYear;
	}
	
	
	@Column(name="START_DATE")
	public Date getStarDate()
	{
		return starDate;
	}
	public void setStarDate(Date starDate)
	{
		this.starDate = starDate;
	}
	
	
	
	@Column(name="END_DATE")
	public Date getEndDate()
	{
		return endDate;
	}
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="STATUS")
	public Mcode getStatus()
	{
		return status;
	}
	public void setStatus(Mcode status)
	{
		this.status = status;
	}
	
	
	@Column(name="RELEASED_DATE")
	public Date getReleasedDate()
	{
		return releasedDate;
	}
	public void setReleasedDate(Date releasedDate)
	{
		this.releasedDate = releasedDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY")
	public User getCreateBy()
	{
		return createBy;
	}
	public void setCreateBy(User createBy)
	{
		this.createBy = createBy;
	}
	
	
	@Column(name="CREATION_DATE")
	public Date getCreationDate()
	{
		return creationDate;
	}
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="LAST_UPDATED_BY")
	public User getLastUpdateBy()
	{
		return lastUpdateBy;
	}
	public void setLastUpdateBy(User lastUpdateBy)
	{
		this.lastUpdateBy = lastUpdateBy;
	}
	
	
	@Column(name="LAST_UPDATE_DATE")
	public Date getLastUpdateDate()
	{
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate)
	{
		this.lastUpdateDate = lastUpdateDate;
	}
	
	
	@Column(name="WORKFLOW_ID",length=20)
	public Integer getWorkFlowId()
	{
		return workFlowId;
	}
	public void setWorkFlowId(Integer workFlowId)
	{
		this.workFlowId = workFlowId;
	}
	

}
