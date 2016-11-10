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
	deleteStores:function(){
	    var rows = $('#userStoreList').datagrid("getSelections"); 
        var copyRows = [];
        for ( var j= 0; j < rows.length; j++) {
          copyRows.push(rows[j]);
        						}
        for(var i =0;i<copyRows.length;i++){    
            var index = $('#userStoreList').datagrid('getRowIndex',copyRows[i]);
            $('#userStoreList').datagrid('deleteRow',index); 
        }
	},
	initEvent:function(){
		$("#userForm").form({
			onSubmit:function(){
				if($(this).form('validate'))
				{
					var stores = $("#userStoreList").datagrid("getData").rows;
					var storesPK = [];
					for(var i = 0;i<stores.length;i++)
					{
						storesPK.push(stores[i].pk);
					}
					
					if($("#userForm-userPk").val()=="")
					$.ajax({
					    type: "post",
					    url: ACC.config.contextPath+"/createUser",
					    data: $(this).serialize()+"&storeList="+storesPK,
					    success: function(data) {
					        $.messager.alert("系统提示","创建用户成功");
					    }
					});
					else
					 $.ajax({
					    type: "post",
					    url: ACC.config.contextPath+"/modifyUser",
					    data: $(this).serialize()+"&storeList="+storesPK,
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
               ACC.storeselector.openDialog({callback:function(selectedStores){
               	 for(var i = 0;i < selectedStores.length;i++)
               	 {
               	   var selectedStore = selectedStores[i];
                   $("#userStoreList").datagrid("appendRow",selectedStore);
                  }
               }});
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