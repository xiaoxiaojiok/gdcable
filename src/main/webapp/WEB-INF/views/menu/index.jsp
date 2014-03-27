<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单管理</title>
<link type="text/css" rel="stylesheet" href="${ctx }/static/css/table.css"/>
<script type="text/javascript" src="${ctx}/static/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/base/base.js"></script>
<script type="text/javascript" src="${ctx }/static/js/syspurview/left/menu.js"></script>

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
	var baseUrl = "${ctx}/menu";
	$(document).ready(function(){
		query();
	});
</script>
</head>
<body>
	<div id="head">
	<div class="weizhi">你的位置是：系统管理>>菜单管理</div>
		<table class="biaoge_1" style="margin-top:10px;">
			<tr>
				<td class="biaoge_11">查询条件</td>
				<td class="biaoge_12">
					上级菜单： <select name="p_menu" id="p_menu" onchange="query();">
									<option value="0">---请选择---</option>
									${menuOptions }
							 </select> 
							 &nbsp;&nbsp;&nbsp;&nbsp;
					菜单名称： <input class="text_1" id="menu_name" type="text" /> 
					菜单状态： 
						 <select name="menu_status" id="menu_status" onchange="query();">
								<option value="-1">---请选择---</option>
								<option value="1">正常</option>
								<option value="0">禁用</option>
						</select> 
				</td>
			</tr>
			<tr>
				<td class="biaoge_11">操作</td>
				<td class="biaoge_12">
					<input type="button" class="button_1" name="" value="查询" onclick="query();"/> 
					<input type="button" class="button_1" name="" value="添加" onclick="edit(0,0);"/>
					<input type="button" class="button_1" name="" value="删除" onclick="delMore();"/>
				</td>
			</tr>
		</table>
	</div>
	<div id="list" style="margin-top:20px;"></div>
	<div id="edit"></div>
</body>
</html>
