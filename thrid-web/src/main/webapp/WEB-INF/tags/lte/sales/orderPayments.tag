<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sales" tagdir="/WEB-INF/tags/lte/sales"%>
<%@ attribute name="orderData" required="false" type="com.third.facade.data.OrderData"%>

<div class="box">
	<div class="box-header">
		<h3 class="box-title">
			<spring:message code="lte.order.payment.title"></spring:message>
		</h3>
	</div>
	<div class="box-body">
		<div class="row">
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
			  <c:if test="${paymentEditable}">
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target="#orderPaymentPanel">
					<spring:message code="lte.order.payment.addpayment"></spring:message>
				</button>
			   </c:if>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.receiveable"></spring:message></label>
		         <input value="${orderData.receiveable}"  disabled="disabled" class="form-control" placeholder="" type="text">
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.openamount"></spring:message></label>
		        <input  value="${orderData.openamount}" disabled="disabled" class="form-control openamount" placeholder="" type="text">
			</div>
			<div class="col-lg-4 cl-md-4 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.basic.paidamount"></spring:message></label>
		        <input  value="${orderData.paidamount}" class="form-control paidamount" disabled="disabled" placeholder="" type="text">
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<table id="paymentGrid" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>
								<spring:message code="lte.order.payment.paymenttype"></spring:message>
							</th>
							<th>
								<spring:message code="lte.order.payment.paymentmethod"></spring:message>
							</th>
							<th>
								<spring:message code="lte.order.payment.amount"></spring:message>
							</th>
							<th>
								<spring:message code="lte.order.payment.paidtime"></spring:message>
							</th>
							<th>
								<spring:message code="lte.order.payment.operation"></spring:message>
							</th>
						</tr>
					</thead>
					<tbody>
					  <c:forEach var="payment" items="${orderData.payments}">
					   <tr>
					      <td>${payment.paymentTypeText}</td>
					      <td>${payment.paymentMethodText}</td>
					      <td>${payment.amount}</td>
					      <td>${payment.paidTime}</td>
					      <td>${payment.pk}</td>
					   </tr>
					   </c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<sales:orderPayment orderCode="${orderData.orderCode}"/>