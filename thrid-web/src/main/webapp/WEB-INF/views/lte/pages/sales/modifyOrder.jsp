<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<template:page>
	<jsp:attribute name="pageScripts">
	  <script src="${lteResourcePath}/js/acc.orderdetails.js"></script>
	  <script src="${lteResourcePath}/js/acc.searchcustomer.js"></script>
	  <script src="${lteResourcePath}/js/acc.modifyorder.js"></script>
	  <script src="${lteResourcePath}/js/acc.message.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.order.title"></spring:message>
        <small>${statusText}</small>
      </h1>
      </div>
      <div class="col-lg-1  col-md-1 pull-right">
      </div>
      <sec:authorize access="hasAnyRole('ROLE_SALES','ROLE_ADMIN')">
      <c:if test="${editable}">
      <div class="col-lg-1  col-md-1 pull-right">
         <a onclick="ACC.modifyorder.save()" class="btn btn-app">
         <i class="fa fa-save"></i>  <spring:message code="lte.save"></spring:message>
       </a>
      </div>
      </c:if>
      </sec:authorize>
      <sec:authorize access="hasAnyRole('ROLE_SALES','ROLE_ADMIN')">
       <c:if test="${orderData.status == 0 }"><!-- only new order -->
       <div class="col-lg-1  col-md-1 pull-right">
         <a onclick="ACC.modifyorder.storeApprove()" class="btn btn-app">
         <i class="fa fa-thumbs-o-up"></i>  <spring:message code="lte.storeapprove"></spring:message>
       </a>
      </div>
       </c:if>
      </sec:authorize>
      <sec:authorize access="hasAnyRole('ROLE_FINICIAL','ROLE_ADMIN')">
       <c:if test="${orderData.status == 10 }"><!-- only store approve order-->
      <div class="col-lg-1  col-md-1 pull-right">
         <a onclick="ACC.modifyorder.fiApprove()" class="btn btn-app">
         <i class="fa fa-thumbs-o-up"></i>  <spring:message code="lte.fiapprove"></spring:message>
       </a>
      </div>
      </c:if>
      </sec:authorize>
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
     <common:message></common:message>
  </jsp:body>
</template:page>