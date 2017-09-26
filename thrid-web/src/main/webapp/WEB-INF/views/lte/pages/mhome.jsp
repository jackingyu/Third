<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<template:page>
	<jsp:attribute name="pageScripts">
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <spring:message code="lte.firstpage"></spring:message>
      </h1>
    </section>
    
    <!-- Main content -->
    <section class="content">
       <c:forEach var="menu" items="${menus}">
            <template:mobilemenu menu="${menu}" ></template:mobilemenu>
	   </c:forEach>
    </section>
  </jsp:body>
</template:page>