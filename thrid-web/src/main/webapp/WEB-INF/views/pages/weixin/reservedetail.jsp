<%@ page import="com.changeman.sales.model.bean.ReservationModel" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>预约详情</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
<%@ include file="inc/stat.jsp"%>
  </head>
  <body>
	<%@ include file="inc/nav2.jsp"%>
	
	<% ReservationModel rev = (ReservationModel)request.getAttribute("reserveModel"); %>
	
	<div class="container wx_container">
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-shopping-cart wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">预约号</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><%=rev.getId() %></span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-user wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">姓名</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><%=rev.getName() %></span>
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
					<span class="wx_label"><%=rev.getPhone() %></span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-calendar wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">预约时间</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><%=(new SimpleDateFormat("yyyy-MM-dd")).format(rev.getDate())%></span>
				</div>
			</div>

			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-map-marker wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">城市</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><%=rev.getCity() %></span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-home wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label">铂玛门店</span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><a href="${pageContext.request.contextPath}/wx/store?id=<%=rev.getStore().getId() %>"><%=rev.getStore().getStoreName() %></a></span>
				</div>
			</div>
			<div class="row wx_button_row">
				<button id="submit_btn" onclick="window.location.href='${pageContext.request.contextPath}/wx/revchange?id=<%=rev.getId()%>'" type="button" class="btn btn-default btn_submit">修改预约</button>
			</div>
	</div>
  </body>
</html>