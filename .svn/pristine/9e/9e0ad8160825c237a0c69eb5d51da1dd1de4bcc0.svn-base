<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@include file="/static/common/common.jsp"%>
<html>
<head>
<%@include file="/static/common/import_js.jsp" %>
<title>广电网络</title>		
 <style type="text/css">
    #showmenu li{
    float:left;
    margin-top:25px;
    margin-left:35px;
    font-size:13px;
    font-weight:bold;
    list-style:none;
    font-family: Verdana, 微软雅黑,黑体;
 } 
 
 #showmenu a{
 	color:white;
 	text-decoration:none;
 }

#showmenu .current_menu{
    background: #FBEC88;
    border: #D4D8D7 solid 1px;
    color: black;
    padding:2px 3px 2px 3px;
}
 </style>
 
 <script type="text/javascript">
	function addTab(obj) {
		var tab_box = $('#tab_box');
		if (tab_box.tabs('exists', $(obj).attr('title'))) {
			tab_box.tabs('select', $(obj).attr('title'));
			var selec_tab = tab_box.tabs('getSelected');
			$(selec_tab).panel('refresh')
			return;
		}
		tab_box.tabs('add', {
			closable : true,
			title : $(obj).attr('title'),
			content: '<iframe style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="'+$(obj).attr('src')+'"></iframe>'
		});
	}
</script>
<script>
function submit_f() {
var userForm = $('#changeForm').form( {
	url :'${ctx}User/ChangePass',
	success : function(data) {
			var r = $.parseJSON(data);
			$.messager.alert('提示',r.msg);
			alert(r.success);
			if(r.success){
				$('#changePass').window('close');
			}
		}
	});
	userForm.submit();
}
function changePass(){
	var win = $('#changePass');
	win.show();
	win.window({
    title: '修改密码',
	flt:true,
	collapsible:false,
	maximizable:false,
	minimizable:false,
	modal:true
	});
	win.window('open');
}
function loginOut(){
	location='${ctx}User/LoginOut';
}
var baseUrl = "${ctx}/menu";
</script>

</head>
<body class="easyui-layout">
	<!-- north 顶栏-->
	<jsp:include page="/${ctx}/WEB-INF/views/frame/north.jsp"></jsp:include>
	<!--end north -->
	<!-- west 左侧-->
	<div region="west" id="leftMenu" split="true" style="width:168px;" title="菜单">
		<jsp:include page="/${ctx}/menu/leftmenu/6"></jsp:include>
	</div>
	<!-- west -->
	<!-- center 中心-->
	<jsp:include page="/${ctx}/WEB-INF/views/frame/center.jsp"></jsp:include>
	<!-- center -->
	<!-- south 底部-->
	<jsp:include page="/${ctx}/WEB-INF/views/frame/south.jsp"></jsp:include>
	<!-- south -->
	
<!--  修改密码  -->
	<div id="changePass" style="display:none">
		<form id="changeForm" style="padding:10px 20px 10px 20px;text-align:right">
			<label for="oldPass">旧密码<input type="password" name="oldPass"/></label><br/>
			<label for="newPass">新密码<input type="password" name="newPass"/></label><br/>
			<label for="confirmPass">确认密码<input type="password" name="confirmPass"/></label><br/>
			<div style="padding: 5px; text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" ico="ico-ok" onclick=submit_f();>确认</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" ico="ico-cancel" onclick= $('#changePass').dialog('close');>取消</a>
			</div>
		</form>
	</div>
	<!--  修改密码  -->

  </body>

</html>