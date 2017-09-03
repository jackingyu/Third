ACC.storesourcelist = {
  remove: function(e){
  	var pk = $(e).attr('pk');
    var storeCode = $("#storeCode").val();
    $.ajax({
      type: 'POST',
      url: ACC.config.contextPath + '/source/removesourcefromstore',
      data: {'storeCode':storeCode,'sourcePK':pk}, 
      error: function (request) {
        ACC.message.alert("保存失败");
      },
      success: function (data) {
      	ACC.message.alert("保存成功");
      	ACC.storesourcelist.query();
      }
    });
  },
  save: function(){
  	var sourcePKs = $("#sourcePKs").val();
  	var storeCode = $("#storeCode").val();
    $("#sourcePKs").val(null).trigger("change");
  	$.ajax({
      type: 'POST',
      url: ACC.config.contextPath + '/source/source2store',
      data: {'storeCode':storeCode,'sourcePKs':sourcePKs.toString()}, 
      error: function (request) {
        ACC.message.alert("保存失败");
      },
      success: function (data) {
      	ACC.message.alert("保存成功");
      	ACC.storesourcelist.query();
      }
    });
  },
  query: function () {
    $('#sourceGrid').dataTable().fnDraw();
  },
  init: function ()
  {
    $('#saveSourceBtn').on('click',ACC.storesourcelist.save);
    
    $('#sourceGrid').DataTable(
      {
      'bPaginate': false,
      'createdRow': function (row, data, index) {
        $('td', row).eq(1).html(
           "<a  class='btn' pk='"+data[1]+"' onclick='ACC.storesourcelist.remove(this)'>删除</a>"
        );
        },
      'ajax': {
        'url': ACC.config.contextPath + '/source/listforstore',
        'data': function (d) {
          formData = getQuery('sourceListForm');
          d.storeCode = formData.storeCode;
        }
      },
      'fnDrawCallback': function () {
      }
    });
    
    ACC.storesourcelist.query();
    
    $("#assignSource2StoreBtn").on('click',ACC.storesourcelist.save);
  }
}

$(document).ready(function ()
{
  ACC.storesourcelist.init();
});
