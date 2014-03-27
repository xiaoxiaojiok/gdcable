<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>权限管理</title>
<link type="text/css" rel="stylesheet" href="${ctx }/static/css/table.css"/>
<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/base/base.js"></script>
<script type="text/javascript" src="${ctx }/static/js/syspurview/role/role.js"></script>

<script type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>

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
		   url:'${ctx}/permission/delete',
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
			    	  alert('删除失败!');
			       }
		       }
	  });
	  
  }
  /*
  * 修改
  */
  function update(permid)
  {
	  window.location.href="/permission/update/"+permid +"?currentIndex=${currentPage}";
  }
//-->
</script>
<div class="weizhi">你的位置是：权限管理>>权限列表</div>
<form name="" method="get"  action="${ctx}/permission/index">
<table class="biaoge_1">
  <tr>
    <td class="biaoge_11">查询条件</td>
    <td class="biaoge_12">
      菜单名称：
      <input class="text_1" name="menuName" id="menuName" type="text" value="${menuName}"/>
    </td>
  </tr>
  <tr>
    <td class="biaoge_11">操作</td>
    <td class="biaoge_12">
    <input class="button_1" name="sub" type="submit" value="查询" />
     <input class="button_1" name="del" type="button" value="删除" onclick="delBatch();"/>
    <input class="button_1" name="add" type="button" onclick="window.location.href='/permission/create'" value="添加" />
    </td>
  </tr>
</table>


<br />
<table class="biaoge_2" width="100%">
  <tr>
    <th><input name="check" id="check" type="checkbox" value="" onclick="checkAll();" />全选</th>
    <th>权限名称</th>
    <th>权限描述</th>
    <th>权限值</th>
    <th>权限所属菜单</th>
    <th>权限类型</th>
    <th>操作</th>
  </tr>
  <c:forEach items="${collection}" var="permission" varStatus="status">
	 <c:choose>
		<c:when test="${(status.count mod 2) eq 0}">
			<tr class="biaoge_21" ondblclick="edit(${subSys.id});" style="cursor:pointer">
		</c:when>
		<c:otherwise>
			<tr class="biaoge_22" ondblclick="edit(${subSys.id});" style="cursor:pointer">
		</c:otherwise>
	</c:choose>
	    <td><input name="childcheck" type="checkbox"  value="${permission.id }" /></td>
	    <td>${permission.permName}</td>
	    <td>${permission.descript}</td>
	    <td>${permission.permValue}</td>
	    <td>${permission.menu.menuName}</td>
	    <td> 
	    	<c:if test="${permission.permType eq 1 }" >功能</c:if>
	    	<c:if test="${permission.permType eq -1 }" >菜单</c:if>
	    </td>
	    <td>    
	    <input class="button_2" name="" type="button" value="修改" onclick="update(${permission.id});"/>
	    </td>
	  </tr>
  </c:forEach>
  
</table>
<c:set var="dir" value="${ctx }/permission/index" scope="request"/>
<jsp:include page="../paging.jsp"/>
</form>
<div class="modal"><!-- Place at bottom of page --></div>
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