package com.gdcable.epm.servicre.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accentrue.gd_net.hibernate.Paging;
import com.gdcable.epm.dao.menu.MenuDao;
import com.gdcable.epm.dao.permission.PermissionDao;
import com.gdcable.epm.entity.Menu;
import com.gdcable.epm.entity.Permission;
import com.gdcable.epm.entity.Role;
import com.gdcable.epm.entity.User;
import com.gdcable.epm.servicre.base.BaseService;
import com.gdcable.epm.util.RandomUtil;

/**
 * 
 * <pre>
 * 菜单管理SERVICE实现类
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-8
 */
@Component
@Transactional(readOnly = true)
public class MenuService extends BaseService {
	
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private PermissionDao permissionDao;
	
	private PageRequest sort;
	
	public Menu findById(Long id){
		return menuDao.FindById(id);
	}
	
	public Menu findByMenuName(String menuName){
		return menuDao.findByMenuName(menuName);
	}
	
	public void findByParams(String menuName,int status,Long pMenu,Paging<Menu> p){
		 menuDao.findByParams(menuName,status,pMenu,p);
	}
	
	@Transactional(readOnly = false)
	public void save(Menu menu){
		if(menu.getId()==null || menu.getId()==0){
			menuDao.save(menu);
		}else{
			menuDao.update(menu);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id){
		Menu menu  = menuDao.FindById(id);
		menuDao.delete(menu);
	}
	
	public void deleteSubMenus(List<Menu> list){
		for (Menu menu : list)
		{
			this.deleteSubMenus(menu.getSubMenuList());
			this.delete(menu.getId());
		}
	}
	
	public List<Menu> getMenuListByCurrUser(User user){
		sort =  new PageRequest(0, 9999, Sort.Direction.ASC, "orderNum,id");
		String roleIds="";
		List<Role> roleList = user.getRoleList();
		if(roleList == null || roleList.isEmpty()){
			roleIds +="1";
		}else{
			for(Role role:roleList){
				roleIds +=role.getId()+",";
			}
			if(roleIds.length()>=2){
				roleIds = roleIds.substring(0,roleIds.length()-1);
			}
		}
		List<Permission> list = permissionDao.getPermissionByRoles(roleIds);
		List<Long> menuIds = new ArrayList<Long>();
		for (Permission per : list){
			menuIds.add(Long.parseLong(per.getPermValue()));
		}
		List<Menu> listMenu = menuDao.getMenuListByLevel(1,sort).getContent();
		//获取其下所有子菜单
		for (Menu menu : listMenu){
			List<Menu> li = this.getSubMenuListByPMenu(menu);
			menu.setSubMenuList(li);
		}
		for (Iterator<Menu> it = listMenu.iterator() ; it.hasNext();){
			int menuCount = 0;
			Menu menu = it.next();
			if(menu.getHasNext() == 1){
				if(menu.getStatus() == 1){
					menuCount = constructionMenuTree(menu,menuIds,menuCount);
					if(menuCount == 0){
						menu.setStatus(0);
					}
				}else{
					//it.remove()报错，把当前menu状态设置禁用，页面一样不显示
					menu.setStatus(0);
				}
			}else{
				if(!menuIds.contains(menu.getId())){
					//it.remove()报错，把当前menu状态设置禁用，页面一样不显示
					menu.setStatus(0);
				}
			}
		}
		return listMenu;
	}
	
	public int constructionMenuTree(Menu menu,List<Long> menuIds,int menuCount){
		for (Iterator<Menu> it = menu.getSubMenuList().iterator() ; it.hasNext();){
			Menu m = it.next();
			if(m.getStatus() != 0){
				if(!menuIds.contains(m.getId())){
					//it.remove()报错，把当前menu状态设置禁用，页面一样不显示
					m.setStatus(0);
				}else{
					menuCount++;
					menuCount = constructionMenuTree(m,menuIds,menuCount);
				}
			}
		}
		//StringUtil.debug(menuCount);
		return menuCount;
	}
	
	public List<Menu> getMenuListByLevel(int menuLevel){
		sort =  new PageRequest(0, 9999, Sort.Direction.ASC, "orderNum,id");
		List<Menu> relist = new ArrayList<Menu>();
		List<Menu> list = menuDao.getMenuListByLevel(menuLevel,sort).getContent();
		//获取其下所有子菜单
		for (Menu menu : list){
			List<Menu> sublist = this.getSubMenuListByPMenu(menu);
			System.out.println(sublist.size());
			menu.setSubMenuList(sublist);
			relist.add(menu);
		}
		return relist;
	}
	
	public List<Menu> getSubMenuListByPMenu(Menu pMenu){
		sort =  new PageRequest(0, 9999, Sort.Direction.ASC, "orderNum,id");
		List<Menu> list = menuDao.getSubMenuListByPMenu(pMenu.getId() ,sort).getContent();
		if(list.size() == 0){
			pMenu.setHasNext(0);	//没有下级菜单
		}else{
			pMenu.setHasNext(1);
			//获取其下所有子菜单
			for (Menu menu : list){
				List<Menu> subList =this.getSubMenuListByPMenu(menu);
				menu.setSubMenuList(subList);
			}
		}
		return list;
	}
	
	public String getJsonStringForZTree(List<Menu> list,String jsonString){
		for (Menu menu : list){
			jsonString += "{";
			jsonString += "id : " + menu.getId() + ",";			
			jsonString += "name : '" + menu.getMenuName() + "'" + ",";
			jsonString += "status : " + menu.getStatus() +  ",";
			jsonString += "orderNum : " + menu.getOrderNum()  + ",";
			jsonString += "menuLevel : " + menu.getMenuLevel()  + ",";
			jsonString += "hasNext : " + menu.getHasNext()  + ",";
			jsonString += "pMaxNum : " + list.size()  + ",";
			jsonString += "maxNum : " + menu.getSubMenuList().size()  + ",";
			
			if(menu.getStatus() == 0){
				jsonString += "font : {'color' : '#c6c6c6'},";
			}
			
			if(menu.getMenuLevel() == 1){
				if(menu.getHasNext() == 1){
					jsonString += "pId : 0,";
					jsonString += "open : true,";
					jsonString += "menuUrl : '',";
					jsonString += "iconSkin : 'pIcon01'";
				}else{
					jsonString += "pId : 0,";
					jsonString += "open : true,";
					jsonString += "menuUrl : '',";
					jsonString += "iconSkin : 'icon0" + RandomUtil.getInstance().randomInt(2,8) + "'";
				}
			}else{
				if(menu.getHasNext() == 1){
					jsonString += "pId : " + menu.getMenu().getId() + ",";
					jsonString += "menuUrl : '" + menu.getMenuUrl() + "',";
					jsonString += "iconSkin : 'pIcon01'";
				}else{
					jsonString += "pId : " + menu.getMenu().getId() + ",";
					jsonString += "menuUrl : '" + menu.getMenuUrl() + "',";
					jsonString += "iconSkin : 'icon0" + RandomUtil.getInstance().randomInt(2,8) + "'";
				}
			}
			jsonString += "},";
			
			if(menu.getHasNext() == 1){
				jsonString = this.getJsonStringForZTree(menu.getSubMenuList(), jsonString);
			}
		}
		return jsonString;
	}
	
	public List<Menu> findAll()
	{
		return (List<Menu>) menuDao.findAll(Menu.class);
	}
	
	/**
	 * 菜单列表下拉框（ 有级别关系）
	 * 
	 * @param list
	 * @return
	 */
	public String menuOptions(List<Menu> list,Menu m){
		StringBuffer sbf = new StringBuffer();
		String blanks = "";
		String subOptions = "";
		for (Menu menu : list){
			blanks = getLevelBlank(menu.getMenuLevel());
			sbf.append("<option value='");
			sbf.append(menu.getId());
			sbf.append("'");
			if(m != null){
				if(m.getId() == menu.getId()){
					sbf.append(" selected");
				}
			}
			sbf.append(">");
			sbf.append(blanks);
			sbf.append(menu.getMenuName());
			sbf.append("</option>");
			if(menu.getSubMenuList().size() != 0){
				subOptions = menuOptions(menu.getSubMenuList(),m);
			}
			sbf.append(subOptions);
		}
		return sbf.toString();
	}
	
	/**
	 * 根据等级获取下拉框名称缩进的空格
	 * 
	 * @return
	 */
	public String getLevelBlank(Integer level){
		if(level == null || level == 0){
			level = 0;
		}
		int count = level * 2;
		String blank = "";
		for (int i = 0;i < count ;i++){
			blank += "&nbsp;&nbsp;&nbsp;";
		}
		return blank;
	}
	
	/**
	 * 重组菜单
	 * 
	 * @param list
	 */
	public List<Menu> constructMenuList(List<Menu> list){
		if(list==null){
			return null;
		}
		List<Menu> menuList = new ArrayList<Menu>();
		int minLevel = 0;
		for (int i = 0 ; i < list.size(); i++){
			Menu menu = list.get(i);
			if(i == 0) minLevel = menu.getMenuLevel();
			if(menu.getMenuLevel() < minLevel){
				minLevel = menu.getMenuLevel();
			}
		}
		for (Menu menu1 : list){
			List<Menu> subMenuList = new ArrayList<Menu>();
			for (Menu menu2 : list){
				if(!menu1.getId().equals(menu2.getId())){
					if(menu2.getMenu()==null)continue;
					if(menu2.getMenu().getId().equals(menu1.getId())){
						subMenuList.add(menu2);
					}
				}
			}
			menu1.setSubMenuList(subMenuList);
			if(menu1.getMenuLevel() <= minLevel){
				menuList.add(menu1);
			}
		}
		menuList = constructSubMenuToList(menuList);
		return menuList;
	}
	
	/**
	 * 根据当前菜单获取其下一级别子菜单
	 * 
	 * @param pid
	 * @param status
	 * @return
	 */
	public List<Menu> constructSubMenuToList(List<Menu> list){
		List<Menu> menuList = new ArrayList<Menu>();
		String blanks = "";
		for (Menu menu : list){
			blanks = getLevelBlank(menu.getMenuLevel());
			menu.setMenuName(blanks + menu.getMenuName());
			menuList.add(menu);
			if(menu.getSubMenuList().size() > 0){
				menuList.addAll(constructSubMenuToList(menu.getSubMenuList()));
			}
		}
		return menuList;
	}
}
