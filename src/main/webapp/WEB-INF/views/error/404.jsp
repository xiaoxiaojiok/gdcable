<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>404 - 页面不存在</title>
</head>

<body>

<div class="top_show">&nbsp;</div>

<div class="err404 clear" style="background:#fff;margin-top:10px;">
	<p><img src="${ctx }/static/images/404.jpg" /></p>
	<b>你可以尝试一下：</b><br />
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.back">&laquo; 返回上一页</a><br />
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/"/>">&laquo; </a>
</div>

<div id="footer">

</div>
<div id="append"></div>
</body>
</html>