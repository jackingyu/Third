<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html lang="zh-cmn-Hans">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title><spring:message code="wx.orderlist.title"/></title>
<template:wxcss/>
<template:javaScriptVariables/>
</head>

<body>
	<%@ include file="inc/nav.jsp"%>
	
	<c:if test="${empty orderList}">
		<div class="container wx_container">
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
				</div>
				<div class="col-md-11 col-xs-11 col-sm-11 col-lg-11">
					<span class="wx_label"><spring:message code="wx.orderlist.empty"/></span>
				</div>
			</div>
		</div>
	</c:if>
	
	<div class="list-group">
	 <c:forEach var="order" items="${orderList}">
		<a href="${contextPath}/wx/getOrderDetail?orderCode=${order.orderCode}" class="list-group-item">
			<div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="30%" class="table_td"><spring:message code="wx.orderlist.ordercode"/></td>
						<td width="70%" class="table_td">${order.orderCode}</td>
					</tr>
					<tr>
						<td class="table_td"><spring:message code="wx.orderlist.orderdate"/></td>
						<td class="table_td">${order.orderDate}</td>
					</tr>
					<tr>
						<td class="table_td"><spring:message code="wx.orderlist.orderstatus"/></td>
						<td class="table_td">${order.status}</td>
					</tr>
				</table>
				<div style="display: inline;position: absolute;right: 5px;top: 50%; margin-top: -10px;"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></div>
			</div>
		</a>
	</c:forEach>
	</div>
</body>
</html>