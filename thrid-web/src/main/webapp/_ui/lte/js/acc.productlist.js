ACC.productlist = {
  query: function () {
    $('#productGrid').dataTable().fnDraw();
  },
  init: function () {
    $('#productGrid').DataTable({
      'createdRow': function (row, data, index) {
        $('td', row).eq(0).html(
           '<a  href="'+ACC.config.contextPath+"/product/modifyproductpage/"+data[0]+'" target="_blank">'+data[0]+'</a>'
        );
      },
      'ajax': {
        'url': ACC.config.contextPath + '/product/productlist1',
        'data': function (d) {
          formData = getQuery('productListForm');
          d.productCode = formData.productCode;
          d.productTitle = formData.productTitle;
          d.productGroups = $('#productGroups').val()!=null?$('#productGroups').val().toString():''; 
          d.categories = $('#categories').val()!=null?$('#categories').val().toString():''; 
        }
      },
      'fnDrawCallback': function () {
      }
    });
    
     if($("#productGroups").val()!=null)
     $("#productGroups").val(null).trigger("change");
     
    if($("#categories").val()!=null)
     $("#categories").val(null).trigger("change");
  }
};

$(document).ready(function () {
  ACC.productlist.init();
});
