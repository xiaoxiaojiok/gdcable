package com.gdcable.epm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gdcable.epm.publics.Constants;

@Entity
@Table(name = "PM_LABOR",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_LABOR", sequenceName = "PM_LABOR_SEQ", allocationSize = 1)
public class Labor implements Serializable {
	
	private Long id;
	
	private String code;
	
	private String type;
	
	private String Description;
	
	private String unit;
	
	private double price;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_LABOR")
	@Column(name="LABOR_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="LABOR_CODE",length=30)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="TYPE",length=50)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="DESCRIPTION",length=250)
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Column(name="UNIT",length=250)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

}
