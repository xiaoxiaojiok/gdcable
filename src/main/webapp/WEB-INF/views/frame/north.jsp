<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div region="north" split="true" style="height:100px;width:100%; background-color:rgb(127,153,190);overflow: hidden;">
	   <div region="north" split="true" border="false" style="margin:0;  height: 100px;
		background-image:url(${basePath}/static/gdstyle/image/logo.jpg);
		background-repeat:no-repeat;
        line-height: 30px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
	        <span style="float:right; padding-right:20px; margin-top:65px;" class="head">欢迎 项目经理 <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
	        <span style="padding-left:10px; font-size: 16px; color:#7F99BE;"><img src="${basePath}/static/gdstyle/image/blocks.gif" width="20" height="20" align="absmiddle" /> 广东广电网络</span>
	  
	   <div>
			<ul id="showmenu">
			<c:forEach items="${menuList}" var="one_menu">
				<li id="${one_menu.id}">
				<a href="#" onclick="openLeftMenu(${one_menu.id})">${one_menu.menuName}</a>
				</li>
			</c:forEach>
			</ul>
		</div>  
   	  </div>
	</div>
