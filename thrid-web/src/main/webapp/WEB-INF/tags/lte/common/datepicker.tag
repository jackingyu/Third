<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ attribute name="id" required="false"%>
<%@ attribute name="dateFormat" required="false"%>
<%@ attribute name="name" required="false"%>
<%@ attribute name="value" required="false" type="java.util.Date"%>

<div class="input-group date">
	<div class="input-group-addon">
		<i class="fa fa-calendar"></i>
	</div>
	<input id="${id}" name="${name}" value="<fmt:formatDate value="${value}" pattern="yyyy-MM-dd" /> " class="form-control" data-date-format="yyyy-mm-dd" data-inputmask="'alias': 'yyyy-mm-dd'" data-mask=""
		type="text">
</div>