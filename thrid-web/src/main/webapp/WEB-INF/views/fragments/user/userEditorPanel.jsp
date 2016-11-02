<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>
<!-- user 编辑页面 -->
 <div id="userPanel" class="easyui-panel" style="width:600px;" title="<spring:message code="userpanel.createtitle"/>">
	<form id="userForm">
		   <table cellpadding="5">
	    		<tr>
	    			<td><spring:message code="user.id" /></td>
	    			<td>
	    			<input class="easyui-textbox" type="text" name="userId" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="user.name" /></td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="user.password" /></td>
	    			<td><input class="easyui-textbox" type="password" name="password" data-options="value:'xxxxxxx'"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="user.usergroup" /></td>
	    			<td>
	    			<input type="hidden"  name="usergroup" id="userForm-userGroupPk"/>
	    			<input class="easyui-textbox" type="text" id="userForm-userGroupName" targetField="userForm-userGroupName" valueField="userForm-userGroupPk" data-options="editable:false,onClickButton:ACC.usergroupselector.openDialog,buttonText:'<spring:message code="user.searchusergroup" />',required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="user.blocked" /></td>
	    			<td><input  id="userForm-blocked"  checked="checked" type="checkbox"  name="blocked"></input></td>
	    		</tr>
	    	</table>
	        <div class="form-submit2">
	             <input id="userForm-userPk" name="pk" type="hidden"/>
	       	    <input style="margin-left:150px;" type="submit" value="<spring:message code="text.submit"/>"></input>
	        </div>
	</form>
</div>
<script type="text/javascript">
	ACC.user.initEvent();
</script>