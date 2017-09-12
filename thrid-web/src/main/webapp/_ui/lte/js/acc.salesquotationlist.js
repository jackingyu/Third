ACC.salesquotationlist = {
  query:function(){
	  $('#salesQuotationGrid').dataTable().fnDraw();
  },
  modify:function(e){
	  var pk = $(e).attr('pk');
	  window.open(ACC.config.contextPath+"/salesquotation/modify/"+pk);
  },
  init: function ()
  {
    $('#salesQuotationGrid').DataTable(
      {
      'createdRow': function (row, data, index) {
        $('td', row).eq(5).html(
           "<a  class='btn'  pk='"+data[5]+"' onclick='ACC.salesquotationlist.modify(this)'>修改</a>"
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/salesquotation/list',
        'data': function (d) {
          formData = getQuery('salesQuotationListForm');
          d.cellphone = formData.cellphone;
          d.exhibitions =  $('#exhibitions').val()!=null? $('#exhibitions').val().toString():'';
          var createDate = getDate4Range($("#createDate").val());
          d.startDate = createDate[0];
          d.endDate = createDate[1];
        }
      },
      'fnDrawCallback': function () {
      }
    });
    
    $('#createDate').daterangepicker({
        locale:datepicker_locale_zh,
        startDate: moment(),
        endDate:moment().add(15, 'days')
      }
      );
  }
}

$(document).ready(function ()
{
  ACC.salesquotationlist.init();
});
