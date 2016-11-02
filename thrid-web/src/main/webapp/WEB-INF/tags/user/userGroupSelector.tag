<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ attribute name="prefix" required="true" %>


<div id="${prefix}_userGroupSelectorDialog" closed="true" class="easyui-dialog" 	title="<spring:message code="usergrouplist.title"/>">
<div id="${prefix}_userGroupSelector-tb">
		<div class="searchcondition">
			<form id="${prefix}_userGroupSelectorForm">
				<span><spring:message code="usergrouplist.groupid" /></span> <input
					name="userGroupId" style="width:50px;"></input> <span><spring:message
						code="usergrouplist.name" /></span> <input name="userGroupName" style="width:50px;"></input>
				<a href="#" class="easyui-linkbutton" plain="true"
					id="${prefix}_userGroupSelectorSearchBtn"><spring:message code="form.select" /></a>
			</form>
		</div>
</div>

<table id="${prefix}_userGroupSelectorGrid" class="easyui-grid" style="width: 460px; height: 220px">
		<thead>
			<tr>
				<th field="groupId" class="column-150"><spring:message
						code="usergrouplist.column.groupid" /></th>
				<th field="name" class="column-150"><spring:message
						code="usergrouplist.column.name" /></th>
			</tr>
		</thead>
</table>
<div>
 <a id="${prefix}_selectUserGroupLink" href="#" style="float:right;margin:5px" onclick="ACC.usergroupselector.selectUserGroup"><spring:message code="text.select"></spring:message></a>
</div>
</div>