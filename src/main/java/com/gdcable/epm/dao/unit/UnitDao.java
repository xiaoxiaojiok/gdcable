package com.gdcable.epm.dao.unit;

import java.util.Collection;
import java.util.List;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.base.BaseDao;
import com.gdcable.epm.entity.Unit;
import com.gdcable.epm.entity.User;

/**
 * 
 * <pre>
 * 单位管理dao
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-13
 */
public interface UnitDao extends BaseDao
{

	/**
	 * 
	 * <pre>
	 * 根据单位代码获取单位
	 * </pre>
	 * @param unitCode
	 * @return
	 */
	public Unit findByUnitCode(String unitCode);
	
	/**
	 * 根据gjdwdm得到机构列表(自定义区域也包含进来)
	 * @param gldwdm 管理单位代码
	 * @return
	 */
	public List<?> getDwList(String gldwdm);
	
	/**
	 * 第一级机构列表 -- 所有机构
	 * 
	 * @return
	 */
	//@Query("FROM Unit u WHERE (u.unit is null or u.unit = '') and u.status != 0")
	public List<Unit> getTopUnitList();
	
	/**
	 *  根据当前机构获取其下一级别子机构
	 * 
	 * @param pid
	 * @return
	 */
//	@Query("FROM Unit u WHERE u.unit.id = ?1 and u.status != 0")
	Collection<Unit> getSubUnitListByPUnit(Long pid);
	
	/**
	 * 
	 * <pre>
	 * 根据条件查询单位
	 * </pre>
	 * @param unitName
	 * @param unitCode
	 * @param pUnit
	 * @param user
	 * @param pageable
	 */
	public void findAll(final String unitName,final String unitCode,final Long pUnit,final User user,Paging<Unit> p);
	
	/**
	 * 
	 * <pre>
	 * 根据机构id获取机构集合（去重复）
	 * </pre>
	 * @return
	 */
	public Collection<Unit> getUnitByIds(Collection<Long> unitids);

}
