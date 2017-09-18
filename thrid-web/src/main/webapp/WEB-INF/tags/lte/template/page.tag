<%@ tag  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>

<template:master>
    <jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss"/>
	</jsp:attribute>
	<jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts"/>
	</jsp:attribute>
<jsp:body>
   <div class="<c:if test="${isMobile}">m</c:if>content-wrapper">
    <jsp:doBody></jsp:doBody>
   </div>
</jsp:body>
</template:master>