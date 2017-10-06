<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="customer" tagdir="/WEB-INF/tags/lte/customer"%>


<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.customerlist.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.customerlist.title"></spring:message>
      </h1>
      </div>
      <div class="col-lg-1 col-md-1">
      <a onclick="ACC.customerlist.query()" class="btn btn-app">
          <i class="fa fa-search"></i>  <spring:message code="lte.search"></spring:message>
       </a>
      </div>
      <div class="col-lg-1 col-md-1">
      <a onclick="ACC.customerlist.create()" class="btn btn-app">
          <i class="fa fa-edit"></i>  <spring:message code="lte.create"></spring:message>
       </a>
      </div>
    </section>
    <section class="content-header">
     <form id="customerListForm">
       <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.customerlist.cellphone"></spring:message></label>
         <input class="form-control" placeholder="" name="cellphone" type="text">
         </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.customerlist.customername"></spring:message></label>
         <input class="form-control" placeholder="" name="customerName" type="text">
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
           <label><spring:message code="lte.customerlist.weddingdate"></spring:message></label>
          <common:datepicker id="weddingDate" name="weddingDate"></common:datepicker>
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
              <table id="customerGrid" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th><spring:message code="lte.customerlist.customername"></spring:message></th>
                  <th><spring:message code="lte.customerlist.cellphone"></spring:message></th>
                  <th><spring:message code="lte.customerlist.weddingdate"></spring:message></th>
                  <th><spring:message code="lte.customerlist.source"></spring:message></th>
                  <th><spring:message code="lte.operation"></spring:message></th>
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