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
