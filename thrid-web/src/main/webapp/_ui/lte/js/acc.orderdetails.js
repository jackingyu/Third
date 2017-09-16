ACC.orderdetail = {
	setCustomer : function(o) {
		$('#customerName').val(o[1]);
		$("#weddingdate").datepicker("update", o[2]);
		$('#cellphone').val(o[0]);
	},
	create : function() {
		$("#orderForm").submit();
	}
}

$(document).ready(function() {
	$('#photodate').datepicker();

	$('#trydate').datepicker();

	$('#deliverydate').datepicker();

	$('#weddingdate').datepicker();

	ACC.searchcustomer.callback = ACC.orderdetail.setCustomer;

	$('#orderForm').validate();

});
