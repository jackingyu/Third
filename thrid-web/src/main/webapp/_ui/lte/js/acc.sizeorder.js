ACC.sizeorder = {
	compositeSizeDatas:function(){
	   var sizeDatas = [];
        $.each($("#sizeDataForm").serializeArray(),function(index){
        	  var sizeData = { name:'',value:'',group:''};
        	  var a1 = this.name.split("-");
              sizeData.group = a1[0];
              sizeData.name = a1[1];
              sizeData.value = this.value;
              sizeDatas.push(sizeData);
        });
      return JSON.stringify(sizeDatas);
	},
	saveSizeOrder:function(){
		$("#sizeDatas").val(ACC.sizeorder.compositeSizeDatas());
		$("#orderEntryForm").submit();
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
		
		$("#orderEntryPK").attr("readonly","readonly");
	}
}

$(document).ready(function() {
			ACC.sizeorder.init();
});