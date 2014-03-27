<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<jsp:include page="/static/common/common.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/static/common/import_js.jsp" %>

<title>登录</title>
<script type="text/javascript">
function tosubmit(){
	$('#loginFrom').submit();
}

</script>

</head>

<body style="background-color:white; padding:0px; margin:0px;">
<div style="width:100%;">
<!--顶部的天空-->
<div style="height:125px; width:100%;background-image:url(/static/gdstyle/image/sky.png);"></div>


<div style="width:750px; background-image:url(/static/gdstyle/image/login.jpg); background-repeat:no-repeat;
 margin-left:250px; padding-top:330px; padding-left:280px;">
 <form action="" method="post" id="loginFrom">
<span style="">
 <span style="font-size:16px;">用户名：</span>
 <select class="easyui-combobox" name="username" style="width:130px;">
			  <option  value="admin">admin</option>
			  <option value="项目经理">项目经理</option>
			  <option value="分公司规建室领导">分公司规建室领导</option>
			  <option value="分公司管理领导">分公司管理领导</option>
			  <option value="省建设管理室领导">省建设管理室领导</option>
			  <option value="省建设运维部领导">省建设运维部领导</option>
			  <option value="系统管理员">系统管理员</option>
			  <option value="设计单位">设计单位</option>
			  <option value="监理单位">监理单位</option>
			  <option value="施工单位">施工单位</option>
		
	</select>
 <span style="font-size:16px; margin-left:10px;">密码：</span>
 <input class="easyui-validatebox" type="password" name="password" 
 data-options="required:true" style="width:100px;"/>
 <a href="javascript:tosubmit();" class="easyui-linkbutton" style="margin-left:5px;">登录</a>
</span>
</form>
<div style="padding-top:100px; padding-bottom:10px;">
广东省广播电视网络有限公司
</div>

</div>

<div style="width:100%; height:6px; background-image:url(/static/gdstyle/image/small_line1.png); background-repeat:repeat;"></div>

</div>
</body>
</html>
