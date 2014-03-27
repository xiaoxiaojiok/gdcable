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
    
    <title>My JSP 'projectList.jsp' starting page</title>
    
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
//		_p_config=$.extend(_p_config,{
//				isAjax:true,

//				defaulthanler : function(msg){
//					console.log(msg);
//				}
//		});
	</script>
	<style type="text/css">
		*{
			margin: 0px;
			padding: 0px;
		}
		.a{
			text-align: center;
		}
		.b{
			width: 1000px;
			margin-left: auto;
			margin-right: auto;
		}
		.c{
			width: 1000px;
			border: 1px solid #000000;
			text-align: left;
			background: #c0d9d9;
		}
		.d{
			height: 40px;
			width: 120px;
			background: blue;
		}
		.d span{
			line-height: 40px;
			color: white;
		}
		.e{
			text-align: left;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		.button,#query{
			width: 60px;
			height: 30px;
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
		
			$('#projectlist').datagrid({
				id : 'projecttable',
				width:1000,
				autoRowHeight : false,
				striped: true,
				autoRowHeight : true,
				url : '${ctx}/Workflow/listall',
				idFiled: 'serviceid',
				loadMsg : '正在加载中,请稍等!',
				columns:[[
								{field:'executionId',title:'流程id',width:220,align:'center',hidden:true},
								{field:'serviceid',title:'业务主键',width:220,align:'center',hidden:true,formatter:function(value,row,index){
										return row.service.id;
								}},
								{field:'projid',title:'项目编号',width:220,align:'center',formatter:function(value,row,index){
										return row.service.projid;
								 }},
								{field:'proname',title:'项目名称',width:220,align:'center',formatter:function(value,row,index){
										return row.service.proname;
								 }},
								{field:'dbid',title:'委托施工申请',width:120,align:'center',formatter:function(value,row,index){
										return '<a href="<%=path %>/Workflow/workInfoView?taskid='+row.dbid+'&serviceid='+row.service.id+'">查看</a>';
								}},
								{field:'transation',title:'意见',width:320,align:'center',formatter:function(value,row,index){
										var bu = [];
										if(row.transation==null){
											return;
										}
										for(var i = 0; i<row.transation.length;i++){
											var o = row.transation[i];
											//taskid  transitionName  processdefinitonkey
											var s = '<input class="button commitprocess" type="button" processdefinitonkey="'+row.executionId+'" taskid="'+row.dbid+'" transitionName="'+o.name+'"  value="'+o.name+'">';
											bu.push(s);
											bu.push('&nbsp;&nbsp;');
										}
										
										return bu.join('');
								}},
								{field:'ck',title:'全选',width:120,align:'center',checkbox:true}
							]],
				pagination:true,
				rownumbers:true
			});
			//datagrid默认阻止click事件冒泡，导致live委派不能使用。所以针对datagrid click事件不能有效，或者从写，不要组织click事件冒泡
			var se =$.data($('#projectlist').get(0),"datagrid");
			se.dc.body1.unbind('click');
			se.dc.body2.unbind('click');
		});
		
		$(function(){
			$('#query').bind('click',function(){
				var projid = $('#projid').val();
				var proname = $('#proname').val();
				var param = {
					'projid' : projid,
					'proname' :proname
				};
				var obj=$('#projectlist').datagrid('options');
				obj.queryParams=param;
				obj.pageNumber=1;
				$('#projectlist').datagrid(obj);
			});
		});

	</script>

  </head>
  
  <body class="a">
    <div class="b">
    		<div>
    				<div class="c">
    					<div class="d">
    							<span>委托施工待审批</span>
    					</div>
    				</div>
    				<div>
    					<div class="e">
    						项目名称:&nbsp;<input type="text" id="proname">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目编码:&nbsp;<input type="text" id="projid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="query" class="button" type="button" value="查询">
    					</div>
    				</div>
    				<div>
    						<div>
    								<table id="projectlist"></table>
    						</div>
    				</div>
    		</div>
    </div>
    <form method="post" id="commandform" action="<%=path %>/Workflow/workTaskComplete">
    
    </form>
    <c:if test="${requestScope.requeststatus !=null &&  requestScope.requeststatus != ''}">
    		<script type="text/javascript">
    			 (function(){
    			 		var status = "<c:out value='${requestScope.requeststatus }'/>";
    			 		alert(status);
    			 })();
    		</script>
    		<% request.removeAttribute("requeststatus"); %>
    </c:if>
  </body>
</html>
