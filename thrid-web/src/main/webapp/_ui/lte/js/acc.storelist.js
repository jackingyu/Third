ACC.storelist = {
  create: function(){
  	window.open(ACC.config.contextPath+"/store/createstorepage");
  },
  query: function () {
    $('#storeGrid').dataTable().fnDraw();
  },
  init: function ()
  {
    $('#storeGrid').DataTable({
       'createdRow': function (row, data, index) {
        $('td', row).eq(0).html(
           '<a  href="'+ACC.config.contextPath+"/store/modifystorepage/"+data[0]+'" target="_blank">'+data[0]+'</a>'
        );
      },
      'bPaginate': false,
      'ajax': {
        'url': ACC.config.contextPath + '/store/storelist',
        'data': function (d) {
          formData = getQuery('storeListForm');
          d.name = formData.name;
        }
      },
      'fnDrawCallback': function () {
      }
    });
  }
}

$(document).ready(function ()
{
  ACC.storelist.init();
});
