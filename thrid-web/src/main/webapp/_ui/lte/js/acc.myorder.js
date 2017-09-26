ACC.myorder = {
	query: function(status) {
		 $.ajax({
		      type: 'GET',
		      url: ACC.config.contextPath + '/order/myorder',
		      data: {"orderStatus":status}, // 你的formid
		      error: function (request) {
		        alert('Connection error');
		      },
		      success: function (data) {
		    	    $("#myOrderPanel").empty();
		        $("#myOrderPanel").append(data);
		      }
		    });
	}
}

