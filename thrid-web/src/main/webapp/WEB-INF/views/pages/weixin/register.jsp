<%@ page import="com.xiangbei.weixin.util.WXConstant" %>
<!DOCTYPE html>
<html lang="zh-cn">
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>会员注册</title>
    
<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
<%@ include file="inc/stat.jsp"%>
</head>

  <body>
	<%@ include file="inc/nav.jsp"%>
	<%@ include file="inc/msg.jsp"%>
	
	<form id="regform" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/wx/register" method="post">
	
		<div class="container wx_container" style="margin-top:20px">
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-user wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">姓名</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<input id="name" name="name" type="text" class="wx_formcontrol" placeholder="请输入姓名" value="${requestScope.name}" />
				</div>
			</div>
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-phone wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">手机号</span>
				</div>
				<div class="col-md-8 col-xs-8 col-sm-8 col-lg-8">
					<input id="phone" name="phone" type="text" class="wx_formcontrol" placeholder="请输入手机号" value="${requestScope.phone}" />
				</div>
			</div>
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-th wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">验证码</span>
				</div>
				<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4">
					<input id="vcode" name="vcode" type="text" class="wx_formcontrol" placeholder="请输入验证码">
				</div>
				<div class="col-md-4 col-xs-4 col-sm-4 col-lg-4" style="text-align:right">
					<button id="verf_btn" type="button" class="btn btn-default wx_button">获取验证码</button>
				</div>
			</div>
			<div class="row wx_button_row">
				<button id="submit_btn" type="button" class="btn btn-default btn_submit">加入铂玛</button>
			</div>
		</div>
		<div class="panel-heading">
			<p>温馨提示</p>
			<p>1. 根据省份，城市，地区不同，一般会在5秒-60秒内收到验证码。如未收到，您可以在60秒后重新获取。</p>
		</div>
	</form>

	<script src="${pageContext.request.contextPath}/wx/js/jquery-2.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/wx/js/blaimar.js"></script>
	<script>
	
	//判断是否需要显示错误消息
	<% 
		String error_msg = (String)request.getAttribute( WXConstant.WX_ERROR_MSG );
		if( error_msg != null && !error_msg.equals("") ){
	%>
			show_error("<%=error_msg %>");
	<%  } %>

	$('#verf_btn').click(function(){
		
		var re = re = /^1\d{10}$/;
		
		if($('#name').val() == ""){
			show_error("请输入姓名");
			$('#name').focus();
			return;
		}
		
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
		
		$('#verf_btn').addClass("disabled");
		hide_error();
		
		//发送短信验证码
		$.ajax({
        	type: "get",//使用get方法访问后台
        	dataType: "json",//返回json格式的数据
        	url: "${pageContext.request.contextPath}/wx/sms",
        	data: "to=" + $('#phone').val(),	//要发送的数据
        	complete :function(){},
        	success: function(msg){	//msg为返回的数据，在这里做数据绑定
            	var result = msg.success;
        		if( result == true ) {

        			$('#verf_btn').addClass("disabled");
        			remainTime = 60;
        			$('#verf_btn').text('已发送(' + remainTime + ')');
        			interval1 = self.setInterval('countdown()', 1000);
        			
        		} else {
        			enableSMSButton( );
        			show_error(msg.message);
        		}
        	}
		});
	});
	
	$('#submit_btn').click(function(){
		
		var re = re = /^1\d{10}$/;
		
		if($('#name').val() == ""){
			show_error("请输入姓名");
			$('#name').focus();
			return;
		}
		
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
		
		if($('#vcode').val() == ""){
			show_error("请输入验证码");
			$('#vcode').focus();
			return;
		}
		
		hide_error();
		$('#regform').submit();
	});
</script>
  </body>
</html>