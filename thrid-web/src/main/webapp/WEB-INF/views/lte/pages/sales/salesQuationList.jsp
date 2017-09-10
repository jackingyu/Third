<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="customer" tagdir="/WEB-INF/tags/lte/customer"%>


<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.salesquationlist.js"></script>
	   <script src="${lteResourcePath}/js/acc.message.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.salesquationlist.title"></spring:message>
      </h1>
      </div>
      <div class="col-lg-1 col-md-1 pull-right">
      </div>
      <div class="col-lg-1 col-md-1 pull-right">
       <a href="${contextPath}/salesquation/createpage" target="_blank" class="btn btn-app">
           <i class="fa fa-edit"></i>  <spring:message code="lte.create"></spring:message>
       </a>
      </div>
      <div class="col-lg-1 col-md-1 pull-right">
       <a onclick="ACC.salesquationlist.query()" class="btn btn-app">
           <i class="fa fa-search"></i>  <spring:message code="lte.search"></spring:message>
       </a>
      </div>
     
      </div>
    </section>
    <section class="content-header">
     <form id="salesQuationListForm">
       <div class="row">
     
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.salesquation.source"></spring:message></label>
         <common:selection2 id="exhibitions" data="${exhibitions}" multiple="true" name="exhibitions"></common:selection2>
        </div>
        
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.salesquation.cellphone"></spring:message></label>
         <input class="form-control"  name="cellphone" type="text">
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
           <label><spring:message code="lte.salesquation.createdate"></spring:message></label>
          <common:datepicker id="createDate" name="createDate"></common:datepicker>
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
              <table id="salesQuationGrid" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th><spring:message code="lte.salesquation.source"></spring:message></th>
                  <th><spring:message code="lte.salesquation.customername"></spring:message></th>
                  <th><spring:message code="lte.salesquation.cellphone"></spring:message></th>
                  <th><spring:message code="lte.salesquation.amount"></spring:message></th>
                  <th><spring:message code="lte.salesquation.paymentmethod"></spring:message></th>
                  <th><spring:message code="lte.operation"></spring:message></th>
                </tr>
                </thead>
              </table>
            </div>
          </div>
          </div>
        </div>
    </section>
    <common:message></common:message>
  </jsp:body>
</template:page>