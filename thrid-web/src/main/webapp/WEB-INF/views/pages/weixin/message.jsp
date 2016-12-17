<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>铂玛西服</title>
<!-- Bootstrap -->
<template:wxcss />
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav.jsp"%>
	<div id="message_div" class="alert alert-success alert-dismissible"
		style="display: block" role="alert">
		<button type="button" class="close" data-dismiss="alert"></button>
		<span class="glyphicon glyphicon-ok"></span>
		<span id="err_msg">${requestScope.wx_msg}</span>
	</div>

	<div class="container wx_container">
		<div class="row wx_button_row">
			<button id="close_btn" type="button"
				class="btn btn-default btn_submit"
				onclick="WeixinJSBridge.call('closeWindow');">确认并返回聊天窗口</button>
		</div>
	</div>
</body>
</html>