<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function save(){
	var param="permName="+encodeURI($("#permName").val())+"&descript="+encodeURI($("#descript").val())+"&permType="+$("#permType").val()+"&menuId="+$("#menuId").val()+"&permValue="+$("#permValue").val();
	if($("#id").val()==""){
		param+="&id=0";
	}else{
		param+="&id="+$("#id").val();
	}
	base.processStatus(1,'save','process');
	base.request("/permission/save",param,function(result){
		if(result == "success"){
			base.tips("保存成功!",'success',1500);
			setTimeout(function(){
				base.forward("/permission/index");
			},2000)
		}else{
			base.alert("操作失败！");
		}
	},"POST","HTML");
}
function back(){
	base.forward("/permission/index");
}
function menuChange()
{
	var varPermType = $("#permType").val();
	if(varPermType =='undefined' || varPermType =='0' || varPermType =='1')
	{
		$("#permValue").attr("value",'');
	}
	if(varPermType =='-1')
	{
		$("#permValue").attr("value",$("#menuId option:selected").val());
	}
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
<div class="weizhi">你的位置是：权限管理>>
	<c:choose>
		<c:when test="${empty  permission.id}">
		添加
		</c:when>
		<c:otherwise>
		修改
		</c:otherwise>
	</c:choose>
</div>
<form  id="mainForm" name="mainForm" method="post" >
<input type="hidden" value="${permission.id}" name="id" id="id"/>
<input type="hidden" value="${currentIndex}" name="currentIndex"/>
<table class="biaoge_3" style="margin-top:10px;">
	<tr>
		<td class="biaoge_31" colspan="4">注：带"*"为必填项</td>
	</tr>
	<tr>
		<td class="biaoge_32" style="width:15%"><span style="color: #F00">*</span>权限名称</td>
		<td class="biaoge_33" style="width:35%"><input name="permName"  id="permName" cssClass="text_3" value="${ permission.permName}"/></td>
		<td class="biaoge_32" style="width:15%">权限描述</td>
		<td class="biaoge_33" style="width:35%"><input name="descript" id="descript" cssClass="text_3" value="${ permission.descript}"/></td>
	</tr>
	<tr>
		<td class="biaoge_32"><span style="color: #F00">*</span>权限类型</td>
		<td class="biaoge_33">
			<select name="permType" id="permType" cssStyle="width:120px;" >
					<option value="0">--请选择--</option>
					<option value="1" <c:if test="${permission.permType eq 1}">selected</c:if>>功能权限</option>
					<option value="-1" <c:if test="${permission.permType eq -1}">selected</c:if>>菜单权限</option>
			</select>
		</td>
		<td class="biaoge_32"><span style="color: #F00">*</span>权限所属菜单</td>
		<td class="biaoge_33"><select name="menuId" id="menuId" cssClass="menu" onchange="menuChange()">
		<c:forEach items="${mlist}" var="menu">
		<option value="${menu.id}" <c:if test="${permission.menu.id eq menu.id}">selected</c:if>>${menu.menuName}</option>
		</c:forEach>
		</select></td>
	</tr>
	<tr id="per_value">
		<td class="biaoge_32"><span style="color: #F00">*</span>权限值</td>
		<td class="biaoge_33"><input name="permValue" id="permValue" cssClass="text_3" value="${permission.permValue}"/></td>
	</tr>
	<tr>
		<th colspan="2" class="t_title t_c">
			<input name="btn01" id="sys_update_btn" type="button" onclick="save()" class="sort_sub" value="提 交" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="btn01" type="button" class="sort_sub" onclick="back()" value="返 回" />
		</th>
	</tr>
</table>
</form>