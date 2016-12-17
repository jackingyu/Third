<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<template:wxcss />
<title><spring:message code="wx.storelist.title" /></title>
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav.jsp"%>

	<c:forEach var="storemap" items="${stores}">
		<div class="wx_label_div">
			<span class="label label-default">${storemap.key}</span>
		</div>
		<div class="container wx_container">
			<c:forEach var="store" items="${storemap.value}">
				<div class="row wx_row">
					<div class="col-xs-12 storename">${store.name}</div>


					<div class="col-xs-12 storeaddress"><spring:message code="wx.storelist.address"/>
					  <common:address1 address="${store.address}">
					  </common:address1>
					</div>


					<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
						<span class="glyphicon glyphicon-phone-alt wx_label_small" aria-hidden="true"></span>
					</div>
					<div class="col-md-5 col-xs-5 col-sm-5 col-lg-5">
						<c:if test="${!empty store.address.tel1}">
							<a class="wx_link_small" href="tel:${store.address.tel1}">
							    ${store.address.tel1}
							</a>
						</c:if>
					</div>
					<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
						<span class="glyphicon glyphicon-map-marker wx_label_small" aria-hidden="true"></span>
					</div>
					<div class="col-md-5 col-xs-5 col-sm-5 col-lg-5">
					   <c:if test="${!empty store.address}">
						<a class="wx_link_small" 
							href="${contextPath}/wx/getStoreDetail?code=${store.code}" ><spring:message code="wx.storelist.showimage"/></a>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
	<br />
</body>
</html>