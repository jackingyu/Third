<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>我的铂玛</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/wx/css/wx.css">
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<h4>
				<b>BLAIMAR</b>
			</h4>
		</div>
	</nav>

	<!-- 我的铂玛 -->
	<div class="container wx_container">
		<div class="row wx_row">
			<div class="col-xs-4">
				<div class="wx_logo"><img src="${pageContext.request.contextPath}/wx/img/blaimar.png" /></div>
			</div>
			<div class="col-xs-8">
				<div class="wx_logo_name">${sessionScope.wx_customer.name}</div>
				<div class="wx_logo_cid">会员号：${sessionScope.wx_customer.id}</div>
			</div>
		</div>
	</div>
	
	<br/>

	<div class="container wx_container">
		<div class="row wx_row">
			<div class="col-md-12">
				<a href="${pageContext.request.contextPath}/wx/orderlist" class="list-group-item wx-list-group-item"><span
					class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
					我的订单 <span class="badge">${requestScope.SOCount}</span></a>
			</div>
		</div>
		<div class="row wx_row">
			<div class="col-md-12">
				<a href="${pageContext.request.contextPath}/wx/reservelist" class="list-group-item wx-list-group-item"><span
					class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
					我的预约<span class="badge">${requestScope.REVCount}</span></a>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/wx/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/wx/js/bootstrap.min.js"></script>
</body>
</html>