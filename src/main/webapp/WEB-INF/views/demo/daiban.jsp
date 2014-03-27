<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${ctx}/static/dguidemo/easyui/jquery-1.8.0.min.js" ></script>
<script type="text/javascript" src="${ctx}/static/dguidemo/easyui/jquery.easyui.min.js"></script>
<link type="text/css"  rel="stylesheet" href="${ctx}/static/dguidemo/easyui/themes/default/easyui.css" />
<link type="text/css"  rel="stylesheet" href="${ctx}/static/dguidemo/easyui/themes/icon.css"/>
<link type="text/css"  rel="stylesheet" href="${ctx}/static/dguidemo/easyui/demo/demo.css"/>
<script type="text/javascript" src="${ctx}/static/dguidemo/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>设计委托代办</title>
<style type="text/css">
.thfont{
font-size:13px;
font-weight:bold;
color:7F99BE;}

</style>
</head>

<body>
<div class="easyui-panel"
 style="width:950px; height:35px; font-size:12px; background-color:#ECF5FF; border:none; padding-left:3px; line-height:35px; margin-bottom:10px;">
<form action="" method="">
	所属年份：<select><option>2011</option><option>2012</option><option>2013</option><option>2014</option></select> &nbsp;
	单位：<select>
	<option>所有单位</option><option>省直属</option><option>东莞分公司</option>
	<option>佛山分公司</option><option>清远分公司</option><option>广州分公司</option>
	</select>  &nbsp; 
	项目类型：<select>
	<option>网改工程</option><option>机房工程</option><option>光缆工程</option>
	<option>管道工程</option><option>专项工程</option><option>配套工程</option>
	</select>   &nbsp;
	项目编号：<input type="text" value="" class="easyui-validatebox"/>   &nbsp;
	项目名称：<input type="text" value="" class="easyui-validatebox"/>   &nbsp;
	<a class="easyui-linkbutton" value="搜索" data-options="iconCls:'icon-search'">搜索</a>
	</form>
</div>
 <table id="table2" border="1" class="easyui-datagrid" style="width:950px;">
		    <thead >
              <tr>
                    <th align="center" field="name1" width="220"><span class="thfont">待办类型<span></th>
                    <th align="center" field="name2" width="350px"><span class="thfont">待办事项</span></th>
                    <th align="center" field="name3" width="215"><span class="thfont">意见</span></th>
                    <th align="center" field="name4" width="160"><span class="thfont">
					<input type="checkbox"/>
					全选</span></th>
                   
              </tr>
			  </thead>
			  <tbody>
			  <c:forEach items="${list}" var="d">
              <tr >
                   <td>设计委托审批</td>
                   <td><a href="toShenpi.do?id=${d.id}" style="text-decoration:none;">${d.prjname}</a></td>
                   <td><input type="text" value="${d.view}"/></td>
                   <td><input type="checkbox"/></td>
                  
              </tr>
             </c:forEach>
              
			  </tbody>
         </table>

</body>
</html>
