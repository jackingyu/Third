<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/lte/user"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.storedetails.js" type="text/javascript"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <c:choose>
          <c:when test="${not empty store.pk}">
             <spring:message code="lte.modifystore.title"></spring:message>
          </c:when>
          <c:otherwise>
             <spring:message code="lte.createstore.title"></spring:message>
          </c:otherwise>
        </c:choose>
      </h1>
       <small class="help-block" style="color: #00a65a">${message}</small>
      </div>
      <div class="col-lg-1 col-md-1">
      <a onclick="ACC.storedetails.save()" class="btn btn-app">
          <i class="fa fa-save"></i>  <spring:message code="lte.save"></spring:message>
       </a>
      </div>
      </div>
    </section>
   
    <section class="content">
        <form id="storeForm" action="${contextPath}/store/save" method="post">
<div class="row">
<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
	<div class="box" id="">
		<div class="box-header">
		</div>

		<div class="box-body">
			<div class="form-group">
				<label><spring:message code="lte.store.code"></spring:message></label>
				<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12"">
					<input id="storeCode" name="storeCode" value="${store.code}" class="form-control" type="text"
					<c:if test="${not empty store.pk}">readonly="readonly"</c:if> >
				</div>
				<input type="hidden" name="storePK" value="${store.pk}">
			</div>

			<div class="form-group">
				<label><spring:message code="lte.store.name"></spring:message></label>
				<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
					<input  name="name" value="${store.name}" class="form-control" type="text">
				</div>
			</div>

		</div>
	</div>
</div>
</div>
</form>
    </section>
  </jsp:body>
</template:page>