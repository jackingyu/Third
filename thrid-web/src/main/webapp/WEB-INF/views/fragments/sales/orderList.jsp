<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<jsp:useBean id="now" class="java.util.Date" />   
<div class="addedTabPanel">
	<div id="orderlist-tb" style="height:60px">
		<div class="searchcondition">
			<form id="searchOrderForm">
			    <input class="easyui-textbox" data-options="width:180,
					         label: '<spring:message code="order.ordercode" />'" name="orderCode"></input> 
			    <input class="easyui-textbox" data-options="width:180,
					         label: '<spring:message code="customer.cellphone" />'" name="cellphone"></input>
			    <input id="searchOrderForm-orderStartDate" data-options="width:200,
					         label: '<spring:message code="order.startdate" />'" name="startDate"name="startDate"></input>
			    <input id="searchOrderForm-orderEndDate" data-options="width:200,
					         label: '<spring:message code="order.enddate" />'" name="endDate"></input>
			    <div style="text-align:center;margin-top:5px">
			    <a href="#"
					class="easyui-linkbutton" data-options="iconCls:'icon-search'"  id="searchOrderBtn"><spring:message
						code="form.select" /></a>
						</div>
			</form>
		</div>
	</div>
	<table id="orderListGrid" style="height:400px"
		title="<spring:message code="orderlist.title"/>">
		<thead>
			<tr>
				<th field="orderCode" class="column-100"><spring:message
						code="orderlist.column.ordercode" /></th>
				<th field="name" class="column-100"><spring:message
						code="orderlist.column.customername" /></th>
			</tr>
		</thead>
	</table>
	<jsp:include page="orderPanel.jsp" flush="true" />
</div>
<script type="text/javascript">
   ACC.orderlist.init();
</script>