ACC.orderlist = {
	init:function() {
		$("#createorder").unbind();
		$("#createorder").on("click",function(){
			ACC.order.create();
		});
		
		$("#searchOrderBtn").unbind();
		$("#searchOrderBtn").on("click",function(){
			var searchForm = $("#searchOrderForm").form();
			$("#orderListGrid").datagrid("load",serializeObject(searchForm));
		});
		
		$("#orderListGrid").datagrid({
			url:ACC.config.contextPath+"/getOrderList",
			singleSelect:true,
            toolbar:'#orderlist-tb',
            iconCls:'icon-save',
            pagination:true,
            rownumbers:true,
            idField:"pk",
            onDblClickRow: function(rowIndex,value) {
		           ACC.order.modify(value);
            }
		});

		
	}
}




