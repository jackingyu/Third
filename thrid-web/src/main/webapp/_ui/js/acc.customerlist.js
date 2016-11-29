ACC.customerlist = {
	init:function() {
		$("#createcustomer").unbind();
		$("#createcustomer").on("click",function(){
			ACC.customer.create();
		});
		
		$("#searchCustomerBtn").unbind();
		$("#searchCustomerBtn").on("click",function(){
			var searchForm = $("#searchCustomerForm").form();

			$("#customerListGrid").datagrid("load",serializeObject(searchForm));
		});
		
		$("#customerListGrid").datagrid({
			url:ACC.config.contextPath+"/getCustomerList",
			singleSelect:true,
            toolbar:'#customerlist-tb',
            iconCls:'icon-save',
            pagination:true,
            rownumbers:true,
            idField:"pk",
            onDblClickRow: function(rowIndex,value) {
		           ACC.customer.modify(value);
            }
		});

		
	}
}




