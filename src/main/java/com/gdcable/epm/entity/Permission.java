package com.gdcable.epm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import com.gdcable.epm.publics.Constants;

/**
 * <pre>
 * 权限表
 * </pre>
 * @author 殷俊
 * @version 1.0, 2012-11-13
 */
@Entity
@Table(name = "gn_base_permission",schema=Constants.GD_NET)
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "GN_BASE_PERMISSION", sequenceName = "GN_BASE_PERMISSION_SEQ", allocationSize = 1)
public class Permission implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -857597867811334021L;

	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="GN_BASE_PERMISSION")
	@Column(name="p_perm_id")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * 权限名
	 */
	@NotEmpty
	private String permName;
	
	/**
	 * 权限描述
	 */
	private String descript;
	
	/**
	 * 权限值
	 */
	@NotEmpty
	private String permValue;
	
	/**
	 * 权限类型(区分菜单权限 -1 与功能权限 1 )
	 */
	private int permType;
	
	private Menu menu;

	 
	public List<Role> roleList = new ArrayList<Role>();
	
	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "permissionList", fetch = FetchType.LAZY)
	public List<Role> getRoleList()
	{
		return roleList;
	}

	public void setRoleList(List<Role> roleList)
	{
		this.roleList = roleList;
	}
	
	@Column(name="p_perm_name")
	public String getPermName()
	{
		return permName;
	}

	public void setPermName(String permName)
	{
		this.permName = permName;
	}

	@Column(name="p_descript")
	public String getDescript()
	{
		return descript;
	}

	public void setDescript(String descript)
	{
		this.descript = descript;
	}
	
	@Column(name="p_perm_value")
	public String getPermValue()
	{
		return permValue;
	}

	public void setPermValue(String permValue)
	{
		this.permValue = permValue;
	}

	@Column(name="p_perm_type")
	public int getPermType()
	{
		return permType;
	}

	public void setPermType(int permType)
	{
		this.permType = permType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="p_menu_id")
	public Menu getMenu()
	{
		return menu;
	}

	public void setMenu(Menu menu)
	{
		this.menu = menu;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		final Permission p = (Permission)obj;
		
		if(this.getId() != p.getId())
		{
			return false;
		}
		return true;
	}
	
}
