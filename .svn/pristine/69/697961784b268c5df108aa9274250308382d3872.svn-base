<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
		var maxPage = 0;
		function toPage(k){
			var page = 0;
			if(maxPage==0){
				page = 1;
			}else if(k>0 && k<=maxPage){
				page = k;
			}else{
				return;
			}
			<c:if test="${empty _formName}">
			  var form=document.forms[document.forms.length-1];
			</c:if>
			<c:if test="${!empty _formName}">
			  var form=document.forms['${_formName}'];
			</c:if>
			  form.elements['currentPage'].value=page;
			  form.submit();
		}
		function toPreviousPage(){
		  toPage(${currentPage-1});
		}
		
		function toNextPage(){
			toPage(${currentPage+1});
		}
		function toEndPage(){
		  toPage(${pages});
		}
		function getPagingFunction(i){
			return function(){
				toPage(i);
			}
		}
		function createPaging(){
			var maxPageNumber=10;//odd
			var centerNumber=(maxPageNumber-maxPageNumber%2)/2;
			var pages="${pages}";
			var currentPage="${currentPage}";
			var dcount = "${amount}";
			if(""==pages){
				maxPage = 0;
			}else{
				maxPage = parseInt(pages);
			}
			var c = 0;
			var d = 0;
			if(""!=currentPage){
				c = parseInt(currentPage);
			}
			if(""!=dcount){
				d = parseInt(dcount);
			}
			showPageList(c, maxPage,d);
		}
		/* -------------- 导航条显示 -----------------*/
		/**
		*	打印导航条
		*/
		function showPageList(nowPage, maxPage,dcount) {
			if(dcount<=0){
				nowPage = 0;
				maxPage = 0;
			}
			var pageList = "";
			if(dcount>0 && maxPage >0){
				pageList += ("<span class='page'>");
				if(nowPage == 1){
					pageList += ("\u9996\u9875&nbsp;");
					pageList += ("\u4e0a\u4e00\u9875&nbsp;");
				}else if(nowPage > 1 ){
					pageList += ("<a href='javascript:toPage(1)'>\u9996\u9875</a>");
					pageList += ("<a href='javascript:toPage(" + (nowPage - 1) + ")'>\u4e0a\u4e00\u9875</a>");
				}
				var mx = 5;
				if (maxPage < 5) {
					mx = maxPage;
				}
				/* 处理最近几个页面 */
				if (nowPage <= 2) {
					for (var i = 1; i <= mx; i++) {
						if (i == nowPage) {
							pageList += ( nowPage);
						} else {
							pageList += ("<a href='javascript:toPage(" + i + ")'>" + i + "</a>");
						}
					}
				} else {
					if (nowPage > (maxPage - 2) && nowPage > 2) {
						var temp_max = 1;
						if(maxPage>4){
							temp_max = maxPage - 4;
						}
						for (var i = temp_max; i <= maxPage; i++) {
							if (i == nowPage) {
								pageList += (nowPage);
							} else {
								pageList += ("<a href='javascript:toPage(" + i + ")'>" + i + "</a>");
							}
						}
					} else {
						for (var i = nowPage - 2; i <= nowPage + 2; i++) {
							if (i == nowPage) {
								pageList += (nowPage);
							} else {
								pageList += ("<a href='javascript:toPage(" + i + ")'>" + i + "</a>");
							}
						}
					}
				}
				/* end */
				if(nowPage == maxPage){
					pageList += ("<a >\u4e0b\u4e00\u9875</a></a>");
					pageList += ("<a >\u5c3e\u9875</a>");
				}else{
					pageList += ("<a href='javascript:toPage(" + (nowPage + 1) + ")'>\u4e0b\u4e00\u9875</a>");
					pageList += ("<a href='javascript:toPage(" + maxPage + ")'>\u5c3e\u9875</a>");
				}			
			}
			pageList += ("\u5171" + maxPage + "\u9875"+"&nbsp;${amount }条记录&nbsp;");
			pageList += ("\u8f6c\u5230");
			pageList += ("<input id='goWhichPage' type='text' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" /> \u9875");
			pageList += ("<a href='javascript:goWhichPage()' >GO</a>");
			pageList += ("</span>");
			document.getElementById("pagingDiv").innerHTML = pageList;
		}
		function goWhichPage(){
			var whichPage = document.getElementById("goWhichPage").value;
			toPage(whichPage);
			return;
		}
	</script>
<input type="hidden" name="currentPage" value="${currentPage}">
<input type="hidden" name="size" value="${size}">
<span id="pagingDiv"></span>
<script type="text/javascript">
	createPaging();
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
