ACC.userdetails = {
	save:function(){
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
