<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/lte/dashboard"%>

<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/plugins/chartjs/Chart.min.js"></script>
	   <script src="${lteResourcePath}/js/acc.storedashboard.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.storedashboard.title"></spring:message>
      </h1>
      </div>
      
      <div class="col-lg-4 col-md-4">
      <a onclick="ACC.storedashboard.query()" class="btn btn-app">
          <i class="fa fa-search"></i>  <spring:message
							code="lte.search"></spring:message>
       </a>
      </div>
      </div>
    </section>
    <section class="content-header">
     <form id="storeDashboardForm">
       <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.storedashboard.store"></spring:message></label>
         	<common:selection2 data="${stores}" multiple="true"
							id="storeCodes" name="storeCodes"></common:selection2>
         </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message
								code="lte.storedashboard.orderdate"></spring:message></label>
            <common:datepicker id="orderDate" name="orderDate"></common:datepicker>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
           <label><spring:message
								code="lte.storedashboard.customersource"></spring:message></label>
           <common:selection2 id="customerSources" multiple="true"
							data="${sources}" name="customerSources"></common:selection2>
         </div>
       </div>
       </form>
    </section>
    <section class="content">
              <table id="storeDashboardGrid"
				class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th><spring:message
								code="lte.storedashboard.store"></spring:message></th>
				 <th><spring:message
								code="lte.storedashboard.ordernumber"></spring:message></th>
                  <th><spring:message
								code="lte.storedashboard.totalamount"></spring:message></th>
                  <th><spring:message
								code="lte.storedashboard.unpaidamount"></spring:message></th>
                  <th><spring:message
								code="lte.storedashboard.paidamount"></spring:message></th>
                </tr>
                </thead>
              </table>
                <canvas id="orderAmountCanvas" class="col-lg-12 col-md-12"></canvas>
    <canvas id="orderNumberCanvas" class="col-lg-12 col-md-12"></canvas>
    </section>
  
   <dashboard:paymentList></dashboard:paymentList>
  </jsp:body>
</template:page>