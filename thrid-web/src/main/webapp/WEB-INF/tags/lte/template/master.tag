<%@ tag trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>

<html>
 <head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Blaimar</title>
  <link rel="shortcut icon" href="${contextPath}/_ui/images/favicon.png" type="image/x-icon">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <template:css/>
  <jsp:invoke fragment="pageCss"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="<c:if test="${isMobile}">m</c:if>wrapper">
  <c:choose>
     <c:when test="${isMobile}">
       <template:mobileheader /> 
     </c:when>
     <c:otherwise>
       <template:header />  
       <template:sidebar />
     </c:otherwise>
  </c:choose>

  <!-- Content Wrapper. Contains page content -->
  <jsp:doBody></jsp:doBody>
 
  <c:if test="${not isMobile}">
     <template:footer/>

  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
  </c:if>
  <template:confirm/>
</div>
<!-- ./wrapper -->
<template:js/>
<jsp:invoke fragment="pageScripts"/>
</body>
</html>
