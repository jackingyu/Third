ACC.customer = {
	create:function() {
		$("#customerPanel").panel("setTitle","创建顾客");
		$("#customerForm").form("clear");
	},
	modify:function(value){
		$("#customerPanel").panel("setTitle","修改顾客-"+value.cellphone);
		$("#customerForm-customerPk").val(value.cellphone);
		//$("#customerForm-cellphone").textbox("readonly",true);
		//$("#customerForm-cellphone").textbox("disable");
		
		$.ajax({
			type:"get",
			url:ACC.config.contextPath+"/getCustomer",
		    data:{cellphone:value.cellphone},
		    success:function(data)
		    {
		    	$("#customerForm-cellphone").textbox("setValue",data.cellphone);
		    	$("#customerForm-name").textbox("setValue",data.name);
		    	$("#customerForm-QQ").textbox("setValue",data.qq);
		    	$("#customerForm-email").textbox("setValue",data.email);
		    	$("#customerForm-birthday").datebox("setValue",data.birthday);
		    	$("#customerForm-weddingdate").datebox("setValue",data.weddingdate);
		    	
		    	$("#customerForm-region").combobox("setValue",data.address.region.isoCode);
		    	$("#customerForm-city").combobox("setValue",data.address.city.isoCode);
		    	$("#customerForm-adr1").textbox("setValue",data.address.adr1);
		    	
		    	$("#customerForm-source").combobox("setValue",data.source.pk);
		    	
		    	$("#customerForm-comment").textbox("setValue",data.comment);
		    
		        //$("#customerForm input[name='QQ']").val(data.qq);
		        //$("#userForm-userGroupName").textbox("setValue",data.userGroup.name);$("#customerForm input[name='email']").val(data.email);
		    }
		})
	},
	init:function(){
		$("#customerForm").form({
			onSubmit:function(){
				if($(this).form('validate'))
				{
					if($("#customerForm-customerPk").val()=="")
					$.ajax({
					    type: "post",
					    url: ACC.config.contextPath+"/createCustomer",
					    data: $(this).serialize(),
					    success: function(data) {
					        $.messager.alert("系统提示","创建顾客信息成功");
					    }
					});
					else
					 $.ajax({
					    type: "post",
					    url: ACC.config.contextPath+"/modifyCustomer",
					    data: $(this).serialize(),
					    success: function(data) {
					        $.messager.alert("系统提示","修改顾客信息成功");
					        ACC.user.create();
					    }
					});
				}
				return false;
			}
		});
	}
}