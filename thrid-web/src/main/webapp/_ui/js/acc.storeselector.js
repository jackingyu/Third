ACC.storeselector = {
	init:function() {
		$("#storeSelectorSearchBtn").unbind();
		$("#storeSelectorSearchBtn").on("click",ACC.storeselector.getStores);
		
		$("#storeSelector").datagrid({
		toolbar:"#storeSelector-tb"
		});
			
		$("#selectStoreLink").linkbutton({
            iconCls: "icon-ok",
            onClick:ACC.storeselector.select
        });
	},
	openDialog:function(){
		$("#storeSelectorDialog").dialog("open");
	},
	select:function(){
	},
	getStores:function(){
		
	}
}
	