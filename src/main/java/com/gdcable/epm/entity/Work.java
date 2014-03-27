package com.gdcable.epm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gdcable.epm.jbpm.vo.JBPMPo;

/**
 * Work entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "int_jb_work")
public class Work extends JBPMPo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8452082871620975482L;
	private Long id;
	private String projid;
	private String proname;
	private String dept;
	private Integer workdate;
	private Integer allfile;
	private Integer donefile;
	private Integer allsum;
	private String description;
	private String man;
	private String phone;

	/** default constructor */
	public Work() {
	}

	/** full constructor */
	public Work(String projid, String proname, String dept, Integer workdate,
			Integer allfile, Integer donefile, Integer allsum, String description,
			String man, String phone) {
		this.projid = projid;
		this.proname = proname;
		this.dept = dept;
		this.workdate = workdate;
		this.allfile = allfile;
		this.donefile = donefile;
		this.allsum = allsum;
		this.description = description;
		this.man = man;
		this.phone = phone;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen")
	@SequenceGenerator(name="gen",sequenceName="t_news")
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "projid", length = 100)
	public String getProjid() {
		return this.projid;
	}

	public void setProjid(String projid) {
		this.projid = projid;
	}

	@Column(name = "proname", length = 200)
	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	@Column(name = "dept", length = 100)
	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "workdate")
	public Integer getWorkdate() {
		return this.workdate;
	}

	public void setWorkdate(Integer workdate) {
		this.workdate = workdate;
	}

	@Column(name = "allfile")
	public Integer getAllfile() {
		return this.allfile;
	}

	public void setAllfile(Integer allfile) {
		this.allfile = allfile;
	}

	@Column(name = "donefile")
	public Integer getDonefile() {
		return this.donefile;
	}

	public void setDonefile(Integer donefile) {
		this.donefile = donefile;
	}

	@Column(name = "allsum")
	public Integer getAllsum() {
		return this.allsum;
	}

	public void setAllsum(Integer allsum) {
		this.allsum = allsum;
	}

	@Column(name = "description", length = 400)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "man", length = 50)
	public String getMan() {
		return this.man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	


	
}