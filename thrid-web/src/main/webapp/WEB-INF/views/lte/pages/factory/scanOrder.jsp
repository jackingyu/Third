<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>

<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.scanorder.js"></script>
	   <script src="${lteResourcePath}/js/acc.message.js"></script>
	   <script src="${lteResourcePath}/plugins/jsbarcode/JsBarcode.all.min.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
      <h1>
        ${title}
      </h1>
      </div>
      <div class="col-lg-3 col-md-3 pull-left" >
         <canvas id="deliveryCanvasOK"></canvas>
      </div>
      <div class="col-lg-2 col-md-2 pull-left" >
      </div>
      <div class="col-lg-3 col-md-3 pull-left" >
         <canvas id="deliveryCanvasR"></canvas>
      </div>
      </div>
    </section>
    <section class="content-header">
     <form id="orderEntryForm">
       <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.scanorder.pk"></spring:message></label>
         <input class="form-control" placeholder="" id="entryPK" name="entryPK" type="text">
         </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.scanorder.externalid"></spring:message></label>
         <input class="form-control" placeholder="" id="externalId" name="externalId" type="text">
         <input type="hidden" name="currentStatus" value="${currentStatus}">
         </div>
       </div>
       </form>
       <input id="successmessage" type="hidden" value="${successmessage}"></input>
       <input id="status" type="hidden" value="${status}"></input>
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
                  <th><spring:message code="lte.scanorder.externalid"></spring:message></th>
                  <th><spring:message code="lte.scanorder.itemcategory"></spring:message></th>
                  <th><spring:message code="lte.scanorder.producttitle"></spring:message></th>
                  <th><spring:message code="lte.scanorder.customername"></spring:message></th>
                  <th><spring:message code="lte.scanorder.pk"></spring:message></th>
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