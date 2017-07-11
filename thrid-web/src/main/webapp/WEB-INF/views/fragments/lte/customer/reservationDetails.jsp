<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags//lte/common"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	 <h4 class="modal-title">
	   <c:choose>
	     <c:when test="${not empty reservation.pk}">
	         <spring:message code="lte.reservation.title2"></spring:message>
	     </c:when>
	     <c:otherwise>
	     	  <spring:message code="lte.reservation.title1"></spring:message>
	     </c:otherwise>
	   </c:choose>
	   
	  </h4>
</div>
<div class="modal-body">
	<div class="form-group">
		<label><spring:message code="lte.reservation.name"></spring:message></label>
		<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
			<input id="rp_name" name="name" class="form-control" type="text" value="${reservation.name}">
		</div>
	</div>

	<div class="form-group">
		<label><spring:message code="lte.reservation.cellphone"></spring:message></label>
		<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
			<input id="rp_cellphone" name="cellphone" class="form-control" type="text" value="${reservation.cellphone}">
		</div>
	</div>

	<div class="form-group">
		<common:selection label="lte.reservation.store" name="storeCode" data="${stores}"></common:selection>
	</div>

	<div class="form-group">
		<label><spring:message code="lte.reservation.reservationdate"></spring:message></label>
		<common:datepicker id="preservationDate" name="reservationDate" value="${reservation.reservationDate}"></common:datepicker>
	</div>

	<c:if test="${not empty reservation.channelText}">
		<div class="form-group" style="z-index:99999">
			<label><spring:message code="lte.reservation.channel"></spring:message></label>
			<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
				<input id="rp_channel" value="${reservation.channelText}" readonly="readonly" name="channel"
					class="form-control" type="text">
			</div>
		</div>
	</c:if>

	<div class="form-group">
		<label><spring:message code="lte.reservation.comment"></spring:message></label>
		<textarea name="comment" class="form-control" rows="3" placeholder="">${reservation.comment}</textarea>
	</div>
	<input id="rp_reservationPK" name="reservationPK" value="${reservation.pk}" type="hidden"></input>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default pull-left" data-dismiss="modal">
		<spring:message code="lte.close"></spring:message>
	</button>
	<button type="button" id="saveReservationBtn" onclick="ACC.reservationlist.save()" class="btn btn-primary">
		<spring:message code="lte.save"></spring:message>
	</button>
</div>
<script>
$('#preservationDate').datetimepicker({
	format:'yyyy-mm-dd hh:ii:ss'
});	
</script>
