<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="tb">
	<span><spring:message code="usergrouplist.groupid" /></span> <input
		id="itemid" style="line-height: 26px; border: 1px solid #ccc">
	<span><spring:message code="usergrouplist.name" /></span> <input
		id="productid" style="line-height: 26px; border: 1px solid #ccc">
	<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()"><spring:message code="form.select" /></a>
</div>
<table id="tt" class="easyui-datagrid"
	style="width: 600px; height: 250px" url="datagrid24_getdata.php"
	toolbar="#tb" title="<spring:message code="usergrouplist.title"/>"
	iconCls="icon-save" rownumbers="true" pagination="true">
	<thead>
		<tr>
			<th field="groupId" width="80"><spring:message
					code="usergrouplist.field.groupid" /></th>
			<th field="name" width="80"><spring:message
					code="usergrouplist.field.name" /></th>
		</tr>
	</thead>
</table>
