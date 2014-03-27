
function userQuery()
{	
	var unit = $("#_dw_id").find("option:selected").val();
	var roleType = $("#_roleId").find("option:selected").val();
	var username = $("#user_name").val();
	var loginname = $("#login_name").val();
	var params = "unit=" + unit + "&roleId=" + roleType + "&username=" + base.encode(username) + "&loginname=" + base.encode(loginname);
	base.load("list","/user/query?" + params,function(){
		base.showList("head","list","edit");		
	});
}

function del(id){
	base.confirm("您确定要删除用户吗?",'',function(){
		base.request("/account/user/delete","ids=" + id,function(result){
				if(result == "success"){
					base.tips("删除成功!",'success',1500);
					setTimeout(function(){
						base.cancel('head','list','edit',userQuery);
					},2000)
				}else{
					base.tips("出现未知异常，操作失败!",'error');
				}
		},"POST","HTML");
	});
}

function delMore(){
	var ids = base.getChecked("cbx",true);
	del(ids);
}

function resetPwd(){
	var ids = base.getChecked("cbx",true);
	if(ids == ""){
		base.alert("请选择所要重置密码的用户!");
		return;
	}
	base.request("/account/user/resetPwd","ids=" + ids,function(result){
		if(result == "success"){
			base.alert("所选用户密码已重置为[123456]");
		}else{
			base.tips("出现未知异常，操作失败!",'error');
		}
	},"POST","HTML");
}
function setup(userId,status){
	base.request("/account/user/setup","userId=" + userId+"&sta="+status,function(result){
		if(result == "success"){
			if(status==1){
				base.tips("停用成功!",'success',1500);
			}else{
				base.tips("启用成功!",'success',1500);
			}	
			setTimeout(function(){
				base.cancel('head','list','edit',userQuery);
			},2000)
		}else{
			base.tips("出现未知异常，操作失败!",'error');
		}
	},"POST","HTML");	
}
function edit(i){
	base.load("edit","/user/edit/" + i,function(){
		base.showEdit("head","list","edit");
	});
	
}

function save(key){
	if(checkForm(key)){
		var roles=$("#roleIds option");
		var roleIds="";
		roles.each(function(){
			roleIds =roleIds+$(this).val()+",";
		});
		if(roleIds.length > 0)
		roleIds = roleIds.substring(0, roleIds.length-1);  //拼接的角色ID字符串
		
		base.processStatus(1,'save','process');
		base.formSubmit("/user/save?roleIds="+roleIds,function(result){
				if(result == "success"){
					base.tips("操作成功",'success',1500);
					setTimeout(function(){
						base.cancel('head','list','edit',userQuery);
					},2000)
				}else{
					base.tips("出现未知异常，操作失败!",'error');
				}
				base.processStatus(0,'save','process');
		},"mainForm");
	}else{
		$("#password").blur();
		base.tips("请完善用户信息!",'warning',1500);
	}
}

function checkForm(key){
	
	if(key){			//新增的时候才检查登录名的唯一性，修改的时候不允许修改登录名
		checks('loginName');
	}
	checks('username');
	checks('password');
	checks('confpas');
	checks('mobile');
	checks('email');

	if( $("#tipFour").html()=="" && $("#tipFive").html()=="" && $("#tips").html()==""){
		if(($("#tipOne").html()=="" && $("#tipTwo").html()=="" && $("#tipThree").html()=="") || $("#tipOne").html()==undefined){  //修改用户时不涉及到登录名、密码、确认密码后的提示检查
			return true;
		}
	}else{
		return false;
	}
}

function changeUserType(chosen){
	if(chosen==1){
		$("#typeOne").show();
		$("#typeTwo").show();			
	}else if(chosen==2){
		$("#typeOne").show();
		$("#typeTwo").hide();
	}
}

function addRole(){
	var slt1 = $("#s_roleId");
	var slt2 = $("#roleIds");
	var opt = slt1.find("option:selected");
	var flag = true;
	slt2.find("option").each(function(){
		if(flag){
			if($(this).val() == opt.val()){
				flag = false;
			}
		}
	});
	if(flag){
		slt2.append("<option value='"+opt.val()+"'>" +opt.text()+ "</option>");
		opt.remove();
	}
}

function delRole(){
	var slt1 = $("#s_roleId");
	var slt2 = $("#roleIds");
	var opt = slt2.find("option:selected");
	var flag = true;
	slt1.find("option").each(function(){
		if(flag){
			if($(this).val() == opt.val()){
				flag = false;
			}
		}
	});
	if(flag){
		slt1.append("<option value='"+opt.val()+"'>" +opt.text()+ "</option>");
	}
	opt.remove();
}


//字符串去空格方法
function trim(str){   
	if(str == "" || str == undefined) return "";
    str = str.replace(/^(\s|\u00A0)+/,'');   
    for(var i=str.length-1; i>=0; i--){   
        if(/\S/.test(str.charAt(i))){   
            str = str.substring(0, i+1);   
            break;   
        }   
    }   
    return str;   
}  
//邮箱验证方法
function IsEmail(str){
		var myreg = /^[-_A-Za-z0-9\.]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;	
		return (myreg.test(str) || str.length==0);
}
//正则表达式验证手机号码
function checkmobile(s){
		if(/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(s) || /^1[3|4|5|8][0-9]\d{4,8}$/.test(s) || s.length==0)
		{
			return true;
		}
		else{
			return false;
		}		
}
function checks(id){
	if(id=='loginName'){  //新增用户时登录名才获取验证
		var logins=trim($("#loginName").val()).length;
		if(logins==0){
			$("#tipOne").html("登录名不能为空！");
			$("#loginName").focus();
		}else {
			base.request(
					'/user/check',
					'loginName='+encodeURI($("#loginName").val(),"utf-8"),
					function(msg){
						if(msg == "false"){
							$("#tipOne").html("此登录名已被注册");
							$("#loginName").focus();
						} else{
							$("#tipOne").html("");
						}		
					},
					'POST',
					'HTML'
			);
		}
	}else if(id=='password'){
		var len = trim($("#initPassword").val()).length;
		if(len==0){			
			$("#tipTwo").html("密码不能为空！");
			$("#initPassword").focus();
		}else{
			$("#tipTwo").html("");
		}
	}else if(id=='confpas'){
		if($("#initPassword").val() !=$("#confpas").val()){
			$("#tipThree").html("密码输入不一致！");
			$("#confpas").attr("value","");
			$("#confpas").focus();
		}else{
			$("#tipThree").html("");
		}		
	}else if(id=='mobile'){
		if(!checkmobile($("#mobile").val())){
			$("#tipFour").html("手机格式不正确！");
			$("#mobile").focus();
		}else{
			$("#tipFour").html("");
		}
	}else if(id =='email'){
			if(!IsEmail($("#email").val())){
				$("#tipFive").html("邮箱格式不正确");
				$("#email").focus();
			}else{
				$("#tipFive").html("");
			}
		}else if(id=='username'){
			var pas = trim($("#username").val()).length;
			if(pas==0){			
				$("#tips").html("真实姓名不能为空！");
				$("#username").focus();
			}else{
				$("#tips").html("");
			}
			
		}		
}

function exportUser(){
	var unit = $("#_dw_id").find("option:selected").val();
	var school = $("#_xx_id").find("option:selected").val();
	var userType = $("#userType").find("option:selected").val();
	var roleType = $("#_roleId").find("option:selected").val();
	var username = $("#user_name").val();
	var loginname = $("#login_name").val();
	var params = "page=1&unit=" + unit +"&school=" + school + "&userType=" + userType + "&roleId=" + roleType + "&username=" + base.encode(username) + "&loginname=" + base.encode(loginname)+ "&"+Math.round(Math.random() * 10000)
	base.forward(baseUrl + "/export?" + params);
}
function importUser(){
	base.forward(baseUrl + "/importInit");
}

