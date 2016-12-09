<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="store" tagdir="/WEB-INF/tags/store"%>
<!-- reservation 编辑页面 -->

<a  id="re-createReservationLink" href="javascript:void(0)" onClick="ACC.reservation.create()" class="easyui-linkbutton" iconCls="icon-add" plain="true">
<spring:message code="text.create"></spring:message>
</a>
<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true" onclick="ACC.reservation.save()">
				<spring:message code="text.save"></spring:message>
			</a>
 <div id="re-reservationPanel" class="easyui-panel" style="width:600px;" title="<spring:message code="reservation.title"></spring:message>">
	<form id="re-reservationForm">
		   <table cellpadding="5">
	    		<tr>
	    			<td>
	    			<input id="re-reservationForm-cellphone" class="easyui-textbox" type="text" name="cellphone" data-options="required:true,validType:'cellphoneRex',label:'<spring:message code="reservation.cellphone" />',width:200"></input></td>
	    		</tr>
	    		<tr>
	    			<td><input id="re-reservationForm-name" class="easyui-textbox" type="text" name="name" data-options="required:true,label:'<spring:message code="reservation.name" />',width:200"></input></td>
	    		</tr>
	    		<tr>
	    			<td><input id="re-reservationForm-reservationdate" class="easyui-datetimebox" name="reservationDate" data-options="required:true,label:'<spring:message code="reservation.reservationdate" />',width:200"></input></td>
	    		</tr>
	    		<tr>
	    			<td><store:storeCombobox swidth="200" ifRequired="true" elementId="re-reservationForm-store" elementName="store"></store:storeCombobox></td>
	    		</tr>
	    		<tr>
	    			<td><input id="re-reservationForm-channel" name="channel" value="${defaultChannel}" type="hidden"></input>
	    			<input id="re-reservationForm-channelText" disabled=true class="easyui-textbox" type="text"  value="${defaultChannelText}" data-options="label:'<spring:message code="reservation.channel" />',width:200"></input></td>
	    		</tr>
	    		<tr>
	    			<td>	
		              <input id="re-reservationForm-comment" class="easyui-textbox" data-options="multiline:true,label:'<spring:message code="reservation.comment" />',width:200" type="text" name="comment">
		            </td>
	    		</tr>
	    	</table>
	        <div class="form-submit2">
	             <input id="re-reservationForm-reservationPK" name="reservationPK" type="hidden"/>
	        </div>
	</form>
</div>