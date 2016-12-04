<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<table id="entryListGrid" style="width: 780px; height: 150px">
	<thead>
		<tr>
			<th field="itemCategoryText" class="column-100">
				<spring:message code="orderentrypanel.entry.itemcategory" />
			</th>
			<th field="customerName" class="column-100">
				<spring:message code="orderentrypanel.entry.customername" />
			</th>
			<th field="productTitle" class="column-100">
				<spring:message code="orderentrypanel.entry.producttitle" />
			</th>
			<th field="quantity" class="column-100">
				<spring:message code="orderentrypanel.entry.quantity" />
			</th>
		</tr>
	</thead>
</table>
