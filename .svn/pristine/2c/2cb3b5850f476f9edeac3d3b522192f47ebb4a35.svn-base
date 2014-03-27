<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form commandName="role" action="${ctx}/role/menu/save" method="post">
<form:hidden path="id"  />
<input type="hidden" value="${menu.id }" name="menuId"/>
<table class="biaoge_2" style="width:100%">
  <tr>
    <th style="width:20%">菜单名称</th>
    <th style="width:80%">菜单权限</th>
  </tr>
  <c:forEach var="perm" items="${permList}" varStatus="p">
  <tr class="biaoge_21">
  <td style="width:20%">${menuList[p.index].menuName}</td>
  <td align="left" style="width:80%">
     <c:if test="${not empty perm}">
  	<form:checkboxes  path="permissionList" items="${perm}" itemLabel="permName" itemValue="id" />
  	</c:if>
  	<c:if test="${empty perm}">
  	  <font color="red">没有数据</font>
  	</c:if>
  </td> 
  </tr>
  </c:forEach>
  <tr>
		<td class="biaoge_34" colspan="4">
			<input type="submit" class="button_3" name="submit" value="保存" />
			<input type="button" class="button_3" name="" value="返回" onclick="history.back();"/>
		</td>
	</tr>
</table>
</form:form>