<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<template:page>
	<jsp:attribute name="pageScripts">
	  <script src="${lteResourcePath}/js/acc.salesquation.js"></script>
	  <script src="${lteResourcePath}/js/acc.message.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.salesquation.title"></spring:message>
      </h1>
      </div>
      
      <div class="col-lg-1  col-md-1 pull-right">
      </div>
      
      <div class="col-lg-1  col-md-1 pull-right">
         <a onclick="ACC.salesquation.save()" class="btn btn-app">
         <i class="fa fa-save"></i>  <spring:message code="lte.save"></spring:message>
       </a>
      </div>
      <div class="col-lg-1  col-md-1 pull-right">
         <a onclick="ACC.salesquation.save1()" class="btn btn-app">
         <i class="fa fa-save"></i>  <spring:message code="lte.save1"></spring:message>
       </a>
      </div>
      <div class="col-lg-1  col-md-1 pull-right">
         <a onclick="ACC.salesquation.convert()" class="btn btn-app">
         <i class="fa fa-edit"></i>  <spring:message code="lte.salesquation.convert"></spring:message>
       </a>
      </div>
      
      </div>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <form id="salesQuationForm" action="${contextPath}/salesquation/save" method="post">
       <div class="box">
	<div class="box-header">
		<h3 class="box-title">
			<spring:message code="lte.order.basic.title"></spring:message>
		</h3>
	</div>
	<div class="box-body">
		<div class="row">
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.source"></spring:message></label>
				 <common:selection2 value="${sq.source.pk}" id="exhibitions" data="${exhibitions}" multiple="false" name="sourcePK"></common:selection2>
			     <input type="hidden" name="pk" value="${sq.pk}">
			     <input type="hidden" id="continuecreate" name="continuecreate" value="true">
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.trydate"></spring:message></label>
				 <common:datepicker value="${sq.tryDate}" name="tryDate" id="trydate"/>
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.photodate"></spring:message></label>
				 <common:datepicker value="${sq.photoDate}" name="photoDate" id="photodate"/>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.deliverydate"></spring:message></label>
		        <common:datepicker value="${sq.deliveryDate}" name="deliveryDate" id="deliverydate"/>
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.cosalesperson"></spring:message></label>
		         <input name="coSalesperson" value="${sq.coSalesperson}" class="form-control" placeholder="" type="text">
			</div>
		</div>
		
		 <div class="row">
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.customername"></spring:message></label>
				<input  name="customerName" class="form-control required" placeholder="" type="text" value="${sq.customerName}">
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.weddingdate"></spring:message></label>
				 <common:datepicker value="${sq.weddingDate}" name="weddingDate" id="weddingdate"/>
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.cellphone"></spring:message></label>
				 <input  name="cellphone" class="form-control required" placeholder="" type="text" value="${sq.cellphone}">
			</div>
		</div>
		
		 <div class="row">
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.amount"></spring:message></label>
				<input  name="paidamount" class="form-control required" placeholder="" type="text" value="${sq.paidamount}">
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.salesquation.paymentmethod"></spring:message></label>
				<common:selection2  value="${sq.paymentMethod}" data="${paymentMethods}"  name="paymentMethod"></common:selection2>
			</div>
		</div>
		
	</div>
</div>
      </form>
    </section>
     <common:message></common:message>
  </jsp:body>
</template:page>