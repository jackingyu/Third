<%@ tag body-content="empty" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>

<template:javaScriptVariables/>
<!-- jQuery 2.2.3 -->
<script src="${lteResourcePath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${lteResourcePath}/plugins/jQueryUI/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script src="${lteResourcePath}/bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="${lteResourcePath}/plugins/raphael-min.js"></script>
<!-- Select2 -->
<script src="${lteResourcePath}/plugins/select2/select2.full.min.js"></script>
<script src="${lteResourcePath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${lteResourcePath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- Sparkline -->
<script src="${lteResourcePath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${lteResourcePath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${lteResourcePath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="${lteResourcePath}/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="${lteResourcePath}/plugins/moment.min.js"></script>
<script src="${lteResourcePath}/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="${lteResourcePath}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${lteResourcePath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="${lteResourcePath}/plugins/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="${lteResourcePath}/plugins/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="${lteResourcePath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="${lteResourcePath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${lteResourcePath}/plugins/fastclick/fastclick.js"></script>

<script src="${lteResourcePath}/plugins/iCheck/icheck.min.js"></script>
<!-- AdminLTE App -->
<script src="${lteResourcePath}/dist/js/app.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!-- AdminLTE for demo purposes -->
<script src="${lteResourcePath}/dist/js/demo.js"></script>
<script src="${lteResourcePath}/js/jquery.validate.js"></script>
<script src="${lteResourcePath}/js/jquery.validate.customize.js"></script>
<script src="${lteResourcePath}/js/acc.common.js"></script>
    <script type="text/javascript">
    function home(){
    	    window.location = ACC.config.contextPath+"/home";
    }
    function getQuery(formId){  
        var fid = "#" + formId;  
        var str = $(fid).serialize();  
        str = decodeURIComponent(str,true);
        //str= cardSelectDate=3&startdate=2012-02-01&enddate=2012-02-04  
        var ob= strToObj(str); 
        return ob;
      }  
         
      function strToObj(str){  
        str = str.replace(/&/g,"','");  
        str = str.replace(/=/g,"':'");  
        str = "({'"+str +"'})";  
        obj = eval(str);   
        return obj;  
      }  
    
      String.prototype.condense = function() 
      { 
      	return this.replace(/\s+/g, ""); 
      }
      
      Date.prototype.Format = function(fmt)   
      { //author: meizz   
        var o = {   
          "M+" : this.getMonth()+1,                 //月份   
          "d+" : this.getDate(),                    //日   
          "h+" : this.getHours(),                   //小时   
          "m+" : this.getMinutes(),                 //分   
          "s+" : this.getSeconds(),                 //秒   
          "q+" : Math.floor((this.getMonth()+3)/3), //季度   
          "S"  : this.getMilliseconds()             //毫秒   
        };   
        if(/(y+)/.test(fmt))   
          fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
        for(var k in o)   
          if(new RegExp("("+ k +")").test(fmt))   
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
        return fmt;   
      }  
      
      function getDate4Range(val){
    	  var theDate  = val.condense().split("-");
          var startDate = new Date(theDate[0]);
          var endDate = new Date(theDate[1]);
          var result = new Array();
          result[0] = startDate.Format("yyyy-MM-dd");
          result[1] = endDate.Format("yyyy-MM-dd");
          return result;
      }
      
      function isMobile(){
    	 if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) )
    	     return true;
    	 else 
    		 return false;
      }
      
      var datepicker_locale_zh =  {
              "format": 'YYYY/MM/DD',
              "separator": " - ",
              "applyLabel": "确定",
              "cancelLabel": "取消",
              "fromLabel": "起始时间",
              "toLabel": "结束时间'",
              "customRangeLabel": "自定义",
              "weekLabel": "W",
              "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
              "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
              "firstDay": 1
          };
</script>
    
    
