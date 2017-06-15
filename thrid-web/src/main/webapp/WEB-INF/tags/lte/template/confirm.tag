<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="confirmDialog">
	<div class="modal-dialog modal-sm" role="dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<spring:message code="lte.confirm.title"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
	            <div class="row">
	               <div  id="confirmDialog-message" class="col-md-12"></div>
	            </div>
			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-default pull-left" data-dismiss="modal">
				<spring:message code="lte.close"></spring:message>
			</button>
			<button id="confirmDialog-confirmBtn" type="button" class="btn btn-primary" data-dismiss="modal">
				<spring:message code="lte.confirm"></spring:message>
			</button>
		</div>
		</div>
	</div>
</div>
