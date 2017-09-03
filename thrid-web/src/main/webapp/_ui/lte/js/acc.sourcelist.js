ACC.sourcelist = {
  modify: function(e){
  	var name= $(e).attr('name');
  	var pk = $(e).attr('pk');
  	$('#sourceForm input[name="name"]').val(name);
  	$('#sourceForm input[name="pk"]').val(pk);
  	
  	$('#sourcePanel').modal('show');
  },
  save: function(){
  	var sourceName = $("#sourceName").val();
  	var sourcePK = $("#sourcePK").val();
  	$('#sourcePanel').modal('hide');
    $.ajax({
      type: 'POST',
      url: ACC.config.contextPath + '/source/save',
      data: {'name':sourceName,'pk':sourcePK}, 
      error: function (request) {
        ACC.message.alert("保存失败");
      },
      success: function (data) {
      	ACC.message.alert("保存成功");
      	ACC.sourcelist.query();
      }
    });
  },
  query: function () {
    $('#sourceGrid').dataTable().fnDraw();
  },
  init: function ()
  {
  	$('#sourcePanel').on('hide.bs.modal', function () {
      $('#sourceForm input').val('');
    });
    
    $('#saveSourceBtn').on('click',ACC.sourcelist.save);
    
    $('#sourceGrid').DataTable(
      {
      'bPaginate': false,
      'createdRow': function (row, data, index) {
        $('td', row).eq(1).html(
           "<a  class='btn' name='"+data[0]+"' pk='"+data[1]+"' onclick='ACC.sourcelist.modify(this)'>修改</a>"
        );
        },
      'ajax': {
        'url': ACC.config.contextPath + '/source/list',
        'data': function (d) {
          formData = getQuery('sourceListForm');
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
  ACC.sourcelist.init();
});
