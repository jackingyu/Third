<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="name" required="false"%>
<%@ attribute name="action" required="false"%>

<div class="modal fade" id="fileuploadPanel">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="lte.order.payment.title"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
			  <input id="${id}" name="${name}"  type="file">
			</div>			
		   <div class="modal-footer">
			<button type="button" class="btn btn-default pull-left" data-dismiss="modal">
				<spring:message code="lte.close"></spring:message>
			</button>
			<button type="button" id="uploadBtn" onclick="${action}" class="btn btn-primary">
				<spring:message code="lte.save"></spring:message>
			</button>
		</div>
		</div>
	
	</div>
</div>

