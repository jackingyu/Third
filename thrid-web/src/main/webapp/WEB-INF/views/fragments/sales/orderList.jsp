<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="addedTabPanel">
	<div id="orderlist-tb">
		<a  id="createorder" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
		<div class="searchcondition">
			<form id="searchOrderForm">
				<span><spring:message code="order.orderno" /></span> <input
					name="orderno"></input> <span><spring:message
						code="customer.cellphone" /></span> <input name="cellphone"></input> <a href="#"
					class="easyui-linkbutton" plain="true" id="searchOrderBtn"><spring:message
						code="form.select" /></a>
			</form>
		</div>
	</div>
	<table id="orderListGrid" style="width: 800px; height: 390px;"
		title="<spring:message code="orderlist.title"/>">
		<thead>
			<tr>
				<th field="orderNo" class="column-100"><spring:message
						code="orderlist.column.orderno" /></th>
				<th field="name" class="column-100"><spring:message
						code="orderlist.column.name" /></th>
			</tr>
		</thead>
	</table>
	<jsp:include page="orderEditorPanel.jsp" flush="true" />
</div>
<script type="text/javascript">
   ACC.orderlist.init();
</script>