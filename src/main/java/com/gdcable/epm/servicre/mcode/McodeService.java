package com.gdcable.epm.servicre.mcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gdcable.epm.dao.mcode.McodeDao;
import com.gdcable.epm.entity.Mcode;
import com.gdcable.epm.servicre.base.BaseService;

/**
 * 
 * <pre>
 * 码表处理service
 * </pre>
 * @author 殷俊
 * @version 1.0, 2013-9-12
 */
@Component
@Transactional(readOnly=true)
public class McodeService extends BaseService
{
	@Autowired
	private McodeDao mcode;
	
	/**
	 * 
	 * <pre>
	 * 根据码值类型获取码集合
	 * </pre>
	 * @return
	 */
	public List<Mcode> findByMtye(String mtype){
		return (List<Mcode>) this.mcode.findByMtype(mtype);
	}

}
