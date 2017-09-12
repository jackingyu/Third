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
			autoclose : true
		});

		$('#trydate').datepicker({
			autoclose : true
		});

		$('#deliverydate').datepicker({
			autoclose : true
		});

		$('#weddingdate').datepicker({
			autoclose : true
		});
	}
}

$(document).ready(function() {
	ACC.salesquotation.init();
});
