<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link type="text/css" rel="stylesheet" href="${ctx }/static/css/menu.css"/>

<!--左侧菜单开始-->
<script type="text/javascript">
 <!--//
	function showMenu(obj, noid) {
		var block = document.getElementById(noid);
		var n = noid.substr(noid.length - 1);
		if (noid.length == 4) {
			var ul = document.getElementById(noid.substring(0, 3))
					.getElementsByTagName("ul");
			var h2 = document.getElementById(noid.substring(0, 3))
					.getElementsByTagName("h2");
			for ( var i = 0; i < h2.length; i++) {
				h2[i].innerHTML = h2[i].innerHTML.replace("+", "-");
				h2[i].style.color = "";
			}
			obj.style.color = "#14508f";
			for ( var i = 0; i < ul.length; i++) {
				if (i != n) {
					ul[i].className = "no";
				}
			}
		} else {
			var span = document.getElementById("menu").getElementsByTagName(
					"span");
			var h1 = document.getElementById("menu").getElementsByTagName("h1");
			for ( var i = 0; i < h1.length; i++) {
				h1[i].innerHTML = h1[i].innerHTML.replace("+", "-");
				h1[i].style.color = "";
			}
			obj.style.color = "#fff";
			for ( var i = 0; i < span.length; i++) {
				if (i != n) {
					span[i].className = "no";
				}
			}
		}
		if (block.className == "no") {
			block.className = "";
			obj.innerHTML = obj.innerHTML.replace("-", "+");
		} else {
			block.className = "no";
			obj.style.color = "";
		}
	}
 
	function todo(url){
		parent.document.getElementById("mainFrame").src = url;
	}
	
	window.onload = function(){
		var spans = document.getElementsByTagName("span");
		spans[0].className = '';
	}
//-->
</script>
 <!--左侧菜单-->
</head>
<div id="menu">
	<c:forEach items="${menuList }" var="m1" varStatus="st1">
		<c:if test="${m1.status eq 1 }">
			<c:choose>
				<c:when test="${(m1.menuLevel eq 1) and (m1.hasNext eq 0)}">
					<h1 onclick="todo('${ctx}${m1.menuUrl}');">${m1.menuName }</h1>
				</c:when>
				<c:otherwise>
					<h1 onclick="javascript:showMenu(this,'NO${st1.count}')">${m1.menuName }</h1>
					<span id="NO${st1.count }" class="no">
						<c:forEach items="${m1.subMenuList }" var="m2" varStatus="st2">
							<c:if test="${m2.status eq 1 }">
								<c:choose>
									<c:when test="${m2.hasNext eq 0 }">
										<h2 onclick="todo('${ctx}${m2.menuUrl}');">${m2.menuName }</h2>
									</c:when>
									<c:otherwise>
										<h2 onClick="javascript:showMenu(this,'NO${st1.count }${st2.index }')">
											<a href="#">${m2.menuName }</a>
										</h2>
										<ul id="NO${st1.count }${st2.index }" class="no">
											<c:forEach items="${m2.subMenuList }" var="m3">
												<c:if test="${m3.status eq 1 }">
													<li><a href="#" onclick="todo('${ctx}${m3.menuUrl}');">${m3.menuName }</a></li>
												</c:if>
											</c:forEach>
										</ul>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</span>
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:forEach>
</div>