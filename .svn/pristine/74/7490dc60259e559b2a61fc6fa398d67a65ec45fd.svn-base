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
/**
 * 项目类别实体类
 * <pre>
 * </pre>
 * @author 华军
 * @version 1.0, 2013-9-25
 */
@Entity
@Table(name = "PM_CATEGORIES",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_CATEGORIES", sequenceName = "PM_CATEGORIES_SEQ", allocationSize = 1)
public class ProCategory implements Serializable
{

	private static final long serialVersionUID = -5404406556479738593L;
	private Long id;     //项目类别编号
	private String categoryName;  //类别名称
	private ProCategory parent;    //上一级类别
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_CATEGORIES")
	@Column(name="CATEGORY_ID")
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	@Column(name="CATEGORY",length=250)
	public String getCategoryName()
	{
		return categoryName;
	}
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_ID")
	public ProCategory getParent()
	{
		return parent;
	}
	public void setParent(ProCategory parent)
	{
		this.parent = parent;
	}
	

}
