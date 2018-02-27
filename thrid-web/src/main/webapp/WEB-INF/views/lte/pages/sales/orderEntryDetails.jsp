<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/lte/product"%>

<template:page>
	<jsp:attribute name="pageScripts">
		<script src="${lteResourcePath}/js/acc.sizeorder.js"></script>
		<script src="${lteResourcePath}/js/acc.createsizeorder.js"></script>
		<script src="${lteResourcePath}/js/acc.searchproduct.js"></script>
		<script src="${lteResourcePath}/js/acc.message.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h2>
        <spring:message code="lte.orderentry.title"></spring:message>-${message1}
        <small>${statusText}</small>
      </h2>
      <small class="help-block" style="color: #00a65a">${message}</small>
      </div>
      <div class="col-lg-1 col-md-1 pull-right">
      </div>
     <c:if test="${enableSaveBtn}">
      <sec:authorize access="hasAnyRole('ROLE_SALES','ROLE_ADMIN','ROLE_FACTORY')">
      <div class="col-lg-1 col-md-1 pull-right">
         <a onclick="ACC.sizeorder.saveSizeOrder()" class="btn btn-app">
          <i class="fa fa-save"></i>  <spring:message code="lte.save"></spring:message>
       </a>
      </div>
      </sec:authorize>
      </c:if>
      <c:if test="${orderEntry.status == 20}">
      <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_DESIGNER','ROLE_FACTORY')">
      <div class="col-lg-1 col-md-1 pull-right">
         <a onclick="ACC.sizeorder.factoryApprove()" class="btn btn-app">
          <i class="fa fa-save"></i> 
          <spring:message code="lte.factoryapprove"></spring:message>
         </a>
      </div>
      </sec:authorize>
      </c:if>
      <c:if test="${editable}">
        <div class="col-lg-1 col-md-1 pull-right">
         <a onclick="ACC.sizeorder.createSizeOrder()" class="btn btn-app">
          <i class="fa fa-edit"></i> 
            <spring:message code="lte.orderentry.create"></spring:message>
          </a>
		</div>
	  </c:if>
   <!-- size order image function do not delete this!!!!     
      <c:if test="${not empty orderEntry.pk}">
      <div class="col-lg-1 col-md-1">
         <a data-toggle="modal" data-target="#fileuploadPanel" class="btn btn-app">
          <i class="fa fa-upload"></i>  <spring:message code="lte.orderentry.upload"></spring:message>
       </a>
      </div>
      </c:if>
      <c:if test="${not empty orderEntry.sizeImageUrl}">
		<div class="col-lg-1 col-md-1">
         <a data-toggle="modal" data-target="#imagePanel"  class="btn btn-app">
          <i class="fa fa-file-image-o"></i>  <spring:message code="lte.orderentry.sizeimage"></spring:message>
         </a>
         <input type="hidden" id="sizeImageUrl" value="${contextPath}${orderEntry.sizeImageUrl}">
        </div>
	  </c:if>
	  -->
   </div>
    </section>
    
    <!-- Main content -->
    <section class="content">
       <order:sizeOrder />
    </section>
     <form id="uploadSizeImageForm" method="post"
			enctype="multipart/form-data">
       <common:fileupload name="fileUpload" id="sizeImageUpload"
				action="ACC.sizeorder.uploadImage()" />
       <input type="hidden" name="entryPK" value="${orderEntry.pk}">
    </form>
    <common:message></common:message>
    <common:image></common:image>
    <product:searchProduct />
    <order:itemCategoryPanel orderCode="${orderEntry.orderCode}"></order:itemCategoryPanel>
  </jsp:body>
</template:page>