ACC.customerlist = {
  create: function(){
  	window.open(ACC.config.contextPath+"/customer/createcustomerpage");
  },
  modify: function(){
  },
  save: function(){
  },
  query: function () {
    $('#customerGrid').dataTable().fnDraw();
  },
  init: function ()
  {
    $('#customerGrid').DataTable({
       'createdRow': function (row, data, index) {
        $('td', row).eq(4).html(
           '<a  href="'+ACC.config.contextPath+"/customer/modifycustomerpage/"+data[0]+'" target="_blank">修改</a>'
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/customer/customerlist',
        'data': function (d) {
          formData = getQuery('customerListForm');
          d.cellphone = formData.cellphone;
          d.customerName = formData.customerName;
          if($("#weddingDate").val()!=''&&$("#weddingDate").val()!=undefined&&$("#weddingDate").val()!=null)
        	  {
        	  	var weddingDate = getDate4Range($("#weddingDate").val());
        	  	d.startDate = weddingDate[0];
        	  	d.endDate = weddingDate[1];
        	  }
          
        }
      },
      'fnDrawCallback': function () {
      }
    });
    
    $('#weddingDate').daterangepicker({
        locale:datepicker_locale_zh,
        startDate:moment().subtract(1,'years'),
        endDate: moment().add(1,'years')  
        }
      );
  }
}

$(document).ready(function ()
{
  ACC.customerlist.init();
});
