<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${ctx}">
    
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
	<script type="text/javascript">
		$(function(){
			$('#formsave').bind('click',function(){
				var form = $('#commandform').get(0);
				form.action = '${ctx}/Workflow/workSave';
				form.submit();
			});
		});
	</script>
  </head>
  
  <body>
    <div class="iscenter">
    		<div>
    				<div class="a"><input id="formsave" type="button" class="button" value="保存" id="savebutton">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input processdefinitonkey="ConstructionDelegate" class="button commitprocess"  type="button" value="提交" id="submitbutton"></div>
    				<div><hr></div>
    				<div class="b"><span>委托施工基本信息</span></div>
    				<div><hr></div>
    				<div>
    						<div>
    								<div><form method="post" id="commandform" action="${ctx}/Workflow/workComplete">
    										<table id="g">
    												<tr><td class="r">项目编号<input type="hidden" name="id" ></td>
    														<td><input type="text" name="projid" value="A9012901"></td>
    														<td class="r">项目名称</td>
    														<td><input type="text" name="proname" value="黄石公安大院小区网络工程"></td>
    												</tr>
    												<tr><td colspan="1" class="r">施工方式：</td><td colspan="3"><input type="radio" name=""></td></tr>
    												<tr><td class="r">*施工单位</td><td><input type="text" name="dept"></td><td class="r">*要求工期</td><td><input type="text" name="commanddate"></td></tr>
    												<tr>
    														<td rowspan="2" class="r">*竣工文件份数</td>
    														<td>全套文件</td>
    														<td>竣工图</td>
    														<td>全套结算</td>
    												</tr>
    												<tr><td><input type="text" name="allfile"></td><td><input type="text" name="donefile"></td><td><input type="text" name="allsum"></td></tr>
    												<tr class="desc"><td class="r">施工内容</td><td colspan="3"><textarea name="description"></textarea></td></tr>
    												<tr><td class="r">项目负责人</td><td><input type="text" name="man"></td><td class="r">联系电话</td><td><input type="text" name="phone"></td></tr>
    											
    										</table>
    										</form>
    								</div>
    						</div>
    				</div>
    		</div>
    </div>
  </body>
</html>
