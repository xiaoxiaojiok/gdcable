
function roleQuery()
{	
	var roleName = $('#roleName').val();
	roleName = roleName==undefined?"":roleName;
	var paras='roleName=' + roleName;
	base.load("list","/role/query.do?" + paras,function(){
		base.showList("head","list","edit");		//标签的显示与隐藏，显示head、list、隐藏edit
	});
}

function editRole(id){
	if(id=undefined)id=null;
	base.load("edit","/role/edit.do",function(){
		base.showEdit("head","list","edit");
	});
	
}

function del(id){
	base.confirm("您确定要删除角色吗?",'',function(){
		base.request("/role/delete.do","ids=" + id,function(result){
				if(result == "success"){
					base.tips("删除成功!",'success',1500);
					setTimeout(function(){
						base.cancel('head','list','edit',roleQuery);
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


function save(){
		var roleName=$("#roleName").val();
		if(roleName==""){
			alert("先填写角色名称");
			return;
		}
		base.processStatus(1,'save','process');
		base.formSubmit("/role/save.do",function(result){
				if(result == "success"){
					base.tips("保存成功!",'success',1500);
					setTimeout(function(){
						base.cancel('head','list','edit',roleQuery);
					},2000)
				}else{
					base.tips("出现未知异常，操作失败!",'error');
				}
				base.processStatus(0,'save','process');
		},"mainForm");
}

function setRoleUnit(){
	var roleType = $("#roleTypeId").val();
	if(roleType==5){
		$("#roleUnitId").val(0);
		$("#roleUnitDiv").hide();
	}else{
		$("#roleUnitDiv").show();
	}
	
}

