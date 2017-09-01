<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<%@ taglib prefix=”sec” uri=”http://www.springframework.org/security/tags” %>

<template:page>
	<jsp:attribute name="pageScripts">
	  <script type="text/javascript" src="${lteResourcePath}/js/acc.orderdetails.js"></script>
	  <script type="text/javascript" src="${lteResourcePath}/js/acc.searchcustomer.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <spring:message code="lte.order.title"></spring:message>
        <small>advanced tables</small>
      </h1>
    </section>
    
    <!-- Main content -->
    <section class="content">
       <order:orderBasic/>
       <order:orderCustomer/>
       <order:orderPayments/>
    </section>
    
  </jsp:body>
</template:page>