ACC.modifyorder = {
  save:function(){
  	$("#orderForm").submit();
  },
  modifyEntry:function(orderEntryPK){
  	window.open(ACC.config.contextPath+"/orderentry/modifyentrypage/"+orderEntryPK);
  },
  storeApprove:function(){
  	 ACC.modifyorder.approveOrder("10");
  },
  fiApprove:function(){
    ACC.modifyorder.approveOrder("20");
  },
  approveOrder:function(toStatus){
  	$.ajax({
      type: 'POST',
      url: ACC.config.contextPath + '/order/updatestatus',
      data: {'orderCode':$("#orderCode").val(),'toStatus':toStatus}, // 你的formid
      error: function (request) {
         ACC.message.alert("目标状态不正确,无法让订单进入下一个环节!");
      },
      success: function (data) {
      	ACC.message.alert("审核成功!");
      }
    });
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
        $('.openamount').val(data[1]);
        $('.paidamount').val(data[0]);
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
          $('.openamount').val(data[1]);
          $('.paidamount').val(data[0]);
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
       
      }
    });
    
     var table = $('#entryGrid').DataTable();
        $('#entryGrid tbody').on('click', 'tr', function () {
          if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
          } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
          }
        });
  },
  init: function () {
     ACC.modifyorder.initPaymentGrid();
     ACC.modifyorder.initEntryGrid();
     $("#orderCode").attr("readonly","readonly");
  }
}

$(document).ready(function () {
  ACC.modifyorder.init();
});