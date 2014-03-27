<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
	<title>后台管理</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/javascript/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/javascript/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/admin/javascript/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="<%=path %>/admin/javascript/jquery.easyui.min.js"></script>
	<style>
		.nav1 .nav2{
			cursor: pointer;
		}
	</style>
	
	
	<script>
		
		
		
		
	function clone(obj){
		var o;
		switch(typeof obj){
			case 'undefined' : break;
			case 'string' : o=obj+'';break;
			case 'number' : o=obj+0;break;
			case 'boolean' : o=obj;break;
			case 'object' : 
				if(obj===null){
					return null;
				}else {
					if(obj instanceof Array){
						o=[];
						for(var i=0;i<obj.length;i++){
							o.push(clone(obj[i]));
						}
					}
					else{
						o={};
						for(var k in obj){
							o[k]=clone(obj[k]);
						}
					}
				}
				break;
			default : o =obj;break;
				
		}
		return o;
	}
		
		
		function addmenu(){
			var header = $('.layout-expand .layout-button-down').parent().parent();
			var menu = $('<div style="position:absolute;left:0;top:0;background:#fafafa;"></div>').appendTo(header);
			var btn = $('<a href="#">test</a>').appendTo(menu);
			btn.menubutton({
				menu:'#mymenu'
			});
		}
		function addTab(obj){
			if ($('#tt').tabs('exists', obj['title'])){
        		$('#tt').tabs('select', obj['title']);
		    } else {
		        $('#tt').tabs('add',{
		            title:obj['title'],
		            content:obj['content'],
		            closable:true
		        });
				
		    }
		} 
		

		$(function(){
			$('.addPanel .nav2').click(function(event){
			
				var title = event.target.innerHTML;
		        var id = event.target.getAttribute('tableid');
		        var src = event.target.getAttribute('demosrc');
		        var array = [];
		       	
		       	var array=[];
		       	
		       	var widths=$('.tabs-panels').get(0).style.width;
		       	var hights=$('.tabs-panels').get(0).style.height;
		       
		       	
		       	array.push('<div style="text-align:center;">');
		       	array.push('<iframe height="'+hights+'" width="'+widths+'" frameborder=0 src="'+src+'">')
		       	array.push('</iframe>');
		       	array.push('</div>');
		       	var obj={'title':title,'content':array.join('')};
		       	addTab(obj);
			});
			
			
			
		});


		

		function cancelClick(e){
			if (window.event){
				window.event.cancelBubble = true;
				window.event.returnValue = false;
			}
			if (e && e.stopPropagation && e.preventDefault){
				e.stopPropagation();
				e.preventDefault();
			}
		}
		function getLocalData(row,name){  //row 从0开始
			var tab = $('#tt').tabs('getSelected');
			var titl = tilteid[tab.panel('options').title];
			var data = $.data($('#'+titl).get(0),"datagrid").data;
			var col = data['rows'];
			return col[row][name];
		}
		


	

		function cancelClick(e){
			if (window.event){
				window.event.cancelBubble = true;
				window.event.returnValue = false;
			}
			if (e && e.stopPropagation && e.preventDefault){
				e.stopPropagation();
				e.preventDefault();
			}
		}
		function hcodedaoaru(){
		
			if($.browser.msie){
				window.showModelessDialog('<%=path%>/admin/upload.jsp','dialogwin','dialogWidth:400px;dialogHeight:400px;edge:sunken;center:yes');
			}
			else window.open('<%=path%>/admin/upload.jsp','dialogwin','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,height=400,width=400;center:yes');
			
			
		}
	</script>
	<style>
		.nav1{
			height: 25px !important;
			height: 35px;
			padding-top : 10px;
			background-color: #E0ECFF;
			width: 100%;
			border-bottom: 1px solid #99BBE8;
			text-align: center;
		}
		.nav2{
			height: 25px !important;
			height: 35px;
			padding-top : 10px;
			background-color: #E0ECFF;
			width: 100%;
			border-bottom: 1px solid #99BBE8;
			text-align: center;
		}
		.quelabel{
			display: inline-block;
			width: 80px;
			padding-right:5px;
			text-align: right;
		}
		.formdiv label{
			display: inline-block;
			width: 80px;
			padding-right:5px;
			text-align: right;
		}
		.formdiv{
			margin-top:8px;
		}
			
			.module110 div{
				
				text-align:left;
			}
			.module111 span{
				display : inline-block;
			}
			.pic1{
				background: url('<%=path %>/admin/image/exit.jpg') no-repeat;
			}
			.pic2{
				background: url('<%=path %>/admin/image/exit_d.jpg') no-repeat;
			}
	</style>
</head>
<body class="easyui-layout">
	<div id="mymenu" style="width:150px;">
		<div></div>
		<div></div>
	</div>
		<div region="north" title="<s:property value='#session.userrole.rolename'/> <s:property value='#session.userinfo.username'/>:欢迎来到后台管理页面" split="true" style="height:93px;">
			<div style="width:100%;height: 60px;background: url('<%=path %>/admin/image/bg.jpg') repeat-x; ">
				<div style="width:30%;height: 60px;background: url('<%=path %>/admin/image/logo.jpg') no-repeat;float: left;">
				
				
				</div>
				<div style="float: left;width: 70%;position: relative;height: 60px;">
					<div style="width:38px;height: 25px;position: absolute;right:15px;" class="pic1" id="logoutd">
							
					</div>
				</div>
			</div>
		</div>
		
		<div region="west" split="true" title="后台管理选项" style="width:280px;padding1:1px;overflow:hidden;">

			<div class="easyui-accordion" fit="true" border="false">
				
				 
				 		<div title="" class="addPanel" selected="true">
				 				<div class="nav2" demosrc="<%=path %>/sigong/add.jsp" tableid="weituo">委托施工</div>
				 				<div class="nav2" demosrc="<%=path %>/sigong/projectList.jsp" tableid="weituo">委托施工审批</div>
				 			
				 		</div>

			</div>
		</div>
		<div region="center" title="内容查看" style="overflow:hidden;">
			<div class="easyui-tabs" fit="true" border="false" id="tt">
				<div title="欢迎" style="padding:20px;overflow:hidden;"> 
					欢迎使用本系统
				</div>
				
			</div>
		</div>
		<div style="" id="okxiala">
		
		</div>
		<div id="dd" icon="icon-save" style="padding:5px;width:250px;height:200px;">
		</div>
		<div id="editdiv" style="width: 290px;height: 250px;">
			<form  id="form11" method="post">
				
			</form>
		</div>
		<div id="dd1" icon="icon-save" style="padding:5px;width:900px;height:450px;">
		
		</div>
		<input type="text" value="<s:property value='autho'/>">
</body>
<script type="text/javascript">
		function runEx(code) {
			    if (code != "") {
			        var newwin = window.open("", "", "");
			        newwin.opener = null;
			        newwin.document.write(code);
			        newwin.document.close();
			    }
			}
			var dom = {
			                    quote: function (str) {
			                        str = str.replace(/[\x00-\x1f\\]/g, function (chr) {
			                            var special = metaObject[chr];
			                            return special ? special : '\\u' + ('0000' + chr.charCodeAt(0).toString(16)).slice(-4)
			                        });
									return str.replace(/"/g, '\\"') ;
			                     
			                    }
			                },
			                metaObject = {
			                    '\b': '\\b',
			                    '\t': '\\t',
			                    '\n': '\\n',
			                    '\f': '\\f',
			                    '\r': '\\r',
			                    '\\': '\\\\'
			                };
			(function(w){
			    w.Template=Template||{};
				function Template(options){
				    return this instanceof arguments.callee?this.init(options):new arguments.callee(options);
				}
				Template.parse=function(self){
				       var temp;
					   if(self.right=="}}"){//这里主要是为了解决{{{造成的bug!
					      temp=self.tplcontent.replace(/(}})([^}])/g,function(){
						     return arguments[1]+"   "+arguments[2];
						  }).split(new RegExp('(?='+self.left+')|('+self.right+')(?:[^}])'))
					   }else{
					      temp=self.tplcontent.split(new RegExp('(?='+self.left+')|('+self.right+')'))
					   }
						temp.filters(function(k,v){
			                   return !(new RegExp(self.right)).test(v);
			            }).each(
						  function(k,v){
						    if((new RegExp('^'+self.left)).test(v)){
							    if(new RegExp('^'+self.left+'\s*=').test(v)){
							       self.body.push(v.replace(new RegExp('^'+self.left+'\s*=(.*)'),'\ttemp.push($1);\n').replace(/\\n/g,''));
							    }else{
								   self.body.push(v.replace(new RegExp('^'+self.left+'\s*(.*)'),'$1\n').replace(/\\n/g,''));
								}
							}
							else {self.body.push('\ttemp.push(\"'+v.replace(/\n|\t/g,'')+'\");\n');}
						  })
						  return self.body.join("");
					};
				Template.prototype={
					init:function(options){
						this.tpl=options.tpl;//待解析的模版
						this.target=options.target||options.tpl;//解析后渲染的模板dom
					    this.tplcontent=dom.quote(options.tpl.text||options.tpl.value);
					    this.left=options.left||"{{";//左分隔符
						this.right=options.rigth||"}}";//右分隔符
						this.namespace=options.namespace||"data";//编译生成的函数，里边所用变量的环境,即模版中所用变量的执行环境
						this.body=[];
						this.compiled="";//编译生成的函数
						this.data=options.data;
					},
					compile:function(){
						if(!this.compiled){
							this.compiled=new Function(this.namespace,'var temp=[];\n'+Template.parse(this)+'\n return  temp.join("");');
						}
						return this.compiled;
					},
					render:function(){
						this.target.innerHTML=this.compile()(this.data);
						return this.compile()(this.data);
					}
				}
			})(this);
			 Array.prototype.filters=function(fn){
			   var temp=[];
			
			   for(var i=0,l=this.length;i<l;i++){
			      this[i]&&fn.call(this,i,this[i])&&temp.push(this[i]);
			   } 
			  return temp;
			}
			Array.prototype.each=function(fn){
			   var temp=[];
			   for(var i=0,l=this.length;i<l;i++){
			     fn.call(this,i,this[i]);
			   } 
			   return this;
			};
</script>

<script>
	$(function(){
		$('#logoutd').bind('mouseover',function(event){
			this.className='pic2';
			cancelevent(event);
		});
		$('#logoutd').bind('mouseleave',function(event){
			this.className='pic1';
			cancelevent(event);
		});
		$('#logoutd').bind('click',function(event){
			if(confirm('是否退出系统!')){
				document.forms[0].action="admin/logoutAction.action";
				document.forms[0].submit();
			}
		});
	});
	
	function cancelevent(e){
		if (window.event){
			window.event.cancelBubble = true;
			window.event.returnValue = false;
		}
		if (e && e.stopPropagation && e.preventDefault){
			e.stopPropagation();
			e.preventDefault();
		}
	}
</script>
</html>