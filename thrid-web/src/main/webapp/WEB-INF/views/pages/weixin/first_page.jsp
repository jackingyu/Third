<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${contextPath}/wx/css/wx.css">
<link rel="stylesheet" href="${contextPath}/wx/css/onepage-scroll.css" />
<style>
.page1 { background-color: #0A0A0B;}
.page2 { background-color: #BFB09D;}
.page3 { background-color: #646147;}
.bottom_section{width:100%;bottom:-110%; position:absolute; text-align:center;}
.detail{
font-size: 16px;
line-height: 1.6875;
margin:30px;
}
.white{font-family: "微软雅黑", "Helvetica Neue", Helvetica, Arial, sans-serif;color: #fff;}
.black{font-family: "微软雅黑", "Helvetica Neue", Helvetica, Arial, sans-serif;color: #000;}
.img{ width:100%; }
</style>
<title>欢迎关注铂玛男士礼服</title>
<%@ include file="inc/stat.jsp"%>
</head>
<body>
<div class="main">
	<div class="page page1">
		<img class="img" date-small="${WXImagePath}/welcome1.jpg" date-big="${pageContext.request.contextPath}/wx/img/welcome1.jpg" src="${pageContext.request.contextPath}/wx/img/welcome1.jpg" alt="量身定制">
	</div>
	<div class="page page2">
		<img class="img" date-small="${WXImagePath}/welcome2.jpg" date-big="${pageContext.request.contextPath}/wx/img/welcome2.jpg" src="${pageContext.request.contextPath}/wx/img/welcome2.jpg" alt="品牌历史">
	</div>
	<div class="page page3">
		<img class="img" date-small="${WXImagePath}/welcome3.jpg" date-big="${pageContext.request.contextPath}/wx/img/welcome3.jpg" src="${pageContext.request.contextPath}/wx/img/welcome3.jpg" alt="绅士文化">
	</div>
</div>
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="${WXJsPath}/jquery.onepage-scroll.min.js"></script>
<script>
$(function(){
	$('.main').onepage_scroll({
		sectionContainer: '.page',
		beforeMove: function(index){},
		afterMove: function(index){},
		loop: false
	});
});

document.ontouchmove = function(e){ e.preventDefault(); };
</script>
</body>
</html>