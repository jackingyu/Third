<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="addedTabPanel">
	<div id="customerlist-tb">
		<div class="searchcondition">
			<form id="searchCustomerForm">
				<span><spring:message code="customer.cellphone" /></span> <input
					name="cellphone"></input> <span><spring:message
						code="customer.name" /></span> <input name="name"></input> <a href="#"
					class="easyui-linkbutton" plain="true" id="searchCustomerBtn"><spring:message
						code="form.select" /></a>
			</form>
		</div>
	</div>
	<table id="customerListGrid" style="width: 600px; height: 390px;"
		title="<spring:message code="customerlist.title"/>">
		<thead>
			<tr>
				<th field="cellphone" class="column-100"><spring:message
						code="customerlist.column.cellphone" /></th>
				<th field="name" class="column-100"><spring:message
						code="customerlist.column.name" /></th>
			</tr>
		</thead>
	</table>
	<jsp:include page="customerEditorPanel.jsp" flush="true" />
</div>
<script type="text/javascript">
   ACC.customerlist.init();
</script>