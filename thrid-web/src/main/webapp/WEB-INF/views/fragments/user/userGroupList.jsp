<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="addedTabPanel">
	<div id="usergrouplist-tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="ACC.usergroup.create();"></a>
		<div class="searchcondition">
			<form id="searchUserGroupForm">
				<span><spring:message code="usergrouplist.groupid" /></span> <input
					name="userGroupId"></input> <span><spring:message
						code="usergrouplist.name" /></span> <input name="userGroupName"></input>
				<a href="#" class="easyui-linkbutton" plain="true"
					id="searchUserGroupBtn"><spring:message code="form.select" /></a>
			</form>
		</div>
	</div>
	<table id="userGroupListGrid" style="width: 600px; height: 390px"
		title="<spring:message code="usergrouplist.title"/>">
		<thead>
			<tr>
				<th field="groupId" class="column-100"><spring:message
						code="usergrouplist.column.groupid" /></th>
				<th field="name" class="column-100"><spring:message
						code="usergrouplist.column.name" /></th>
			</tr>
		</thead>
	</table>
	<jsp:include page="userGroupEditorPanel.jsp" flush="true" />
</div>
<script type="text/javascript">
	ACC.usergrouplist.initEvent();
</script>

