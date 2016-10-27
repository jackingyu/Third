<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div style="margin:5px;">
<div id="tb">
   <div>
        <a href="#" class="easyui-linkbutton addUserGroupButton" iconCls="icon-add" plain="true"></a>    
   </div>
   <div class="selectcondition">
	<span><spring:message code="usergrouplist.groupid" /></span> <input
		id="itemid"></input> <span><spring:message
			code="usergrouplist.name" /></span> <input id="productid"></input> <a
		href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()"><spring:message
			code="form.select" /></a>
	</div>
</div>
<table id="tt" class="easyui-datagrid usergroupgrid"
    style="width:600px; height:250px"
	url="datagrid24_getdata.php" toolbar="#tb"
	title="<spring:message code="usergrouplist.title"/>"
	iconCls="icon-save" rownumbers="true" pagination="true">
	<thead>
		<tr>
			<th field="groupId" class="column-80"><spring:message
					code="usergrouplist.field.groupid" /></th>
			<th field="name" class="column-80"><spring:message
					code="usergrouplist.field.name" /></th>
		</tr>
	</thead>
</table>
</div>
<!-- 新建usergroup 窗口 -->
<div closed="true" class="easyui-dialog">
<form>
<label><spring:message code="usergroup.id"/></label>
<input name="userGroupId"></input>
<label><spring:message code="usergroup.name"/></label>
<input name="userGroupName"></input>
</form>
</div>