<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>


<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.storelist.js"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <spring:message code="lte.storelist.title"></spring:message>
      </h1>
      </div>
      <div class="col-lg-1 col-md-1">
      <a onclick="ACC.storelist.query()" class="btn btn-app">
          <i class="fa fa-search"></i>  <spring:message code="lte.search"></spring:message>
       </a>
      </div>
      <div class="col-lg-1 col-md-1">
      <a onclick="ACC.storelist.create()" class="btn btn-app">
          <i class="fa fa-edit"></i>  <spring:message code="lte.create"></spring:message>
       </a>
      </div>
    </section>
    <section class="content-header">
     <form id="storeListForm">
       <div class="row">
       <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
         <label><spring:message code="lte.storelist.name"></spring:message></label>
         <input class="form-control" placeholder="" name="name" type="text">
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
              <table id="storeGrid" class="table table-bordered table-hover">
                <thead>
                <tr>
                 <th><spring:message code="lte.storelist.code"></spring:message></th>
                  <th><spring:message code="lte.storelist.name"></spring:message></th>
                </tr>
                </thead>
              </table>
            </div>
          </div>
          </div>
        </div>
    </section>
  </jsp:body>
</template:page>