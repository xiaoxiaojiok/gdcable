<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="weizhi">你的位置是：数据管理 >> 用户管理 >> 
<!-- key为true时新增，key为false时修改 -->
	<c:choose>
		<c:when test="${key eq true}">
		新增用户
		</c:when>
		<c:otherwise>
		编辑用户
		</c:otherwise>
	</c:choose>
</div>
<form name="mainForm" id="mainForm">
	<input type="hidden" name="id" id="id" value="${user.id }"/>
	<table class="biaoge_3" style="margin-top:10px;">
		<tr>
			<td class="biaoge_31" colspan="4">注：带"*"为必填项</td>
		</tr>
		<tr>
			<td class="biaoge_32" style="width:15%"><span style="color: #F00">*</span>登录名称</td>
			<c:choose>
				<c:when test="${key eq true}">
					<td class="biaoge_33" style="width:35%"><input type="text" class="text_3" name="loginName" id="loginName" onblur="checks('loginName')"/><font color="red"  id="tipOne"></font>（字母、数字及下划线，不包含中文）</td>
				</c:when>
				<c:otherwise>
					<td class="biaoge_33" style="width:35%"><input type="text" class="text_3" name="loginName" id="loginName"  readonly="readonly"  value="${user.loginName }" /></td>
				</c:otherwise>
			</c:choose>			
			<td class="biaoge_32" style="width:15%"><span style="color: #F00">*</span>真实姓名</td>
			<td class="biaoge_33" style="width:35%"><input type="text" class="text_3" name="username" id="username"  value="${user.username }" /><font color="red"  id="tips"></font></td>
		</tr>
		<tr>
			<td class="biaoge_32">性别</td>
			<td class="biaoge_33">
			<c:choose>
			<c:when test="${user.gender eq 0}">
			<input type="radio" id="man" name="gender" value="1"/>男&nbsp;&nbsp;
			<input type="radio" id="woman" name="gender" checked="checked" value="0"/>女&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
			<input type="radio" id="man" name="gender" checked="checked" value="1"/>男&nbsp;&nbsp;
			<input type="radio" id="woman" name="gender" value="0"/>女&nbsp;&nbsp;
			</c:otherwise>
			</c:choose>			
			</td>
			<td class="biaoge_32"><span style="color: #F00"></span></td>
			<td class="biaoge_33">
				&nbsp;
			</td>
		</tr>
		<c:if test="${key eq true}">
		<tr>
			<td class="biaoge_32"><span style="color: #F00">*</span>登录密码</td>
			<td class="biaoge_33">
				<input type="password" name="initPassword" id="initPassword" /><font color="red"  id="tipTwo"></font>
			</td>
			<td class="biaoge_32"><span style="color: #F00">*</span>确认密码</td>
			<td class="biaoge_33">
				<input type="password" name="confpas" id="confpas" /><font color="red"  id="tipThree"></font>
			</td>
		</tr>
		</c:if>
		<tr>
			<td class="biaoge_32"><span style="color: #F00"></span>手机号码</td>
			<td class="biaoge_33">
				<input type="text" name="mobile" id="mobile" value="${user.mobile}" /><font color="red"  id="tipFour"></font>
			</td>
			<td class="biaoge_32"><span style="color: #F00"></span>电子邮件</td>
			<td class="biaoge_33">
				<input type="text" name="email" id="email" value="${user.email}" /><font color="red"  id="tipFive"></font>
			</td>
		</tr>
		<c:choose>
		<c:when test="${key eq true and empty  dw_select}">
		<input type="radio"  style="display: none" id="XX" name="usertype" checked="checked" value="1" />
		</c:when>
		<c:otherwise>
		</c:otherwise>		
		</c:choose>		
		<c:if test="${not empty  dw_select}">		
		<tr>
			<td class="biaoge_32">所在机构</td>
			<td class="biaoge_33">
				<select name="unit_id" id="unit_id">
					<option value="0">---请选择---</option>
					${dw_select}
				</select>
			</td>
			<td class="biaoge_32">&nbsp;</td>
			<td class="biaoge_33">&nbsp;</td>
		</tr>
		</c:if>
		<tr>
			<td class="biaoge_32">用户角色</td>
			<td class="biaoge_33">
					<table  class="biaoge_3" style="border:none;" >
						<tr>
							<td style="width:131px;">
									<select name="s_roleId" id="s_roleId" size="6" multiple style="width:100px;height:110px;overflow: hidden;overflow-y:scroll; ">
										<c:forEach items="${roleList}" var="s_role">
											<option value="${s_role.id}">${s_role.name}</option>
										</c:forEach>
									</select>
							</td>
							<td>
								<p><input name="btn01" type="button" class="sort_sub" onclick="addRole()" value="添 加" /></p>
								<p><input name="btn01" type="button" class="sort_sub" onclick="delRole()" value="删 除" /></p>
							</td>
							<td>
								<select name="roleIds" id="roleIds" size="6" multiple style="width:100px;height:110px;overflow: hidden;overflow-y:auto;">
									<c:forEach items="${roles}" var="v_role">
										<option value="${v_role.id}">${v_role.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
			</td>
			<td class="biaoge_32">&nbsp;</td>
			<td class="biaoge_33">&nbsp;</td>
		</tr>
		<tr>
			<td class="biaoge_34" colspan="4">
				<input type="button" class="button_3" id="save_btn" name="" value="保存" onclick="save(${key})"/>
				<input type="button" class="button_3" name="" value="返回" onclick="base.cancel('head','list','edit',userQuery);"/>
			</td>
		</tr>
	
	</table>
</form>