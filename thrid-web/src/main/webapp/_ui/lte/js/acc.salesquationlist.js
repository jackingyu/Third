ACC.salesquationlist = {
  init: function ()
  {
    $('#salesQuationGrid').DataTable(
      {
      'createdRow': function (row, data, index) {
        $('td', row).eq(4).html(
           "<a  class='btn' name='"+data[0]+"' pk='"+data[1]+"' onclick='ACC.sourcelist.modify(this)'>修改</a>"
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/salesquation/list',
        'data': function (d) {
          formData = getQuery('salesQuatioinListForm');
          d.name = formData.name;
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
