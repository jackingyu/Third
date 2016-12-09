<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>铂玛西服</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav.jsp"%>
	<div id="message_div" class="alert alert-success alert-dismissible"
		style="display: block" role="alert">
		<button type="button" class="close" data-dismiss="alert"></button>
		<span class="glyphicon glyphicon-ok"></span>
		<span id="err_msg">${requestScope.wx_msg}</span>
	</div>

	<div class="container wx_container">
		<div class="row wx_button_row">
			<button id="close_btn" type="button"
				class="btn btn-default btn_submit"
				onclick="WeixinJSBridge.call('closeWindow');">确认并返回聊天窗口</button>
		</div>
	</div>
</body>
</html>