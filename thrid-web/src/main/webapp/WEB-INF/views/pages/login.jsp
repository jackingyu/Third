<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<html>
<head>
    <template:css/>
</head>
<body>
    <div class="easyui-layout" style="width:300px; height:300px;">
    <div region="north">顶部</div>
    <div region="west" style="width:100px">左侧</div>
    <div region="center">主工作区</div>
    <div region="east" style="width:100px">右侧</div>
    <div region="south">底部</div>
</div>
<!-- 	<div> -->
<!-- 		<h1>spring login deploy test</h1> -->
<%-- 		<form action="<c:url value='/j_spring_security_check' />" --%>
<!-- 			method="post" class="form-signin"> -->
<!-- 			<input type="text" name="username"></input> <input type="password" -->
<!-- 				name="password"></input> <input type="submit" value="login" /> -->
<!-- 		</form> -->
<!-- 	</div> -->
	<template:js/>
</body>
</html>