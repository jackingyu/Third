<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>


<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.orderlist.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.orderlist.title"></spring:message>
      </h1>
      </div>
      <div class="col-lg-4 col-md-4">
      <a onclick="ACC.orderList.query()" class="btn btn-app">
          <i class="fa fa-search"></i>  <spring:message code="lte.search"></spring:message>
       </a>
      </div>
    </section>
    <section class="content-header">
     <form id="orderListForm">
       <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderlist.ordercode"></spring:message></label>
         <input class="form-control" placeholder="" name="orderCode" type="text">
         </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderlist.customername"></spring:message></label>
         <input class="form-control" placeholder="" name="customerName" type="text">
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderlist.cellphone"></spring:message></label>
         <input class="form-control" placeholder="" name="cellphone" type="text">
         </div>
       </div>
       <div class="row">
         <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
           <label><spring:message code="lte.orderlist.orderdate"></spring:message></label>
          <common:datepicker id="orderDate" name="orderDate"></common:datepicker>
         </div>
         <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
           <label><spring:message code="lte.orderlist.deliverydate"></spring:message></label>
          <common:datepicker id="deliveryDate" name="deliveryDate"></common:datepicker>
         </div>
             <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderlist.store"></spring:message></label>
         	<common:selection2 data="${stores}"  multiple="true" id="storeCodes" name="storeCodes"></common:selection2>
         </div>
       </div>
       <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.orderlist.orderstatus"></spring:message></label>
         <common:selection data="${orderStatus}" name="orderStatus"></common:selection>
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
              <table id="orderGrid" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th><spring:message code="lte.orderlist.ordercode"></spring:message></th>
                  <th><spring:message code="lte.orderlist.customername"></spring:message></th>
                  <th><spring:message code="lte.orderlist.cellphone"></spring:message></th>
                  <th><spring:message code="lte.orderlist.store"></spring:message></th>
                </tr>
                </thead>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      <!-- /.row -->
    </section>
    <!-- /.content -->

  <!-- /.content-wrapper -->
  </jsp:body>
</template:page>