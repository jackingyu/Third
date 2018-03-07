<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>

<div class="modal fade" id="paymentListPanel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="lte.storedashboard.paymentlist"></spring:message>
					-<label id="storeNameLabel"></label>
				</h4>
				<div class="row">
				   <div class="col-lg-6 col-md-6">
				     <form id="savedSearchCondition">
				     <div class="form-group">
				       <label><spring:message code="lte.storedashboard.orderdate"></spring:message></label>
				       <input class="formcontrol" readonly="readonly" name="startDate"></input>
				     </div>
				     <div class="form-group">
				       <label><spring:message code="lte.storedashboard.orderdate"></spring:message></label>
				       <input class="formcontrol" readonly="readonly" name="endDate"></input>
		          	</div>
				     <div class="form-group">
<%-- 				       <label><spring:message code="lte.storedashboard.customersource"></spring:message></label> --%>
<!-- 				       <input class="formcontrol" readonly="readonly" name="customerSources"></input> -->
				       <input type="hidden" name="storeCode"></input>
		          	</div>
        
                
                </form>
				   </div>
				   <div class="col-lg-3 col-md-6">
				     <canvas id="paymentMethodChart"></canvas>
				   </div>
				</div>
				
			</div>
			<div class="modal-body">
			   <div class="row">
				<table id="paymentListGrid" class="table table-bordered table-striped dataTable">
					<thead>
						<tr>
							<th>
								<spring:message code="lte.storedashboard.customername"></spring:message>
							</th>
							<th>
								<spring:message code="lte.storedashboard.paymentmethod"></spring:message>
							</th>
							<th>
								<spring:message code="lte.storedashboard.paymentamount"></spring:message>
							</th>
							<th>
								<spring:message code="lte.storedashboard.paymenttime"></spring:message>
							</th>
							<th>
								<spring:message code="lte.storedashboard.ordercode"></spring:message>
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		<div class="modal-footer">
			
		</div>
		</div>
	</div>
</div>
