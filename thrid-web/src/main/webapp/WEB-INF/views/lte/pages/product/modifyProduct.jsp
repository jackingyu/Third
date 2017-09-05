<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.product.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.modifyproduct.title"></spring:message>
      </h1>
      </div>
      <div class="col-lg-4 col-md-4">
      <a onclick="ACC.product.save()" class="btn btn-app">
          <i class="fa fa-save"></i>  <spring:message code="lte.save"></spring:message>
       </a>
      </div>
      </div>
    </section>
    <!-- Main content -->
    <section class="content">
      <form id="productForm" action="${contextPath}/product/save" method="post">
<div class="row">
<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
	<div class="box" id="">
		<div class="box-header">
		</div>

		<div class="box-body">
			<div class="form-group">
				<label><spring:message code="lte.modifyproduct.code"></spring:message></label>
				<div class="input-group">
					<input <c:if test="${readonly}">readonly="readonly"</c:if> name="productCode" value="${product.code}" class="form-control" type="text">
				</div>
			</div>

			<div class="form-group">
				<label><spring:message code="lte.modifyproduct.producttitle"></spring:message></label>
				<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
					<input name="producttitle" value="${product.producttitle}" class="form-control" type="text">
				</div>
			</div>

			<div class="form-group">
			    <common:selection name="category" data="${categories}" label="lte.modifyproduct.category" />
			</div>
			
			<div class="form-group">
			    <common:selection name="productGroup" data="${productGroups}" label="lte.modifyproduct.productgroup" />
			</div>
		
		</div>
	</div>
</div>
</div>
</form>
    </section>
  </jsp:body>
</template:page>