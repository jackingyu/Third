<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>


<spring:url value="/_ui/js/jquery.min.js" var="jqueryJs" />
<script type="text/javascript" src="${jqueryJs}"></script>

<spring:url value="/_ui/js/jquery.easyui.min.js" var="easyuiJs" />
<script type="text/javascript" src="${easyuiJs}"></script>

<spring:url value="/_ui/js/main.js" var="mainJs" />
<script type="text/javascript" src="${mainJs}"></script>