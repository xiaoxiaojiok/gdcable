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
@Table(name = "PM_MATERIAL_CLASS",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_MATERIAL_CLASS", sequenceName = "PM_MATERIAL_CLASS_SEQ", allocationSize = 1)
public class MaterialClass implements Serializable {
	
	private Long id;
	
	private String code;
	
	private String name;
	
	private MaterialClass materialClass;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_MATERIAL_CLASS")
	@Column(name="ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="CODE",length=30)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="NAME",length=250)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PARENT_ID")
	public MaterialClass getMaterialClass() { 
		return materialClass;
	}

	public void setMaterialClass(MaterialClass materialClass) {
		this.materialClass = materialClass;
	}
	
	
	
	

}
