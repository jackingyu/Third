ACC.uploadfile = {
	url:null,
	callback:null,
	upload:function(){
		var form = $("#uploadFileForm")[0];
        var fd = new FormData(form);
        var url = ACC.config.contextPath+ACC.uploadfile.url;  
        $.ajax({  
            url:url,  
            type:'POST',
            data:fd,  
            processData:false,  
            contentType:false,  
            success:function(data) {  
               ACC.uploadfile.close();
               ACC.uploadfile.callback(data);
               $.messager.alert("系统提示", "上传成功");
            },  
            error:function(responseStr) {  
               ACC.uploadfile.close();
               $.messager.alert("系统提示", "上传失败");
            }  
        });  
	},
	initUploadWindow:function(key,url,cback)
	{
		$("#upload-file-window").window("open");
		$("#extraParameter").val(key);
		ACC.uploadfile.url = url;
		ACC.uploadfile.callback = cback;
	},
	close:function()
	{
		ACC.uploadfile.reset();
		$("#upload-file-window").window("close");
	},
	reset:function()
	{	
	  $("#fileUpload").text("setValue","");
	}
}