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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gdcable.epm.publics.Constants;

/**
 * 项目实体类
 * 
 * <pre>
 * </pre>
 * @author 华军
 * @version 1.0, 2013-9-25
 */
@Entity
@Table(name = "PM_PROJECTS", schema = Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_PROJECTS", sequenceName = "PM_PROJECTS_SEQ", allocationSize = 1)
public class Project implements Serializable
{
	private static final long serialVersionUID = -8317226322289388083L;

	private Long id; // 项目编号

	private String code; // 项目编码

	private String name; // 项目名称

	private Project parentProject; // 所属的父级项目

	private ProCategory type; // 项目所属的类型

	private Mcode peoperty; // 项目性质，来自码表，码表中有四种取值：新建/升级改造/维护迁改/优化

	private Mcode priority; // 项目的紧急程度，来自码表，有两种取值：正常/紧急

	private Mcode issafeshow; // 项目是否涉及安全播出，来自码表，有两种取值：是/否

	private ProjPartner build_orgId; // 建设单位

	private ProjPartner use_orgId; // 使用单位

	private ProjPartner demand_orgId; // 需求单位

	private String address; // 项目建设地址

	private String description; // 项目概述

	private Date startDate; // 计划开始时间

	private Date completionDate; // 计划结束时间

	private Date closeDate; // 项目终止时间

	private Date actual_startDate; // 项目实际开始时间

	private Date actual_finishedDate; // 项目实际结束时间

	private Date early_startDate; // 项目最早开始时间

	private Date early_finishDate; // 项目最早结束时间

	private Date late_startDate; // 项目最晚开始时间

	private Date late_finishDate; // 项目最晚结束时间

	private Mcode status; // 项目所处的状态，来自码表

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PM_PROJECTS")
	@Column(name = "PROJECT_ID")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "PROJECT_CODE", length = 250)
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	@Column(name = "NAME", length = 250)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_PROJECT_ID")
	// 一个父级项目中可能包含多个子级项目
	public Project getParentProject()
	{
		return parentProject;
	}

	public void setParentProject(Project parentProject)
	{
		this.parentProject = parentProject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_TYPE")
	// 一个项目类别对应多个项目
	public ProCategory getType()
	{
		return type;
	}

	public void setType(ProCategory type)
	{
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property")
	// 多个项目可以同时处于同一个码表状态
	public Mcode getPeoperty()
	{
		return peoperty;
	}

	public void setPeoperty(Mcode peoperty)
	{
		this.peoperty = peoperty;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priority")
	public Mcode getPriority()
	{
		return priority;
	}

	public void setPriority(Mcode priority)
	{
		this.priority = priority;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issafeshow")
	public Mcode getIssafeshow()
	{
		return issafeshow;
	}

	public void setIssafeshow(Mcode issafeshow)
	{
		this.issafeshow = issafeshow;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUILD_OGR_ID")
	public ProjPartner getBuild_orgId()
	{
		return build_orgId;
	}

	public void setBuild_orgId(ProjPartner buildOrgId)
	{
		build_orgId = buildOrgId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USE_ORG_ID")
	public ProjPartner getUse_orgId()
	{
		return use_orgId;
	}

	public void setUse_orgId(ProjPartner useOrgId)
	{
		use_orgId = useOrgId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEMAND_ORG_ID")
	public ProjPartner getDemand_orgId()
	{
		return demand_orgId;
	}

	public void setDemand_orgId(ProjPartner demandOrgId)
	{
		demand_orgId = demandOrgId;
	}

	@Column(name = "ADDRESS", length = 250)
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column(name = "DESCRIPTION", length = 250)
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COMPLETION_DATE")
	public Date getCompletionDate()
	{
		return completionDate;
	}

	public void setCompletionDate(Date completionDate)
	{
		this.completionDate = completionDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CLOSE_DATE")
	public Date getCloseDate()
	{
		return closeDate;
	}

	public void setCloseDate(Date closeDate)
	{
		this.closeDate = closeDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACTUAL_START_DATE")
	public Date getActual_startDate()
	{
		return actual_startDate;
	}

	public void setActual_startDate(Date actualStartDate)
	{
		actual_startDate = actualStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACTUAL_FINISHED_DATE")
	public Date getActual_finishedDate()
	{
		return actual_finishedDate;
	}

	public void setActual_finishedDate(Date actualFinishedDate)
	{
		actual_finishedDate = actualFinishedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EARLY_START_DATE")
	public Date getEarly_startDate()
	{
		return early_startDate;
	}

	public void setEarly_startDate(Date earlyStartDate)
	{
		early_startDate = earlyStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EARLY_FINISH_DATE")
	public Date getEarly_finishDate()
	{
		return early_finishDate;
	}

	public void setEarly_finishDate(Date earlyFinishDate)
	{
		early_finishDate = earlyFinishDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LATE_START_DATE")
	public Date getLate_startDate()
	{
		return late_startDate;
	}

	public void setLate_startDate(Date lateStartDate)
	{
		late_startDate = lateStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LATE_FINISH_DATE")
	public Date getLate_finishDate()
	{
		return late_finishDate;
	}

	public void setLate_finishDate(Date lateFinishDate)
	{
		late_finishDate = lateFinishDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS")
	public Mcode getStatus()
	{
		return status;
	}

	public void setStatus(Mcode status)
	{
		this.status = status;
	}

}
