package com.gdcable.epm.util;

import com.gdcable.epm.entity.Unit;


public class CodeUnit
{
	
	/**
	 * 以下算法通用于六位的码（管理单位代码、地区码等）
	 * 计算gldwdm截取的位数
	 * @param gldwdm
	 * @return
	 */
	public static int getGldwdmSubLength(String gldwdm) {
		String dwLevel = getDwLevel(gldwdm);

		if (dwLevel == Unit.SHENG) {
			return 2;
		} else if (dwLevel == Unit.CITY) {
			return 4;
		} else if (dwLevel == Unit.COUNTY) {
			return 6;
		} else {
			return gldwdm.length();
		}
	}
	/**
	 *  以下算法通用于六位的码（管理单位代码、地区码等）
	 * 根据单位代码得到机构等级（市，县，镇，学校）
	 * @param gjdwdm
	 * @return
	 */
	public static String getDwLevel(String gldwdm) {
		int length = gldwdm.length();
		if (gldwdm.substring(2, length).equals("0000000")) {
			return Unit.SHENG;
		} else if (gldwdm.substring(4, length).equals("00000")) {
			return Unit.CITY;
		} else if (gldwdm.substring(6, length).equals("000")) {
			return Unit.COUNTY;
		} else {
			return Unit.TOWN;
		}
	}
	
	/**
	 * 
	 * 
	 * @param gldwdm
	 * @param JB_M
	 * @return
	 */
	public static String getGldwdmPrefixByUpAndLevel(String gldwdm,String JB_M){
		if(gldwdm != "" && !"".equals(gldwdm)){
			if("0000000".equals(gldwdm.substring(2))){
				gldwdm = gldwdm.substring(0,2);
			}else
			if("00000".equals(gldwdm.substring(4))){
				gldwdm = gldwdm.substring(0,4);
			}else
			if("000".equals(gldwdm.substring(6))){
				gldwdm = gldwdm.substring(0,6);
			}else{
				
			}
		}
		return gldwdm;
	}
}
