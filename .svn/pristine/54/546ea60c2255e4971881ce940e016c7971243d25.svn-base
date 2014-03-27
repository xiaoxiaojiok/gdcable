/**
 * dxq
 */

var _p_config = {
		eleementclass : 'commitprocess',
		nexpersonurl : '/SpringMVC_Hibernate_001/Login/obtainuser',
		formid : '', //设置默认的form id 例如填写表单  既要保存又要完成第一步,
		defaultform : ['taskid','transitionName','processdefinitonkey','jbpm4_opinion'],
		prefix : 'jbpm4_',
		defaultassign : 'assignname',
		opinionid : '',
		defaultopinionid : 'defaultopinionid',
		defaultopinionname : 'opinion',
		width : 300,
		height : 300,
		isAjax : false, //默认提交方式是form
		completeurl :'', //流程实例开始的时候 最好用form提交。
		defaulthanler : function(){
			alert('这是默认的处理函数需要重构');
		}
		
};
var Process = function(){
	var t = this;
	var defaultconfig = {};
	var cacheNext = {};
	var taskid,transitionName,processdefinitonkey,jbpm4_opinion;
	this.init = function(){
		var event = arguments[0];
		
		defaultconfig=_p_config;
		
		var _j_target = $(this);
		
		taskid=_j_target.attr('taskid');
		transitionName=_j_target.attr('transitionName');
		processdefinitonkey=_j_target.attr('processdefinitonkey');//暂时用processinstance id
		
		
		if(taskid!=null && taskid !=''){
			
			var ta = taskid+'_'+transitionName;
			if(cacheNext[ta]!=null && cacheNext[ta]!=''){
				renderCandidate.call(t,cacheNext[ta]);
				return ;
			}
		}else if(processdefinitonkey!=null && processdefinitonkey!=''){
			
			if(cacheNext[processdefinitonkey]!=null && cacheNext[processdefinitonkey]!=''){
				renderCandidate.call(t,cacheNext[processdefinitonkey]);
				return ;
			}
		}
		
		$.ajax({
			type: "POST",
			url: defaultconfig.nexpersonurl,
			data: {'taskid':taskid,'transitionName':transitionName,'processkey':processdefinitonkey},
			success: function(msg){
				
				if(msg == null || msg==''){
					renderCandidate.call(t,msg);
					return ;
				}
				if(typeof msg == 'string'){
					msg = $.parseJSON(msg);
				}
				if(msg['status']=='-1'){
					alert(msg['info']);
					return ;
				}
				
				if(taskid!=null && taskid !=''){
					var ta = taskid+'_'+transitionName;
					cacheNext[ta]=msg;
				}else if(processdefinitonkey!=null && processdefinitonkey!=''){
					cacheNext[processdefinitonkey]=msg;
				}
				renderCandidate.call(t,msg);
			}
		});
		
	};
	var renderCandidate=function(msg){
		
		var defaultassign = defaultconfig.defaultassign;
		if(msg['status']!=null && msg['status']=='-1'){
			alert(msg['info']);
			return ;
		}
		else if(msg['data']==null || msg['data']==''){
			
			this.completeStep.call(t);
			return ;
		}
		
		var sts = [];
		sts.push('<div>');
		sts.push('  <div>');
		if(msg.type!='fork'){
			if(msg.data[0].candidate.length==1){
				var a_u = msg.data[0].candidate[0];
				sts.push(' <div> <div class="userinfo" style="display:none;"><span><input type="radio" checked="true" name="'+defaultassign+'" value="'+a_u['id']+'">'+a_u['givenName']+'</span></div></div>');
			}else{
				for(var i=0;i<msg.data[0].candidate.length;i++){
					var a_u = msg.data[0].candidate[i];
					sts.push(' <div> <div class="userinfo"><span><input type="radio" name="'+defaultassign+'" value="'+a_u['id']+'">'+a_u['givenName']+'</span></div></div>');
				}
			}
		}
		else{
			
			for(var i=0;i<msg.data.length;i++){
				var a_u = msg.data[i];
				sts.push('  <div><span>请选择<label>'+a_u['name']+'</label>的办理人</span></div>');
				for(var m=0;m<a_u.candidate.length;m++){
					var a_m = a_u.candidate[m];
					sts.push('  <div><div class="userinfo"><span><input type="radio" name="'+defaultassign+i+'" value="'+a_m['id']+'">'+a_m['givenName']+'</span></div></div>');
				}
				
			}
		}
		if(defaultconfig.opinionid == null || defaultconfig.opinionid == ''){
			sts.push('<div class="userinfo"><div><span>意见:</span></div><div><textarea style="width:'+(defaultconfig.width-100)+'px;height:40px;" id="'+defaultconfig.defaultopinionid+'"></textarea></div></div>');
		}

		sts.push('<div class="userinfo"><input id="processsubit" type="button" value="提交" style="width:80px;height:40px;">&nbsp;&nbsp;&nbsp<input id="processcancel" type="button" value="取消" style="width:80px;height:40px;"></div>');
		sts.push('  </div>');
		sts.push('</div>');
		
		if($('#candidate').size()==0){
			$('body').append('<div id="candidate" style="text-align:left;" class="easyui-window" title="确认提交" ></div>');
			$('#candidate').html(sts.join(''));
			$('#candidate').window({
				width:defaultconfig.width,
				height:defaultconfig.height,
				modal:true,
				closable : true,
				minimizable : false,
				maximizable : false,
				collapsible : false
				
			});
		
		}
		
		$('#candidate').html(sts.join(''));
		$('#candidate').window('open');

	};
	var submitProcess = function(){
		$('#candidate').window('close');
	};
	this.addCache = function(taskid,o){
		if(cacheNext[taskid]!=null && cacheNext[taskid]!=''){
			cacheNext[taskid]=taskid;
		}
	};
	this.defaultRender=function(){
		var o = arguments[0];
	};
	this.cancelStep=function(){
		$('#candidate').window('close');
	};
	this.completeStep=function(){
		var target1 = this;
		var formid = defaultconfig.formid;
		
		if(formid!=null && formid!=''){
			var e_form = $("#"+formid).get(0);
			var forme = defaultconfig.defaultform;
			var addElement = [];
			for(var i=0;i<forme.length;i++){
				var t = forme[i];
				if(e_form[t]==null){
					addElement.push('<input type="hidden"  name="'+t+'"/>');
				}
			}
			//userinfo
			$('.userinfo input:checked').each(function(index, domEle){
				var e_name = $(domEle).attr('name');
				
				var e_value = $(domEle).val();
				var prefix = defaultconfig.prefix;
				addElement.push('<input type="hidden" value="'+e_value+'"  name="'+prefix+e_name+'"/>');
			});
			
			$(e_form).append(addElement.join(''));
			
			//opinion值
			var opinionid = '';
			if(defaultconfig.opinionid == null || defaultconfig.opinionid==''){
				opinionid = defaultconfig.defaultopinionid;
			}
			else opinionid = defaultconfig.opinionid;
			jbpm4_opinion=$('#'+opinionid).val();
			
			
			for(var i=0;i<forme.length;i++){
				var t = forme[i];
				var v = eval(t);
				$(e_form[t]).val(v);
			}
			
			
			
			e_form.submit();
		}else{
			if(defaultconfig.isAjax){
				var param = {};
				var forme = defaultconfig.defaultform;
				
				var opinionid = '';
				if(defaultconfig.opinionid == null || defaultconfig.opinionid==''){
					opinionid = defaultconfig.defaultopinionid;
				}
				else opinionid = defaultconfig.opinionid;
				jbpm4_opinion=$('#'+opinionid).val();
				
				for(var i=0;i<forme.length;i++){
					var t = forme[i];
					var v = eval(t);
					param[t]=v;
				}
				
				$('.userinfo input:checked').each(function(index, domEle){
					var e_name = $(domEle).attr('name');
					var e_value = $(domEle).val();
					var prefix = defaultconfig.prefix;
					param[prefix+e_name]=e_value;
				});
				$.ajax({
					type: "POST",
					url : defaultconfig.completeurl,
					data : param,
					success : function(data, textStatus){
						defaultconfig.defaulthanler.call(target1,data);
					}
				});
			}
		}
	};
};

$(function(){
	var p = new Process();
	$(".commitprocess").live("click", p.init);  
	$("#processsubit").live("click", p.completeStep);
	$("#processcancel").live("click", p.cancelStep);
	
});

$(function(){
	var e_if = $("#taskinfo");
	if(e_if.size()<=0)
		return ;
	var eif = e_if.get(0);
	$(eif).bind('load',function(){
		var ifm = eif;
		var subWeb = document.frames ? document.frames["taskinfo"].document : ifm.contentDocument;
		if(ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight; 
		}
	});
});