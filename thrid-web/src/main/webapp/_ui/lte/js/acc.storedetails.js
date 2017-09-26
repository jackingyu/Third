ACC.storedetails = {
	save:function(){
		$("#storeForm").submit();
	},
	init:function(){
	  $(".select2").select2();
	}
}

$(document).ready(function ()
{
  ACC.storedetails.init();
});
