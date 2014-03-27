<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
<!--
.success {
	color:green;
}
-->
</style>
<script type="text/javascript">
<!--

var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: this.onClick
		},
		view: {
			fontCss: function(treeId, node){
				return node.font ? node.font : {};
			},
			nameIsHTML: true
		}
	};
var zNodes = null;

$(document).ready(function(){
	$("#message").fadeOut(3000);
	var result = $("#jValue").val();
	zNodes = base.json(result);
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	onClick();
});
	
	
function onClick(){
	var roleid = $("#roleid").val();
	base.load("content","${ctx}/role/menu/crt/"+roleid,function(){});
	//base.load("content","${ctx}/role/menu/crt/"+roleid + "?menuId="+menuid,function(){});
	//$("#content").load("${ctx}/role/menu/accredit",{'menuid':menuid,'roleid':roleid});
	//base.load("content","${ctx}/system/subSys/index",function(){});
}
//-->
</script>
<div class="weizhi">你的位置是：角色管理>>角色授权>>${role.name}授权</div>
<input type="hidden" name="jValue" id="jValue" value="${jValue }"/>
<input type="hidden" name="roleid" id="roleid" value="${role.id}"/>
<c:if test="${not empty message}">
		<div id="message" class="success">${message}</div>	
	</c:if>
<table style="width:100%" >
  <tr>
    
    <th><div id="content"></div></th>
  </tr>
</table>

