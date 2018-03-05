<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<template:page>
	<jsp:attribute name="pageScripts">
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
  
    </section>
    
    <!-- Main content -->
    <section class="content">
     
        <h2 class="headline text-red">Exception</h2>

        <div class="error-content">
          <h3><i class="fa fa-warning text-red"></i><spring:message code="lte.exception"></spring:message></h3>
          <p>
          </p>
    </section>
    
  </jsp:body>
</template:page>