package com.gdcable.epm.entity;

import java.io.Serializable;

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
@Table(name = "PM_BUDGET_CATEGORY_DETAIL",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_BUDGET_CATEGORY_DETAIL", sequenceName = "PM_BUDGET_CATEGORY_DETAIL_SEQ", allocationSize = 1)
public class BudgetCategoryDetail implements Serializable{
	private Integer categoryDetailId;//预算细类编号
	private BudgetCategory categoryId;//预算分类编号
	private String categoryName;//名称
	private Integer parentId;//上级预算分类
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_BUDGET_CATEGORY_DETAIL")
	@Column(name="CATEGORY_DETAIL_ID")
	public Integer getCategoryDetailId()
	{
		return categoryDetailId;
	}
	public void setCategoryDetailId(Integer categoryDetailId)
	{
		this.categoryDetailId = categoryDetailId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID")
	public BudgetCategory getCategoryId()
	{
		return categoryId;
	}
	public void setCategoryId(BudgetCategory categoryId)
	{
		this.categoryId = categoryId;
	}
	
	
	@Column(name="NAME",length=150)
	public String getCategoryName()
	{
		return categoryName;
	}
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
	
	
	@Column(name="PARENTID" ,length=22)
	public Integer getParentId()
	{
		return parentId;
	}
	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}
	
	
	

}
