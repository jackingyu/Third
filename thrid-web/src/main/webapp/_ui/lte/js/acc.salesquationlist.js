ACC.salesquationlist = {
  query:function(){
	  $('#salesQuationGrid').dataTable().fnDraw();
  },
  modify:function(e){
	  var pk = $(e).attr('pk');
	  window.open(ACC.config.contextPath+"/salesquation/modify/"+pk);
  },
  init: function ()
  {
    $('#salesQuationGrid').DataTable(
      {
      'createdRow': function (row, data, index) {
        $('td', row).eq(5).html(
           "<a  class='btn'  pk='"+data[5]+"' onclick='ACC.salesquationlist.modify(this)'>修改</a>"
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/salesquation/list',
        'data': function (d) {
          formData = getQuery('salesQuationListForm');
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
  ACC.salesquationlist.init();
});
