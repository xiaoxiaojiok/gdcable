<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${ctx}/static/dguidemo/easyui/jquery-1.8.0.min.js" ></script>
<script type="text/javascript" src="${ctx}/static/dguidemo/easyui/jquery.easyui.min.js"></script>
<link type="text/css"  rel="stylesheet" href="${ctx}/static/dguidemo/easyui/themes/default/easyui.css" />
<link type="text/css"  rel="stylesheet" href="${ctx}/static/dguidemo/easyui/themes/icon.css"/>
<link type="text/css"  rel="stylesheet" href="${ctx}/static/dguidemo/easyui/demo/demo.css"/>
<script type="text/javascript" src="${ctx}/static/dguidemo/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>设计委托审批</title>

<style type="text/css">
	table{
	font-size:12px;
	}
	.tdright{
	text-align:right;
}
	
	.tcolor{
	color:#0E2D5F;}
	
	.required{
	color:#FF0000;}
	
	
</style>

</head>

<body>
<div style="margin:10px 0;"></div>
	<div class="easyui-panel" title="设计委托审批" style="width:800px; background-color:#F6F7FB;">
		<div style="padding:10px 0 10px 60px">
	    <form id="ff" method="post">
	    	<table>
	    		<tr>
	    			<td class="tdright tcolor">项目编号:</td>
	    			<td><input value="${designInfo.prjid}" disabled="disabled"/></td>
					<td class="tdright tcolor">项目名称:</td>
	    			<td><input value="${designInfo.prjname}" disabled="disabled"/></td>
	    		</tr>
	    		
	    		<tr>
	    			<td class="tdright">*设计单位名称:</td>
	    			<td>
	    			<c:choose>
					<c:when test="${designInfo.depname=='1'}">南方设计公司</c:when>
					<c:when test="${designInfo.depname=='2'}">工程设计部</c:when>
					<c:when test="${designInfo.depname=='3'}">无限创意室</c:when>
					<c:otherwise>未确定</c:otherwise>
				</c:choose> </td>
					<td class="tdright">*要求工期:</td>
	    			<td><input value="${designInfo.worktime}个月" disabled="disabled"/></td>
	    		</tr>
				
				<tr>
	    			<td class="tdright ">*设计文件份数-全套文件:</td>
	    			<td><input value="${designInfo.allfileamount}" disabled="disabled"/></td>
					<td class="tdright ">*设计文件份数-竣工图:</td>
	    			<td><input value="${designInfo.comphoamount}" disabled="disabled"/></td>
	    		</tr>
				
				<tr>
	    			<td class="tdright">*项目设计负责人:</td>
	    			<td><input value="${designInfo.designperson}" disabled="disabled"/></td>
					<td class="tdright ">*联系电话:</td>
	    			<td><input value="${designInfo.phone}" disabled="disabled"/></td>
	    		</tr>
				
	    		<tr>
	    			<td class="tdright ">*设计内容:</td>
	    			<td colspan="3"><textarea name="message" style="height:60px; width:97%" 
	    			disabled="disabled">${designInfo.designdesc}</textarea></td>
	    		</tr>
				<tr>
	    			<td class="tdright ">审批意见:</td>
	    			<td colspan="3"><textarea name="message" style="height:30px; width:97%" ></textarea></td>
	    		</tr>
	    		
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" style=" margin-right:20px;"
			>同意</a>
		   <a href="#" class="easyui-linkbutton" 
			>不同意</a>
	    	<a href="daiban.do?page=1" class="easyui-linkbutton" 
			style="margin-left:20px;" data-options="iconCls:'icon-back'">返回</a>
	    </div>
	</div>
	<script> 
		function submitForm(){
			$('#ff').form('submit');
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>
