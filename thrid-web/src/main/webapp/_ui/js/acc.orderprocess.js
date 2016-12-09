ACC.orderprocess = {
	init:function() {
		$("#op-orderCode").textbox();
        $("#op-orderCode").textbox("textbox").on("keydown", function(e) {
			if (e.keyCode == 13 && $("#op-orderCode").textbox("getValue")!=null 
			    && $("#op-orderCode").textbox("getValue")!="") {
				ACC.orderprocess.process();
			}
		});
		
		$("#op-orderProcessListGrid").datagrid({
			//url:ACC.config.contextPath+"/getCustomerList",
			singleSelect:true,
            toolbar:'#op-tb',
            pagination:false,
            rownumbers:true,
            idField:"pk"
		});
		
	},
	process:function(){
		$.ajax({
			type : "post",
			url : ACC.config.contextPath + "/processOrder",
			data :$("#op-searchForm").serialize(),
			success : function(data) {
				$.messager.alert("系统提示", "操作成功");
				$("#op-orderProcessListGrid").datagrid("loadData",data);
			},
			error: function(data){
				var p = JSON.parse(data.responseText);
				$.messager.alert("系统提示", p.message);
			}
		});
	}
}




