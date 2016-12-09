<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<common:h10 />
<form id="orderEntryForm">
	<a href="javascript:void(0)" class="easyui-linkbutton" id="saveOrderEntryLink"
		data-options="iconCls:'icon-save',disabled:true,plain:true" onclick="ACC.orderentry.save()">
		<spring:message code="orderentrypanel.saveentry"></spring:message>
	</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
		onclick="ACC.orderentry.create()">
		<spring:message code="orderentrypanel.createentry"></spring:message>
	</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" id="removeOrderEntryLink"
		data-options="iconCls:'icon-remove',disabled:true,plain:true" onclick="ACC.orderentry.remove()">
		<spring:message code="orderentrypanel.removeentry"></spring:message>
	</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" id="uploadSizeImageLink"
		data-options="iconCls:'icon-upload',disabled:true,plain:true" onclick="ACC.orderentry.uploadImage()">
		<spring:message code="orderentrypanel.uploadfile"></spring:message>
	</a>
	<div class="easyui-panel" title="<spring:message code="orderentrypanel.basicinformation"/>"
		data-options="collapsible:true" style="width: 780px; height: 250px; padding: 10px;">
		<table class="orderpanel-table">
			<tr>
				<td>
					<input type="hidden" id="entryPK" name="entryPK" />
					<input type="hidden" id="sizeImageUrl" name="sizeImageUrl" />
					<input type="text" class="easyui-combobox" id="orderentry-itemcategory" name="itemCategory"
						data-options="required:true,
						     width:200,
						     valueField:'code',
                             textField:'text',
                             editable:false,
                             multiple:false,
                             url:ACC.config.contextPath+'/getItemCategories',
                             onSelect:function(value){
                                ACC.orderentry.getSizeDatasByItemCategory(value);
                              },
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
			<tr>
			<td>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="getSizeImage"
		       data-options="iconCls:'icon-large_picture',plain:true" onclick="ACC.orderentry.getSizeImage()">
		     <spring:message code="orderentrypanel.getsizeimage"></spring:message>
	        </a>
			</td>
			</tr>

		</table>
	</div>
</form>