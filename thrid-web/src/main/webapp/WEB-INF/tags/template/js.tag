<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>


<template:javaScriptVariables/>



<spring:url value="/_ui/js/jquery.easyui.min.js" var="easyuiJs" />
<script type="text/javascript" src="${easyuiJs}"></script>

<spring:url value="/_ui/js/jquery.scrollTo.min.js" var="scrollToJs" />
<script type="text/javascript" src="${scrollToJs}"></script>

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

<spring:url value="/_ui/js/acc.user.js" var="userJs" />
<script type="text/javascript" src="${userJs}"></script>

<spring:url value="/_ui/js/acc.userlist.js" var="userListJs" />
<script type="text/javascript" src="${userListJs}"></script>

<spring:url value="/_ui/js/acc.usergroupselector.js" var="userGroupSelectorJs" />
<script type="text/javascript" src="${userGroupSelectorJs}"></script>

<spring:url value="/_ui/js/acc.storeselector.js" var="storeSelectorJs" />
<script type="text/javascript" src="${storeSelectorJs}"></script>

<spring:url value="/_ui/js/acc.customer.js" var="customerJs" />
<script type="text/javascript" src="${customerJs}"></script>

<spring:url value="/_ui/js/acc.customerlist.js" var="customerListJs" />
<script type="text/javascript" src="${customerListJs}"></script>

<spring:url value="/_ui/js/acc.order.js" var="orderJs" />
<script type="text/javascript" src="${orderJs}"></script>

<spring:url value="/_ui/js/acc.orderlist.js" var="orderListJs" />
<script type="text/javascript" src="${orderListJs}"></script>