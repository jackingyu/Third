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
		
		var tryDate = getDate4Range($("#tryDate")
				.val());
		if(tryDate.length > 0&&tryDate[0]!='NaN-aN-aN')
	    {
		  d.startTryDate = tryDate[0];
		  d.endTryDate = tryDate[1];//moment(tryDate[1]).add(1,'days').format('YYYY-MM-DD');
	    }
		
		var actualTryDate = getDate4Range($("#actualTryDate").val());
		
		if(actualTryDate.length > 0&&actualTryDate[0]!='NaN-aN-aN')
		{
			d.startActualTryDate = actualTryDate[0];
			d.endActualTryDate = actualTryDate[1];//moment(actualTryDate[1]).add(1,'days').format('YYYY-MM-DD');
		}
		
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
							d.endDate = deliveryDate[1];//moment(deliveryDate[1]).add(1,'days').format('YYYY-MM-DD');
							
							var tryDate = getDate4Range($("#tryDate")
									.val());
							if(tryDate.length > 0&&tryDate[0]!='NaN-aN-aN')
						    {
							  d.startTryDate = tryDate[0];
							  d.endTryDate = tryDate[1];//moment(tryDate[1]).add(1,'days').format('YYYY-MM-DD');
						    }
							
							var actualTryDate = getDate4Range($("#actualTryDate").val());
							
							if(actualTryDate.length > 0&&actualTryDate[0]!='NaN-aN-aN')
							{
								d.startActualTryDate = actualTryDate[0];
								d.endActualTryDate = actualTryDate[1];//moment(actualTryDate[1]).add(1,'days').format('YYYY-MM-DD');
							}
							
							
							
							
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
			endDate : moment().add(365, 'days')
		});
		$('#tryDate').daterangepicker({
			locale : datepicker_locale_zh,
			autoUpdateInput: false
		});
		
		$('#tryDate').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('YYYY/MM/DD') + ' - ' + picker.endDate.format('YYYY/MM/DD'));
		  });

		  $('#tryDate').on('cancel.daterangepicker', function(ev, picker) {
		      $(this).val('');
		  });

		$('#actualTryDate').daterangepicker({
			locale : datepicker_locale_zh,
			autoUpdateInput: false
		});
		
		$('#actualTryDate').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('YYYY/MM/DD') + ' - ' + picker.endDate.format('YYYY/MM/DD'));
		  });

		  $('#actualTryDate').on('cancel.daterangepicker', function(ev, picker) {
		      $(this).val('');
		  });


	}
}

$(document).ready(function() {
	ACC.orderEntryList.init();
});
