<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>


<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.paymentlist.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.paymentlist.title"></spring:message>
      </h1>
      </div>
      
      <div class="col-lg-4 col-md-4">
      <a onclick="ACC.paymentList.query()" class="btn btn-app">
          <i class="fa fa-search"></i>  <spring:message code="lte.search"></spring:message>
       </a>
      </div>
      </div>
    </section>
    <section class="content-header">
     <form id="paymentListForm">
       <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.paymentlist.store"></spring:message></label>
         	<common:selection2 data="${stores}"  multiple="true" id="storeCodes" name="storeCodes"></common:selection2>
         </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.paymentlist.paymentdate"></spring:message></label>
            <common:datepicker id="paymentDate" name="paymentDate"></common:datepicker>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.paymentlist.paymentmethod"></spring:message></label>
         	<common:selection2 id="paymentMethods" multiple="true" data="${paymentMethods}"  name="paymentMethods"></common:selection2>
         </div>
       </div>
       
       <div class="row">
         <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
           <label><spring:message code="lte.paymentlist.customersource"></spring:message></label>
       <common:selection2 id="sourcePKs" multiple="true" data="${sources}"  name="sources"></common:selection2>
         </div>
         <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
           <label><spring:message code="lte.paymentlist.orderstatus"></spring:message></label>
            <common:selection2 id="orderStatus" multiple="true" data="${orderStatus}"  name="orderStatus"></common:selection2>
         </div>
             <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.paymentlist.salesperson"></spring:message></label>
             <common:selection2 data="${salesPersons}"  multiple="true" id="salesPersons" name="salesPersons"></common:selection2>
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
              <table id="paymentGrid" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th><spring:message code="lte.paymentlist.store"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.customername"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.ordercode"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.salesperson"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.paymentmethod"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.paymentamount"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.paymentdate"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.receiveable"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.paidamount"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.openamount"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.orderdate"></spring:message></th>
                  <th><spring:message code="lte.paymentlist.customersource"></spring:message></th>
                </tr>
                </thead>
              </table>
            </div>
          </div>
          </div>
        </div>
    </section>
    <section class="content">
    	 <div class="row">
		    <div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.paymentlist.creditcard"></spring:message></label>
				<input id="creditCardAmount" readonly="true"  class="form-control required"
					placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.paymentlist.cash"></spring:message></label>
				<input id="cashAmount"  readonly="true"  class="form-control required"
					placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.paymentlist.alipay"></spring:message></label>
				<input id="aliPayAmount"  readonly="true"  class="form-control required"
					placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.paymentlist.totalpayment"></spring:message></label>
				<input id="totalPayment"  readonly="true"  class="form-control required"
					placeholder="" type="text">
			</div>
		</div>
        <div class="row">
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.paymentlist.receiveable"></spring:message></label>
				<input id="receiveable" readonly="true"  class="form-control required"
					placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.paymentlist.paidamount"></spring:message></label>
				<input id="paidAmount"  readonly="true"  class="form-control required"
					placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.paymentlist.openamount"></spring:message></label>
				<input id="openAmount"  readonly="true"  class="form-control required"
					placeholder="" type="text">
			</div>
		</div>
    </section>
  </jsp:body>
</template:page>