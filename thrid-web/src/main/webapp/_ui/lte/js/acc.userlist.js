ACC.userlist = {
  create: function(){
  	window.open(ACC.config.contextPath+"/user/createuserpage");
  },
  query: function () {
    $('#userGrid').dataTable().fnDraw();
  },
  init: function ()
  {
    $('#userGrid').DataTable({
       'createdRow': function (row, data, index) {
        $('td', row).eq(4).html(
           '<a  href="'+ACC.config.contextPath+"/user/modifyuserpage/"+data[0]+'" target="_blank">修改</a>'
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/user/userlist',
        'data': function (d) {
          formData = getQuery('userListForm');
          d.userId = formData.userId;
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
  ACC.userlist.init();
});
