<%@ page contentType="text/html;charset=UTF-8"%>
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
<title>机构管理</title>
<link type="text/css" rel="stylesheet" href="${ctx }/static/css/table.css"/>
<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/base/base.js"></script>
<script type="text/javascript" src="${ctx }/static/js/syspurview/unit/unit.js"></script>

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
		query();
	});
</script>
</head>
<body>
	<div id="head">
	<div class="weizhi">你的位置是：数据管理 >> 机构管理</div>
		<table class="biaoge_1" style="margin-top:10px;">
			<tr>
				<td class="biaoge_11">查询条件</td>
				<td class="biaoge_12">
					上级机构： <select name="p_unit" id="p_unit" onchange="query();">
									<c:if test="${flag }">
										<option value="0">---请选择---</option>
									</c:if>
									${unitOptions }
							 </select> 
							 &nbsp;&nbsp;&nbsp;&nbsp;
					机构名称： <input class="text_1" name="name" id="name" type="text" /> 
					机构代码： <input class="text_1" name="unitCode" id="unitCode" type="text" /> 
				</td>
			</tr>
			<tr>
				<td class="biaoge_11">操作</td>
				<td class="biaoge_12">
					<input type="button" class="button_1" name="" value="查询" onclick="query();"/>
					<input type="button" class="button_1" name="" value="添加" onclick="edit(0);"/>
					<input type="button" class="button_1" name="" value="删除" onclick="delMore();"/>
					<input type="button" class="button_1" name="" value="批量导入" onclick="importUnit();"/>
					<input type="button" class="button_1" name="" value="批量导出" onclick="exportUnit();"/>
				</td>
			</tr>
		</table>
	</div>
	<div id="list" style="margin-top:20px;"></div>
	<div id="edit"></div>
</body>
</html>
