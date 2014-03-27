<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link type="text/css" rel="stylesheet" href="${ctx }/static/css/table.css"/>
<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/base/base.js"></script>
<script type="text/javascript" src="${ctx }/static/js/syspurview/role/role.js"></script>

<script type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
<script type="text/javascript">

function back(){
	base.forward("/role/list");
}
</script>
<style>
.error {
	color: #ff0000;
}
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<div class="weizhi">你的位置是：角色管理>>添加</div>
<form  id="mainForm" name="mainForm" method="post"  action="${ctx}/role/save">
<input type="hidden" value="${role.id}" name="id" id="id"/>
<table class="biaoge_3" style="margin-top:10px;">
	<tr>
		<td class="biaoge_31" colspan="4">注：带"*"为必填项</td>
	</tr>
	<tr>
		<td class="biaoge_32" style="width:15%"><span style="color: #F00">*</span>角色名称</td>
		<td class="biaoge_33" style="width:35%"><input name="name" id="name" value="${role.name }" class="text_3"/><form:errors path="name" cssClass="error" /></td>
		<td class="biaoge_32" style="width:15%">角色描述</td>
		<td class="biaoge_33" style="width:35%"><input name="description" id="description" value="${role.description }" class="text_3"/></td>
	</tr>
	<tr>
		<td class="biaoge_32"><span style="color: #F00">*</span>角色状态</td>
		<td class="biaoge_33">
			<select name="status" id="status" style="width:120px;" >
					<option value="0">--请选择--</option>
					<option value="1" <c:if test="${role.status eq 1}">selected</c:if>>正常</option>
					<option value="-1" <c:if test="${role.status eq -1}">selected</c:if>>停用</option>
			</select>
		</td>
		<td class="biaoge_32">角色类型：</td>
		<td class="biaoge_33">
		<select name="roleTypeId" id="roleTypeId" onchange="setRoleUnit()">
			<c:forEach items="${roleType}" var="rt">
				<option value="${rt.id}" <c:if test="${role.roleType.id eq rt.id }">selected</c:if>>${rt.mkey}</option>
			</c:forEach>	
		</select>
</td>
	</tr>
	
	<tr id="roleUnitDiv" 
	<c:choose>
		<c:when test="${role.roleType.id eq 2 }">style="display: '';"</c:when>
		<c:otherwise> style="display: none;"</c:otherwise>
	</c:choose>
	>
		<td class="biaoge_32"><span style="color: #F00">*</span>角色所属单位${role.roleType.id}--</td>
		<td class="biaoge_33">
			<select name="roleUnitId" id="roleUnitId" style="width:120px;" >
					<option value="0">请选择</option>
					${unitOptions}
			</select>
		</td>
		<td class="biaoge_32">&nbsp;</td>
		<td class="biaoge_33">&nbsp;</td>
	</tr>
	
	<tr>
		<td class="biaoge_34" colspan="4">
			<input type="submit" class="button_3" name="submit" value="保存" />
			<input type="button" class="button_3" name="" value="返回" onclick="history.back();"/>
		</td>
	</tr>
	
</table>
</form>