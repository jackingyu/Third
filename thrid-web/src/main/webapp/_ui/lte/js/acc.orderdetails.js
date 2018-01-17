ACC.orderdetail = {
	setCustomer : function(o) {
		$('#customerName').val(o[1]);
		$("#weddingdate").datepicker("update", o[2]);
		$('#cellphone').val(o[0]);
		 $.ajax({
		      type: 'GET',
		      url: ACC.config.contextPath + '/customer/get?cellphone='+$('#cellphone').val(),
		      error: function (request) {
		        alert('Connection error');
		      },
		      success: function (data) {
		    		$('#customerSource').val(data.source.name);
		      }
		});
	
	},
	create : function() {
		$("#orderForm").submit();
	}
}

$(document).ready(function() {
	$('#photodate').datepicker({
		autoclose : true,
		locale : {
			format : 'YYYY/MM/DD'
		},
		language : 'zh-CN'
	});

	$('#trydate').datepicker({
		autoclose : true,
		locale : {
			format : 'YYYY/MM/DD'
		},
		language : 'zh-CN'
	});

	$('#deliverydate').datepicker({
		autoclose : true,
		locale : {
			format : 'YYYY/MM/DD'
		},
		language : 'zh-CN'
	});

	$('#weddingdate').datepicker({
		autoclose : true,
		locale : {
			format : 'YYYY/MM/DD'
		},
		language : 'zh-CN'
	});

	ACC.searchcustomer.callback = ACC.orderdetail.setCustomer;

	$('#orderForm').validate();

});
