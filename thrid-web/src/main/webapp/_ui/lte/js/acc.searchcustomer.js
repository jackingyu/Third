ACC.searchcustomer = {
	callback : null,
	select : function() {
		var selectedRow = $('#customerSearchGrid').DataTable()
				.rows('.selected');

		if (selectedRow.length > 0) {
			var dd = selectedRow.data();
			ACC.searchcustomer.callback(dd[0]);
		}

		$('#customerSearchPanel').modal('hide');
	},
	init : function() {
		$('#searchCustomer-select').unbind();
		$('#searchCustomer-select').on('click', ACC.searchcustomer.select);
		$('#searchCustomerBtn').unbind();
		$('#searchCustomerBtn').on('click', function() {
			$('#customerSearchGrid').dataTable().fnDraw();
		});

		$('#customerSearchGrid').DataTable({
			"pageLength" : 5,
			"ajax" : {
				"url" : ACC.config.contextPath + "/customer/customerlist",
				"data" : function(d) {
					d.cellphone = $("#searchCustomer-cellphone").val();
					d.customerName = $("#searchCustomer-name").val();
				}
			},
			"fnDrawCallback" : function() {
			}
		});
		var table = $('#customerSearchGrid').DataTable();
		$('#customerSearchGrid tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
	}
};

$(document).ready(function() {
	ACC.searchcustomer.init();
});