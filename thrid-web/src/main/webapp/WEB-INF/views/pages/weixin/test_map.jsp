<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>会员绑定</title>

<style type="text/css">
body,html,#map {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2&ak=1K5r4oHl76NyDaEfYZfaoYLj"></script>

</head>
<body onload="init()" class="fullscreen">

	<div id="map" class="fullscreen"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	//var map = new BMap.Map("map");    // 创建Map实例
	//map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
 function getLocation()
 { 
  if(navigator.geolocation){
   navigator.geolocation.getCurrentPosition(showMap, handleError, {enableHighAccuracy:true, maximumAge:1000});
  }else{
   alert("您的浏览器不支持使用HTML 5来获取地理位置服务");
  }
 }
 
 function showMap(value)
 {
  var longitude = value.coords.longitude;
  var latitude = value.coords.latitude;
  var map = new BMap.Map("map");
  var point = new BMap.Point(longitude, latitude);    // 创建点坐标
  map.centerAndZoom(point, 15);
  var marker = new BMap.Marker(new BMap.Point(longitude, latitude));  // 创建标注
  map.addOverlay(marker);              // 将标注添加到地图中
 }
 
 function handleError(value)
 {
  switch(value.code){
   case 1:
    alert("位置服务被拒绝");
    break;
   case 2:
    alert("暂时获取不到位置信息");
    break;
   case 3:
    alert("获取信息超时");
    break;
   case 4:
    alert("未知错误");
    break;
  }
 }
 
 function init()
 {
  getLocation();
 }
</script>