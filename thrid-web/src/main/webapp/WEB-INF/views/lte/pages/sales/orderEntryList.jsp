<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>


<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.orderentrylist.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.orderentrylist.title"></spring:message>
      </h1>
      </div>
      <div class="col-lg-2 col-md-2">
      <a onclick="ACC.orderEntryList.query()" class="btn btn-app">
          <i class="fa fa-search"></i>  <spring:message code="lte.search"></spring:message>
       </a>
      </div>
      <div class="col-lg-2 col-md-2">
      <a onclick="ACC.orderEntryList.exportExcel()" class="btn btn-app">
          <i class="fa fa-download"></i>  <spring:message code="lte.export"></spring:message>
       </a>
      </div>
      </div>
    </section>
    <section class="content-header">
     <form id="orderEntryListForm" action="${contextPath}/orderentry/export'">
       <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderentrylist.externailid"></spring:message></label>
         <input class="form-control" placeholder="" name="externalId" type="text">
         </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderentrylist.deliverydate"></spring:message></label>
         <common:datepicker id="deliveryDate" name="deliveryDate"></common:datepicker>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderentrylist.store"></spring:message></label>
         <common:selection2 data="${stores}"  multiple="true" id="storeCodes" name="storeCodes"></common:selection2>         
         </div>
       </div>
       <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderentrylist.trydate"></spring:message></label>
           <common:datepicker id="tryDate" name="tryDate"></common:datepicker>
         </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderentrylist.status"></spring:message></label>
         <common:selection data="${orderStatus}" name="orderEntryStatus"></common:selection>
         </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderentrylist.customername"></spring:message></label>
           <input class="form-control" placeholder="" name="customerName" type="text">
         </div>
       </div>
       <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderentrylist.actualtrydate"></spring:message></label>
           <common:datepicker id="actualTryDate" name="actualTryDate"></common:datepicker>
         </div>
       </div>
       </form>
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><spring:message code="lte.report.results"></spring:message></h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="orderEntryGrid" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th><spring:message code="lte.orderentrylist.itemcategory"></spring:message></th>
                  <th><spring:message code="lte.orderentrylist.externailid"></spring:message></th>
                  <th><spring:message code="lte.orderentrylist.deliverydate"></spring:message></th>
                  <th><spring:message code="lte.orderentrylist.customername"></spring:message></th>
                  <th><spring:message code="lte.orderentrylist.store"></spring:message></th>
                  <th><spring:message code="lte.orderentrylist.status"></spring:message></th>
                  <th><spring:message code="lte.orderentrylist.trydate"></spring:message></th>
                  <th><spring:message code="lte.orderentrylist.actualtrydate"></spring:message></th>
                </tr>
                </thead>
              </table>
            </div>
          </div>
          </div>
        </div>
    </section>
  </jsp:body>
</template:page>