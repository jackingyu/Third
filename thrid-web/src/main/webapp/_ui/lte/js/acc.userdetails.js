ACC.userdetails = {
	save:function(){
		var storeCodes = $('#storeCodes').val();
		if(storeCodes==null)
		{
			alert("可查看门店不得为空!");
			return;
		}
		
		$("#userForm").submit();
	},
	init:function(){
	  $(".select2").select2();
	  $("#userForm").validate();
	}
}

$(document).ready(function ()
{
  ACC.userdetails.init();
});
