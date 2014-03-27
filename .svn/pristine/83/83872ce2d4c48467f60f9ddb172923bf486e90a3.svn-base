<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<jsp:include page="/static/common/import_js.jsp"></jsp:include>	

<!-- 首页 -->
	<div class="easyui-accordion" fit="true" style="display:block;">
	<c:forEach items="${menu.subMenuList}" var="fir_menu">
		<div title="${fir_menu.menuName }">
			<c:forEach items="${fir_menu.subMenuList}" var="sec_menu">
				<a class="easyui-linkbutton" 
				plain="true" title="${sec_menu.menuName}" src="${sec_menu.menuUrl}" href="javascript:void(0)" 
				onClick=addTab(this); style="margin-left:15px;">${sec_menu.menuName}</a>
				<br>
			</c:forEach>
		</div>
	</c:forEach>
	</div>

