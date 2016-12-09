<%@ page import="java.util.*" %>
<%@ page import="com.changeman.masterdata.model.bean.Store" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/wx/css/wx.css">
<title>铂玛西服门店列表</title>
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav.jsp"%>
	
	<% Map<String, ArrayList<Store>> map = (Map<String, ArrayList<Store>>)request.getAttribute("storelist"); 
	   if( map != null ){
		   Set<String> keySet = map.keySet();
		   Iterator<String> iterator = keySet.iterator();
		   while( iterator.hasNext() ){
			   String city = iterator.next();
	%>
		<div class="wx_label_div"><span class="label label-default"><%=city %></span></div>
		<div class="container wx_container">
	<%         ArrayList<Store> list = map.get(city);
			   if( list != null ){
				   for( int i = 0; i < list.size(); i++){
					   Store store = list.get(i);
	%>
		<div class="row wx_row">
			<div class="col-xs-12 storename"><%=store.getStoreName()==null?"":store.getStoreName()%></div>


			<div class="col-xs-12 storeaddress">地址：<%=store.getAddress()==null?"":store.getAddress()%></div>


			<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1"><span class="glyphicon glyphicon-phone-alt wx_label_small" aria-hidden="true"></span></div>
			<div class="col-md-5 col-xs-5 col-sm-5 col-lg-5"><% if( store.getPhone() !=null ){ %><a class="wx_link_small" href="tel:<%= store.getPhone() %>"><%=store.getPhone()==null?"":store.getPhone()%></a><% } %></div>
			<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1"><span class="glyphicon glyphicon-map-marker wx_label_small" aria-hidden="true"></span></div>
			<div class="col-md-5 col-xs-5 col-sm-5 col-lg-5"><a class="wx_link_small" <% if( store.getAddress() != null ){ %> href="${pageContext.request.contextPath}/wx/store?id=<%=store.getId() %>"<% } %>>地图显示</a></div>
		</div>
	<% 				} %>
	</div>
	<% } } } %>
	<br/>
</body>
</html>