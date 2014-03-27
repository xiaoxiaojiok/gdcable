package com.gdcable.epm.controller.common.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.accentrue.gd_net.hibernate.HibernateUtils;
import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.entity.Mcode;
import com.gdcable.epm.entity.Unit;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.publics.StringView;
import com.gdcable.epm.servicre.mcode.McodeService;
import com.gdcable.epm.servicre.unit.UnitService;
import com.gdcable.epm.servicre.user.UserService;


/**
 * Title: 		机构管理
 * Project: 	gd_net
 * Author:		殷俊
 * Copyright: 	Copyright (c) 2013
 * Company:		
 */
@Controller
@RequestMapping(value = "/unit")
public class UnitController
{
	@Resource
	private UnitService unitService;
	@Resource
	private UserService accountService;
	@Autowired
	private McodeService mcodeService;
	
	/**
	 * 机构管理首页
	 * 
	 * @return
	 */
	@RequiresPermissions("unit:view")
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		User user = accountService.getUser(request);
		//机构列表（ 有级别关系）
		List<Unit> list = unitService.getUnitList(user);
		//机构列表下拉框（ 有级别关系）
		String unitOptions = unitService.unitOptions(list,null,null,null);
		boolean flag = false;
		if(list.size() > 1){
			flag = true;
		}else if(list.size() == 1){
			if(list.get(0).getUnitList().size() > 0){
				flag = true;
			}
		}
		map.put("unitOptions", unitOptions);
		map.put("flag", flag);
		return new ModelAndView("unit/index",map);
	}
	
	/**
	 * 机构管理列表
	 * 
	 * @return
	 */
	@RequiresPermissions("test:query")
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request,Integer page,String unitName,String unitCode,Long pUnit){
		Map<String,Object> map = new HashMap<String,Object>();
		User user = accountService.getUser(request);
		Paging<Unit> p = HibernateUtils.createPaging(request);
		unitService.findAll(unitName,unitCode,pUnit,user,p);
		HibernateUtils.savePaging(request, p);
		return new ModelAndView("unit/list",map);
	}
	
	/**
	 * 机构管理编辑
	 * 
	 * @return
	 */
	@RequiresPermissions("unit:edit")
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(HttpServletRequest request,@PathVariable Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		Unit unit = null;
		if(id!=0){
			unit = unitService.findById(id);
			map.put("unit", unit);
		}
		User user = accountService.getUser(request);
		List<Unit> list = unitService.getUnitList(user);
		String unitOptions = unitService.unitOptions(list,unit == null ? null : unit.getUnit(),null,unit == null ? "00" : unit.getUnit() == null ? "00" : unit.getUnit().getManagerUnitCode());
		List<Mcode> mcodelist = this.mcodeService.findByMtye("JB_M");
		map.put("mcodes", mcodelist);
		map.put("unitOptions", unitOptions);
		return new ModelAndView("unit/edit",map);
	}
	
	/**
	 * 机构管理保存
	 * 
	 * @return
	 */
//	@RequiresPermissions("unit:save")
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request,Unit unit){
		StringView view = new StringView();
		try{
			System.out.println(request.getParameter("topUnit"));
			Unit topUnit = unitService.findByUnitCode(request.getParameter("topUnit"));
			unit.setManagerUnitCode(unit.getUnitCode());
			unit.setDepartmentCode(unit.getUnitCode() + "000");
			unit.setCountryUnitCode(unit.getUnitCode() + "000");
			unit.setUnit(topUnit);
			unitService.save(unit);
			view.setContent("success");
		}catch(Exception ex){
			view.setContent("faild");
			ex.printStackTrace();
		}
		return new ModelAndView(view);
	}
	
	/**
	 * 机构管理单位代码验证是否存在
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkUnitCode")
	public ModelAndView checkUnitCode(HttpServletRequest request,String unitCode){
		Unit unit = unitService.findByUnitCode(unitCode);
		StringView view = new StringView();
		if(unit == null){
			view.setContent("notExsit");
		}else{
			view.setContent(String.valueOf(unit.getId()));
		}
		return new ModelAndView(view);
	}
	
	
	/**
	 * 机构管理删除
	 * 
	 * @return
	 */
//	@RequiresPermissions("unit:delete")
	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request,String ids){
		StringView view = new StringView();
		try{
			if(StringUtils.isNotEmpty(ids)){
				String arr[] = ids.split(",");
				for (String string : arr)
				{
					Unit unit = unitService.findById(new Long(string));
					unitService.delete(unit.getId());
				}
 			}
			view.setContent("success");
		}catch(Exception ex){
			view.setContent("faild");
			ex.printStackTrace();
		}
		return new ModelAndView(view);
	}
	
	/**
	 * 机构管理启用 /禁用
	 * 
	 * @return
	 */
//	@RequiresPermissions("unit:setup")
	@RequestMapping(value = "/setup")
	public ModelAndView setup(Long id,Integer status){
		StringView view = new StringView();
		try{
			Unit unit = unitService.findById(id);
			unit.setStatus(status);
			
			unitService.save(unit);
			view.setContent("success");
		}catch(Exception ex){
			view.setContent("faild");
			ex.printStackTrace();
		}
		return new ModelAndView(view);
	}
//
//	/**
//	 * 机构管理导出机构信息
//	 * 
//	 * @return
//	 */
//	@RequiresPermissions("unit:export")
//	@RequestMapping(value = "/export")
//	public void export(HttpServletRequest request,HttpServletResponse response,String unitName,String unitCode,Long pUnit){
//		Map<Object,Object> map = new HashMap<Object,Object>();
//		try{
//			
//			User user = accountService.getUser(request);
//			Page<Unit> paginate = unitService.findAll(unitName,unitCode,pUnit,user,pageable);
//			
//			String [] column = {"id","unitName","dwdm","dh","lxr","unit.unitName"};
//			String [] tabHead = {"ID","机构名称","机构代码","联系电话","联系人","上级单位"};
//			HSSFWorkbook workbook = ExcelManager.exportNormal(paginate.getContent(), column, tabHead);
//			OutputStream out = response.getOutputStream();
//	        response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode("excel_unit_info.xls", "UTF-8"));
//	        response.setContentType("application/msexcel;charset=UTF-8");
//	        workbook.write(out);
//	        out.flush();
//	        out.close(); 
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}

	
}
