ACC.message = {
  alert:function(text){
  	$("#message").html(text);
  	$("#messagePanel").modal("show");
  },
  init:function(){
    $("#messagePanel").on("shown.bs.modal",function(){
      setTimeout(function(){$("#messagePanel").modal("hide")},200);
    });
  }
}


$(document).ready(function() {
	ACC.message.init();
});