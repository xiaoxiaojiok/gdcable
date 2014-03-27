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
@Table(name = "PM_MATERIAL",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_MATERIAL", sequenceName = "PM_MATERIAL_SEQ", allocationSize = 1)
public class Material implements Serializable{
	
	private Long id;
	
	private String code;
	
	private String name;
	
	private Date importDate;
	
	private MaterialClass MaterialClass;
	
	private String type;
	
	private String unit;
	
	private Organizations organization;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_MATERIAL")
	@Column(name="MATERIAL_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="MATERIAL_CODE",length=20)
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

	@Column(name="IMPORT_DATE")
	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TYPE")
	public MaterialClass getMaterialClass() {
		return MaterialClass;
	}

	public void setMaterialClass(MaterialClass materialClass) {
		MaterialClass = materialClass;
	}

	@Column(name="MAT_TYPE",length=20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="UNIT",length=250)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ORGANIZATION_ID")
	public Organizations getOrganization() {
		return organization;
	}

	public void setOrganization(Organizations organization) {
		this.organization = organization;
	}
	
	


}
