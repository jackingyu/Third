<%@ page import="java.util.*" %>
<%@ page import="com.changeman.sales.model.bean.SalesOrder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>铂玛订单列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
</head>

<body>
	<%@ include file="inc/nav.jsp"%>
	
	<% List<SalesOrder> soList = (List<SalesOrder>)request.getAttribute("SOList"); %>
	
	<% if( soList == null || soList.size() == 0){ %>
		<div class="container wx_container">
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
				</div>
				<div class="col-md-11 col-xs-11 col-sm-11 col-lg-11">
					<span class="wx_label">您目前还没有订单</span>
				</div>
			</div>
		</div>
	<% } %>
	
	<div class="list-group">
	<% if( soList != null ){
		 for( int i = 0; i < soList.size(); i++){
			 SalesOrder so = soList.get(i);
	%>
		<a href="${pageContext.request.contextPath}/wx/orderdetail?id=<%=so.getId()%>" class="list-group-item">
			<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="30%" class="table_td">订单号：</td>
						<td width="70%" class="table_td"><%=so.getOrderNo()%></td>
					</tr>
					<tr>
						<td class="table_td">订单时间：</td>
						<td class="table_td"><%=(new SimpleDateFormat("yyyy-MM-dd")).format(so.getCreateDate())%></td>
					</tr>
					<tr>
						<td class="table_td">订单状态：</td>
						<td class="table_td"><%=so.getStatusText()%></td>
					</tr>
				</table>
				<div style="display: inline;position: absolute;right: 5px;top: 50%; margin-top: -10px;"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></div>
			</div>
		</a>
	<% } } %>
	</div>
</body>
</html>