package com.gdcable.epm.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;

/**
 * 
 * <pre>
 * 基础dao接口
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-8
 */
public interface BaseDao
{
	/**
	 * 
	 * <pre>
	 * 根据id和class得到一条实体
	 * </pre>
	 * @param id
	 * @param cls
	 * @return
	 */
	 <T>T  findById(long id,Class<T> cls);
	
	/**
	 * 
	 * <pre>
	 * 添加一条记录
	 * </pre>
	 * @param obj
	 * @return
	 */
	boolean save(Object obj);
	
	/**
	 * 
	 * <pre>
	 * 更新一条记录
	 * </pre>
	 * @param obj
	 * @return
	 */
	boolean update(Object obj);
	
	/**
	 * 
	 * <pre>
	 * 删除一条记录
	 * </pre>
	 * @param obj
	 * @return
	 */
	boolean delete(Object obj);
	
	/**
	 * 
	 * <pre>
	 * 查询所有记录
	 * </pre>
	 * @param cls
	 * @return
	 */
	<T> List<T> findAll(Class<T> cls);
	
	<T>List<T> getAll(Class<T> cls,Map map);

}
