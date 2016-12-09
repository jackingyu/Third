<%@ page import="java.util.*" %>
<%@ page import="com.changeman.masterdata.model.bean.Store" %>
<% Store store = (Store)request.getAttribute("store"); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#map {width:100%;height:100%;overflow: hidden;margin:0;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ArGdHSPAPk1pjAy1lWsYA5ae"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
	<title>铂玛西服<%=store.getStoreName() %></title>
	<%@ include file="inc/stat.jsp"%>
</head>
<body>

	<div id="map"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("map");
	map.addControl(new BMap.NavigationControl());
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	
	var sContent = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'><%=store.getStoreName() %></h4>" +
	"<div>电话:<a href='tel:<%=store.getPhone() %>'><%=store.getPhone() %></a></div>" +
	"<div>地址:<%=store.getAddress() %></div>";
	
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint("<%=store.getAddress() %>", function(point){
		if (point) {
			map.centerAndZoom(point, 16);
			var marker = new BMap.Marker(point);
			//创建检索信息窗口对象
			var searchInfoWindow = new BMapLib.SearchInfoWindow(map,sContent,{
				title  : "铂玛西服<%=store.getStoreName() %>",   //标题
				width  : 260,             //宽度
				height : 70,              //高度
				panel  : "panel",         //检索结果面板
				enableAutoPan : true,     //自动平移
				searchTypes   :[
					BMAPLIB_TAB_SEARCH,   //周边检索
					BMAPLIB_TAB_TO_HERE,  //到这里去
					BMAPLIB_TAB_FROM_HERE //从这里出发
				]
			});
			map.addOverlay(marker);
			//searchInfoWindow.open(marker);
			
			marker.addEventListener("click", function(){    
				searchInfoWindow.open(marker);
				});
		}
	}, "上海市");
</script>