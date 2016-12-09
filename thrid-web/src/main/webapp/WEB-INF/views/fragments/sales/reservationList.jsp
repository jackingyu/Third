<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="store" tagdir="/WEB-INF/tags/store"%>
<div class="addedTabPanel">
	<div id="rl-tb">
		<div class="searchcondition">
			<form id="rl-searchForm">
				<span><spring:message code="reservation.cellphone" /></span>
				<input name="cellphone"></input>
				<span><spring:message code="reservation.name" /></span>
				<input name="name"></input>
				<store:storeCombobox swidth="200" ifRequired="true" elementId="rl-store" elementName="store"></store:storeCombobox>
				<input id="rl-rStartDate" value="${defaultStartDate}" data-options="width:200,
					         label: '<spring:message code="reservation.startdate" />'" name="fromDate"></input>
			    <input id="rl-rEndDate" value="${defaultEndDate}" data-options="width:200,
					         label: '<spring:message code="reservation.enddate" />'" name="toDate"></input>
				<a href="#" class="easyui-linkbutton" plain="true" id="rl-searchBtn">
					<spring:message code="form.select" />
				</a>
			</form>
		</div>
	</div>
	<table id="rl-reservationListGrid" style="height: 390px;"
		title="<spring:message code="reservationlist.title"/>">
		<thead>
			<tr>
				<th field="cellphone" class="column-100">
					<spring:message code="reservation.cellphone" />
				</th>
				<th field="name" class="column-100">
					<spring:message code="reservation.name" />
				</th>
				<th field="channelText" class="column-100">
					<spring:message code="reservation.channel" />
				</th>
				<th field="reservationDate" class="column-100">
					<spring:message code="reservation.reservationdate" />
				</th>
				<th field="reservationStore" class="column-100" data-options="formatter:ACC.reservationlist.formatStore
				">
					<spring:message code="reservation.store" />
				</th>
			</tr>
		</thead>
	</table>
	<jsp:include page="reservationEditorPanel.jsp" flush="true" />
</div>
<script type="text/javascript">
	ACC.reservationlist.init();
</script>