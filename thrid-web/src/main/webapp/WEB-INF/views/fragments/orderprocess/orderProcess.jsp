<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="store" tagdir="/WEB-INF/tags/store"%>
<div class="addedTabPanel">
	<div id="op-tb">
		<div class="searchcondition">
			<form id="op-searchForm">
				<input 
					data-options="width:180,required:true,label: '<spring:message code="order.ordercode" />'"
					name="orderCode" id="op-orderCode"></input>
				<input type="hidden" name="status" value="10"/>
			</form>
		</div>
	</div>
	<table id="op-orderProcessListGrid" style="height: 390px;"
		title="<spring:message code="orderprocesslist.title"/>">
		<thead>
			<tr>
				<th field="orderCode" class="column-100">
					<spring:message code="orderprocess.ordercode" />
				</th>
				<th field="fromStatusText" class="column-100">
					<spring:message code="orderprocess.fromstatus" />
				</th>
				<th field="toStatusText" class="column-100">
					<spring:message code="orderprocess.tostatus" />
				</th>
				<th field="processTime" class="column-100">
					<spring:message code="orderprocess.processtime" />
				</th>
				<th field="message" class="column-200">
					<spring:message code="orderprocess.message" />
				</th>
				<th field="userId" class="column-100"
					data-options="formatter:ACC.orderprocess.formatUser">
					<spring:message code="orderprocess.userid" />
				</th>
				<th field="userName" class="column-100"
					data-options="formatter:ACC.orderprocess.formatUserName">
					<spring:message code="orderprocess.username" />
				</th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript">
	ACC.orderprocess.init();
</script>