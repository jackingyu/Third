ACC.salesquotation = {
	save : function() {
		$("#continuecreate").val(false);
		$("#salesQuotationForm").submit();
	},

	save1 : function() {
		$("#salesQuotationForm").submit();
	},
	convert: function(){
		$("#salesQuotationForm").attr("action", ACC.config.contextPath+"/salesquotation/convert");  
		$("#salesQuotationForm").submit();
	},
	init : function() {
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
		
		$('#salesQuotationForm').validate();
	}
}

$(document).ready(function() {
	ACC.salesquotation.init();
});
