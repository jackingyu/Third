<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.third.controller.weixin.WXConstant" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title><spring:message code="wx.orderdetail.title"/></title>

<template:wxcss/>
<template:javaScriptVariables/>
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav2.jsp"%>
	
	<div class="container wx_container">
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-shopping-cart wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.orderdetail.ordercode"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${order.orderCode}</span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-calendar wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.orderdetail.orderdate"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${order.orderDate}</span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-calendar wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.orderdetail.deliverydate"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${order.deliveryDate}</span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-shopping-cart wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.orderdetail.orderstatus"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${order.statusText}</span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-map-marker wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.orderdetail.store"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><a href="${contextPath}/wx/getStoreDetail?code=${order.store.code}">${order.store.name}</a></span>
				</div>
			</div>
	</div>

</body>
</html>