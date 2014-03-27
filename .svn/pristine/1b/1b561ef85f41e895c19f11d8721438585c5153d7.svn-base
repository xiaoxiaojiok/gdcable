<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>教育云平台</title>
<style>
body{ font-family:microsoft yahei}
/*页码样式*/
.page {padding:4px 6px 4px 0; font-size: 13px;  color:#313031;  font-family: Verdana,Tahoma,Arial,Helvetica,Sans-Serif; background-color: #fff; text-align:right; margin-top:20px}
.page a{padding:5px 6px 4px 5px; margin:0px 3px; color:#333; border:1px solid #a9e6f4; text-decoration: none}
.page a:hover{border:1px solid #a9e6f4; COLOR: #333; background:#a9e6f4}
.pagination a:active{border:1px solid #a9e6f4; COLOR: #0066a7; background-color:#d2eaf6}
.page span.disabled {padding:5px 6px 4px 5px; margin:0px 3px; color:#999; border:1px solid #e1e1e1;}
table.pop{padding:0; margin:0; width:100%; text-align:center}
</style>
<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.2.min.js"></script>
<link type="text/css" href="${ctx }/static/css/table.css" rel="stylesheet"/>
<link type="text/css" href="${ctx }/static/jquery-zTree-v3.4/css/demo.css" rel="stylesheet"/>
<link type="text/css" href="${ctx }/static/jquery-zTree-v3.4/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx }/static/jquery-zTree-v3.4/js/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript" src="${ctx }/static/js/base/base.js"></script>
<script  language="javascript">
function chg(id_num){
var oa = document.getElementById(id_num);
var ob = document.getElementById("grade");
if(oa.style.display == "block"){
   oa.style.display = "none";
}else{
   oa.style.display = "block";
}
}
</script>
<sitemesh:head/>
</head>
<body>
	<sitemesh:body/>
</body>
</html>
