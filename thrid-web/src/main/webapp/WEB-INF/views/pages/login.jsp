<%@ page contentType="text/html;charset=utf-8" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div>
	<h1>login</h1>
		<form action="<c:url value='/j_spring_security_check' />" method="post" class="form-signin">
	       <input type="text" name="username"></input>
	       <input type="password" name="password"></input>
	       <input type="submit" value="login"/>
		</form>
	</div>
	<div>
	   <c:if test="${not empty login_error_message}">
	     <spring:message code="${login_error_message}"></spring:message>
	   </c:if>
	</div>
    <div>
     测试用户名:test,密码:test
    </div>
</body>
</html>