ACC.salesquation = {
    save : function(){
    	$("#continuecreate").val(false);
    	$("#salesQuationForm").submit();
    },
    
    save1 : function(){
    	$("#salesQuationForm").submit();
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
	ACC.salesquation.init();
});
