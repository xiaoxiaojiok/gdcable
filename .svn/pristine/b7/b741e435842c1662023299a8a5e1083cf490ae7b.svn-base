<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css"  href="${ctx }/static/css/css.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/base/base.js"></script>

<script type="text/javascript"> 
//系统切换
function onMShow(XelementId,Xstatus){ 
    if(Xstatus) 
      document.getElementById(XelementId).style.display="block"; 
    else 
      document.getElementById(XelementId).style.display="none"; 
} 

//注销
function logout(url){
	if(!confirm("您确定要注销并退出系统吗?")){
		return;
	}
	base.forward(url,parent);
}

function getCurDate(){
	var d = new Date();
	var week;
	switch (d.getDay()){
		case 1: week="星期一"; 
		break; 
		case 2: week="星期二"; 
		break; 
		case 3: week="星期三"; 
		break; 
		case 4: week="星期四"; 
		break; 
		case 5: week="星期五";
		break; 
		case 6: week="星期六"; 
		break; 
		default: week="星期天";
	} 
	var years = d.getFullYear();
	var month = add_zero(d.getMonth()+1);
	var days = add_zero(d.getDate());
	var hours = add_zero(d.getHours());
	var minutes = add_zero(d.getMinutes());
	var seconds=add_zero(d.getSeconds());
	var ndate = years+"年"+month+"月"+days+"日 "+hours+":"+minutes+":"+seconds+" "+week; 
	$(".time").text(ndate);
}
function add_zero(temp){
	if(temp<10) 
		return "0"+temp; 
	else 
		return temp;
}

$(function(){
	setInterval("getCurDate()",100);
});
</script>
</head>
<body>
<div id="header">
  <div class="top">
        <div class="logo_yun"><img src="${ctx }/static/images/logo_yun_2.gif"/></div>
        <div class="splitline"></div>
        <div class="logo_ecs">
          <div class="cname">${name }</div>
          <div class="ename">Growth File Cover</div>
        </div>
        <div class="logo_sys"><!--<img src="${ctx }/static/images/logo_sys_1.gif"/>--></div>    
        <div class="info">
          <div class="clear"></div>
          <div class="user_info">
          		  欢迎您：${user.username } >> <!-- 角色：教师 > 职位：主任 > --> 
          </div> 
        </div>  
  </div>
  <div class="banner1">
    <div class="menu_a">
    <!-- 
      <a href="#">功能一</a> | 
      <a href="#">功能二</a> |
      <a href="#">功能三</a>
     -->
    </div>
    <div class="menu_b" style="width:20%">
       <div class="time"></div>
       <div class="ico_b"><a href="#" onclick="logout('${logoutUrl }');" title="注销"><img border="0" src="${ctx }/static/images/ico_b_5.gif"/></a></div>
    </div>
  </div>
</div>
</body>
</html>
