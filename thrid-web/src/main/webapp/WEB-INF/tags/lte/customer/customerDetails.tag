<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="paymentPK" type="java.lang.String" required="false"%>

<form id="customerForm" action="${contextPath}/customer/save" method="post">
<div class="row">
<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
	<div class="box" id="">
		<div class="box-header">
		</div>

		<div class="box-body">
			<div class="form-group">
				<label><spring:message code="lte.customer.name"></spring:message></label>
				<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
					<input id="customerName" name="name" value="${customer.name}" class="form-control required" type="text">
				</div>
			</div>
			
			<div class="form-group">
				<label><spring:message code="lte.customer.cellphone"></spring:message></label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-mobile-phone"></i></span>
					<input id="cellphone" name="cellphone" value="${customer.cellphone}" class="form-control required cellphone" type="text">
				</div>
				<input type="hidden" name="customerPK" value="${customer.pk}">
			</div>

	

			<div class="form-group">
				<label><spring:message code="lte.customer.QQ"></spring:message></label>
				<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
					<input id="qq" name="qq" value="${customer.QQ}" class="form-control" type="text">
				</div>
			</div>

			<div class="form-group">
				<label><spring:message code="lte.customer.email"></spring:message></label>
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
					<input id="email" name="email" value="${customer.email}" class="form-control email" type="text">
				</div>
			</div>

			<div class="form-group">
			    <common:selection2 validator="required" value="${customer.source.pk}" name="source" data="${sources}"  label="lte.customer.source"/>
			</div>

			<div class="form-group">
				<label><spring:message code="lte.customer.birthday"></spring:message></label>
				<common:datepicker name="birthday" value="${customer.birthday}" id="birthday" />
			</div>

			<div class="form-group">
				<label><spring:message code="lte.customer.weddingdate"></spring:message></label>
				<common:datepicker validator="required" name="weddingdate" value="${customer.weddingdate}" id="weddingdate" />
			</div>

			<!--  
			<div class="form-group">
				<label><spring:message code="lte.customer.address"></spring:message></label>
				<common:address address="${customer.address}"/>
			</div>
			-->

			<div class="form-group">
				<label><spring:message code="lte.customer.comment"></spring:message></label>
				<textarea name="comment" class="form-control" rows="3" cols="">${customer.comment}</textarea>
			</div>
		</div>
	</div>
</div>
</div>
</form>
