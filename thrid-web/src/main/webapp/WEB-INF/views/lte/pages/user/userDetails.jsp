<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/lte/user"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<template:page>
	<jsp:attribute name="pageScripts">
	   <script src="${lteResourcePath}/js/acc.userdetails.js" type="text/javascript"></script>
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-6 col-xs-6">
      <h1>
        <c:choose>
          <c:when test="${not empty user.userId}">
             <spring:message code="lte.modifyuser.title"></spring:message>
          </c:when>
          <c:otherwise>
             <spring:message code="lte.createuser.title"></spring:message>
          </c:otherwise>
        </c:choose>
      </h1>
       <small class="help-block" style="color: #00a65a">${message}</small>
      </div>
      <div class="col-lg-1 col-md-1">
      <a onclick="ACC.userdetails.save()" class="btn btn-app">
          <i class="fa fa-save"></i>  <spring:message code="lte.save"></spring:message>
       </a>
      </div>
      </div>
    </section>
   
    <section class="content">
        <user:userDetails/>
    </section>
  </jsp:body>
</template:page>