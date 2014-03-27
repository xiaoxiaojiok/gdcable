package com.gdcable.epm.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gdcable.epm.publics.Constants;
/**
 * 结算表
 * <pre>
 * </pre>
 * @author 黄炽威
 * @version 1.0, 2013年9月25日
 */
@Entity
@Table(name = "PM_SETTLE",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_SETTLE", sequenceName = "PM_SETTLE_SEQ", allocationSize = 1)
public class Settle implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_SETTLE")
	@Column(name="SETTLE_ID")
	private Long id;
	
	/**
	 * 项目
	 */
	private Project project;
	
	/**
	 * 结算类型
	 */
	private Mcode clazz;
	
	/**
	 * 填单人
	 */
	private User createdBy;
	
	/**
	 * 填单日期
	 */
	private Date createDate;
	
	/**
	 * 最后更新人
	 */
	private User lastUpdatedBy;
	
	/**
	 * 最后更新日期
	 */
	private Date lastUpdateDate;
	
	/**
	 * 业务唯一ID
	 */
	private Long workFlowId;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL)  
	@JoinColumn(name="PROJECT_ID") 
	public Project getProject()
	{
		return project;
	}

	public void setProject(Project project)
	{
		this.project = project;
	}

	@Column(name="CLASS")
	public Mcode getClazz()
	{
		return clazz;
	}

	public void setClazz(Mcode clazz)
	{
		this.clazz = clazz;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY")
	public User getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(User createdBy)
	{
		this.createdBy = createdBy;
	}

	@Column(name="CREATION_DATE")
	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="LAST_UPDATED_BY")
	public User getLastUpdatedBy()
	{
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(User lastUpdatedBy)
	{
		this.lastUpdatedBy = lastUpdatedBy;
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

	@Column(name="WORKFLOW_ID")
	public Long getWorkFlowId()
	{
		return workFlowId;
	}

	public void setWorkFlowId(Long workFlowId)
	{
		this.workFlowId = workFlowId;
	}

	@Override
	public String toString()
	{
		return "Settle [id=" + id + ", clazz=" + clazz + ", createdBy=" + createdBy + ", createDate=" + createDate
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", workFlowId="
				+ workFlowId + "]";
	}
	
	
	
}
