<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 订单 编辑页面 -->
 <div id="orderPanel" class="easyui-panel" style="width:800px;" title="<spring:message code="orderpanel.createtitle"/>">
	<form id="customerForm">
		   <table cellpadding="5">
	    		<tr>
	    			<td><spring:message code="customer.cellphone" /></td>
	    			<td>
	    			<input id="customerForm-cellphone" class="easyui-textbox" type="text" name="cellphone" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="customer.name" /></td>
	    			<td><input id="customerForm-name" class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="customer.QQ" /></td>
	    			<td><input id="customerForm-QQ" class="easyui-textbox" type="text" name="QQ"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="customer.email" /></td>
	    			<td><input id="customerForm-email" class="easyui-textbox" type="text" name="email"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="customer.birthday" /></td>
	    			<td><input id="customerForm-birthday" class="easyui-datebox" type="text" name="birthday"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="customer.weddingday" /></td>
	    			<td><input id="customerForm-weddingday" class="easyui-datebox" type="text" name="weddingday"  data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="address.title" /></td>
	    			<td>	
	    			<select id="customerForm-region" class="easyui-combobox width-80" name="region"  data-options="required:true">
	    			   <c:forEach var="region" items="${regions}">
	    			    <option value="${region.isoCode}">${region.name}</option>
	    			   </c:forEach>
	              	   
		            </select>
		            <select id="customerForm-city" class="easyui-combobox width-80" name="city"  data-options="required:true" >
	              	   <c:forEach var="city" items="${citys}">
	    			    <option value="${city.isoCode}">${city.name}</option>
	    			   </c:forEach>
		            </select>
		            <input id="customerForm-adr1" class="easyui-textbox" type="text" name="adr1"  data-options="required:true">
		            </td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="customer.source" /></td>
	    			<td>	
		            <select id="customerForm-source" class="easyui-combobox width-80" name="source"  data-options="required:true">
	              	    <c:forEach var="source" items="${sources}">
	    			    <option value="${source.pk}">${source.name}</option>
	    			   </c:forEach>
		            </select>
		            </td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="customer.comment" /></td>
	    			<td>	
		              <input id="customerForm-comment" class="easyui-textarea" type="text" name="comment">
		            </td>
	    		</tr>
	    	</table>
	        <div class="form-submit2">
	             <input id="customerForm-customerPk" name="pk" type="hidden"/>
	       	    <input style="margin-left:150px;" type="submit" value="<spring:message code="text.submit"/>"></input>
	        </div>
	</form>
</div>
<script type="text/javascript">
	ACC.order.init();
</script>