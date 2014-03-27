<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }"/>
<c:set var="pageNum" value="${pageSize + 1 }" scope="session"/>
<table class="biaoge_2" width="100%">
	<tr>
	    <th width="5%"><input type="checkbox" onclick="base.checkAll('cbx',this);"/></th>
	    <th width="12%">机构名称</th>
	    <th width="8%">联系电话</th>
	    <th width="11%">国家单位代码</th>
	    <th width="11%">管理单位代码</th>
	    <th width="11%">机构代码</th>
	    <th width="10%">上级单位</th>
	     <th width="5%">状态</th>
	    <th>操作</th>
	</tr>
	<c:choose>
		<c:when test="${pageSize eq 0 }">
			<tr class="biaoge_21">
				<td colspan="9" style="color:red;">系统暂时还不存在任何机构!</td>
			</tr>
		</c:when>	
		<c:otherwise>
			<c:forEach items="${collection }" var="unit" varStatus="status">
				<c:choose>
					<c:when test="${(status.count mod 2) eq 0}">
						<tr class="biaoge_21" ondblclick="edit(${unit.id});" style="cursor:pointer">
					</c:when>
					<c:otherwise>
						<tr class="biaoge_22" ondblclick="edit(${unit.id});" style="cursor:pointer">
					</c:otherwise>
				</c:choose>
					<td><input type="checkbox" name="cbx" value="${unit.id }"/></td>
					<td>${unit.unitName }</td>
					<td>${unit.telphone }</td>
					<td>${unit.countryUnitCode }</td>
					<td>${unit.managerUnitCode }</td>
					<td>${unit.departmentCode }</td>
					<td>${unit.unit.unitName }</td>
					<td>
						<c:choose>
							<c:when test="${unit.status eq 0}">
								<span id="statusDisplay${unit.id}"><font color="red">禁用</font></span>
							</c:when>
							<c:otherwise>
								<span id="statusDisplay${unit.id}">正常</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<input class="button_2" name="" type="button" value="修改" onclick="edit(${unit.id});"/>
				   	 	<input class="button_2" name="" type="button" value="删除" onclick="del(${unit.id});" />
					    <input class="button_2" id="setUnable${unit.id}" type="button" value="停用" onclick="setup(${unit.id},0);" <c:if test="${unit.status eq 0}">style="display:none;"</c:if> />
					    <input class="button_2" id="setAble${unit.id}" type="button" value="启用" onclick="setup(${unit.id},1);" <c:if test="${unit.status eq 1}">style="display:none;"</c:if>/>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>

<form action="/unit/list" method="get">
<input type="hidden" value="${p_menu}" name="p_menu">
<input type="hidden" value="${menu_name}" name="menu_name">
<input type="hidden" value="${menu_status}" name="menu_status">
<jsp:include page="../paging.jsp"/>
</form>
