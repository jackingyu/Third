ACC.user = {
	create:function() {
		$("#userPanel").panel("setTitle","创建用户");
		$("#userForm").form("clear");
		$("#userStoreList").datagrid("loadData",{total:0,rows:[]});
	},
	modify:function(value){
		$("#userPanel").panel("setTitle","修改用户-"+value.userId);
		$("#userForm").form("load",value);
		
		$.ajax({
			type:"get",
			url:ACC.config.contextPath+"/getDetailsForUser",
		    data:{userId:value.userId},
		    success:function(data)
		    {
		    	$("#userForm-userGroupPk").val(data.userGroup.pk);
		    	$("#userForm-userGroupName").textbox("setValue",data.userGroup.name);
		    	$("#userForm-blocked").attr("checked","checked");
		    }
		})
	},
	initEvent:function(){
		$("#userForm").form({
			onSubmit:function(){
				if($(this).form('validate'))
				{
					if($("#userForm-userPk").val()=="")
					$.ajax({
					    type: "post",
					    url: ACC.config.contextPath+"/createUser",
					    data: $(this).serialize(),
					    success: function(data) {
					        $.messager.alert("系统提示","创建用户成功");
					    }
					});
					else
					 $.ajax({
					    type: "post",
					    url: ACC.config.contextPath+"/modifyUser",
					    data: $(this).serialize(),
					    success: function(data) {
					        $.messager.alert("系统提示","修改用户成功");
					        ACC.user.create();
					    }
					});
				}
				return false;
			}
		});
		
		$("#userStoreList").datagrid({
			singleSelect:true,
			toolbar:["-", {
            id: 'addStore',
            iconCls: 'icon-add',
            handler: function () {
               ACC.storeselector.openDialog();
            }
            },"-",{
            id:'deleteStore',
            iconCls: 'icon-remove',
            handler: function(){
            	ACC.user.deleteStores();
            }
            }],
            iconCls:'icon-save',
            rownumbers:true,
            idField:"pk"
		});
	}
}