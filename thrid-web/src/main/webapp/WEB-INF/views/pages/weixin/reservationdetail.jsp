<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ page import="java.text.SimpleDateFormat" %>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title><spring:message code="wx.reservationdetail.title"/></title>

<template:wxcss/>
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
					<span class="wx_label"><spring:message code="wx.reservationdetail.reservationpk"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${reservation.pk}</span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-user wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.reservationdetail.name"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${reservation.name}</span>
				</div>
			</div>

			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-phone wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.reservationdetail.cellphone"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${reservation.cellphone}</span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-calendar wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.reservationdetail.reservationdate"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${reservation.reservationDate}</span>
				</div>
			</div>

			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-map-marker wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.reservationdetail.city"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label">${reservation.store.address.city.name}</span>
				</div>
			</div>
			
			<div class="row wx_row">
				<div class="col-md-1 col-xs-1 col-sm-1 col-lg-1">
					<span class="glyphicon glyphicon-home wx_label" aria-hidden="true"></span>
				</div>
				<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
					<span class="wx_label"><spring:message code="wx.reservationdetail.store"/></span>
				</div>
				<div class="col-md-8  col-xs-8 col-sm-8 col-lg-8">
					<span class="wx_label"><a href="${contextPath}/wx/getStoreDetail?code=${reservation.store.code}">${reservation.store.name}</a></span>
				</div>
			</div>
			<div class="row wx_button_row">
				<button id="submit_btn" onclick="window.location.href='${contextPath}/wx/getReservationPage?reservationPK=${reservation.pk}'" type="button" class="btn btn-default btn_submit">
				<spring:message code="wx.reservationdetail.chgreservation"/></button>
			</div>
	</div>
  </body>
</html>