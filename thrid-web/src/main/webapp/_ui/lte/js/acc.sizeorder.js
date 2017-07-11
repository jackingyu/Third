ACC.sizeorder = {
	compositeSizeDatas : function() {
		var sizeDatas = [];
		$.each($("#sizeDataForm").serializeArray(), function(index) {
					var sizeData = {
						name : '',
						value : '',
						group : ''
					};
					var a1 = this.name.split("-");
					sizeData.group = a1[0];
					sizeData.name = a1[1];
					sizeData.value = this.value;
					sizeDatas.push(sizeData);
				});
		return JSON.stringify(sizeDatas);
	},
	saveSizeOrder : function() {
		$("#sizeDatas").val(ACC.sizeorder.compositeSizeDatas());
		$("#orderEntryForm").submit();
	},
	uploadImage : function() {
		var form = $("#uploadSizeImageForm")[0];
        var fd = new FormData(form);
		$.ajax({
					type : 'POST',
					processData:false,  
                    contentType:false,  
					url : ACC.config.contextPath + '/sizeorder/uploadsizeimage',
					data :fd, 
					error : function(request) {
						alert('Connection error');
					},
					success : function(data) {
						$("#fileuploadPanel").modal("hide");
						$("#sizeImageUrl").val(ACC.config.contextPath+data);
						ACC.message.alert("上传成功!");
					}
				});

	},

	init : function() {
		$('#tryDate').datepicker({
					autoclose : true
				});

		$('#deliveryDate').datepicker({
					autoclose : true
				});

		$('#sizeDate').datepicker({
					autoclose : true
				});

		$("#orderEntryPK").attr("readonly", "readonly");
		
		$("#imagePanel").on("show.bs.modal",function(){
		    $("#imagePanel .image").attr("src",$("#sizeImageUrl").val());
		});
	}
}

$(document).ready(function() {
			ACC.sizeorder.init();
		});