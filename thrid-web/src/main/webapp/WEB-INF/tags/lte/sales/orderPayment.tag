<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="orderCode" type="java.lang.String"  required="false"%>
<%@ attribute name="paymentPK" type="java.lang.String"  required="false"%>

<form id="paymentForm">
    <div class="modal fade" id="orderPaymentPanel">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><spring:message code="lte.order.payment.title"></spring:message></h4>
              </div>
              <div class="modal-body">
                <common:selection name="paymentType" label="lte.order.payment.paymenttype" data="${paymentTypes}"></common:selection>
                <common:selection name="paymentMethod" label="lte.order.payment.paymentmethod" data="${paymentMethods}"></common:selection>
				 <label><spring:message code="lte.order.payment.amount"></spring:message></label>
				<div class="input-group">
                <span class="input-group-addon"><i class="fa fa-dollar"></i></span>
                <input name="amount" class="form-control required money" type="text">
              </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal"><spring:message code="lte.close"></spring:message></button>
                <button type="button" id="addPaymentBtn" class="btn btn-primary"><spring:message code="lte.save"></spring:message></button>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <input name="orderPK" value="${orderPK}" type="hidden"></input>
</form>
        