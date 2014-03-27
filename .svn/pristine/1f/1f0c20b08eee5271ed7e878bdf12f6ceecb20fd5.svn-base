<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<div class="weizhi">你的位置是：数据管理 >> 机构管理 >> 编辑机构</div>
<form name="mainForm" id="mainForm">
	<input type="hidden" name="id" id="id" value="${unit.id }"/>
	<table class="biaoge_3" style="margin-top:10px;">
		<tr>
			<td class="biaoge_31" colspan="4">注：带"*"为必填项，不存在上级机构可不必选择</td>
		</tr>
		<tr>
			<td class="biaoge_32" style="width:15%"><span style="color: #F00">*</span>上级机构</td>
			<td class="biaoge_33" style="width:35%">
				<select name="topUnit" id="topUnit" onchange="changeUnit();">
					<option value="">---请选择---</option>
					${unitOptions }
				</select> 
			</td>
			<td class="biaoge_32" style="width:15%"><span style="color: #F00">*</span>机构级别</td>
			<td class="biaoge_33" style="width:35%">
				<select name="jb_m" id="jb_m">
					<c:forEach var="mcode" items="${mcodes }">
						<option value="${mcode.mvalue }" <c:if test="${mcode.mvalue eq unit.jb_m}">selected</c:if>>${mcode.mkey }</option>
					</c:forEach>
				</select> 
			</td>
		</tr>
		<tr>
			<td class="biaoge_32"><span style="color: #F00">*</span>机构代码</td>
			<td class="biaoge_33">
				<span id="t_unit" style="font-size:13px;"></span>
				<input type="hidden" name="inputLength" id="inputLength" value="" />
				<input type="hidden" id="unitCodeNo" value="${unit.gldwdm }"/>
				<input type="text" name="gldwdm" id="gldwdm" class="text_3" value="" onblur="checkUnitCode(this.value,'${unit.id }')" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
			</td>
			<td class="biaoge_32" style="width:15%"><span style="color: #F00">*</span>机构名称</td>
			<td class="biaoge_33" style="width:35%"><input type="text" class="text_4" name="unitName" id="unitName" value="${unit.unitName }"/></td>
		</tr>
		<tr>
			<td class="biaoge_32">联系人</td>
			<td class="biaoge_33"><input type="text" class="text_3" name="lxr" id="lxr" value="${unit.lxr }" /></td>
			<td class="biaoge_32"><span style="color: #F00">*</span>地区码</td>
			<td class="biaoge_33"><span id="dq_m_show"></span><input type="hidden" class="text_3" name="dq_m" id="dq_m" value="${unit.dq_m }" /></td>
		</tr>
		<tr>
			<td class="biaoge_32"><span style="color: #F00">*</span>国家单位代码</td>
			<td class="biaoge_33"><span id="gjdwdm_show"></span></td>
			<td class="biaoge_32"><span style="color: #F00">*</span>管理单位代码</td>
			<td class="biaoge_33"><span id="gldwdm_show"></span></td>
		</tr>
		<tr>
			<td class="biaoge_32">邮编</td>
			<td class="biaoge_33"><input type="text" class="text_3" name="yb" id="yb" value="${unit.yb }" /></td>
			<td class="biaoge_32">电话</td>
			<td class="biaoge_33"><input type="text" class="text_3" name="dh" id="dh" value="${unit.dh }" /></td>
		</tr>
		<tr>
			<td class="biaoge_32">电子邮件</td>
			<td class="biaoge_33"><input type="text" class="text_3" name="dzyj" id="dzyj" value="${unit.dzyj }" /></td>
			<td class="biaoge_32">地址</td>
			<td class="biaoge_33"><input type="text" class="text_4" name="dz" id="dz" value="${unit.dh }" /></td>
		</tr>
		<tr>
			<td class="biaoge_32">状态</td>
			<td class="biaoge_33">
				<select name="status" id="status" style="width:100px;">
					<option value="1" <c:if test="${unit.status eq 1}">selected</c:if>>启用</option>
					<option value="0" <c:if test="${unit.status eq 0}">selected</c:if>>禁用</option>
				</select> 
			</td>
			<td class="biaoge_32"></td>
			<td class="biaoge_33"></td>
		</tr>
		<tr>
			<td class="biaoge_34" colspan="4">
						<input type="button" class="button_3" id="save_btn" name="" value="保存" onclick="save();"/>
					<input type="button" class="button_3" id="process_btn" name="" value="正在处理" style="display:none;" disabled/>
					<input type="button" class="button_3" name="" value="返回" onclick="base.cancel('head','list','edit');"/>
			</td>
		</tr>
	</table>
</form>