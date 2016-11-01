ACC.user = {
	create:function() {
		$("#userForm").form("clear");
		$("#userDialog").dialog("open");
	},
	modify:function(value){
		$("#userForm").form("load",value);
		$("#userDialog").dialog("open");
	},
	initEvent:function(config){
		$("#userForm").form({
			onSubmit:function(){
				if($(this).form('validate'))
				{
					$.ajax({
					    type: "post",
					    url: ACC.config.contextPath+"/modifyUser",
					    data: $(this).serialize(),
					    success: function(data) {
					        $.messager.alert("系统提示","保存成功");
					        $("#userDialog").dialog("close");
					    }
					});
				}
				return false;
			}
		});
		$("#userDialog").dialog({
		    title: '用户组',
		    width: 400,
		    height: 200,
		    closed: true,
		    cache: false,
		    modal: true,
		    onClose:function(){
		    	$("#userListGrid").datagrid("reload");
		    }
		});
	}
}