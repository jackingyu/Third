ACC.scansizeorder = {
	save : function() {
		var d = {};
		formData = getQuery('sizeOrderForm');
		d.externalId = formData.externalId==undefined?"":formData.externalId;
		d.entryPK = formData.entryPK==undefined?"":formData.entryPK;
		$.ajax({
	  	      type: 'POST',
	  	      url: ACC.config.contextPath + '/orderentry/updateactualtrydate',
	  	      data: d, 
	  	      dataType:'json',
	  	      error: function (request) {
	  	        ACC.message.alert("通讯失败");
	  	      },
	  	      success: function (data) {
	  	       	ACC.message.alert("量身单更新成功!");
	  	      }
	  	});
		
		$('#record').val($('#externalId').val()+"/"+$('#entryPK').val());
        $('#externalId').val('');
        $('#entryPK').val('');
	},
	init : function() {
		$('#entryPK').on('keypress', function(event) {
			if (event.keyCode == 13) {
				ACC.scansizeorder.save();
			}
		});

		$('#externalId').on('keypress', function(event) {
			if (event.keyCode == 13) {
				ACC.scansizeorder.save();
			}
		});

	}
}

$(document).ready(function() {
	ACC.scansizeorder.init();
});
