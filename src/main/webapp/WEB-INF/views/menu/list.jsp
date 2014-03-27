<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }"/>
<c:set var="pageNum" value="${pageSize + 1 }" scope="session"/>
<table class="biaoge_2" width="100%">
	<tr>
	    <th width="8%"><input type="checkbox" onclick="base.checkAll('cbx',this);"/></th>
	    <th width="15%">菜单名称</th>
	    <th width="15%">请求地址</th>
	    <th width="8%">菜单等级</th>
	    <th width="8%">同级排序</th>
	    <th width="12%">上级菜单</th>
	    <th width="8%">状态</th>
	    <th>操作</th>
	</tr>
	<c:choose>
		<c:when test="${pageSize eq 0 }">
			<tr class="biaoge_21">
				<td colspan="8" style="color:red;">系统暂时还不存在菜单!</td>
			</tr>
		</c:when>	
		<c:otherwise>
			<c:forEach items="${list }" var="menu" varStatus="status">
				<c:choose>
					<c:when test="${(status.count mod 2) eq 0}">
						<tr class="biaoge_21" ondblclick="edit(${menu.id});" style="cursor:pointer">
					</c:when>
					<c:otherwise>
						<tr class="biaoge_22" ondblclick="edit(${menu.id});" style="cursor:pointer">
					</c:otherwise>
				</c:choose>
					<td><input type="checkbox" name="cbx" value="${menu.id }"/></td>
					<td style="text-align:left;">${menu.menuName }</td>
					<td style="text-align:left;">${menu.menuUrl }</td>
					<td>${menu.menuLevel }</td>
					<td>${menu.orderNum }</td>
					<td><c:if test="${not(menu.menu.menuName eq 'ROOT')}">${menu.menu.menuName }</c:if></td>
					<td>
						<c:choose>
							<c:when test="${menu.status eq 0}">
								<span id="statusDisplay${menu.id}"><font color="red">禁用</font></span>
							</c:when>
							<c:otherwise>
								<span id="statusDisplay${menu.id}">正常</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<input class="button_2" name="" type="button" value="修改" onclick="edit(${menu.id},1);"/>
						<input class="button_2" name="" type="button" value="添加下级" onclick="edit(${menu.id},2);" style="font-size:10px;"/>
				   	 	<input class="button_2" name="" type="button" value="删除" onclick="del(${menu.id});" />
					    <input class="button_2" id="setUnable${menu.id}" type="button" value="停用" onclick="setup(${menu.id},0);" <c:if test="${menu.status eq 0}">style="display:none;"</c:if> />
					    <input class="button_2" id="setAble${menu.id}" type="button" value="启用" onclick="setup(${menu.id},1);" <c:if test="${menu.status eq 1}">style="display:none;"</c:if>/>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<form action="/menu/list" method="get">
<input type="hidden" value="${p_menu}" name="pMenu">
<input type="hidden" value="${menu_name}" name="menuName">
<input type="hidden" value="${menu_status}" name="status">
<jsp:include page="../paging.jsp"/>
</form>

