<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'processinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript" src="${ctx}/static/easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/Flotr2-master/flotr2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/Flotr2-master/flotr2.ie.min.js"></script>

		<style type="text/css">
				*{
						padding: 0px;
						margin: 0px;
				}
				.task{
					z-index: 5;
				}
				.waitprocess{
					background: gray;
					background:url(${ctx}/static/images/u447_normal.png) no-repeat; /*未审批背景图片*/
				}
				.passprocess{
					background: blue;
					background:url(${ctx}/static/images/u36_normal.png) no-repeat; /*审批通过背景图片*/
				}
				.currentprocess{
					background: red;
					background:url(${ctx}/static/images/u451_normal.png) no-repeat; /*当前节点背景图片*/
					border: 1px solid red;
					font-weight: bold;
					border-width: medium;
					border-collapse: separate;
					border-spacing: 5px
				}
				.info{
					display: none;	
				}
				.hiddenclass{
					display: none;
					z-index: 6;
				}
		</style>
		<script type="text/javascript">
			$(function(){
				var hid = 'hiddenclass';
				
				$('#outer .task').bind('click',function(){
						
				});
				$('#outer .task').bind('mouseover',function(){
						
					var _e_show = $('#showinfo');
					if(!_e_show.hasClass(hid)){
						return ;
					}
					var _h = $(this).find('.info').get(0).innerHTML;
					_e_show.empty();
					_e_show.append(_h);
					var _p_s_l = $(this).css('left');
					_p_s_l = _p_s_l.substring(0,_p_s_l.indexOf('px'));
					_p_s_l = new Number(_p_s_l);
					
					var _p_s_t = $(this).css('top');
					_p_s_t = _p_s_t.substring(0,_p_s_t.indexOf('px'));
					_p_s_t = new Number(_p_s_t);
					
					var _p_s_w = $(this).css('width');
					_p_s_w = _p_s_w.substring(0,_p_s_w.indexOf('px'));
					_p_s_w = new Number(_p_s_w);
					
					var _p_s_h = $(this).css('height');
					_p_s_h = _p_s_h.substring(0,_p_s_h.indexOf('px'));
					_p_s_h = new Number(_p_s_h);
					
					_e_show.css('left',(_p_s_l+_p_s_w+10));
					_e_show.css('top',(_p_s_t+_p_s_h));
					_e_show.removeClass(hid);
				});
				$('#outer .task').bind('mouseout',function(){
					var _e_show = $('#showinfo');
					if(_e_show.hasClass(hid)){
						return ;
					}
					
					_e_show.empty();
					_e_show.addClass(hid);
				});
			});
		</script>
		
		<script type="text/javascript">
			var transition = [];
			var maxbottom1=0;
			var maxright1=0;
			//针对节点对点
			function obtainPositionByPoint(source,target){
				var point, element,elementpoint;
				if(source instanceof Array){
					point = source;
					element = target;
				}
				else {
					point = target;
					element = source;
				}
				if(element.left.y <0){
					element.left.y = 0-element.left.y;
				}
				if(element.bottom.y <0){
					element.bottom.y = 0-element.bottom.y;
				}
				if(element.right.y <0){
					element.right.y = 0-element.right.y;
				}
				if(element.top.y <0){
					element.top.y = 0-element.top.y;
				}
				
				
				for (var i in point){
					if(point[i] instanceof String){
						point[i] = new Number(point[i]);
					}
					if(point[i] < 0 ){
						point[i] = 0-point[i];
					}
					
				}
				if(element.left.x > point[0]){
					elementpoint=[];
					elementpoint.push(element.left.x);
					elementpoint.push(element.left.y);
				}else if(element.right.x < point[0]){
					elementpoint=[];
					elementpoint.push(element.right.x);
					elementpoint.push(element.right.y);
				}else if(element.top.y > point[1]){
					elementpoint=[];
					elementpoint.push(element.top.x);
					elementpoint.push(element.top.y);
				}
				else{
					elementpoint=[];
					elementpoint.push(element.bottom.x);
					elementpoint.push(element.bottom.y);
				}
				var o ={};
				if(point[1]>0){
					point[1]=0-point[1];
				}
				if(elementpoint[1]>0){
					elementpoint[1]=0-elementpoint[1];
				}
				if(source instanceof Array){
					o.begin = point;
					o.end = elementpoint;
					
					var j = obtainJianTou(point,elementpoint);
					if(j!=null){
						o.step1 = j.step1;
						o.step2 = j.step2;
					}
				}
				else{
					o.begin = elementpoint;
					o.end = point;
				}
				return o;
				
			}
			
			// 获取 线路的起始和终点坐标  针对节点对节点
			function obtainPosition(source,target){
				sorceelement =[];
					targetelement=[];
					if(source.left.y <0){
						source.left.y = 0-source.left.y;
					}
					if(source.bottom.y <0){
						source.bottom.y = 0-source.bottom.y;
					}
					if(source.right.y <0){
						source.right.y = 0-source.right.y;
					}
					if(source.top.y <0){
						source.top.y = 0-source.top.y;
					}
					if(target.left.y <0){
						target.left.y = 0-target.left.y;
					}
					if(target.bottom.y <0){
						target.bottom.y = 0-target.bottom.y;
					}
					if(target.right.y <0){
						target.right.y = 0-target.right.y;
					}
					if(target.top.y <0){
						target.top.y = 0-target.top.y;
					}
					
					if(source.right.x < target.left.x){
						if(source.top.y > target.bottom.y){
							sorceelement.push(source.right.x);
							sorceelement.push(source.right.y);
							targetelement.push(target.bottom.x);
							targetelement.push(target.bottom.y);
						}
						else if(source.bottom.y < target.top.y){
							sorceelement.push(source.right.x);
							sorceelement.push(source.right.y);
							targetelement.push(target.top.x);
							targetelement.push(target.top.y);
						}
						else{
							sorceelement.push(source.right.x);
							sorceelement.push(source.right.y);
							targetelement.push(target.left.x);
							targetelement.push(target.left.y);
						}
					}
					else if(source.left.x > target.right.x){
						if(source.top.y > target.bottom.y){
							sorceelement.push(source.left.x);
							sorceelement.push(source.left.y);
							targetelement.push(target.bottom.x);
							targetelement.push(target.bottom.y);
						}
						else if(source.bottom.y < target.top.y){
							sorceelement.push(source.left.x);
							sorceelement.push(source.left.y);
							targetelement.push(target.top.x);
							targetelement.push(target.top.y);
						}
						else{
							sorceelement.push(source.left.x);
							sorceelement.push(source.left.y);
							targetelement.push(target.right.x);
							targetelement.push(target.right.y);
						}
					}
					else if(source.top.y > target.bottom.y){
						sorceelement.push(source.top.x);
						sorceelement.push(source.top.y);
						targetelement.push(target.bottom.x);
						targetelement.push(target.bottom.y);
					}
					else {
						sorceelement.push(source.bottom.x);
						sorceelement.push(source.bottom.y);
						targetelement.push(target.top.x);
						targetelement.push(target.top.y);
					}
					sorceelement[1]=0-sorceelement[1];
					targetelement[1]=0-targetelement[1];
					var result = {begin : sorceelement ,end : targetelement};
					
					var j = obtainJianTou(sorceelement,targetelement);
					if(j!=null){
						result.step1 = j.step1;
						result.step2 = j.step2;
					}
					
				return result;

			}
			
			function obtainJianTou(sorceelement,targetelement){
				var step1,step2;
				
				if(Math.abs(Math.abs(targetelement[0])-Math.abs(sorceelement[0]))<20){
					
					if(Math.abs(targetelement[1])>Math.abs(sorceelement[1])){
						step1 = [targetelement[0]-10,targetelement[1]+20];
						step2 = [targetelement[0]+10,targetelement[1]+20];
						
					}
					else{
						step1 = [targetelement[0]-10,targetelement[1]-20];
						step2 = [targetelement[0]+10,targetelement[1]-20];
					}
				}
				
				else if(Math.abs(Math.abs(targetelement[1])-Math.abs(sorceelement[1]))<20){
					var step1,step2;
					if(Math.abs(targetelement[0])>Math.abs(sorceelement[0])){
						step1 = [targetelement[0]-20,targetelement[1]+10];
						step2 = [targetelement[0]-20,targetelement[1]-10];
					}
					else{
						step1 = [targetelement[0]+20,targetelement[1]+10];
						step2 = [targetelement[0]+20,targetelement[1]-10];
					}
				}
				var result =[];

				
				if(step1!=null && step1!=''){
					return {'step1' : step1,'step2':step2};
				}
				return null;
			}
		</script>
	</head>

	<body style="text-align: center;">
	<div style="margin-left: auto;margin-right: auto;" id="outer">
	<c:if test="${requestScope.processdefinition != null }">
		<c:if test="${requestScope.processdefinition.initial !=null }">
			<c:set var="maxleft" value="0"></c:set>
			<c:set var="maxtop" value="0"></c:set>
			<c:set var="maxwidth" value="0"></c:set>
			<c:set var="maxheight" value="0"></c:set>
			<div style="position: relative;" id="process">
						
			<c:forEach items="${requestScope.processdefinition.activities }" var="row">
				<div style="position: absolute;" id="showinfo" class="hiddenclass"></div>
				<c:set var="itemsclass" value="waitprocess"></c:set>
				<c:if test="${requestScope.historyexecution !=null}">
					<c:if test="${not empty requestScope.historyexecution[row.name]}">
						<c:if test="${requestScope.historyexecution[row.name][0].endTime!=null }">
							<c:set var="itemsclass" value="passprocess"></c:set>
						</c:if>
						<c:if test="${requestScope.historyexecution[row.name][0].endTime==null }">
							<c:set var="itemsclass" value="currentprocess"></c:set>
						</c:if>
					</c:if>
				</c:if>
				
				<c:if test="${row.type == 'start' }">
					<c:set var="itemsclass" value="passprocess"></c:set>
				</c:if>
				<div class="${ itemsclass} ${row.type}" style="position: absolute;left:${row.coordinates.x}px;top:${row.coordinates.y}px; width:${row.coordinates.width}px;height:${row.coordinates.height}px;">
					<span><br/><br/><br/><br/><c:out value="${ row.name}"></c:out></span>
					<div class="info">
						<ul>
							<c:forEach  var="row1"  items="${requestScope.historyexecution[row.name]}">
								<li>环节开始时间 ${row1.startTime } 
									<c:if test="row1.endTime != null">
										结束时间${row1.endTime } 
									</c:if>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<c:if test="${row.coordinates.x > maxleft }" >
						<c:set var="maxleft" value="${row.coordinates.x}"></c:set>
				</c:if>
				<c:if test="${row.coordinates.y > maxtop }" >
						<c:set var="maxtop" value="${row.coordinates.y}"></c:set>
				</c:if>
				<c:if test="${row.coordinates.width > maxwidth }" >
						<c:set var="maxwidth" value="${row.coordinates.width}"></c:set>
				</c:if>
				<c:if test="${row.coordinates.height > maxheight }" >
						<c:set var="maxheight" value="${row.coordinates.height}"></c:set>
				</c:if>
				<c:if test="${row.outgoingTransitions != null}">
					<script>
						
						(function(){
							var source = {left:'',bottom:'',right:'',top:''};
							var target = {left:'',bottom:'',right:'',top:''};
							var oleft,otop,owidth,oheight,sorceelement,targetelement,posi,po,dytr,dytrs,zj,lastnew;
							<c:forEach items="${row.outgoingTransitions}"   var="transition">
								oleft = <c:out value="${row.coordinates.x}"/>
								otop = <c:out value="${row.coordinates.y}"/>
								owidth = <c:out value="${row.coordinates.width}"/>
								oheight = <c:out value="${row.coordinates.height}"/>
								source.left = {x:oleft,y:otop+0.5*oheight};
								source.bottom = {x:oleft+0.5*owidth,y:otop+oheight};
								source.right =  {x:oleft+owidth,y:otop+0.5*oheight};
								source.top = {x:oleft+0.5*owidth,y:otop};
								
								<c:if test="${transition.destination != null}">
									oleft = <c:out value="${transition.destination.coordinates.x}"/>
									otop = <c:out value="${transition.destination.coordinates.y}"/>
									owidth = <c:out value="${transition.destination.coordinates.width}"/>
									oheight = <c:out value="${transition.destination.coordinates.height}"/>
									target.left = {x:oleft,y:otop+0.5*oheight};
									target.bottom = {x:oleft+0.5*owidth,y:otop+oheight};
									target.right =  {x:oleft+owidth,y:otop+0.5*oheight};
									target.top = {x:oleft+0.5*owidth,y:otop};
									
									<c:if  test="${transition.description != null && fn:indexOf(transition.description, ':')>-1}">
										
										dytr = "${transition.description}";
										
										dytr = dytr.split(':')[0];
										dytrs = dytr.split(';');
										posi = [];
										lastnew = [];
										for(var s in dytrs){
											zj = dytrs[s].split(',');
											
											var x2 = new Number(zj[0]);
											var y2 = new Number(zj[1]);
											if(x2 > maxright1){
												
												maxright1 = x2;
												
											}
											if(y2 > maxbottom1){
												maxbottom1 = y2;
												
											}
											
											if(s==0){
												po = obtainPositionByPoint(source,zj);
												posi.push(po.begin);
												posi.push(po.end);
											}
											
											if(dytrs.length != 1 && s!=0 && s!=(dytrs.length-1)){
												posi.push(lastnew);
												posi.push([new Number(zj[0]),0-new Number(zj[1])]);
											}
											lastnew = [];
											lastnew.push(new Number(zj[0]));
											lastnew.push(new Number(zj[1]));
											
											if(s==(dytrs.length-1)){
												po = obtainPositionByPoint(lastnew,target);
												posi.push(po.begin);
												posi.push(po.end);
												if(po.step1!=null && po.step1 !=''){
													posi.push(po.step1);
													posi.push(po.end);
													posi.push(po.step2);
												}
											}
										}
										
										transition.push(posi);
									</c:if>
									<c:if  test="${transition.description == null || fn:indexOf(transition.description, ':')<0}">
										
										po = obtainPosition(source,target);
										
										posi = [];
										posi.push(po.begin);
										posi.push(po.end);
										if(po.step1 !=null && po.step1 !=''){
											posi.push(po.step1);
											posi.push(po.end);
											posi.push(po.step2);
											
										}
										transition.push(posi);
										
									</c:if>
								</c:if>
							</c:forEach>
						})(window);
					</script>
				</c:if>
			</c:forEach>
			<div id="container"  style="position: absolute;left: 0px;top: 0px;z-index: 1;"></div>
			</div>
		</c:if>
	</c:if>
	</div>
	
	<script type="text/javascript">
		var grap = {};
		grap=(function(){
			var maxleft = ${maxleft};
			var	maxtop=${maxtop};
			var	maxwidth=${maxwidth};
			var	maxheight=${maxheight};
			
			
			if(maxleft < maxright1){
				
				maxleft=maxright1;
				
			}
			if(maxtop < maxbottom1){
				maxtop=maxbottom1;
				
			}
			var width1 = maxleft+maxwidth+10;
			var height1 = maxtop+maxheight+10;
			$("#container").css({ width : width1+'px',height: height1+'px'});
			$("#outer").css({ width : width1+'px'});
			return {x:width1,y:height1};
		})();
	</script>
	<script type="text/javascript">
	
		(function(){
			var 
			container = document.getElementById('container'),
			start = (new Date).getTime(),
			data, graph, offset, i;
			
			graph = Flotr.draw(container,transition, {
				colors : ['gray','gray','gray','gray','gray'],
				grid : {
					color : '#FFFFFF',
					horizontalLines : false,
					verticalLines : false,
					labelMargin : 0,
					outlineWidth : 0,
					outline : null
	
				},
				lines : {
					show : true
	
				},
				xaxis : {
					min : 0,
					max :grap.x,
					noTicks : 0
				},
				yaxis : {
					min : (0-grap.y),
					max :0,
					noTicks : 0
				},
				bars : {
					topPadding: 0,
					lineWidth: 0,
					barWidth :0
				}
			});
			
		})();			
</script>
</body>
</html>
