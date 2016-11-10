ACC.storeselector = {
	callback:null,
	init:function(config) {
		$("#storeSelectorSearchBtn").unbind();
		$("#storeSelectorSearchBtn").on("click",ACC.storeselector.getStores);
		
		$("#storeSelectorGrid").datagrid({
		toolbar:"#storeSelector-tb",
		  idField:"pk"
		});
			
        $("#storeSelectorDialog").dialog({
        	onClose:ACC.storeselector.reset,
        	closed: true,
		    cache: false,
		    modal: true
		    });
	},
	reset:function(){
		$("#storeSelectorGrid").datagrid("clearSelections");
		$("#storeSelectorForm").form("clear");
	},
	openDialog:function(config){
		$("#storeSelectorDialog").dialog("open");
		$("#selectStoreLink").linkbutton({
            iconCls: "icon-ok",
            onClick:ACC.storeselector.selectStore
        });
        
        ACC.storeselector.callback = config.callback;
	},
	selectStore:function(){
		var row = $("#storeSelectorGrid").datagrid("getSelections");
		if(row==null)
		{
			$.messager.alert("系统提示","至少选择一项");
			return;
		}
		
		ACC.storeselector.callback(row);
		$("#storeSelectorDialog").dialog("close");
		
	//	ACC.storeselector.reset();
	},
	getStores:function(){
		var searchForm = $("#searchStoreForm").form();
		$("#storeSelectorGrid").datagrid({
			url:ACC.config.contextPath+"/getStores",
			parmas:serializeObject(searchForm)}
		);
	}
}
	