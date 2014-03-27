/**
 * <pre>
 * Title: 		SchoolService.java
 * Project: 	qtypt
 * Type:		cn.qtone.qtypt.service.unit.SchoolService
 * Author:		Administrator
 * Create:	 	2012-11-15 上午11:48:15
 * Copyright: 	Copyright (c) 2012
 * Company:		
 * <pre>
 */
package com.gdcable.epm.servicre.unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.unit.UnitDao;
import com.gdcable.epm.entity.Unit;
import com.gdcable.epm.entity.User;

/**
 * 
 * <pre>
 * 单位管理service
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-11
 */
@Component
@Transactional(readOnly = true)
public class UnitService{
	
	@Autowired
	private UnitDao unitDao;
	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public Unit findById(Long id){
		return (Unit) unitDao.findById(id, Unit.class);
	}

	/**
	 * 根据单位代码查询
	 * 
	 * @param unitCode
	 * @return
	 */
	public Unit findByUnitCode(String unitCode){
		return unitDao.findByUnitCode(unitCode);
	}
	
	/**
	 * 删除机构
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(Long id) throws Exception{
		Unit unit = (Unit) unitDao.findById(id, Unit.class);
		unitDao.delete(unit);
	}
	
	/**
	 * 保存/更新
	 * 
	 * @param unit
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public void save(Unit unit) throws Exception{
		if(unit.getId()!=null){
			unitDao.update(unit);
		}else{
			unitDao.save(unit);
		}
		
	}

	
	/**
	 *  获取单位下拉(下拉值为id)
	 * @param dwId
	 * @param xxId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getDwSelect(long dwId, long selected) {
		Unit dw = (Unit) unitDao.findById(dwId, Unit.class);
		String gldwdm = dw.getManagerUnitCode();

		List<?> list = unitDao.getDwList(gldwdm);
		if (list == null)
			return null;

		StringBuffer sb = new StringBuffer();		
		Iterator<Object[]> it = (Iterator<Object[]>) list.iterator();
		while(it.hasNext()){
			Object [] arr = it.next();
			String key = String.valueOf(arr[0]);
			sb.append("<option value='").append(key).append("'");
			if (key.equals(selected + ""))
				sb.append(" selected");
			sb.append(">");
			if (list.size() > 1) {
				for (int i = 0; i < Integer.parseInt(String.valueOf(arr[2])) - 2; i++)
					sb.append("&nbsp;&nbsp;&nbsp;");
			}
			sb.append(String.valueOf(arr[1])).append("</option>");
		}
		
		return sb.toString();
	}
	
	/**
	 * 获得机构 及其下属的所有机构
	 */
	@SuppressWarnings("unchecked")
	public List<Unit> getUnitAndAllSonUnitByUnitId(Long dwId){
		List<Unit> unitList = new ArrayList<Unit>();
		
		Unit dw = (Unit) unitDao.findById(dwId, Unit.class);
		String gldwdm = dw.getManagerUnitCode();
		List<?> list = unitDao.getDwList(gldwdm);
		if (list == null)
			return unitList;	
		Iterator<Object[]> it = (Iterator<Object[]>) list.iterator();
		while(it.hasNext()){
			Object [] arr = it.next();
			Long key = Long.valueOf(String.valueOf(arr[0]));
			unitList.add(this.findById(key));					
		}
		return unitList;
	}
	
	/**
	 * 获得机构 及其下属的所有机构IDs
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getUnitIdAndAllSonUnitIdsByUnitId(Long dwId){
		List<Long> unitIdsList = new ArrayList<Long>();		
		Unit dw = (Unit) unitDao.findById(dwId, Unit.class);
		String gldwdm = dw.getManagerUnitCode();
		List<?> list = unitDao.getDwList(gldwdm);
		if (list == null)
			return unitIdsList;	
		Iterator<Object[]> it = (Iterator<Object[]>) list.iterator();
		while(it.hasNext()){
			Object [] arr = it.next();
			Long key = Long.valueOf(String.valueOf(arr[0]));
			unitIdsList.add(key);
			//unitList.add(this.findById(key));					
		}
		return unitIdsList;
	}
	
	/**
	 * 机构列表（ 有级别关系）
	 * 
	 * @return
	 */
	public List<Unit> getUnitList(User user){
		List<Unit> toplist = new ArrayList<Unit>();
		if(user.getSuperManager() == 1){	//超管
			//上级单位为空时 该单位是第一级
			toplist = unitDao.getTopUnitList();
		}else{
			toplist.add(user.getUnitId());
		}
		//获取所有下级机构
		for (Unit unit : toplist){
			unit.setUnitList((List<Unit>) this.getSubUnitListByPUnit(unit));
		}
		return toplist;
	}
	
	
	
	/**
	 *  根据当前机构获取其下一级别子机构
	 *  
	 */
	public Collection<Unit> getSubUnitListByPUnit(Unit pUnit){
		Collection<Unit> ulist = unitDao.getSubUnitListByPUnit(pUnit.getId());
		//获取其下所有子菜单
		for (Unit unit : ulist){
			unit.setUnitList((List<Unit>)getSubUnitListByPUnit(unit));
		}
		return ulist;
	}
	
	/**
	 * 机构列表下拉框（ 有级别关系）
	 * 
	 * @param list
	 * @return
	 */
	public String unitOptions(List<Unit> list,Unit obj,String dwdm,String gldwdm){
		StringBuffer sbf = new StringBuffer();
		String blanks = "";
		String subOptions = "";
		for (Unit unit : list){
			blanks = getLevelBlank(unit.getUnitLevel());
			sbf.append("<option value='");
			if(gldwdm == null || "".equals(gldwdm)){	 
				if(dwdm == null || "".equals(dwdm)){	//如果gldwdm、dwdm都没有值，那么options里的value放ID值
					sbf.append(unit.getId()+"'");
					if(obj != null){
						if(obj.getId() == unit.getId()){
							sbf.append(" selected ");
						}
					}
				}else{	//如果传过来的参数dwdm有值，那么options里的value放dwdm值
					sbf.append(unit.getDepartmentCode()+"'");
					if(unit.getDepartmentCode().equals(dwdm)){
						sbf.append(" selected ");
					}
				}
			}else{	//如果传过来的参数gldwdm有值，那么options里的value放gldwdm值
				sbf.append(unit.getManagerUnitCode()+"'");
				if(unit.getManagerUnitCode().equals(gldwdm)){
					sbf.append(" selected ");
				}
			}
			sbf.append(">");
			sbf.append(blanks);
			sbf.append(unit.getUnitName());
			sbf.append("</option>");
			if(unit.getUnitList().size() != 0){
				subOptions = unitOptions(unit.getUnitList(),obj,dwdm,gldwdm);
			}
			sbf.append(subOptions);
		}
		return sbf.toString();
	}
	
	/**
	 * 机构列表下拉框（ 有级别关系）
	 * 
	 * @param list
	 * @return
	 */
	public String roleUnitOptions(List<Unit> list){
		StringBuffer sbf = new StringBuffer();
		for (Unit unit : list){
			sbf.append("<option value='");
			sbf.append(unit.getId()+"'");
			sbf.append(">");
			sbf.append(unit.getUnitName());
			sbf.append("</option>");
		}
		return sbf.toString();
	}
	
	/**
	 * 根据等级获取下拉框机构名称缩进的空格
	 * 
	 * @return
	 */
	public String getLevelBlank(String level){
		if(level == null || "".equals(level)){
			level = "1";
		}
		int count = Integer.parseInt(level) - 1;
		String blank = "";
		for (int i = 0;i < count ;i++){
			blank += "&nbsp;&nbsp;&nbsp;";
		}
		return blank;
	}

	/**
	 * 根据条件查询机构列表
	 * 
	 * @param unitName
	 * @param unitCode
	 * @param pUnit
	 * @param pageable
	 * @return
	 */
	public void findAll(final String unitName,final String unitCode,final Long pUnit,final User user,Paging<Unit> p){
		unitDao.findAll(unitName, unitCode, pUnit, user, p);
	}
	
	/**
	 * 
	 * <pre>
	 * 根据机构id获取机构集合（去重复）
	 * </pre>
	 * @return
	 */
	public List<Unit> getUnitByIds(List<Unit> unitList){
		Collection<Long> unitids = new ArrayList<Long>();
		for(Unit u:unitList){
			unitids.add(u.getId());
		}
		Collection<Unit> ulist = this.unitDao.getUnitByIds(unitids);
		List<Unit> relist = new ArrayList<Unit>();
		for(Unit unit:ulist){
			relist.add(unit);
		}
		return relist;
	}
}
