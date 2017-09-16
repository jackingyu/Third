ACC.customerdetails = {
	save : function() {
		$("#customerForm").submit();
	},

	init : function() {
		$("#weddingdate").datepicker();

		$("#birthday").datepicker();

		$(".select2").select2();

		if ($("#cellphone").val() != null && $("#cellphone").val() != '')
			$("#cellphone").attr("readonly", "readonly");

		$("#region").on(
				"select2:select",
				function(e) {
					var regionISOCode = $("#region option:selected").val();
					$.ajax({
						type : 'GET',
						url : ACC.config.contextPath
								+ '/address/getcityforregion',
						data : {
							regionISOCode : regionISOCode
						}, // 你的formid
						error : function(request) {
							alert('Connection error');
						},
						success : function(data) {
							$("#city").empty();
							for (var i = 0; i < data.length; i++)
								$("#city").append(
										"<option value='" + data[i].code
												+ "'>&nbsp;" + data[i].text
												+ "</option>");
						}
					});

				});
	}
}

$(document).ready(function() {
	ACC.customerdetails.init();
});
