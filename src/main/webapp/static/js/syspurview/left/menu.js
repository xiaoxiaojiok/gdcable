var currOneMenuId=0;
function query(){
	var pMenu = $("#p_menu").find("option:selected").val();
	var menu_name = $("#menu_name").val();
	var menu_status = $("#menu_status").find("option:selected").val();
	var paras = "menuName=" + encodeURI(menu_name,"utf-8") + "&status=" + menu_status + "&pMenu=" + pMenu;
	base.load("list",baseUrl + "/list.do?" + paras,function(){
		base.showList("head","list","edit");
	});
}

function edit(i,addType){
	base.load("edit",baseUrl + "/edit.do?id=" + i + "&addType=" + addType,function(){
		base.showEdit("head","list","edit");
		var addType = $("#addType").val();
		if(addType == 0 || addType == 2){
			changeMenu();
		}
	});
}

function setup(id,status){
	base.request(baseUrl + "/setup.do","id=" + id + "&status=" + status,function(result){
				if(result == "success"){
					base.tips("修改成功!",'success',1500);
					if(status == 1){
						$("#setUnable" + id).show();
						$("#setAble" + id).hide();
						$("#statusDisplay" + id).html("正常");
					}else{
						$("#setUnable" + id).hide();
						$("#setAble" + id).show();
						$("#statusDisplay" + id).html("<font color='red'>停用</font>");
					}
				}else{
					base.tips("出现未知异常，操作失败!",'error');
				}
	},"POST","HTML");
}

function changeMenu(){
	var value = $("#top_menu").find("option:selected").val();
	if(value == 0){
		$("#menuLevelSpan").text("1");
		$("#menuLevel").val(1);
		$("#orderNum").val(1);
	}else{
		base.request(baseUrl + "/getLayerInfo.do","id=" + value,function(result){
			var arr = result.split(",");
			$("#orderNum").val(parseInt(arr[0]) + 1);
			$("#menuLevelSpan").text(parseInt(arr[1]) + 1);
			$("#menuLevel").val(parseInt(arr[1]) + 1);
		},"POST","HTML");
	}
}


function openLeftMenu(menuId){
	if(menuId==undefined || menuId==0)
		return ;
	if(currOneMenuId==0 || currOneMenuId!=menuId){
		currOneMenuId = menuId;
	}else{
		return ;
	}	
	if(base==null){
		alert("null");
	}else{
		base.load("leftMenu",baseUrl + "/leftmenu/" + currOneMenuId,function(){
			base.showLeftMenu("leftMenu");
		});
	}
}

function save(){
	if(checkForm()){
		base.processStatus(1,'save_btn','process_btn');
		base.formSubmit(baseUrl + "/save.do",function(result){
				if(result == "success"){
					base.tips("添加成功!",'success',1500);
					setTimeout(function(){
						query();
					},2000)
				}else{
					base.tips("出现未知异常，操作失败!",'error');
				}
		},"mainForm");
	};
}

function checkForm(){
	var menuName = $("#menuName").val();
	if(menuName == ""){
		base.alert("菜单名称不许为空!");
		return false;
	}
	var status = $("#status").find("option:selected").val(); 
	if(status == "-1"){
		base.alert("请选择菜单状态!");
		return false;
	}
	var order = $("#orderNum").val();
	if(order == ""){
		$("#orderNum").val(0);
	} 
	return true;
}

function delMore(){
	var ids = base.getChecked("cbx",true);
	del(ids);
}

function del(id){
	base.confirm("删除菜单将会删除其下的所有子菜单，您确定要还删除吗?",'',function(){
		base.request(baseUrl + "/delete.do","ids=" + id,function(result){
				if(result == "success"){
					base.tips("删除成功!",'success',1500);
					setTimeout(function(){
						base.cancel('head','list','edit');
					},2000)
				}else{
					base.tips("出现未知异常，操作失败!",'error');
				}
		},"POST","HTML");
	});
}