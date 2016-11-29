<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<div title="<spring:message code="orderpanel.title"/>"
	style="width: 800px; height: 500px; padding: 10px;">
	<form id="orderForm">
	    	<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="ACC.order.create()">
				<spring:message code="orderpanel.createorder"></spring:message>
			</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true" onclick="ACC.order.save()">
				<spring:message code="orderpanel.saveorder"></spring:message>
			</a>
		<!-- 订单基本信息 -->
		<div id="orderpanel-basic" class="easyui-panel"
			title="<spring:message code="orderpanel.basic.title"/>" data-options="collapsible:true"
			style="width: 780px; height: 100px; padding: 10px;">
			<table class="orderpanel-table">
				<tr>
					<td>
						<input id="orderpanel-orderpk" type="hidden" name="orderPK"></input>
						<input id="orderpanel-ordercode" class="easyui-textbox" type="text" name="orderCode"
							data-options="width:200,required:true,
					         label: '<spring:message code="orderpanel.basic.ordercode" />'"></input>
					</td>
				</tr>
				<tr>
					<td>
						<input id="orderpanel-trydate" class="easyui-datebox" type="text" name="tryDate"
							data-options="required:true,width:200,label:'<spring:message
							code="orderpanel.basic.trydate" />'"></input>
					</td>
					<td>
						<input id="orderpanel-photodate" class="easyui-datebox" type="text" name="photoDate"
							data-options="required:true,width:200,label:'<spring:message
							code="orderpanel.basic.photodate" />'"></input>
					</td>
					<td>
						<input id="orderpanel-deliverydate" class="easyui-datebox" type="text" name="deliveryDate"
							data-options="required:true,width:200,label:'<spring:message
							code="orderpanel.basic.deliverydate" />'"></input>
					</td>
				</tr>
			</table>
		</div>
		<common:h10 />
		<div class="easyui-panel" title="<spring:message code="orderpanel.customer.title"/>"
			data-options="collapsible:true" style="width: 780px; height: 100px; padding: 10px;">
			<a href="#" style="text-decoration: underline"
				title="<spring:message code='orderpanel.howtocustomer1'/>" class="easyui-tooltip">
				<spring:message code="orderpanel.howtocustomer" />
			</a>
			<table class="orderpanel-table">
				<tr>
					<td>
						<input id="orderpanel-cellphone" type="text" name="cellphone"
							data-options="required:true,width:200,label:'<spring:message
							code="orderpanel.customer.cellphone"/>',
							validType:'cellphoneRex',
						    icons: [{
			               	iconCls:'icon-search',
				              handler: function(e){
				             }
			                }]"></input>
						<!-- 						<input id="orderpanel-cellphone" class="easyui-textbox" type="text" name="cellphone" -->
						<%-- 							data-options="required:true,width:200,label:'<spring:message --%>
						<%-- 							code="orderpanel.customer.cellphone"/>'" --%>
						<!-- 							></input> -->
					</td>
					<td>
						<input id="orderpanel-customername" class="easyui-textbox" type="text" name="customerName"
							data-options="required:true,readonly:true,width:200,label:'<spring:message
							code="orderpanel.customer.name"/>'"></input>
					</td>
					<td>
						<input id="orderpanel-weddingdate" class="easyui-datebox" type="text" name="weddingDate"
							data-options="required:true,width:200,label:'<spring:message
							code="orderpanel.customer.weddingdate"/>'"></input>
					</td>
				</tr>
			</table>
		</div>

	</form>
	<common:h10 />
	<div id="paymentListtb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="ACC.payment.add()">
			<spring:message code="orderpanel.payment.addpayment"></spring:message>
		</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="ACC.payment.remove()">
			<spring:message code="orderpanel.payment.removepayment"></spring:message>
		</a>
	</div>
	<table id="paymentListGrid" style="width: 780px; height: 300px;"
		title="<spring:message code="orderpanel.payment.title"/>">
		<thead>
			<tr>
				<th field="paymentType" class="column-100"
					data-options="
				         formatter:function(value,row){
                            return row.paymentTypeText;
                        },
                        panelHeight:'auto',
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'code',
                                textField:'text',
                                editable:false,
                                url:ACC.config.contextPath+'/getPaymentTypes',
                                required:true
                            }
                        }
				">
					<spring:message code="orderpanel.payment.paymenttype" />
				</th>
				<th field="paymentMethod" class="column-100"
					data-options="
				        formatter:function(value,row){
                            return row.paymentMethodText;
                        },
                        panelHeight:'auto',
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'code',
                                textField:'text',
                                editable:false,
                                url:ACC.config.contextPath+'/getPaymentMethods',
                                required:true
                            }
                        }
				">
					<spring:message code="orderpanel.payment.paymentmethod" />
				</th>
				<th field="amount" class="column-100"
					data-options="editor:{type:'numberbox',options:{precision:1,required:true}}">
					<spring:message code="orderpanel.payment.amount" />
				</th>
				<th field="paidTime" class="column-100">
					<spring:message code="orderpanel.payment.paidtime" />
				</th>
			</tr>
		</thead>
	</table>
</div>