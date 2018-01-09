ACC.orderprocessrecord = {
	query : function() {
		$('#orderProcessRecordGrid').dataTable().fnDraw();
	},
	exportExcel : function() {
		var d = {};
		 formData = getQuery('orderProcessRecordForm');
         d.externalId = formData.externalId;
        
         var processTime = getDate4Range($("#processTime").val());
         d.startProcessTime = processTime[0];
         d.endProcessTime = processTime[1];
         d.orderStatus = formData.orderStatus;
         d.storeCodes = $('#storeCodes').val()!=null?$('#storeCodes').val().toString():'';
         
		var url = ACC.config.contextPath + '/order/exportorderprocessrecord?externalId='+d.externalId
		          +"&startProcessTime="+d.startProcessTime+"&endProcessTime="+d.endProcessTime
		          +"&orderStatus="+d.orderStatus
		          +"&storeCodes="+d.storeCodes;
		
		window.open(url);
	},
	init: function ()
	  {
	    $('#orderProcessRecordGrid').DataTable({
	       'createdRow': function (row, data, index) {
//	        $('td', row).eq(0).html(
//	           '<a  href="'+ACC.config.contextPath+"/order/modifyorderpage/"+data[0]+'" target="_blank">'+data[0]+'</a>'
//	        );
	      },
	      'ajax': {
	        'url': ACC.config.contextPath + '/order/getorderprocessrecord',
	        'data': function (d) {
	          formData = getQuery('orderProcessRecordForm');
	          d.externalId = formData.externalId;
	         
	          var processTime = getDate4Range($("#processTime").val());
	          d.startProcessTime = processTime[0];
	          d.endProcessTime = processTime[1];
              d.orderStatus = formData.orderStatus;
	          d.storeCodes = $('#storeCodes').val()!=null?$('#storeCodes').val().toString():'';
	        }
	      },
	      'fnDrawCallback': function () {
	      }
	    });
	    $('#processTime').daterangepicker({
	      locale:datepicker_locale_zh,
	      startDate:moment().subtract(15, 'days'),
	      endDate: moment().add(15,'days')    }
	    );
	  }
}

$(document).ready(function() {
	ACC.orderprocessrecord.init();
});
