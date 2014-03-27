<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="firstUrl" value="${dir}/1" />
<c:url var="lastUrl" value="${dir }/${pages}" />
<c:url var="prevUrl" value="${dir }/${currentIndex - 1}" />
<c:url var="nextUrl" value="${dir }/${currentIndex + 1}" />
<input type="hidden" name="currentPage" value="${currentPage}">
<input type="hidden" name="size" value="${size}">
<c:set var="total" value="${amount}" />
		
<c:set var="pre" value="${currentPage gt 1}" scope="page"/>
<c:set var="end" value="${currentPage lt pages}" scope="page"/>

<div class="page">
[
<c:choose>
	<c:when test="${pre }">
		<a href="${firstUrl}">首 页</a>
	</c:when>
	<c:otherwise>
		首 页
	</c:otherwise>
</c:choose>
|
<c:choose>
	<c:when test="${pre }">
		<a href="${prevUrl}">上一页</a>
	</c:when>
	<c:otherwise>
		上一页
	</c:otherwise>
</c:choose>
|
<c:choose>
	<c:when test="${end }">
		<a href="${nextUrl}">下一页</a>
	</c:when>
	<c:otherwise>
		下一页
	</c:otherwise>
</c:choose>
|
<c:choose>
	<c:when test="${end }">
		<a href="${lastUrl}">末  页</a>
	</c:when>
	<c:otherwise>
		末  页
	</c:otherwise>
</c:choose>
]
总数:<c:out value="${total }"/>
页数:<select onchange="changePage(this)">
		<c:forEach var="i" begin="1" end="${pages}" step="1"> 
	     	 <c:choose>
	     	 	<c:when test="${currentIndex eq i}">
	     	 		<option value="${i }" selected>${i }/${pages}</option>
	     	 	</c:when>
	     	 	<c:otherwise>
	     	 		<option value="${i }">${i }/${pages}</option>
	     	 	</c:otherwise>
	     	 </c:choose>
	    </c:forEach>
	</select>	
</div>
<script type="text/javascript">
<!--
	//roll page
	function changePage(obj){
		var options = obj.getElementsByTagName("option");
		var sltnum = 0;
		for(var i = 0;i < options.length;i++){
			if(options[i].selected){
				sltnum = options[i].value;
				break;
			}
		}
		//alert("${dir }/"+sltnum+"${per}");
		document.location.href = "${dir }/"+sltnum+"${per}";
	}
//-->
</script>
<style>
	/*页码样式*/
	.page {padding:4px 6px 4px 0; font-size: 13px;  color:#313031;  font-family: Verdana,Tahoma,Arial,Helvetica,Sans-Serif; background-color: #fff; text-align:right; margin-top:20px}
	.page a{padding:5px 6px 4px 5px; margin:0px 3px; color:#333; border:1px solid #a9e6f4; text-decoration: none}
	.page a:hover{border:1px solid #a9e6f4; COLOR: #333; background:#a9e6f4}
	.pagination a:active{border:1px solid #a9e6f4; COLOR: #0066a7; background-color:#d2eaf6}
	.page span.disabled {padding:5px 6px 4px 5px; margin:0px 3px; color:#999; border:1px solid #e1e1e1;}
	table.pop{padding:0; margin:0; width:100%; text-align:center}
</style>