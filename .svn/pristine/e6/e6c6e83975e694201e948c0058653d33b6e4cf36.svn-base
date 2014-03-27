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


@Entity
@Table(name = "PM_PROJ_PARTNER",schema=Constants.GD_NET)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "PM_PROJ_PARTNER", sequenceName = "PM_PROJ_PARTNER_SEQ", allocationSize = 1)
public class ProjPartner implements Serializable{
	
	private Long id;
	
	private String Description;
	
	private User name;
	
	private Mcode type;
	
	private String address;
	
	private Organizations organization;
	
	private Mcode status;
	
	private double rate;
	
	private User createdBy;
	
	private Date createdDate;
	
	private User lastUpdatedBy; 
	
	private Date lastUpdateDate;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PM_PROJ_PARTNER")
	@Column(name="PARTNER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="DESCRIPTION",length=250,nullable=false)
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="NAME")
	public User getName() {
		return name;
	}

	public void setName(User name) {
		this.name = name;
	}

	@OneToOne(cascade = CascadeType.ALL)  
	@JoinColumn(name="PRO_TYPE")  
	public Mcode getType() {
		return type;
	}

	public void setType(Mcode type) {
		this.type = type;
	}

	@Column(name="ADDRESS",length=250)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ORGANIZATION_ID")
	public Organizations getOrganization() {
		return organization;
	}

	public void setOrganization(Organizations organization) {
		this.organization = organization;
	}

	@OneToOne(cascade = CascadeType.ALL)  
	@JoinColumn(name="STATUS")  
	public Mcode getStatus() {
		return status;
	}

	public void setStatus(Mcode status) {
		this.status = status;
	}

	@Column(name="RATE")
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY")
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="CREATION_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="LAST_UPDATED_BY")
	public User getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(User lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name="LAST_UPDATE_DATE")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	

}
