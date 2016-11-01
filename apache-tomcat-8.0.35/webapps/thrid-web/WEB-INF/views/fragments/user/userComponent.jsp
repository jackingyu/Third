<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- user 编辑页面 -->
 <div class="class="easyui-panel"">
	<form id="userForm">
		   <table cellpadding="5">
	    		<tr>
	    			<td><spring:message code="user.id" /></td>
	    			<td><input class="easyui-textbox" type="text" name="id" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="user.name" /></td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	        <div class="form-submit2">
	             <input name="pk" type="hidden"/>
	       	    <input type="submit"></input>
	        </div>
	</form>
</div>