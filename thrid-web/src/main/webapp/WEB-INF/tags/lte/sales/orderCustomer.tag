<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="customer" tagdir="/WEB-INF/tags/lte/customer"%>
<%@ attribute name="orderData" required="false" type="com.third.facade.data.OrderData"%>

<div class="box">
	<div class="box-header">
		<h3 class="box-title">
			<spring:message code="lte.order.customer.title"></spring:message>
		</h3>
	</div>
	<div class="box-body">
		<div class="row">
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
			<label><spring:message code="lte.order.customer.name"></spring:message></label>
			<div class="input-group">
                <div class="input-group-btn">
                  <button type="button" class="btn btn-danger" data-toggle="modal"
					data-target="#customerSearchPanel"><spring:message code="lte.order.customer.search"></spring:message></button>
                </div>
                <input id="customerName"  readonly="readonly" value="${orderData.customerName}" name="customerName" class="form-control required" type="text">
              </div>
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.customer.weddingdate"></spring:message></label>
				 <input value="${orderData.weddingDate}"  disabled="disabled" name="weddingDate" id="weddingdate" class="form-control" type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.customer.cellphone"></spring:message></label>
				<input id="cellphone" value="${orderData.cellphone }" readonly="readonly" name="cellphone" class="form-control"  type="text">
			</div>
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
				<label><spring:message code="lte.order.customer.contactinfo"></spring:message></label>
				<input  value="${orderData.contactinfo}"  name="contactinfo" class="form-control"  type="text">
			</div>
		</div>
	</div>
</div>

<customer:searchCustomer/>
        