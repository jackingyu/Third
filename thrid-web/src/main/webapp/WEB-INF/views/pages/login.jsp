<%@ page contentType="text/html;charset=utf-8" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

</body>
</html>