<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理</title>
<script type="text/javascript">
<!--
$(document).ready(function(){
	$("body").on({
	    ajaxStart: function() { 
	        $(this).addClass("loading"); 
	    },
	    ajaxStop: function() { 
	        $(this).removeClass("loading"); 
	    }    
	});
});
  /*
  * 全选 、反选
  */
  function checkAll()
  {
	if($("#check").attr("checked"))
	 {
		$("[name='childcheck']").attr("checked",'true');//全选
	 }
	else
	 {
		$("[name='childcheck']").removeAttr("checked");//取消全选 
	 }
  }
  /*
  * 批量删除
  */
  function delBatch()
  {
	  var v ='';
	  $("[name='childcheck'][type='checkbox']:checked").each(function(){ 
		  v += $(this).val() +',';
	  });
	  if(v=='' || v==undefined)
	  {
		alert("请选择您要删除的记录!"); 
		return;
	  }
	  if(v.length>1)
	  {
		  v = v.substring(0, v.lastIndexOf(','));
	  }
	  $.ajax({
		   url:'${ctx}/role/delete',
		   type:'post', //数据发送方式 
		   async:false,//同步
		   dataType:'text', //接受数据格式       
		   data:{'ids':v}, //要传递的数据       
		   success:
			   function (data){//回传函数(这里是函数名) 
			      if(data=='true')
			       {
			    	 alert('删除成功!');
			    	 window.location.reload(true);
			       }
			      else
			       {
			    	  alert('删除失败,已有用户关联此角色!');
			       }
		       }
	  });
	  
  }
  /*
  * 修改
  */
  function update(roleid)
  {
	  window.location.href="/role/update/"+roleid +"?currentIndex=${currentIndex}";
  }
  /*
  * 停用
  */
  function stopRole(roleid)
  {
	  $.ajax({
		   url:'${ctx}/role/stop/'+roleid,
		   type:'get', //数据发送方式 
		   async:false,//同步
		   dataType:'text', //接受数据格式       
		   data:null, //要传递的数据       
		   success:
			   function (data){//回传函数(这里是函数名) 
			      if(data=='true')
			       {
			    	 alert('角色停用成功!');
			    	 window.location.reload(true);
			       }
			      else
			       {
			    	  alert('角色停用失败!');
			       }
		       }
	  });
  }
  /*
  * 启用
  */
  function startRole(roleid)
  {
	  $.ajax({
		   url:'${ctx}/role/start/'+roleid,
		   type:'get', //数据发送方式 
		   async:false,//同步
		   dataType:'text', //接受数据格式       
		   data:null, //要传递的数据       
		   success:
			   function (data){//回传函数(这里是函数名) 
			      if(data=='true')
			       {
			    	 alert('角色启用成功!');
			    	 window.location.reload(true);
			       }
			      else
			       {
			    	  alert('角色启用失败!');
			       }
		       }
	  });
  }
  /*
  * 授权
  */
  function accredit(roleid)
  {
	  window.location.href="${ctx}/role/accredit/"+roleid;
  }
  
  function searchRole(){
	  window.location.href="${ctx}/role/list?roleName="+encodeURI($("#roleName").val());
  }
//-->
</script>

<style>
.modal {
    display:    none;
    position:   fixed;
    z-index:    1000;
    top:        0;
    left:       0;
    height:     100%;
    width:      100%;
    background: rgba( 255, 255, 255, .8 ) 
                url('http://i.stack.imgur.com/FhHRx.gif') 
                50% 50% 
                no-repeat;
}

/* When the body has the loading class, we turn
   the scrollbar off with overflow:hidden */
body.loading {
    overflow: hidden;   
}

/* Anytime the body has the loading class, our
   modal element will be visible */
body.loading .modal {
    display: block;
}
</style>
</head>
<body>
<div class="weizhi">你的位置是：角色管理>>角色列表</div>
<form name="" method="get" action="${ctx}/role/list">
<table class="biaoge_1">
  <tr>
    <td class="biaoge_11">查询条件</td>
    <td class="biaoge_12">
      角色名称：
      <input class="text_1" name="roleName" id="roleName" type="text"  value="${roleName}"/>
    </td>
  </tr>
  <tr>
    <td class="biaoge_11">操作</td>
    <td class="biaoge_12">
    <input class="button_1" name="sub" type="submit" value="查询" />
     <input class="button_1" name="del" type="button" value="删除" onclick="delBatch();"/>
    <input class="button_1" name="add" type="button" onclick="window.location.href='/role/create'" value="添加" />
    </td>
  </tr>
</table>


<br />
<table class="biaoge_2" width="100%">
  <tr>
    <th><input name="check" id="check" type="checkbox" value="" onclick="checkAll();" />全选</th>
    <th>角色名称</th>
    <th>角色描述</th>
    <th>状态</th>
    <th>操作</th>
  </tr>
  <c:forEach items="${collection}" var="role" varStatus="status">
	 <c:choose>
		<c:when test="${(status.count mod 2) eq 0}">
			<tr class="biaoge_21" ondblclick="edit(${subSys.id});" style="cursor:pointer">
		</c:when>
		<c:otherwise>
			<tr class="biaoge_22" ondblclick="edit(${subSys.id});" style="cursor:pointer">
		</c:otherwise>
	</c:choose>
	    <td><input name="childcheck" type="checkbox"  value="${role.id }" /></td>
	    <td>${role.name }</td>
	    <td>${role.description}</td>
	    <td>
	    	<c:if test="${role.status eq 1 }">启用</c:if>
	    	<c:if test="${role.status eq -1 }">停用</c:if>
	    </td>
	    <td>    
	    <input class="button_2" name="" type="button" value="修改" onclick="update(${role.id});"/>
	    <c:if test="${role.status eq 1 }">
	    	<input class="button_2" name="" type="button" value="停用" onclick="stopRole(${role.id})"/>
	    </c:if>
	    <c:if test="${role.status eq -1 }">
	    	<input class="button_2" name="" type="button" value="启用" onclick="startRole(${role.id})"/>
	    </c:if>
	    <input class="button_2" name="" type="button" value="授权" onclick="accredit(${role.id})"/>
	    </td>
	  </tr>
  </c:forEach>
  
</table>
<jsp:include page="../paging.jsp"/>
</form>
<div class="modal"><!-- Place at bottom of page --></div>

</body>
</html>