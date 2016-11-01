ACC.userlist = {
	initEvent:function() {
		$("#createuser").unbind();
		$("#createuser").on("click",function(){
			ACC.user.create();
		});
		
		$("#searchUserBtn").unbind();
		$("#searchUserBtn").on("click",function(){
			var searchForm = $("#searchUserForm").form();
			$("#userListGrid").datagrid("load",serializeObject(searchForm));
		});
		
		$("#userListGrid").datagrid({
			url:ACC.config.contextPath+"/getUserList",
			singleSelect:true,
            toolbar:'#userlist-tb',
            iconCls:'icon-save',
            pagination:true,
            rownumbers:true,
            idField:"pk",
            onDblClickRow: function(rowIndex,value) {
		           ACC.user.modify(value);
            }
		});

		
	}
}




