<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>

<template:javaScriptVariables/>

<spring:url value="/_ui/js/acc.uploadfile.js" var="uploadfileJs" />
<script type="text/javascript" src="${uploadfileJs}"></script>

