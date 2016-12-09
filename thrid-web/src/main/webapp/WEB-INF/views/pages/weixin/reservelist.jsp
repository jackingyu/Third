<%@ page import="java.util.*" %>
<%@ page import="com.changeman.sales.model.bean.ReservationModel" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>预约列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
<%@ include file="inc/stat.jsp"%>
  </head>
  <body>
	<%@ include file="inc/nav.jsp"%>

	<% 	
		List<ReservationModel> reserveList = null;
		Object list = request.getAttribute("reserveList");
		
		if( list != null ){
			reserveList = (List<ReservationModel>)list; 
		}
	%>
	
	<% if( reserveList == null || reserveList.size() == 0){ %>
		<div class="container wx_container">
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
				</div>
				<div class="col-md-11 col-xs-11 col-sm-11 col-lg-11">
					<span class="wx_label">您目前还没有预约</span>
				</div>
			</div>
		</div>
	<% } %>
	
	<div class="list-group">
	<% if( reserveList != null ){
		 for( int i = 0; i < reserveList.size(); i++){
			 ReservationModel reservation = reserveList.get(i);
	%>
		<a href="${pageContext.request.contextPath}/wx/reservedetail?id=<%=reservation.getId()%>" class="list-group-item">
			<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="30%" class="table_td">预约号：</td>
						<td width="70%" class="table_td"><%=reservation.getId()%></td>
					</tr>
					<tr>
						<td class="table_td">预约时间：</td>
						<td class="table_td"><%=(new SimpleDateFormat("yyyy-MM-dd")).format(reservation.getDate())%></td>
					</tr>
					<tr>
						<td class="table_td">预约门店：</td>
						<td class="table_td"><%=reservation.getStore().getStoreName() %></td>
					</tr>
				</table>
				<div style="display: inline;position: absolute;right: 5px;top: 50%; margin-top: -10px;"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></div>
			</div>
		</a>
	<% } } %>
	</div>
  </body>
</html>