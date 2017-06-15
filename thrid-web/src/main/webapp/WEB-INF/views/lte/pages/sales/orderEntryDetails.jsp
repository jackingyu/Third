<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<template:page>
	<jsp:attribute name="pageScripts">
		<script src="${lteResourcePath}/js/acc.sizeorder.js"></script>
		<script src="${lteResourcePath}/js/acc.createsizeorder.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h2>
        <spring:message code="lte.orderentry.title"></spring:message>
      </h2>
      <small class="help-block" style="color:#00a65a">${message}</small>
      </div>
      <div class="col-lg-1 col-md-1">
         <a onclick="ACC.sizeorder.saveSizeOrder()" class="btn btn-app">
          <i class="fa fa-save"></i>  <spring:message code="lte.save"></spring:message>
       </a>
      </div>
   </div>
    </section>
    
    <!-- Main content -->
    <section class="content">
       <order:sizeOrder/>
    </section>
    
  </jsp:body>
</template:page>