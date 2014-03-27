<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
</head>
<frameset cols="220px,*"  frameborder="0" >
  <frame name="leftFrame" id="leftFrame" src="${ctx }/left.do"  noresize="noresize"/>
  <frame name="mainFrame" id="mainFrame" src="${ctx }/welcome.do"/>
</frameset>
<noframes>您的浏览器不支持框架</noframes>
</html>


