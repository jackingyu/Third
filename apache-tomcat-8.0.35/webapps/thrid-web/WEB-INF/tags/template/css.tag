<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>

<spring:url value="/_ui/css/easyui/themes/default/easyui.css" var="easyuiCss" />
<spring:url value="/_ui/css/easyui/themes/icon.css" var="iconCss" />
<spring:url value="/_ui/css/main.css" var="mainCss" />
<link rel="stylesheet" type="text/css" href="${easyuiCss}" />
<link rel="stylesheet" type="text/css" href="${iconCss}" />
<link rel="stylesheet" type="text/css" href="${mainCss}" />