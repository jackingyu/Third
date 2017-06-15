ACC.modifyorder = {
  save:function(){
  	$("#orderForm").submit();
  },
  modifyEntry:function(orderEntryPK){
  	window.open(ACC.config.contextPath+"/orderentry/modifyentrypage/"+orderEntryPK);
  },
  addPayment: function () {
    $.ajax({
      type: 'POST',
      url: ACC.config.contextPath + '/order/addpayment',
      data: $('#paymentForm').serialize(), // 你的formid
      error: function (request) {
        alert('Connection error');
      },
      success: function (data) {
        ACC.modifyorder.refreshPayments();
        $('#orderPaymentPanel').modal('hide');
      }
    });
  },
  removePayment: function (paymentPK) {
    $('#confirmDialog-message').html('确认删除付款记录?');
    $('#confirmDialog').modal('show');
    $('#confirmDialog-confirmBtn').unbind();
    $('#confirmDialog-confirmBtn').on('click', {
      'paymentPK': paymentPK
    }, function (d) {
      $.ajax({
        type: 'POST',
        url: ACC.config.contextPath
        + '/order/removepayment?paymentPK='
        + d.data.paymentPK,
        error: function (request) {
          alert('Connection error');
          $('#confirmDialog').modal('hide');
        },
        success: function (data) {
          $('#confirmDialog').modal('hide');
          ACC.modifyorder.refreshPayments();
        }
      });
    });
  },
  refreshPayments: function () {
    $('#paymentGrid').dataTable().fnDraw();
  },
  createSizeOrderPage: function () {
    window.open(ACC.config.contextPath + '/orderentry/createorderentrypage/' +
    $('#orderCode').val() + '?itemCategory=' + $('#itemCategory').val());
  },
  initPaymentGrid:function(){
  	// 初始化 paymentGrid
    var orderCode = $('#orderCode').val();
    $('#paymentGrid').DataTable({
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
      'bPaginate': false,
      // 'sPaginationType' : "full_numbers",
      'bLength': 5,
      'bLengthChange': false,
      'bFilter': false,
      'iDeferLoading': 1,
      'bProcessing': true, // 开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
      'bServerSide': true, // 开启服务器模式，你对datatables的每个操作
      // 每页显示多少条记录、下一页、上一页、排序（表头）、搜索，这些都会传给服务器相应的值
      // 'aLengthMenu' : [[20, 50, 100, 100000], [20, 50, 100,
      // "全部"]],
      // 'sDom' : 'frtipl',
      'bStateSave': true,
      'createdRow': function (row, data, index) {
        $('td', row).eq(4).html('<a class="removePaymentBtn" onclick="ACC.modifyorder.removePayment(\'' + data[4] + '\')" paymentpk="' +
        data[4] +
        '"> <i class="fa fa-remove"></i>删除</a>'
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/order/payments/' + orderCode,
        'data': function (d) {
          // d.approveStatus = $('#approveStatus
          // option:selected').val();
        }
      },
      'fnDrawCallback': function () {
        var table = $('#paymentGrid').DataTable();
        $('#paymentGrid tbody').on('click', 'tr', function () {
          if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
          } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
          }
        });
      }
    });
    // 初始化addPayment button
    $('#addPaymentBtn').on('click', ACC.modifyorder.addPayment);

  },
  initEntryGrid:function(){
  	// 初始化 paymentGrid
    var orderCode = $('#orderCode').val();
    $('#entryGrid').DataTable({
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
      'bPaginate': false,
      // 'sPaginationType' : "full_numbers",
      'bLength': 5,
      'bLengthChange': false,
      'bFilter': false,
      'iDeferLoading': 1,
      'bProcessing': true, // 开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
      'bServerSide': true, // 开启服务器模式，你对datatables的每个操作
      // 每页显示多少条记录、下一页、上一页、排序（表头）、搜索，这些都会传给服务器相应的值
      // 'aLengthMenu' : [[20, 50, 100, 100000], [20, 50, 100,
      // "全部"]],
      // 'sDom' : 'frtipl',
      'bStateSave': true,
      'createdRow': function (row, data, index) {
        $('td', row).eq(4).html('<a class="btn" onclick="ACC.modifyorder.modifyEntry(\'' + data[4] + '\')" paymentpk="' +
        data[4] +
        '"><i class="fa fa-edit"></i>编辑</a>'
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/order/entries/' + orderCode,
        'data': function (d) {
          // d.approveStatus = $('#approveStatus
          // option:selected').val();
        }
      },
      'fnDrawCallback': function () {
        var table = $('#entryGrid').DataTable();
        $('#entryGrid tbody').on('click', 'tr', function () {
          if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
          } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
          }
        });
      }
    });
  },
  init: function () {
     ACC.modifyorder.initPaymentGrid();
     ACC.modifyorder.initEntryGrid();
  }
}

$(document).ready(function () {
  ACC.modifyorder.init();
});