ACC.scanorder = {
  lockflag :'',
  status:'',
  message:'',
  lock:function(){
  	ACC.scanorder.lockflag = 'X';
  },
  unlock:function(){
  	ACC.scanorder.lockflag = '';
  },
  isLock:function(){  
  	return ACC.scanorder.lockflag == 'X';
  },
  delivery:function(){
   var i = 0;
   $('#orderEntryGrid tbody tr').each(function(){
     if(!$(this).hasClass('picked'))
      i++;
   });
   
   if(i!=0)
   {
      ACC.message.alert("剩余"+i+"量身单未拣货,请查看表格中非绿色行");
      //return;
   }
   
    var orderCode  = $('#orderEntryGrid').DataTable().row(0).data()[5];
    $.ajax({
      type: 'POST',
      url: ACC.config.contextPath + '/order/updatestatus',
      data: {'orderCode':orderCode,'toStatus':ACC.scanorder.status}, // 你的formid
      error: function (request) {
         ACC.message.alert("请检查该订单状态！");
      },
      success: function (data) {
      	ACC.message.alert(ACC.scanorder.message);
      }
    });

  },
  reset:function(){
  	$('#entryPK').val('');
  	$('#externalId').val('');
  	ACC.scanorder.unlock();
  },
  scan:function(){
  	var i = 0;
    $('#orderEntryGrid').DataTable().rows().every( function () {
       var d = this.data();
       if(d[4] == $('#entryPK').val())
       {
       	$('#orderEntryGrid tbody tr:eq('+i+')').addClass('picked');
       }
       i++;
    });
  },
  getEntries:function(){
    $('#orderEntryGrid').dataTable().fnDraw();
  },
  init:function(){
  	
  	$('#entryPK').on('keypress',function(event){ 
       if(event.keyCode == 13)      
       { 
       	 //only can get another details in unlock model
       	if($('#entryPK').val() == 'chongzhi')
       	{
        	ACC.scanorder.reset();
        	return;
       	}
       	
       	 if(!ACC.scanorder.isLock())
        	ACC.scanorder.getEntries();
         else
           if($('#entryPK').val() == 'fahuo')
            ACC.scanorder.delivery();
           else
            ACC.scanorder.scan();
            
          $('#externalId').val('');
          $('#entryPK').val('');
       }  
     });
     
  	$('#externalId').on('keypress',function(event){ 
       if(event.keyCode == 13)      
       {  
       	if(!ACC.scanorder.isLock())
          ACC.scanorder.getEntries();
          $('#externalId').val('');
          $('#entryPK').val('');
          $('#entryPK').focus();
       }  
     });
   JsBarcode("#deliveryCanvasOK", "fahuo", {
     format: "CODE128"//选择要使用的条形码类型
   });
   JsBarcode("#deliveryCanvasR", "chongzhi", {
     format: "CODE128"//选择要使用的条形码类型
   });
   
 
   $('#orderEntryGrid').DataTable({
      'ajax': {
        'url': ACC.config.contextPath + '/orderscan/getentrylist',
        'data': function (d) {
          formData = getQuery('orderEntryForm');
          d.entryPK = formData.entryPK;
          d.extenralId = formData.externalId;
          d.currentStatus = formData.currentStatus;
        }
      },
      'fnDrawCallback': function () {
      	var r = $('#orderEntryGrid').DataTable().rows();
      	var l = r[0].length;
      	if(l > 0)
      	  ACC.scanorder.lock();
      }
    });
   
    ACC.scanorder.message = $('#successmessage').val();
    ACC.scanorder.status = $('#status').val();
  }
}

$(document).ready(function() {
	ACC.scanorder.init();
});