<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>

<div class="modal fade" id="productSearchPanel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="lte.product.search"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
			    <form id="searchProductForm">
				<div class="row">
					<div class="col-xs-3">
						<label><spring:message code="lte.product.name"></spring:message></label>
						<input name="productTitle" placeholder="<spring:message code="lte.search.like"/>" class="form-control" type="text">
					</div>
					<div class="col-xs-3">
						<label><spring:message code="lte.product.code"></spring:message></label>
						<input name="productCode"  placeholder="<spring:message code="lte.search.exact"/>"  class="form-control" type="text">
					</div>
					<div class="col-xs-3">
						<label><spring:message code="lte.product.productgroup"></spring:message></label>
						<common:selection data="${productGroups}" name="productGroup"></common:selection>
					</div>
					<input name="category" type="hidden" value="${searchCategory}">
				</div>
				</form>
				<button id="searchProductBtn" type="button" class="btn btn-block btn-default">
					<spring:message code="lte.search"></spring:message>
				</button>
				<table id="productSearchGrid" class="table table-bordered table-striped dataTable">
					<thead>
						<tr>
							<th>
								<spring:message code="lte.product.code"></spring:message>
							</th>
							<th>
								<spring:message code="lte.product.name"></spring:message>
							</th>
							<th>
								<spring:message code="lte.product.category"></spring:message>
							</th>
							<th>
								<spring:message code="lte.product.productgroup"></spring:message>
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">
					<spring:message code="lte.close"></spring:message>
				</button>
				<button id="searchProduct-select" type="button" class="btn btn-primary" data-dismiss="modal">
					<spring:message code="lte.save"></spring:message>
				</button>
			</div>
		</div>
	</div>
</div>
