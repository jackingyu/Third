ACC.usergroup = {
	create:function() {
		  $("#userGroupForm").form("clear");
		  $("#roleListGrid").datagrid("loadData",{total:0,rows:[]}); 
	},
	modify:function(value){
		$("#userGroupForm").form("load",value);
		//$("#userGroupDialog").dialog("open");
		$("#roleListGrid").datagrid({url:ACC.config.contextPath+"/getRolesForUserGroup",queryParams:{userGroupPK:value.pk}});
	},
	openSelectRolesDialog:function(){
		$("#roleSelectorList").datagrid("clearSelections");
		$("#roleSelector").dialog("open");
	},
	deleteRoles:function(){
		var rows = $('#roleListGrid').datagrid("getSelections"); 
        var copyRows = [];
        for ( var j= 0; j < rows.length; j++) {
          copyRows.push(rows[j]);
        						}
        for(var i =0;i<copyRows.length;i++){    
            var index = $('#roleListGrid').datagrid('getRowIndex',copyRows[i]);
            $('#roleListGrid').datagrid('deleteRow',index); 
        }
	},
	selectRoles:function(){
		var rows = $("#roleSelectorList").datagrid("getSelections");
		$("#roleSelector").dialog("close");
		for(var i = 0;i < rows.length;i++)
		{
		   if($("#roleListGrid").datagrid("getRowIndex",rows[i]) == -1)
		   $("#roleListGrid").datagrid("appendRow",rows[i]);
		}
	},
	initEvent:function(config){
		$("#userGroupForm").form({
			onSubmit:function(){
				if($(this).form('validate'))
				{
					var roles = $("#roleListGrid").datagrid("getData").rows;
					var rolesPK = [];
					for(var i = 0;i<roles.length;i++)
					{
						rolesPK.push(roles[i].pk);
					}
					
					$.ajax({
					    type: "post",
					    url: ACC.config.contextPath+"/modifyUserGroup",
					    data: $(this).serialize()+"&roleList="+rolesPK,
					    success: function(data) {
					        $.messager.alert("系统提示","保存成功");
					        //重置页面信息,可以新建
					        ACC.usergroup.create();
					    }
					});
				}
				return false;
			}
		});

		
	   $("#roleListGrid").datagrid({
			singleSelect:false,
            toolbar:["-", {
            id: 'addRole',
            iconCls: 'icon-add',
            handler: function () {
               ACC.usergroup.openSelectRolesDialog();
            }
            },"-",{
            id:'deleteRole',
            iconCls: 'icon-remove',
            handler: function(){
            	ACC.usergroup.deleteRoles();
            }
            }],
            rownumbers:true,
            idField:"pk"
		});
		
	   	$("#roleSelector").dialog({
		    width: 400,
		    height: 230,
		    title: "角色列表",
		    closed: true,
		    cache: false,
		    modal: true
		});
		//此处已经开始请求数据,所以role的获取在进入该页面的时候已经会load一次
	   $("#roleSelectorList").datagrid({
			url:ACC.config.contextPath+"/getRoles",
			singleSelect:false,
            rownumbers:true,
            idField:"pk",
            height:150
		});
		
		$('#selectRolesLink').linkbutton({
            iconCls: 'icon-ok'
         });
	}
}




