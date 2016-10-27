ACC.usergrouplist = {
	initEvent:function() {
		$("#createusergroup").unbind();
		$("#createusergroup").on("click",function(){
			ACC.usergroup.createUserGroup();
		});
		
		$("#queryUserGroupButton").unbind();
		$("#queryUserGroupButton").on("click",function(){
			$("#userGroupListGrid").datagrid('loadData',"/test");
		});
		
	}
}




