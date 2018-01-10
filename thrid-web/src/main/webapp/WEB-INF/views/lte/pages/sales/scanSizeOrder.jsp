<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>


<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.scansizeorder.js"></script>
	   <script src="${lteResourcePath}/js/acc.message.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.scansizeorder.title"></spring:message>
      </h1>
      </div>
<!--       <div class="col-lg-2 col-md-2"> -->
<!--           <a onclick="ACC.scansizeorder.save()" class="btn btn-app"> -->
<%--           <i class="fa fa-save"></i>  <spring:message code="lte.search"></spring:message> --%>
<!--        </a> -->
<!--       </div> -->
      </div>
    </section>
    <section class="content-header">
     <form id="sizeOrderForm">
       <div class="row">
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.scansizeorder.externalid"></spring:message></label>
           <input class="form-control" id="externalId" placeholder="" name="externalId" type="text">
         </div>
       </div>
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.scansizeorder.sizeorderpk"></spring:message></label>
          <input class="form-control" placeholder="" id="entryPK" name="entryPK" type="text">
        </div>
       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
         <label><spring:message code="lte.scansizeorder.last"></spring:message></label>
          <input class="form-control" readonly="true" placeholder="" id="record" type="text">
        </div>
<!--         <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"> -->
<%--           <label><spring:message code="lte.scansizeorder.actualtrydate"></spring:message></label> --%>
<%--           <common:datepicker id="actualTryDate" name="actualTryDate"></common:datepicker> --%>
<!--          </div> -->
       </div>
       </form>
    </section>
    <!-- Main content -->
    <section class="content">
    </section>
    <common:message></common:message>
  </jsp:body>
</template:page>