<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>

<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>500 - 系统内部错误</title>
</head>

<body>
<div><h1>系统发生内部错误.</h1></div>
<div >原因：<c:out value="${ex}" /></div>
<div class="top_show">&nbsp;</div>

<div class="err404 clear" style="background:#fff;margin-top:10px;">
	<p><img src="${ctx }/static/images/404.jpg" /></p>
	<b>你可以尝试一下：</b><br />
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">&laquo; 返回上一页</a><br />
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/"/>">&laquo; </a>
</div>

<div id="footer">
</div>
<div id="append"></div>
</body>
</html>
