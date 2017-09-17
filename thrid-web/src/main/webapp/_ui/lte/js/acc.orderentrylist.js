ACC.orderEntryList = {
  query: function () {
    $('#orderEntryGrid').dataTable().fnDraw();
  },
  init: function ()
  {
    $('#orderEntryGrid').DataTable({
      'createdRow': function (row, data, index) {
        $('td', row).eq(1).html(
           '<a  href="'+ACC.config.contextPath+"/orderentry/modifyentrypage/"+data[6]+'" target="_blank">'+data[1]+'</a>'
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/orderentry/list',
        'data': function (d) {
          formData = getQuery('orderEntryListForm');
          d.externalId = formData.externalId;
          d.customerName = formData.customerName;
         
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
          
          d.orderEntryStatus = formData.orderEntryStatus;
          d.storeCodes = $('#storeCodes').val().toString();
        }
      },
      'fnDrawCallback': function () {
      }
    });
    $('#deliveryDate').daterangepicker({
      locale:datepicker_locale_zh,
      startDate:moment().subtract(15, 'days'),
      endDate: moment().add(15,'days')
    }
    );
    
  }
}

$(document).ready(function ()
{
  ACC.orderEntryList.init();
});
