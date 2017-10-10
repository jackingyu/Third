ACC.orderList = {
  query: function () {
    $('#orderGrid').dataTable().fnDraw();
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
         
          var orderDate = $('#orderDate').datepicker('getDate');
          if(!isNaN(orderDate))
          d.orderDate = new Date(orderDate).Format('yyyy-MM-dd');
          var deliveryDate = getDate4Range($("#deliveryDate").val());
          d.startDate = deliveryDate[0];
          d.endDate = deliveryDate[1];
         
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
    $('#deliveryDate').daterangepicker({
      locale:datepicker_locale_zh,
      startDate:moment().subtract(15, 'days'),
      endDate: moment().add(15,'days')    }
    );
    
    $('#orderDate').datepicker({
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
