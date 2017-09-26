<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.myorder.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.myorder.title"></spring:message>
      </h1>
      </div>
      </div>
      <div class="row">
       <div class="col-lg-4 col-md-4">
      <a onclick="ACC.myorder.query(0)"
						class="btn btn-default btn-block">
            <spring:message code="lte.myorder.new"></spring:message>
       </a>
      </div>
       <div class="col-lg-4 col-md-4">
      <a onclick="ACC.myorder.query(10)"
						class="btn btn-default btn-block">
            <spring:message code="lte.myorder.storeapprove"></spring:message>
       </a>
      </div>
       <div class="col-lg-4 col-md-4">
      <a onclick="ACC.myorder.query(20)"
						class="btn btn-default btn-block">
            <spring:message code="lte.myorder.finicialapprove"></spring:message>
       </a>
      </div>
      </div>
        <br>
        <div class="row">
       <div class="col-lg-4 col-md-4">
      <a onclick="ACC.myorder.query(30)"
						class="btn btn-default btn-block">
            <spring:message code="lte.myorder.factoryapprove"></spring:message>
       </a>
      </div>
       <div class="col-lg-4 col-md-4">
      <a onclick="ACC.myorder.query(40)"
							class="btn btn-default btn-block">
            <spring:message code="lte.myorder.factorydelivered"></spring:message>
       </a>
      </div>
       <div class="col-lg-4 col-md-4">
      <a onclick="ACC.myorder.query(50)"
							class="btn btn-default btn-block">
            <spring:message code="lte.myorder.storereceipt"></spring:message>
       </a>
      </div>
      </div>
    </section>
    <section class="content" id="myOrderPanel">
    
    </section>
  </jsp:body>
</template:page>