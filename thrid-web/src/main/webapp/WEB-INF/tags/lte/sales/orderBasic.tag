<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="orderData" required="false"
	type="com.third.facade.data.OrderData"%>

<div class="box">
	<div class="box-header">
		<h3 class="box-title">
			<spring:message code="lte.order.basic.title"></spring:message>
		</h3>
	</div>
	<div class="box-body">
		<div class="row">
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.ordercode"></spring:message></label>
				<input id="orderCode" name="orderCode" class="form-control required"
					placeholder="" type="text" value="${orderData.orderCode}">
				<input name="orderPK" id="orderPK" type="hidden" value="${orderData.pk}">
				<input id="orderStatus" type="hidden" value="${orderData.status}">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.trydate"></spring:message></label>
				<common:datepicker value="${orderData.tryDate}" name="tryDate"
					id="trydate" />
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.photodate"></spring:message></label>
				<common:datepicker value="${orderData.photoDate}" name="photoDate"
					id="photodate" />
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.store"></spring:message></label>
				<common:selection data="${stores}" name="storeCode"></common:selection>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.deliverydate"></spring:message></label>
				<common:datepicker validator="required"
					value="${orderData.deliveryDate}" name="deliveryDate"
					id="deliverydate" />
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.cosalesperson"></spring:message></label>
				<input name="coSalesperson" value="${orderData.coSalesperson}"
					class="form-control" placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.salesperson"></spring:message></label>
				<input name="salesperson" value="${orderData.salesPerson.name}"
					disabled="disabled" class="form-control" placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.orderdate"></spring:message></label>
				<input name="orderDate" value="${orderData.orderDate}"
					disabled="disabled" class="form-control" placeholder="" type="text">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.receiveable"></spring:message></label>
				<input name="receiveable" value="${orderData.receiveable}"
					<c:if test="${not empty orderData.pk}">
		            <c:if test="${!receivableEditable}">
		            readonly="readonly"
		            </c:if>
				</c:if>
				class="form-control required money" placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.openamount"></spring:message></label>
				<input value="${orderData.openamount}" disabled="disabled"
					class="form-control openamount" placeholder="" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.paidamount"></spring:message></label>
				<input value="${orderData.paidamount}"
					class="form-control paidamount" disabled="disabled" placeholder=""
					type="text">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6 cl-md-6 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.comment"></spring:message></label>
				<textarea name="comment" class="form-control" rows="3"
					placeholder="">${orderData.comment}</textarea>
			</div>
		</div>
	</div>
</div>