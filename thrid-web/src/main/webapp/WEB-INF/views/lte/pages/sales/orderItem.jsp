<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
a:hover{color:red;cursor:pointer;border:red}
</style>
<c:forEach var="orderData" items="${orders}">
<a href="${contextPath}/order/modifyorderpage/${orderData.orderCode}">
<div class="box">
 <div class="box-header with-border">
 <h3 class="box-title">${orderData.orderCode}</h3></div>
 <div class="box-body" style="font-size:20px;color:#000000">
        <div class="row">
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.trydate"></spring:message></label>
				<div><fmt:formatDate pattern="yyyy-MM-dd" value="${orderData.tryDate}"/></div>
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.photodate"></spring:message></label>
				<div><fmt:formatDate pattern="yyyy-MM-dd" value="${orderData.photoDate}"/></div>
			</div>
			<div class="col-lg-4 cl-md-4s cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.store"></spring:message></label>
				<div>${orderData.store.name}</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.deliverydate"></spring:message></label>
		       	<div><fmt:formatDate pattern="yyyy-MM-dd" value="${orderData.deliveryDate}"/></div>
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.cosalesperson"></spring:message></label>
		         <div>${orderData.coSalesperson}</div>
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.orderdate"></spring:message></label>
		        <div>${orderData.orderDate}</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.receiveable"></spring:message></label>
		        <div>${orderData.receiveable}</div>
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.openamount"></spring:message></label>
		        <div>${orderData.openamount}</div>
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.paidamount"></spring:message></label>
		        <div>${orderData.paidamount}</div>
			</div>
		</div>  
 </div>
 </div>
 </a>
</c:forEach>