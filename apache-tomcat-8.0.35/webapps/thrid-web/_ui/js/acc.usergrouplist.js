ACC.usergrouplist = {
	initEvent:function() {
		$("#createusergroup").unbind();
		$("#createusergroup").on("click",function(){
			ACC.usergroup.create();
		});
		
		$("#searchUserGroupBtn").unbind();
		$("#searchUserGroupBtn").on("click",function(){
			var searchForm = $("#searchUserGroupForm").form();
			$("#userGroupListGrid").datagrid("load",serializeObject(searchForm));
		});
		
		$("#userGroupListGrid").datagrid({
			url:ACC.config.contextPath+"/getUserGroupList",
			singleSelect:true,
            toolbar:"#usergrouplist-tb",
            iconCls:"icon-save",
            pagination:true,
            rownumbers:true,
            idField:"pk",
            onDblClickRow: function(rowIndex,value) {
		           ACC.usergroup.modify(value);
            }
		});
	}
}




