ACC.userdetails = {
	save:function(){
		$("#userForm").submit();
	},
	init:function(){
	  $(".select2").select2();
	}
}

$(document).ready(function ()
{
  ACC.userdetails.init();
});
