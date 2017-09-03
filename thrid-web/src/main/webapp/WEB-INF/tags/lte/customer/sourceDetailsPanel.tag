<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="paymentPK" type="java.lang.String" required="false"%>

<form id="sourceForm" action="">
	<div class="modal fade" id="sourcePanel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<spring:message code="lte.sourcelist.title1"></spring:message>
					</h4>
				</div>

				<div class="modal-body">
					<div class="form-group">
						<label><spring:message code="lte.sourcelist.pk"></spring:message></label>
						<div class="input-group">
							<input id="sourcePK" name="pk" readonly="readonly" class="form-control" type="text">
						</div>
					</div>

					<div class="form-group">
						<label><spring:message code="lte.sourcelist.name"></spring:message></label>
						<div class="input-group">
							<input id="sourceName" name="name" class="form-control" type="text">
						</div>
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">
						<spring:message code="lte.close"></spring:message>
					</button>
					<button type="button" id="saveSourceBtn" class="btn btn-primary">
						<spring:message code="lte.save"></spring:message>
					</button>
				</div>
			</div>
		</div>
	</div>
</form>
