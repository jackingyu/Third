<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>


<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.productlist.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.productlist.title"></spring:message>
      </h1>
      </div>
      <div class="col-lg-1 col-md-1 pull-left">
      </div>
      <div class="col-lg-1 col-md-1 pull-left">
      <a onclick="ACC.productlist.query()" class="btn btn-app">
          <i class="fa fa-search"></i>  <spring:message code="lte.search"></spring:message>
       </a>
      </div>
      <div class="col-lg-1 col-md-1 pull-left">
      <a href="${contextPath}/product/creatproductpage" class="btn btn-app">
          <i class="fa fa-plus-square"></i>  <spring:message code="lte.create"></spring:message>
       </a>
      </div>
      </div>
    </section>
    <section class="content-header">
     <form id="productListForm">
       <div class="row">
		            <div class="col-xs-3">
					<label><spring:message code="lte.product.name"></spring:message></label>
						<input name="productTitle" placeholder="<spring:message code="lte.search.like"/>" class="form-control" type="text">
					</div>
					<div class="col-xs-3">
						<label><spring:message code="lte.product.code"></spring:message></label>
						<input name="productCode" placeholder="<spring:message code="lte.search.exact"/>" class="form-control" type="text">
					</div>
					<div class="col-xs-3">
						<label><spring:message code="lte.product.productgroup"></spring:message></label>
						<common:selection2 multiple="true" id="productGroups" data="${productGroups}" name="productGroups"></common:selection2>
					</div>
					<div class="col-xs-3">
						<label><spring:message code="lte.product.category"></spring:message></label>
						<common:selection2 multiple="true" id="categories" data="${categories}" name="categories"></common:selection2>
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
              <h3 class="box-title">
					<spring:message code="lte.report.results"></spring:message>
			  </h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="productGrid" class="table table-bordered table-striped dataTable">
					<thead>
						<tr>
							<th>
								<spring:message code="lte.product.code"></spring:message>
							</th>
							<th>
								<spring:message code="lte.product.name"></spring:message>
							</th>
							<th>
								<spring:message code="lte.product.category"></spring:message>
							</th>
							<th>
								<spring:message code="lte.product.productgroup"></spring:message>
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
            </div>
          </div>
          </div>
        </div>
    </section>
  </jsp:body>
</template:page>