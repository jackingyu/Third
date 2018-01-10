ACC.orderEntryList = {
	query : function() {
		$('#orderEntryGrid').dataTable().fnDraw();
	},
	exportExcel : function() {
		var d = {};
		formData = getQuery('orderEntryListForm');
		d.externalId = formData.externalId==undefined?"":formData.externalId;
		d.customerName = formData.customerName==undefined?"":formData.customerName;

		var deliveryDate = getDate4Range($("#deliveryDate")
				.val());
		d.startDate = deliveryDate[0];
		d.endDate = deliveryDate[1];

		d.orderEntryStatus = formData.orderEntryStatus;
		d.storeCodes = $('#storeCodes').val()!=null?$('#storeCodes').val().toString():'';	
		
		var url = ACC.config.contextPath + '/orderentry/export?externalId='+d.externalId
		          +"&startDate="+d.startDate+"&endDate="+d.endDate+"&customerName="+d.customerName
		          +"&orderEntryStatus="+d.orderEntryStatus
		          +"&storeCodes="+d.storeCodes;
		
		window.open(url);
	},
	init : function() {
		$('#orderEntryGrid').DataTable(
				{
					'createdRow' : function(row, data, index) {
						$('td', row).eq(1).html(
								'<a  href="' + ACC.config.contextPath
										+ "/orderentry/modifyentrypage/"
										+ data[8] + '" target="_blank">'
										+ data[1] + '</a>');
					},
					'ajax' : {
						'url' : ACC.config.contextPath + '/orderentry/list',
						'data' : function(d) {
							formData = getQuery('orderEntryListForm');
							d.externalId = formData.externalId;
							d.customerName = formData.customerName;

							var deliveryDate = getDate4Range($("#deliveryDate")
									.val());
							d.startDate = deliveryDate[0];
							d.endDate = moment(deliveryDate[1]).add(1,'days').format('YYYY-MM-DD');
                   
							var tryDate = getDate4Range($("#tryDate").val());
							/*
							 * var storeCodes = $('#storeCodes').val(),;
							 * d.storeCodes = new Array(storeCodes.length);
							 * for(var i = 0;i < storeCodes.length;i++) {
							 * d.storeCodes[i] = storeCodes[i]; }
							 */

							d.orderEntryStatus = formData.orderEntryStatus;
							d.storeCodes = $('#storeCodes').val()!=null? $('#storeCodes').val().toString():'';
						}
					},
					'fnDrawCallback' : function() {
					}
				});
		$('#deliveryDate').daterangepicker({
			locale : datepicker_locale_zh,
			startDate : moment().subtract(15, 'days'),
			endDate : moment().add(15, 'days')
		});
		$('#tryDate').daterangepicker({
			locale : datepicker_locale_zh
		});
		$('#actualTryDate').daterangepicker({
			locale : datepicker_locale_zh
		});

	}
}

$(document).ready(function() {
	ACC.orderEntryList.init();
});
