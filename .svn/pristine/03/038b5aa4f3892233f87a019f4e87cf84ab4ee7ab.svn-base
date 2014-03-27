/**
 * <pre>
 * Title: 		SelectHTMLUtil.java
 * Project: 	qtypt
 * Type:		cn.qtone.qtypt.util.SelectHTMLUtil
 * Author:		Administrator
 * Create:	 	2013-1-28 下午05:19:16
 * Copyright: 	Copyright (c) 2013
 * Company:		
 * <pre>
 */
package com.gdcable.epm.util;

import java.util.List;


/**
 * 生成html下拉options公用方法
 * 
 */
public class SelectHTMLUtil
{
	
	/**
	 * 根据list生成<option>的html能用方法
	 * 
	 * @param list 需要生成的list
	 * @param optName 需要获取的name的“属性名”
	 * @param optValue 需要获取的value的“属性名”
	 * @param defaultValue value的默认选中的值
	 * @return
	 * @throws Exception
	 */
	public static String buildSelectHTML(List<?> list,String optName,String optValue,String defaultValue) throws Exception{
		StringBuffer sbf = new StringBuffer();
		optName = "get" + SelectHTMLUtil.firstLetterToUpper(optName);
		optValue = "get" + SelectHTMLUtil.firstLetterToUpper(optValue);
		Object mName;
		Object mValue;
		for (Object t : list){
			mName = t.getClass().getMethod(optName).invoke(t);
			mValue = t.getClass().getMethod(optValue).invoke(t);
			if(mName == null){
				mName = "";
			}
			if(mValue == null){
				mValue = "";
			}
			sbf.append("<option value=\"");
			sbf.append(mValue.toString());
			sbf.append("\"");
			if(defaultValue != null){
				if(defaultValue.equals(mValue.toString())){
					sbf.append(" selected");
				}
			}
			sbf.append(">");
			sbf.append(mName.toString());
			sbf.append("</option>");
		}
		return sbf.toString();
	}
	
	/**
	 * 首字母转化成大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstLetterToUpper(String str){  
		  char[] array = str.toCharArray();  
		  array[0] -= 32;  
		  return String.valueOf(array);  
	} 

	
	public static void main(String[] args) throws Exception
	{
//		Lb lb1 = new Lb();
//		
//		lb1.setLbmc("nam1");
//		lb1.setId(1L);
//		
//		Lb lb2 = new Lb();
//		lb2.setLbmc("nam2");
//		lb2.setId(2L);
//		
//		List<Lb> list = new ArrayList<Lb>();
//		list.add(lb1);
//		list.add(lb2);
//		
//		String HTML = SelectHTMLUtil.buildSelectHTML(list, "lbmc", "id", "1");
//		StringUtil.debug(HTML);
	}
	
}
