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
      'oLanguage': {
        // "sSearch": "搜索条件",
        'sZeroRecords': '抱歉， 没有找到',
        'sInfoEmpty': '无数据',
        'sEmptyTable': '抱歉,没有找到符合要求的数据',
        'sInfoFiltered': '',
        'sProcessing': '正在加载数据...',
        'sInfo': '共 _TOTAL_ 条',
        'sLengthMenu': '每页显示 _MENU_ 条',
        'oPaginate': {
          'sFirst': '首页',
          'sPrevious': '上一页',
          'sNext': '下一页',
          'sLast': '尾页'
        }
      },
      'bPaginate': true,
      //'sPaginationType' : "full_numbers",
      'bLengthChange': false,
      'bFilter': false,
      'iDeferLoading': 1,
      'bProcessing': true, // 开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
      'bServerSide': true, // 开启服务器模式，你对datatables的每个操作
      // 每页显示多少条记录、下一页、上一页、排序（表头）、搜索，这些都会传给服务器相应的值
      //'aLengthMenu' : [[20, 50, 100, 100000], [20, 50, 100, "全部"]],
      //'sDom' : 'frtipl',
      'bStateSave': true,
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
