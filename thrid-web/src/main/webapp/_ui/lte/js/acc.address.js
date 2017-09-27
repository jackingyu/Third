ACC.address = {
	init:function(){
		$("#region").on("select2:select", function(e) {
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
							{
								$("#city").append("<option value='"
										+ data[i].code + "'>&nbsp;"
										+ data[i].text + "</option>");
								
							}
							$.ajax({
								type : 'GET',
								url : ACC.config.contextPath
								+ '/address/getdistrictforcity',
								data : {
									cityISOCode:data[0].code
								}, // 你的formid
								error : function(request) {
									alert('Connection error');
								},
								success : function(data) {
									$("#district").empty();
									for (var i = 0; i < data.length; i++)
										$("#district").append("<option value='"
												+ data[i].code + "'>&nbsp;"
												+ data[i].text + "</option>");
								}
							});
						}
					});

		});
		
		$("#city").on("select2:select", function(e) {
			var cityISOCode = $("#city option:selected").val();
			$.ajax({
				type : 'GET',
				url : ACC.config.contextPath
				+ '/address/getdistrictforcity',
				data : {
					cityISOCode:cityISOCode
				}, // 你的formid
				error : function(request) {
					alert('Connection error');
				},
				success : function(data) {
					$("#district").empty();
					for (var i = 0; i < data.length; i++)
						$("#district").append("<option value='"
								+ data[i].code + "'>&nbsp;"
								+ data[i].text + "</option>");
				}
			});
			
		});
	}
}

$(document).ready(function() {
	//alert($(window).height());
	ACC.address.init();
});