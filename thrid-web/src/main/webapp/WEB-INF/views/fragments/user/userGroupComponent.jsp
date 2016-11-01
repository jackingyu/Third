<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
<div id="userGroupPanel" class="easyui-panel" style="width:600px;"  title="<spring:message code="usergrouppanel.title"/>">
	<form id="userGroupForm" method="post">
		   <table cellpadding="5">
	    		<tr>
	    			<td><spring:message code="usergroup.groupid" /></td>
	    			<td><input class="easyui-textbox" type="text" name="groupId" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="usergroup.groupname" /></td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	
	<table id="roleListGrid" style="margin:20px;width: 400px; height: 200px" 
		title="<spring:message code="rolelist.title"/>">
		<thead>
			<tr>
				<th field="roleId" class="column-80"><spring:message
						code="rolelist.column.id" /></th>
				<th field="roleName" class="column-80"><spring:message
						code="rolelist.column.name" /></th>
				<th field="description" class="column-80"><spring:message
						code="rolelist.column.description" /></th>
			</tr>
		</thead>
	</table>
	<div class="form-submit1">
	             <input name="pk" type="hidden"/>
	       	     <input type="submit"></input>
	 </div>
	 </form>
</div>
<div id="roleSelector" closed="true" class="easyui-dialog">
<table id="roleSelectorList">
		<thead>
			<tr>
				<th field="roleId" class="column-80"><spring:message
						code="rolelist.column.id" /></th>
				<th field="roleName" class="column-80"><spring:message
						code="rolelist.column.name" /></th>
				<th field="description" class="column-150"><spring:message
						code="rolelist.column.description" /></th>
			</tr>
		</thead>
</table>
<div>
 <a id="selectRolesLink" href="#" style="float:right;margin:5px" onclick="ACC.usergroup.selectRoles();"><spring:message code="text.select"></spring:message></a>
</div>
</div>
<script type="text/javascript">
ACC.usergroup.initEvent();
</script>
