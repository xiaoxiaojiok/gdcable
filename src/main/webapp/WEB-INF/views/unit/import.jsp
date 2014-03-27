<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构管理 -- 批量导入</title>
<link type="text/css" rel="stylesheet" href="${ctx }/static/css/table.css"/>
<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/base/base.js"></script>
<script type="text/javascript" src="${ctx }/static/js/syspurview/unit/unit.js"></script>
<script type="text/javascript" src="${ctx }/static/js/base/ajaxupload.js"></script>
<script type="text/javascript">
	function chg(id_num){
		var oa = document.getElementById(id_num);
		var ob = document.getElementById("grade");
		if(oa.style.display == "block"){
		   oa.style.display = "none";
		}else{
		   oa.style.display = "block";
		}
	}
	var baseUrl = "${ctx}/unit";
	$(document).ready(function(){
			base.initUpload2(baseUrl + '/importSave','ubtn1','xls',1,function(filename,result){
				$("#importResultTd").html(result);
			});
	});
	
</script>
</head>
<body>
	<div id="head">
	<div class="weizhi">你的位置是：数据管理 >> 机构管理 >> 批量导入</div>
		<table class="biaoge_1" style="margin-top:10px;">
			<tr>
				<td class="biaoge_11">下载模板</td>
				<td class="biaoge_12"><input type="button" class="button_1" name="" value="下载模板" onclick="base.downloadTemplate('${ctx }/static/template/unit_template.xls','机构导入模板.xls');"/></td>
			</tr>
			<tr>
				<td class="biaoge_11">批量导入</td>
				<td class="biaoge_12"><input type="button" class="button_1" id="ubtn1" name="" value="选择文件" onclick=""/><input type="button" class="button_1" name="" value="返回" onclick="history.go(-1)"/></td>
			</tr>
		</table>
	</div>
	<div id="importResult" style="margin-top:20px;">
		<table class="biaoge_3" style="margin-top:10px;">	
			<th colspan="4" class="t_title t_c" style="text-align:left;">导入结果：</th>
			<tr>
				<td class="biaoge_33" style="width:35%" colspan="4" id="importResultTd" name="importResultTd" align="center"></td>
			</tr>	
		</table>
	</div>
</body>
</html>
