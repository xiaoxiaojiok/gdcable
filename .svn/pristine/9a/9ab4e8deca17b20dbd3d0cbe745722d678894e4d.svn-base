package com.gdcable.epm.servicre.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gdcable.epm.dao.base.BaseDao;

/**
 * 
 * <pre>
 * 基础service实现类
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-9
 */
@Component
@Transactional(readOnly=true)
public class BaseService 
{
	@Autowired
	@Qualifier("baseDaoImpl")
	private BaseDao baseDao;

	@Transactional(readOnly=false)
	public boolean delete(Object obj)
	{
		return baseDao.delete(obj);
	}

	public <T>List<T> findAll(Class<T> cls)
	{
		return baseDao.findAll(cls);
	}

	public <T>T findById(long id, Class<T> cls)
	{
		return baseDao.findById(id, cls);
	}

	@Transactional(readOnly=false)
	public boolean save(Object obj)
	{
		return baseDao.save(obj);
	}

	@Transactional(readOnly=false)
	public boolean update(Object obj)
	{
		return baseDao.update(obj);
	}

}
