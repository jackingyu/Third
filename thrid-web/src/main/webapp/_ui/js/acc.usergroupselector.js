ACC.usergroupselector = {
	prefix:null,
    targetField:null,
    valueField:null,
	openDialog:function(){
		$(ACC.usergroupselector.getElementId("userGroupSelectorForm")).form("clear");
		$(ACC.usergroupselector.getElementId("userGroupSelectorDialog")).dialog("open");
		
		ACC.usergroupselector.targetField = $(this).attr("targetField");
		ACC.usergroupselector.valueField = $(this).attr("valueField");
	},
	getUserGroups:function(){
		var searchForm = $(ACC.usergroupselector.getElementId("searchUserGroupForm")).form();
		$(ACC.usergroupselector.getElementId("userGroupSelectorGrid")).datagrid({
			url:ACC.config.contextPath+"/getUserGroupList",
			parmas:serializeObject(searchForm)}
		);
	},
	selectUserGroup:function(){
		var row = $(ACC.usergroupselector.getElementId("userGroupSelectorGrid")).datagrid("getSelected");
		if(row==null)
		{
			$.messager.alert("系统提示","至少选择一项");
			return;
		}
		$("#"+ACC.usergroupselector.valueField).val(row.pk);
		$("#"+ACC.usergroupselector.targetField).textbox("setValue",row.name);
		
		$(ACC.usergroupselector.getElementId("userGroupSelectorDialog")).dialog("close");
		ACC.usergroupselector.valueField = null;
		ACC.usergroupselector.targetField = null;
	},
	init:function(config){
		
		this.prefix = config.prefix;
		
	   $(ACC.usergroupselector.getElementId("userGroupSelectorGrid")).datagrid({
			singleSelect:true,
            toolbar:ACC.usergroupselector.getElementId("userGroupSelector-tb"),
            rownumbers:true,
            idField:"pk",
            pagination:true
		});
	   
	   	$(ACC.usergroupselector.getElementId("userGroupSelectorDialog")).dialog({
		    width: 470,
		    height: 300,
		    title: "用户组列表",
		    closed: true,
		    cache: false,
		    modal: true
		});
	   	
		$(ACC.usergroupselector.getElementId("selectUserGroupLink")).linkbutton({
            iconCls: "icon-ok",
            onClick:ACC.usergroupselector.selectUserGroup
        });
		
		$(ACC.usergroupselector.getElementId("userGroupSelectorSearchBtn")).unbind();
		$(ACC.usergroupselector.getElementId("userGroupSelectorSearchBtn")).on("click",ACC.usergroupselector.getUserGroups);
	},
	getElementId:function(elementId){
		return "#"+ACC.usergroupselector.prefix+"_"+elementId;
	}
}




