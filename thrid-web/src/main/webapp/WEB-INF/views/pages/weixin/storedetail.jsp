<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ page import="java.util.*" %>
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
	<title><spring:message code="wx.storedetail.title"/>${store.name }</title>
	<%@ include file="inc/stat.jsp"%>
</head>
<body>

	<div id="map"></div>
	<div id="addressData" display="none">
	   <input id="storeName" value="${store.name}"></input>
	   <input id="storeAddress" value="<common:address1 address="${store.address}"/>"></input>
	   <input id="storeTel1" value="${store.address.tel1}"></input>
	</div>
	<script src="${WXJsPath}/jquery-2.1.1.min.js"></script>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("map");
	map.addControl(new BMap.NavigationControl());
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	
	var sContent = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+$("#storeName").val()+"</h4>" +
	"<div>电话:<a href='tel:"+$("#storeTel1").val()+"'>"+$("#storeTel1").val()+"</a></div>" +
	"<div>地址:"+$("#storeAddress").val()+"</div>";
	
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint($("#storeAddress").val(), function(point){
		if (point) {
			map.centerAndZoom(point, 16);
			var marker = new BMap.Marker(point);
			//创建检索信息窗口对象
			var searchInfoWindow = new BMapLib.SearchInfoWindow(map,sContent,{
				title  : "铂玛西服${store.name}",   //标题
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