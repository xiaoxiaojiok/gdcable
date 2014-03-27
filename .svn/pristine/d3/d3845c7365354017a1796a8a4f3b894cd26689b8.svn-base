<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx}/static/easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/syspurview/sigong/JbpmCommon.js"></script>
	<script type="text/javascript">
	_p_config=$.extend(_p_config,{formid:'commandform',nexpersonurl:'/jbpm/nextPerson'});
	</script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			padding: 0px;
			margin: 0px;
		}
		table{
			border:1px solid #AEDEF2;
			border-collapse: collapse;
			
		}
		td{
			border:1px solid #AEDEF2;
			width: 300px;
			height: 40px;
		}
		body{
			text-align: center;
		}
		.iscenter{
			width: 1000px;
			margin-left: auto;
			margin-right: auto;
		}
		#g td{
			text-align: center;
		}
		#g .r{
			text-align: right;
		}
		.button{
			width: 60px;
			height: 30px;
		}
		.a,.b{
			text-align: left;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		#g{
			margin-top: 10px;
		}
		#g td input{
			height: 30px;
			width: 250px;
		}
		#g .desc td{
			height: 90px;
		}
		#g .desc textarea{
			height: 80px;
			width: 750px;
		}
		
		.panel td{
			padding-top: 5px;
			padding-bottom: 5px;
		}
		.panel-title{
			text-align: left;
		}
		.userinfo{
			margin-top:15px;
			padding-left: 20px;
		}
	</style>
  </head>
  
  <body>
    <div class="iscenter">
    		<div>
    				<div class="a"><input processdefinitonkey="ConstructionDelegate" class="button commitprocess"  type="button" value="提交" id="submitbutton"></div>
    				<div><hr></div>
    				<div class="b"><span>委托施工基本信息</span></div>
    				<div><hr></div>
    				<div>
    						<div>
    								<div>
    										<table id="g">
    												
    												<tr><td class="r">项目编号
    													<form method="post" id="commandform" action="<%=path %>/Workflow/workComplete">
    														<input type="hidden" name="id" value="<c:out value='${requestScope.work.id}'/>">
    													</form>
    													</td>
    														<td><c:out value='${requestScope.work.projid}'/></td>
    														<td class="r">项目名称</td>
    														<td><c:out value='${requestScope.work.proname}'/></td>
    												</tr>
    												<tr><td colspan="1" class="r">施工方式：</td><td colspan="3"><input type="radio" name=""></td></tr>
    												<tr><td class="r">*施工单位</td><td><c:out value='${requestScope.work.dept}'/></td><td class="r">*要求工期</td><td><c:out value='${requestScope.work.workdate}'/></td></tr>
    												<tr>
    														<td rowspan="2" class="r">*竣工文件份数</td>
    														<td>全套文件</td>
    														<td>竣工图</td>
    														<td>全套结算</td>
    												</tr>
    												<tr><td><c:out value='${requestScope.work.allfile}'/></td><td><c:out value='${requestScope.work.donefile}'/></td><td><c:out value='${requestScope.work.allsum}'/></td></tr>
    												<tr class="desc"><td class="r">施工内容</td><td colspan="3"><c:out value='${requestScope.work.description}'/></td></tr>
    												<tr><td class="r">项目负责人</td><td><c:out value='${requestScope.work.man}'/></td><td class="r">联系电话</td><td><c:out value='${requestScope.work.phone}'/></td></tr>
    										</table>
    								</div>
    						</div>
    				</div>
    		</div>
    </div>
  </body>
</html>
