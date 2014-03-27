package com.gdcable.epm.jbpm.helper.opinion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;

import com.gdcable.epm.jbpm.po.opinion.JbOption;
import com.gdcable.epm.jbpm.vo.opinion.JbOptionVo;

public class JbOptionHelper {
	
	public static List coverPoToVo(List result){
		Iterator it = result.iterator();
		Object[] o;
		JbOptionVo vo = null;
		List li = new ArrayList();
		while(it.hasNext()){
			o=(Object[])it.next();
			HistoryTaskImpl hist = (HistoryTaskImpl) o[0];
			JbOption option = (JbOption) o[1];
			vo = new JbOptionVo();
			vo.setId(option.getId());
			vo.setAssignee(hist.getAssignee());
			vo.setChecktime(option.getChecktime());
			vo.setCreateTime(hist.getCreateTime());
			vo.setEndTime(hist.getEndTime());
			vo.setInstance(option.getInstance());
			vo.setServiceid(option.getServiceid());
			vo.setSuggestion(option.getSuggestion());
			vo.setTaskid(option.getTaskid());
			li.add(vo);
		}
		return li;
	}

}
