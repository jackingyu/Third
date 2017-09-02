ACC.searchproduct = {
	callback : null,
	select : function() {
		var selectedRow = $('#productSearchGrid').DataTable().rows('.selected');

		if(selectedRow.length > 0)
		{
		    var dd = selectedRow.data();
		    ACC.searchproduct.callback(dd[0]);
		}
		
	    $('#SearchPanel').modal('hide');
	},
	init : function() {
		$('#searchProduct-select').unbind();
		$('#searchProduct-select').on('click', ACC.searchproduct.select);
		$('#searchProductBtn').unbind();
		$('#searchProductBtn').on('click', function() {
					$('#productSearchGrid').dataTable().fnDraw();
				});
				
		$('#productSearchGrid').DataTable({
					"ajax" : {
						"url" : ACC.config.contextPath + "/product/productlist",
						"data" : function(d) {
							 d.productCode = $("#searchProduct-productCode").val();
							 d.productTitle = $("#searchProduct-productTitle").val();
							 d.category = $("#searchProduct-category").val();
						}
					},
					"fnDrawCallback" : function() {
					}
				});
		var table = $('#productSearchGrid').DataTable();
		$('#productSearchGrid tbody').on('click', 'tr', function() {
					if ($(this).hasClass('selected')) {
						$(this).removeClass('selected');
					} else {
						table.$('tr.selected').removeClass('selected');
						$(this).addClass('selected');
					}
				});
	}
};

$(document).ready(function() {
			ACC.searchproduct.init();
		});