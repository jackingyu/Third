<%@ page import="com.changeman.sales.model.bean.SalesOrder" %>
<%@ page import="com.changeman.sales.model.bean.SizeOrder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>订单详情</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav2.jsp"%>
	
	<% SalesOrder so = (SalesOrder)request.getAttribute("SO"); %>
	
	<div class="container wx_container">
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-shopping-cart wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">订单号</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><%=so.getOrderNo() %></span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-calendar wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">订单时间</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><%=(new SimpleDateFormat("yyyy-MM-dd")).format(so.getCreateDate())%></span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-calendar wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">取件时间</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><%=(new SimpleDateFormat("yyyy-MM-dd")).format(so.getFetchDate())%></span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-shopping-cart wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">订单状态</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><%=so.getStatusText()%></span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-map-marker wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">铂玛门店</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><a href="${pageContext.request.contextPath}/wx/store?id=<%=so.getStore().getId() %>"><%=so.getStore().getStoreName() %></a></span>
				</div>
			</div>
	</div>

</body>
</html>