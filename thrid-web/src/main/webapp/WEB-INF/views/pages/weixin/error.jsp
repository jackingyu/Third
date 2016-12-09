<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>出错了</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav.jsp"%>
	<div id="err_div" class="alert alert-danger alert-dismissible"
		style="display: block" role="alert">
		<button type="button" class="close" data-dismiss="alert"></button>
		<span class="glyphicon glyphicon-info-sign"></span>
		<span id="err_msg">出错啦: ${requestScope.wx_error_msg}</span>
	</div>
</body>
</html>