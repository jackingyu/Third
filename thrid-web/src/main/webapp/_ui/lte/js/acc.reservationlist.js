ACC.reservationlist = {
  query: function () {
    $('#reservationGrid').dataTable().fnDraw();
  },
  save: function () {
    $.ajax({
      type: 'POST',
      url: ACC.config.contextPath + '/reservation/save',
      data: $('#reservationForm').serialize(), // 你的formid
      error: function (request) {
        alert('Connection error');
      },
      success: function (data) {
        $('#reservationPanel').modal('hide');
      }
    });
  },
  init: function () {
    $('#reservationGrid').DataTable({
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
      // 'sPaginationType' : "full_numbers",
      'bLengthChange': false,
      'bFilter': false,
      'iDeferLoading': 1,
      'bProcessing': true, // 开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
      'bServerSide': true, // 开启服务器模式，你对datatables的每个操作
      'bStateSave': true,
      'createdRow': function (row, data, index) {
        $('td', row).eq(4).html('<a data-toggle="modal" data-target="#reservationPanel" href="'
        + ACC.config.contextPath
        + '/reservation/modifyreservationpage/'
        + data[4] + '">修改</a>');
      },
      'ajax': {
        'url': ACC.config.contextPath + '/reservation/reservationlist',
        'data': function (d) {
          formData = getQuery('reservationListForm');
          d.name = formData.name;
          d.cellphone = formData.cellphone;
          d.storeCode = formData.storeCode;
          var reservationDate = getDate4Range($('#reservationDate').val());
          d.startDate = reservationDate[0] + ' 00:00:01';
          d.endDate = reservationDate[1] + ' 23:59:59';
        }
      },
      'fnDrawCallback': function () {
      }
    });
    $('#reservationDate').daterangepicker({
      locale: {
        format: 'YYYY/MM/DD'
      }
    });
    $('#reservationPanel').on('hidden.bs.modal', function (e) {
      $(this).removeData('bs.modal');
    });
  }
}

$(document).ready(function () {
  ACC.reservationlist.init();
});
