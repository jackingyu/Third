<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>


<template:javaScriptVariables/>

<spring:url value="/_ui/js/jquery.min.js" var="jqueryJs" />
<script type="text/javascript" src="${jqueryJs}"></script>

<spring:url value="/_ui/js/jquery.easyui.min.js" var="easyuiJs" />
<script type="text/javascript" src="${easyuiJs}"></script>

<spring:url value="/_ui/js/main.js" var="mainJs" />
<script type="text/javascript" src="${mainJs}"></script>

<spring:url value="/_ui/js/juicer-min.js" var="juicerJs" />

<script type="text/javascript" src="${juicerJs}"></script>
<spring:url value="/_ui/js/easyui-lang-zh_CN.js" var="easyuiLocaleJs" />
<script type="text/javascript" src="${easyuiLocaleJs}"></script>

<spring:url value="/_ui/js/acc.usergroup.js" var="userGroupJs" />
<script type="text/javascript" src="${userGroupJs}"></script>

<spring:url value="/_ui/js/acc.usergrouplist.js" var="userGroupListJs" />
<script type="text/javascript" src="${userGroupListJs}"></script>
