ACC.orderList = {
  query: function () {
    $('#orderGrid').dataTable().fnDraw();
  },
  exportExcel:function() {
		var d = {};
		formData = getQuery('orderListForm');
        d.orderCode = formData.orderCode;
        d.customerName = formData.customerName;
        d.cellphone = formData.cellphone;
       
        var deliveryDate = $('#deliveryDate').datepicker('getDate');
        if(!isNaN(deliveryDate))
        d.deliveryDate = new Date(deliveryDate).Format('yyyy-MM-dd');
        var orderDate = getDate4Range($("#orderDate").val());
        d.startDate = orderDate[0];
        d.endDate = orderDate[1];
     
        d.storeCodes = $('#storeCodes').val()!=null?$('#storeCodes').val().toString():'';
        d.orderStatus = formData.orderStatus;
		
		var url = ACC.config.contextPath + '/order/export?&startDate='+d.startDate
		          +"&endDate="+d.endDate
		          +"&customerName="+d.customerName
		          +"&orderStatus="+d.orderStatus
		          +"&storeCodes="+d.storeCodes
		          +"&orderCode="+d.orderCode
		          +"&cellphone="+d.cellphone
		
		if(!isNaN(orderDate))
	    {
		   d.orderDate = new Date(orderDate).Format('yyyy-MM-dd');
	       url = url +"&orderDate="+d.orderDate;
	    }
		  
		window.open(url);
  },
  init: function ()
  {
    $('#orderGrid').DataTable({
       'createdRow': function (row, data, index) {
        $('td', row).eq(0).html(
           '<a  href="'+ACC.config.contextPath+"/order/modifyorderpage/"+data[0]+'" target="_blank">'+data[0]+'</a>'
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/order/orderlist',
        'data': function (d) {
          formData = getQuery('orderListForm');
          d.orderCode = formData.orderCode;
          d.customerName = formData.customerName;
          d.cellphone = formData.cellphone;
         
          var deliveryDate = $('#deliveryDate').datepicker('getDate');
          if(!isNaN(deliveryDate))
          d.deliveryDate = new Date(deliveryDate).Format('yyyy-MM-dd');
          var orderDate = getDate4Range($("#orderDate").val());
          d.startDate = orderDate[0];
          d.endDate = orderDate[1];
         
        /*  var storeCodes = $('#storeCodes').val(),;
           d.storeCodes = new Array(storeCodes.length);
          for(var i = 0;i < storeCodes.length;i++)
          {
          	d.storeCodes[i] = storeCodes[i];
          }
          */
          d.storeCodes = $('#storeCodes').val()!=null?$('#storeCodes').val().toString():'';
          d.orderStatus = formData.orderStatus;
           
        }
      },
      'fnDrawCallback': function () {
      }
    });
    $('#orderDate').daterangepicker({
      locale:datepicker_locale_zh,
      startDate:moment().subtract(30, 'days'),
      endDate: moment().add(1,'days')    
     }
    );
    
    $('#deliveryDate').datepicker({
      autoclose:true,
      locale: {
        format: 'YYYY/MM/DD'
      },
      language: 'zh-CN'
    }
    );
  }
}

$(document).ready(function ()
{
  ACC.orderList.init();
});
