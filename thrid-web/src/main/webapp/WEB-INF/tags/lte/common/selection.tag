<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ attribute name="label" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="data" type="java.util.List"  required="true"%>
<%@ attribute name="value" type="java.lang.String"  required="false"%>
<%@ attribute name="disabled" type="java.lang.String"  required="false"%>
<%@ attribute name="readonly" type="java.lang.Boolean"  required="false"%>


	<label>
	<c:if test="${not empty label}">
	<spring:message code="${label}"></spring:message>
	</c:if>
	</label>
	 <select
	    name="${name}" id="${id}" class="form-control" <c:if test="${not empty disabled}">disabled="${disabled}"</c:if>
	     <c:if test="${readonly}">readonly="readonly"</c:if>
	    
	    >
		<c:forEach var="item" items="${data}">
			<option value="${item.code}" <c:if test="${item.selected}">selected="selected"</c:if> >${item.text}</option>
		</c:forEach>
	</select>
