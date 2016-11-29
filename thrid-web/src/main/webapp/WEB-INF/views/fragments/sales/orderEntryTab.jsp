<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<div title="<spring:message code="orderpanel.entry.title"/>" style="padding: 10px"
	data-options="disabled:true">
	<table id="entryListGrid" style="height: 150px">
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

	<common:h10 />
	<form id="orderEntryForm">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveOrderEntryLink"
			data-options="iconCls:'icon-save',disabled:true,plain:true" onclick="ACC.orderentry.save()">
			<spring:message code="orderentrypanel.saveentry"></spring:message>
		</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="ACC.orderentry.create()">
			<spring:message code="orderentrypanel.createentry"></spring:message>
		</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" id="removeOrderEntryLink"
			data-options="iconCls:'icon-remove',disabled:true,plain:true" onclick="ACC.orderentry.remove()">
			<spring:message code="orderentrypanel.removeentry"></spring:message>
		</a>
		<div class="easyui-panel" title="<spring:message code="orderentrypanel.basicinformation"/>"
			data-options="collapsible:true" style="width: 780px; height: 300px; padding: 10px;">
			<table class="orderpanel-table">
				<tr>
					<td>
						<input type="hidden" id="entryPK" name="entryPK" />
						<input type="text" class="easyui-combobox" id="orderentry-itemcategory" name="itemCategory"
							data-options="required:true,
						     width:200,
						     valueField:'code',
                             textField:'text',
                             editable:false,
                             url:ACC.config.contextPath+'/getItemCategories',
						     label:'<spring:message
							code="orderentry.itemcategory"/>'"></input>
					</td>
					<td>
						<input class="easyui-textbox" type="text" id="orderentry-ordercode" name="orderCode"
							data-options="required:false,width:200,readonly:true,label:'<spring:message
							code="orderentry.ordercode"/>'"></input>
					</td>
					<td>
						<input class="easyui-textbox" type="text" id="orderentry-customername" name="customerName"
							data-options="required:true,width:200,label:'<spring:message
							code="orderentry.customername"/>'"></input>
					</td>
				</tr>
				<tr>
					<td>
						<input class="easyui-textbox" type="text" id="orderentry-style" name="style"
							data-options="required:false,width:200,label:'<spring:message
							code="orderentry.style"/>'"></input>
					</td>
					<td>
						<input class="easyui-textbox" type="text" id="orderentry-designer" name="designer"
							data-options="required:true,width:200,label:'<spring:message
							code="orderentry.designer"/>'"></input>
					</td>
					<td>
						<input class="easyui-textbox" type="text" id="orderentry-storename"
							data-options="required:true,width:200,disabled:true,label:'<spring:message
							code="orderentry.store"/>'"></input>
					</td>
				</tr>
				<tr>
					<td>
						<input class="easyui-datebox" type="text" id="orderentry-trydate" name="tryDate"
							data-options="required:true,width:200,label:'<spring:message
							code="orderentry.trydate"/>'"></input>
					</td>
					<td>
						<input class="easyui-datebox" type="text" id="orderentry-sizedate" name="sizeDate"
							data-options="required:true,width:200,label:'<spring:message
							code="orderentry.sizedate"/>'"></input>
					</td>
					<td>
						<input class="easyui-datebox" type="text" id="orderentry-deliverydate" name="deliveryDate"
							data-options="required:true,width:200,label:'<spring:message
							code="orderentry.deliverydate"/>'"></input>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<input class="easyui-textbox" type="text" id="orderentry-producttitle" name="productTitle"
							data-options="required:true,width:300,label:'<spring:message
							code="orderentry.producttitle"/>'"></input>
					</td>
					<td>
						<input class="easyui-numberbox" type="text" id="orderentry-quantity" name="quantity"
							data-options="required:true,width:200,label:'<spring:message
							code="orderentry.quantity"/>'"></input>
					</td>
				</tr>
				<tr>
					<td>
						<input class="easyui-textbox" type="text" id="orderentry-comment" name="comment"
							data-options="required:false,width:300,multiline:true,label:'<spring:message
							code="orderentry.comment"/>'"></input>
					</td>
				</tr>

			</table>
		</div>
		<common:h10 />
		<!-- 具体的量身数据 -->
		<div class="easyui-panel" title="<spring:message code="orderentrypanel.sizeorderdetails"/>"
			data-options="collapsible:true" style="width: 780px; height: 100px; padding: 10px;">
			<table class="orderpanel-table">
				<tr>
					<td>
						<input type="text" class="easyui-textbox"
							data-options="required:false,width:200,label:'<spring:message
							code="orderpanel.customer.cellphone"/>'"></input>
					</td>
					<td>
						<input class="easyui-textbox" type="text"
							data-options="required:false,width:200,label:'<spring:message
							code="orderpanel.customer.name"/>'"></input>
					</td>
					<td>
						<input class="easyui-datebox" type="text"
							data-options="required:false,width:200,label:'<spring:message
							code="orderpanel.customer.weddingdate"/>'"></input>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>