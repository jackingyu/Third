<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="paymentPK" type="java.lang.String" required="false"%>

<form id="customerForm" action="">
	<div class="modal fade" id="customerPanel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<spring:message code="lte.customerlist.title1"></spring:message>
					</h4>
				</div>

				<div class="modal-body">
					<div class="form-group">
						<label><spring:message code="lte.customer.cellphone"></spring:message></label>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-mobile-phone"></i></span>
							<input id="cellphone" name="cellphone" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<label><spring:message code="lte.customer.name"></spring:message></label>
						<div class="input-group">
							<input id="customerName" name="customerName" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<label><spring:message code="lte.customer.QQ"></spring:message></label>
						<div class="input-group">
							<input id="qq" name="qq" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<label><spring:message code="lte.customer.email"></spring:message></label>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
							<input id="email" name="email" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
					</div>

					<div class="form-group">
					    <label><spring:message code="lte.customer.birthday"></spring:message></label>
						<common:datepicker name="birthday" id="birthday"/>
					</div>

					<div class="form-group">
					    <label><spring:message code="lte.customer.weddingdate"></spring:message></label>
						<common:datepicker name="weddingdate" id="weddingdate"/>
					</div>

					<div class="form-group">
						<label><spring:message code="lte.customer.address"></spring:message></label>
						<div class="input-group">

							<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
							<input name="amount" class="form-control" >
						</div>
					</div>

					<div class="form-group">
						<label><spring:message code="lte.customer.comment"></spring:message></label>
					     <textarea  name="comment" value="${customer.comment}" class="form-control" rows="3" ></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">
						<spring:message code="lte.close"></spring:message>
					</button>
					<button type="button" id="saveCustomerBtn" class="btn btn-primary">
						<spring:message code="lte.save"></spring:message>
					</button>
				</div>
			</div>
		</div>
	</div>
</form>
