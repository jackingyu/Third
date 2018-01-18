ACC.paymentList = {
  query: function () {
    $('#paymentGrid').dataTable().fnDraw();
  },
  exportExcel:function() {
	  var d = {};
	  var deliveryDate = getDate4Range($("#paymentDate").val());
	  d.startDate = deliveryDate[0];
	  d.endDate = moment(deliveryDate[1]).add(1,'days').format('YYYY-MM-DD');
	  d.storeCodes = $('#storeCodes').val()!=null?$('#storeCodes').val().toString():'';
	  d.sourcePKs = $('#sourcePKs').val()!=null?$('#sourcePKs').val().toString():'';
	  d.paymentMethods = $('#paymentMethods').val()!=null?$('#paymentMethods').val().toString():'';
	  d.orderStatus = $('#orderStatus').val()!=null?$('#orderStatus').val().toString():'';
	  d.salesPersons = $('#salesPersons').val()!=null?$('#salesPersons').val().toString():'';
		
		var url = ACC.config.contextPath + '/payment/export?storeCodes='+d.storeCodes
		          +"&startDate="+d.startDate+"&endDate="+d.endDate+"&sourcePKs="+d.sourcePKs
		          +"&paymentMethods="+d.paymentMethods
		          +"&orderStatus="+d.orderStatus
		          +"&salesPersons"+d.salesPersons;
		
		window.open(url);
},
  querySummary:function(){
	  var deliveryDate = getDate4Range($("#paymentDate").val());
	  var d = {};
      d.startDate = deliveryDate[0];
      d.endDate = moment(deliveryDate[1]).add(1,'days').format('YYYY-MM-DD');
      d.storeCodes = $('#storeCodes').val()!=null?$('#storeCodes').val().toString():'';
      d.sourcePKs = $('#sourcePKs').val()!=null?$('#sourcePKs').val().toString():'';
      d.paymentMethods = $('#paymentMethods').val()!=null?$('#paymentMethods').val().toString():'';
      d.orderStatus = $('#orderStatus').val()!=null?$('#orderStatus').val().toString():'';
      d.salesPersons = $('#salesPersons').val()!=null?$('#salesPersons').val().toString():'';
      
	  $.ajax({
  	      type: 'GET',
  	      url: ACC.config.contextPath + '/payment/getsummary',
  	      data: d, 
  	      error: function (request) {
  	        ACC.message.alert("通讯失败");
  	      },
  	      success: function (data) {
  	      	$("#cashAmount").val(data[0]);
  	    	    $("#creditCardAmount").val(data[1]);
  	        $("#alipayAmount").val(data[2]);
  	        $("#receiveable").val(data[3]);
  	        $("#openAmount").val(data[4]);
  	        $("#paidAmount").val(data[5]);
  	        $("#totalPayment").val(data[0]+data[1]+data[2]);
  	      }
  	  });
  },
  init: function ()
  {
    $('#paymentGrid').DataTable({
       'createdRow': function (row, data, index) {
        $('td', row).eq(2).html(
           '<a  href="'+ACC.config.contextPath+"/order/modifyorderpage/"+data[2]+'" >'+data[2]+'</a>'
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/payment/getlist',
        'data': function (d) {
           var deliveryDate = getDate4Range($("#paymentDate").val());
           d.startDate = deliveryDate[0];
           d.endDate = moment(deliveryDate[1]).add(1,'days').format('YYYY-MM-DD');
           d.storeCodes = $('#storeCodes').val()!=null?$('#storeCodes').val().toString():'';
           d.sourcePKs = $('#sourcePKs').val()!=null?$('#sourcePKs').val().toString():'';
           d.paymentMethods = $('#paymentMethods').val()!=null?$('#paymentMethods').val().toString():'';
           d.orderStatus = $('#orderStatus').val()!=null?$('#orderStatus').val().toString():'';
           d.salesPersons = $('#salesPersons').val()!=null?$('#salesPersons').val().toString():'';
       
        }
      },
      'fnDrawCallback': function () {
    	      ACC.paymentList.querySummary();
      }
    });
    
    $('#paymentDate').daterangepicker({
      locale:datepicker_locale_zh,
      startDate:moment().subtract(1, 'months'),
      endDate: moment().add(1,'days')
    }
    );
    
    if($("#sourcePKs").val()!=null)
     $("#sourcePKs").val(null).trigger("change");
     
    if($("#storeCodes").val()!=null)
     $("#storeCodes").val(null).trigger("change");
     
    if($("#salesPersons").val()!=null)
     $("#salesPersons").val(null).trigger("change");
    
  }
}

$(document).ready(function ()
{
  ACC.paymentList.init();
});
