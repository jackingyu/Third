ACC.storedetails = {
	save:function(){
	   if($("#storeForm").valid())
		$("#storeForm").submit();
	},
	init:function(){
	  $("#storeForm").validate();
	}
}

$(document).ready(function ()
{
  ACC.storedetails.init();
});
