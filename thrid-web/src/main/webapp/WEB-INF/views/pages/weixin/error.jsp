<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>出错了</title>
<template:wxcss />
<%@ include file="inc/stat.jsp"%>
</head>
<body>
	<%@ include file="inc/nav.jsp"%>
	<div id="err_div" class="alert alert-danger alert-dismissible"
		style="display: block" role="alert">
		<button type="button" class="close" data-dismiss="alert"></button>
		<span class="glyphicon glyphicon-info-sign"></span>
		<span id="err_msg">出错啦: ${requestScope.wx_error_msg}</span>
	</div>
</body>
</html>