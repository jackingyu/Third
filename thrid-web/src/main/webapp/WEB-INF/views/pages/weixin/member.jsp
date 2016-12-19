<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title><spring:message code="wx.member.title"></spring:message></title>

<!-- Bootstrap -->
<template:wxcss />
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<h4>
				<b>BLAIMAR</b>
			</h4>
		</div>
	</nav>

	<!-- 我的铂玛 -->
	<div class="container wx_container">
		<div class="row wx_row">
			<div class="col-xs-4">
				<div class="wx_logo"><img src="${WXImagePath}/blaimar.png" /></div>
			</div>
			<div class="col-xs-8">
				<div class="wx_logo_name">${customer.name}</div>
				<div class="wx_logo_cid">${customer.cellphone}</div>
			</div>
		</div>
	</div>
	
	<br/>

	<div class="container wx_container">
		<div class="row wx_row">
			<div class="col-md-12">
				<a href="${contextPath}/wx/getOrderList" class="list-group-item wx-list-group-item"><span
					class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
					<spring:message code="wx.member.mysalesorder"/> <span class="badge">${numberOfOrder}</span></a>
			</div>
		</div>
		<div class="row wx_row">
			<div class="col-md-12">
				<a href="${contextPath}/wx/getReservationList " class="list-group-item wx-list-group-item"><span
					class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
					<spring:message code="wx.member.myreservation"/><span class="badge">${numberOfReservation}</span></a>
			</div>
		</div>
	</div>
    <template:wxjs/>
</body>
</html>