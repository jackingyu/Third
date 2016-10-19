<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<%-- JS configuration --%>
	<script type="text/javascript">
		/*<![CDATA[*/
		<%-- Define a javascript variable to hold the content path --%>
		var ACC = { config: {} };
			ACC.config.contextPath = "${contextPath}";
			ACC.config.encodedContextPath = "${encodedContextPath}";
			ACC.config.commonResourcePath = "${commonResourcePath}";
	
		/*]]>*/
	</script>
	<template:javaScriptAddOnsVariables/>