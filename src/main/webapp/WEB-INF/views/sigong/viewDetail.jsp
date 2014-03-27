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
		_p_config=$.extend(_p_config,{formid:'commandform',opinionid:'suggestion',nexpersonurl:'/jbpm/nextPerson'});
		var isFirst = true;
		$(function(){
			$('#tt').tabs({
				width : 1000,
				border : false,
				onSelect : function(title,index){
					if(title=='审批信息' && isFirst){
						document.getElementById('taskinfo').contentWindow.location.reload(true); //flotr2在隐藏的时候出现bug
						isFirst = false;
					}
				}
			});
		});
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
			width: 1000px;
		}
		td{
			border:1px solid #AEDEF2;
			width: 250px;
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
		#p td{
			text-align: center;
		}
		#g td{
			text-align: center;
		}
		#p .r{
			text-align: right;
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
		#p td{
			text-align: left;
		}
		#p .r{
			text-align: right;
		}
		#p  input{
			width: 600px;
			height: 30px;
		}
		#p div{
			padding-left: 20px;
		}
		#tt .tabs{
			background: #ffffff;
		}
		.m{
			padding: 10px;
			margin-bottom: 10px;
			text-align: left;
		}
		.m span{
			font-size: 16px;
			
		}
		.gq{
			color: red;
		}
	</style>
  </head>
  
  <body>
    <div class="iscenter">
    	
    		<div>
    				<div class="a">
    					<c:forEach items="${requestScope.outcome }" var="row">
    						<input taskid="<c:out value='${requestScope.taskid}'/>" transitionName="${row.name}" class="button commitprocess"  type="button" value="${row.name}" id="submitbutton">
    					</c:forEach>
    					
    				</div>
    				<div><hr></div>
    				<div class="b"><span>委托施工审批</span></div>
    				<div>
    					<table id="p">
    						<tr><td class="r">审核意见</td><td colspan="3"><div><input type="text" id="suggestion"></div></td></tr>
    						<tr><td class="r">审核日期</td><td colspan="3"><div>2012-9-21</div></td></tr>
    					</table>
    				</div>
    				<div id="tt" >
    				
	    				<div title="委托施工信息">
	    						<div>	<div><div class="m"><span>派工基本信息</span></div></div>
	    								<div>
	    										<table id="g">
	    												
	    												<tr><td class="r">项目编号
	    													<form method="post" id="commandform" action="<%=path %>/Workflow/workTaskComplete">
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
	    				<div title="审批信息">
	    					<div>
	    						<div id="xian">
	    							<iframe name="taskinfo" id="taskinfo" style="width: 1000px;" frameborder=0 src="<%=path %>/Workflow/processview?taskid=<c:out value='${requestScope.taskid}'/>">
	    							</iframe>
	    						</div>
	    					</div>
	    					<div>
	    						<div>
	    							<c:forEach items="${requestScope.suggestion}" var="row">
	    								<div>
	    									<span class="gq">${row.assignee }</span>
	    									在
	    									<span class="gq">${row.endTime }</span>
	    									审批时留言
	    									<span class="gq">${row.suggestion }</span>
	    								</div>
	    							</c:forEach>
	    						</div>
	    					</div>
	    				</div>
    				</div>
    				
    		</div>
    </div>
  </body>
</html>
