ACC.order = {
	create : function() {
		$("#orderForm").form("clear");
		$("#paymentListGrid").datagrid("loadData", {
					total : 0,
					rows : []
				});
		$("#orderpanel").tabs("disableTab", 1);
		$("#orderpanel-orderpk").val("");
		$("#orderpanel-ordercode").textbox("readonly", false);
		$("#orderpanel-basic").panel("setTitle", "新建销售订单");
	},
	save : function() {
		ACC.payment.acceptChanges();

		if (!$("#orderForm").form("validate")) {
			$.messager.alert("系统提示", "订单信息有误,请输入必输字段或者调整输入内容");
			return;
		}

		var rows = $("#paymentListGrid").datagrid("getRows");

		for (var i = 0; i < rows.length; i++) {
			if (!$("#paymentListGrid").datagrid("validateRow", i)) {
				$.messager.alert("系统提示", "第" + (i + 1) + "条付款记录有误,请补充");
				return;
			}
		}

		$.ajax({
					type : "post",
					url : ACC.config.contextPath + "/saveOrder",
					data : $("#orderForm").serialize()
							+ "&payments="
							+ JSON.stringify($("#paymentListGrid")
									.datagrid("getData").rows),
					success : function(data) {
						$.messager.alert("系统提示", "订单保存成功");
						// 在order的环节中我们需要遵循everything depends on order
						// code的原则,此处PK只是表明数据库中有这么一条记录了,
						// 暂时给一个mockup值即可
						var modifyData = {
							orderCode : $("#orderpanel-ordercode")
									.textbox("getValue"),
							pk : "111"
						};
						ACC.order.modify(modifyData);
					}
				});
	},
	modify : function(value) {
		$("#orderpanel-basic").panel("setTitle", "修改订单-" + value.orderCode);
		$("#orderpanel").tabs("enableTab", 1);
		$("#orderpanel").tabs("select", 0);
		$("#orderpanel-ordercode").textbox("readonly", true);
		// $("#customerForm-customerPk").val(value.cellphone);
		// $("#customerForm-cellphone").textbox("readonly",true);
		// $("#customerForm-cellphone").textbox("disable");
		$("#orderpanel-ordercode").textbox("setValue", value.orderCode);
		$.ajax({
					type : "get",
					url : ACC.config.contextPath + "/getOrder",
					data : {
						orderCode : value.orderCode
					},
					success : function(data) {
						$("#orderpanel-trydate").datebox("setValue",
								data.tryDate);
						$("#orderpanel-photodate").datebox("setValue",
								data.photoDate);
						$("#orderpanel-deliverydate").datebox("setValue",
								data.deliveryDate);

						$("#orderpanel-customername").textbox("setValue",
								data.customer.name);
						$("#orderpanel-cellphone").textbox("setValue",
								data.cellphone);
						$("#orderpanel-weddingdate").datebox("setValue",
								data.weddingDate);

						$("#orderpanel-orderpk").val(data.pk);
						$("#paymentListGrid").datagrid("loadData",
								data.payments);
						ACC.orderentry.refreshList();
						ACC.orderentry.create();
					},
					faliure : function() {

					}
				});
	},
	init : function() {
		$("#entryListGrid").datagrid({
					singleSelect : true,
					rownumbers : true,
					idField : "pk",
					onClickRow : function(rowIndex, value) {
						ACC.orderentry.modify(value);
					}
				});

		$("#paymentListGrid").datagrid({
					singleSelect : true,
					iconCls : "icon-save",
					rownumbers : true,
					idField : "pk",
					toolbar : "#paymentListtb",
					onClickCell : ACC.payment.onClickCell,
					onEndEdit : ACC.payment.onEndEdit
				});

		$("#orderForm").form({
					onSubmit : function() {
						return false;
					}
				});
		//
		$("#orderpanel-cellphone").textbox();
		$('#orderpanel-cellphone').textbox('textbox').on('keydown',
				function(e) {
					if (e.keyCode == 13
							&& $('#orderpanel-cellphone').textbox("validate")) {
						$.ajax({
									type : "get",
									url : ACC.config.contextPath
											+ "/getCustomer",
									dataType : "json",
									data : {
										cellphone : $('#orderpanel-cellphone')
												.textbox('getValue')
									},
									success : function(data) {
										if (data.pk == null) {
											$.messager.alert("系统提示",
													"手机号码不存在,请至顾客管理创建顾客信息");
											return;
										}

										$('#orderpanel-cellphone').textbox(
												"setValue", data.cellphone);
										$('#orderpanel-weddingdate').datebox(
												"setValue", data.weddingdate);
										$('#orderpanel-customername').textbox(
												"setValue", data.name);
									}
								});
					}
				});
	}
}

ACC.payment = {
	editIndex : undefined,
	refreshList : function() {
		if ($("#paymentListGrid").datagrid("options").url == null)
			$("#paymentListGrid").datagrid({
						url : ACC.config.contextPath + "/getPaymentEntries",
						queryParams : {
							orderCode : $("#orderpanel-ordercode")
									.textbox("getValue")
						}
					});
		else
			$("#entryListGrid").datagrid("load", {
						orderCode : $("#orderpanel-ordercode")
								.textbox("getValue")
					});
	},
	endEditing : function() {
		if (ACC.payment.editIndex == undefined) {
			return true
		}
		if ($('#paymentListGrid')
				.datagrid('validateRow', ACC.payment.editIndex)) {
			$('#paymentListGrid').datagrid('endEdit', ACC.payment.editIndex);
			ACC.payment.editIndex = undefined;
			return true;
		} else {
			return false;
		}
	},
	onClickCell : function(index, field) {
		if (ACC.payment.editIndex != index) {
			if (ACC.payment.endEditing()) {
				$('#paymentListGrid').datagrid('selectRow', index).datagrid(
						'beginEdit', index);
				var ed = $('#paymentListGrid').datagrid('getEditor', {
							index : index,
							field : field
						});
				if (ed) {
					($(ed.target).data('textbox') ? $(ed.target)
							.textbox('textbox') : $(ed.target)).focus();
				}
				ACC.payment.editIndex = index;
			} else {
				setTimeout(function() {
							$('#dg').datagrid('selectRow',
									ACC.payment.editIndex);
						}, 0);
			}
		}
	},
	acceptChanges : function() {
		if (ACC.payment.endEditing()) {
			$('#paymentListGrid').datagrid('acceptChanges');
		}
	},
	onEndEdit : function(index, row) {
		var ed = $(this).datagrid('getEditor', {
					index : index,
					field : 'paymentType'
				});
		row.paymentTypeText = $(ed.target).combobox('getText');
		ed = $(this).datagrid('getEditor', {
					index : index,
					field : 'paymentMethod'
				});
		row.paymentMethodText = $(ed.target).combobox('getText');
	},
	reset : function() {
		ACC.payment.editIndex = undefined;
	},
	add : function() {
		if (ACC.payment.endEditing()) {
			$('#paymentListGrid').datagrid('appendRow', {});
			ACC.payment.editIndex = $('#paymentListGrid').datagrid('getRows').length
					- 1;
			$('#paymentListGrid').datagrid('selectRow', ACC.payment.editIndex)
					.datagrid('beginEdit', ACC.payment.editIndex);
		}
	},
	remove : function() {
		if (ACC.payment.editIndex == undefined) {
			return;
		}
		$('#paymentListGrid').datagrid('cancelEdit', ACC.payment.editIndex)
				.datagrid('deleteRow', ACC.payment.editIndex);
		ACC.payment.editIndex = undefined;
	}
}

ACC.orderentry = {
	reset:function() {
		if ($("#saveOrderEntryLink").linkbutton("options").disabled == true)
			$("#saveOrderEntryLink").linkbutton("enable");
		
		$("#entryPK").val("");
		$("#sizeImageUrl").val("");
		$("#removeOrderEntryLink").linkbutton("disable");
		$("#orderEntryForm").form("clear");
		$("#sizeDatasForm").form("clear");
		$("#orderentry-itemcategory").combobox("enable");
		$("#uploadSizeImageLink").linkbutton("disable");
		$("#getSizeImage").linkbutton("disable");
	},
	refreshList : function() {
		if ($("#entryListGrid").datagrid("options").url == null)
			$("#entryListGrid").datagrid({
						url : ACC.config.contextPath + "/getOrderEntries",
						queryParams : {
							orderCode : $("#orderpanel-ordercode")
									.textbox("getValue")
						},
						pageNumber : 1
					});
		else
			$("#entryListGrid").datagrid("load", {
						orderCode : $("#orderpanel-ordercode")
								.textbox("getValue")
					});
	},
	modify : function(value) {
		if ($("#saveOrderEntryLink").linkbutton("options").disabled == true)
			$("#saveOrderEntryLink").linkbutton("enable");
			
		$("#entryPK").val(value.pk);
		$("#sizeImageUrl").val(value.sizeImageUrl);
		$("#orderentry-itemcategory").combobox("setValue", value.itemCategory);
		$("#orderentry-itemcategory").combobox("disable");
		$("#orderentry-ordercode").textbox("setValue", value.orderCode);
		$("#orderentry-customername").textbox("setValue", value.customerName);
		$("#orderentry-storename").textbox("setValue", value.storeName);
		$("#orderentry-style").textbox("setValue", value.style);
		$("#orderentry-designer").textbox("setValue", value.designer);
		$("#orderentry-producttitle").textbox("setValue", value.productTitle);
		$("#orderentry-quantity").numberbox("setValue", value.quantity);
		$("#orderentry-trydate").datebox("setValue", value.tryDate);
		$("#orderentry-deliverydate").datebox("setValue", value.deliveryDate);
		$("#orderentry-sizedate").datebox("setValue", value.sizeDate);
		$("#orderentry-comment").textbox("setValue", value.comment);
		
		
		$("#removeOrderEntryLink").linkbutton("enable");
		$("#uploadSizeImageLink").linkbutton("enable");
		$("#getSizeImage").linkbutton("enable");
		ACC.orderentry.getSizeDatasByEntry(value.pk);
	},
	create : function() {
        ACC.orderentry.reset();
		$.ajax({
			type : "get",
			url : ACC.config.contextPath + "/getOrder",
			data : {
				orderCode : $("#orderpanel-ordercode").textbox("getValue")
			},
			success : function(data) {
				$("#orderentry-ordercode").textbox("setValue", data.orderCode);
				$("#orderentry-customername").textbox("setValue",
						data.customerName);
				$("#orderentry-storename").textbox("setValue", data.store.name);
				$("#orderentry-trydate").datebox("setValue", data.tryDate);
				$("#orderentry-deliverydate").datebox("setValue",
						data.deliveryDate);
				$("#orderentry-sizedate").datebox("setValue", data.sizeDate);
			},
			faliure : function() {

			}
		});
	},
	remove : function() {
		var selected_item_text = $("#orderentry-itemcategory")
				.combobox("getText");
		$.messager.confirm('删除确认', '确认是否删除-' + selected_item_text, function(r) {
					if (r) {
						$.ajax({
							type : "post",
							url : ACC.config.contextPath + "/removeOrderEntry",
							data : {
								entryPK : $('#entryPK').val()
							},
							success : function(data) {
								$.messager.alert("系统提示", "删除成功");
								ACC.orderentry.refreshList();
								ACC.orderentry.reset();
							}
						});
					}
				});

	},
	save : function() {
		if (!$("#orderEntryForm").form("validate")) {
			$.messager.alert("系统提示", "订单信息有误,请输入必输字段或者调整输入内容");
			return;
		}
		
		var sizeDatas = JSON.stringify(ACC.orderentry.compositeSizeDatas());
		$.ajax({
			type : "post",
			url : ACC.config.contextPath + "/saveOrderEntry",
			data : $("#orderEntryForm").serialize()+"&sizeDetails="+sizeDatas,
			success : function(data) {
				$.messager.alert("系统提示", "保存成功");
				ACC.orderentry.refreshList();
				$("#entryPK").val(data.pk);
	        }
		});
	},
	getSizeDatasByItemCategory:function(value){
	   $('#sizeDataPanel').panel('refresh',ACC.config.contextPath + "/getSizeDatas?itemCategory="+value.code);
	},
	getSizeDatasByEntry:function(entryPK){
	   $('#sizeDataPanel').panel('refresh',ACC.config.contextPath + "/getSizeDatas?entryPK="+entryPK);
	},
	compositeSizeDatas:function(){
	   var sizeDatas = [];
        $.each($("#sizeDatasForm").serializeArray(),function(index){
        	  var sizeData = { name:'',value:'',group:''};
        	  var a1 = this.name.split("-");
              sizeData.group = a1[0];
              sizeData.name = a1[1];
              sizeData.value = this.value;
              sizeDatas.push(sizeData);
        });
      return sizeDatas;
	},
	uploadImage:function(){
		var key = $("#entryPK").val();
		ACC.uploadfile.initUploadWindow(key,"/uploadSizeImage",function(mediaUrl){
			
		    $("#sizeImageUrl").val(mediaUrl);
		    //保证图片的URL加载进datagrid中,这样点击链接的时候可以显示图片
		    ACC.orderentry.refreshList();
		});
	},
	getSizeImage:function(){
		if($("#sizeImageUrl").val()==null||$("#sizeImageUrl").val()=="")
		{
		    $.messager.alert("系统提示", "暂无图片");
			return;
		}
		
		 $.colorbox({
            closeButton:true,
            href:ACC.config.contextPath+$("#sizeImageUrl").val(),//ACC.config.contextPath+"/getSizeImage"+"?entryPK="+$("#entryPK").val(),
            onComplete: function() {
			   ACC.common.refreshScreenReaderBuffer();
			},
			onClosed: function() {
			   ACC.common.refreshScreenReaderBuffer();
			   $.colorbox.remove();
			}
          });
	}
}
