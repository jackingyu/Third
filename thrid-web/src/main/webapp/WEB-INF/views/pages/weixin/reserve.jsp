<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>预约</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap-datetimepicker.min.css">
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav.jsp"%>
	<%@ include file="inc/msg.jsp"%>

	<form id="revform" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/wx/reserve" method="post">
		<div class="container wx_container">
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-user wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">姓名</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<input id="name" name="name" type="text" class="wx_formcontrol" placeholder="中文名" value="${sessionScope.wx_customer.name}" />
				</div>
			</div>
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-phone wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">手机号</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<input id="phone" name="phone" type="text" class="wx_formcontrol" placeholder="手机号" value="${sessionScope.wx_customer.tel1}" />
				</div>
			</div>
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-calendar wx_label"
						aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">预约时间</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<input type="text" class="wx_formcontrol" value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date())  %>"
						id="date" name="date" data-date-format="yyyy-mm-dd" readonly>
				</div>
			</div>
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-map-marker wx_label"
						aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">城市</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<div class="h5_select_long">
						<span class="wx_formspan" id="city"></span>
						<select class="wx_formcontrol" id="city_select" name="city_select">
						</select>
					</div>
				</div>
			</div>
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-home wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">门店</span>
				</div>
				<div class="col-md-8 col-xs-8 col-sm-8 col-lg-8">
					<div class="h5_select_long">
						<span class="wx_formspan" id="store"></span>
						<select class="wx_formcontrol" id="store_select" name="store_select">
						</select>
					</div>
				</div>
			</div>
			<div class="row wx_button_row">
				<button id="submit_btn" type="button" class="btn btn-default btn_submit">提 交</button>
			</div>
		</div>
	</form>

	<script src="${pageContext.request.contextPath}/wx/js/jquery-2.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/wx/js/blaimar.js"></script>
	<script src="${pageContext.request.contextPath}/wx/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/wx/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/wx/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

	<script type="text/javascript">
		$('#date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
		
		//城市门店信息
		var cityStores = <%=request.getAttribute("storelist") %>;
		
		//初始化城市下拉框
		$("#city_select").append("<option value=''></option>");
		
		for(var p in cityStores){
			if(typeof(cityStores[p]) != "function") { 
				$("#city_select").append("<option value='" + p + "'>" + p + "</option>");
			}
		}
		
		//下拉框设置默认值
		$('#city').text($("#city_select").find("option:selected").text());
		
		$('#city_select').change( function(){
			var city = $("#city_select").find("option:selected").text();
			$('#city').text(city);
			$('#store').text('');
			$("#store_select option").remove();
			 for(var i = 0; i < cityStores[city].length; i++) {
				 $("#store_select").append("<option value='" + cityStores[city][i].id + "'>" + cityStores[city][i].storeName + "</option>");
				 if( i == 0 ){
					 $('#store').text(cityStores[city][i].storeName);
				 }
			 }
		});
		$('#store_select').change( function(){ $('#store').text($("#store_select").find("option:selected").text()); } );
		
		$('#submit_btn').click(function(){
			//校验姓名
			if($('#name').val() == ""){
				show_error("请输入姓名");
				$('#name').focus();
				return;
			}
			
			//校验手机号
			var re = re = /^1\d{10}$/;
			
			if($('#phone').val() == ""){
				show_error("请输入手机号");
				$('#phone').focus();
				return;
			} else if($('#phone').val().length != 11){
				show_error("请输入正确的手机号码");
				$('#phone').focus();
				return;
			} else if( re.test($('#phone').val()) != true ){
				show_error("请输入正确的手机号码");
				$('#phone').focus();
				return;
			}
			
			//校验日期
			if($('#date').val() == ""){
				show_error("请输入日期");
				$('#date').focus();
				return;
			}
			
			//校验城市
			if($('#city_select').val() == ""){
				show_error("请选择城市");
				return;
			}
			
			//校验门店
			if($('#store_select').val() == ""){
				show_error("请选择门店");
				return;
			}
			
			hide_error();
			$('#revform').submit();
		});
		
	</script>
</body>
</html>