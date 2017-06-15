<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<template:page>
	<jsp:attribute name="pageScripts">
	  <script src="${lteResourcePath}/js/acc.orderdetails.js"></script>
	  <script src="${lteResourcePath}/js/acc.searchcustomer.js"></script>
	  <script src="${lteResourcePath}/js/acc.modifyorder.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.order.title"></spring:message>
      </h1>
      </div>
      <div class="col-lg-4 col-md-4">
         <a onclick="ACC.modifyorder.save()" class="btn btn-app">
          <i class="fa fa-save"></i>  <spring:message code="lte.save"></spring:message>
       </a>
      </div>
      </div>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <form id="orderForm" action="${contextPath}/order/save" method="post">
       <order:orderBasic/>
       <order:orderCustomer/>
      </form>
       <order:orderPayments/>
       <order:orderEntries/>
    </section>
    
  </jsp:body>
</template:page>