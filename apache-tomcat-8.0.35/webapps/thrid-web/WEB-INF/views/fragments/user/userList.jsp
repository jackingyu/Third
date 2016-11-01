<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div style="margin: 5px;">
	<div id="userlist-tb">
		<a href="#" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="ACC.usergroup.createUser();"></a>
		<div  id="createuser" class=""easyui-linkbutton" iconCls="icon-del" plain="true"></div>
		<div class="searchcondition">
		  <form id="searchUserForm" >
			<span><spring:message code="userlist.id" /></span> <input
				name="userId"></input> <span><spring:message
					code="userlist.name" /></span> <input name="name"></input> <a
				href="#" class="easyui-linkbutton" plain="true" id="searchUserBtn"><spring:message
					code="form.select" /></a>
		  </form>
		</div>
	</div>
	<table id="userListGrid" style="width: 600px; height: 390px" 
		title="<spring:message code="userlist.title"/>">
		<thead>
			<tr>
				<th field="userId" class="column-100"><spring:message
						code="userlist.column.id" /></th>
				<th field="name" class="column-100"><spring:message
						code="userlist.column.name" /></th>
			</tr>
		</thead>
	</table>
</div>
<!-- 新建usergroup 窗口 -->
<div id="userGroupDialog" closed="true" class="easyui-dialog">
  <div class="class="easyui-panel"">
	<form id="userGroupForm">
		   <table cellpadding="5">
	    		<tr>
	    			<td><spring:message code="user.id" /></td>
	    			<td><input class="easyui-textbox" type="text" name="groupId" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="user.name" /></td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	        <div class="form-submit1">
	             <input name="pk" type="hidden"/>
	       	    <input type="submit"></input>
	        </div>
	</form>
	</div>
</div>
<jsp:include page="userComponent.jsp" flush="true"/>
<script type="text/javascript">
$(document).ready(function(){
	ACC.userlist.initEvent();
});
</script>
