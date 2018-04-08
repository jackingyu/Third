<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ attribute name="label" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="value" type="java.lang.String" required="false"%>
<%@ attribute name="data" type="java.util.List" required="true"%>
<%@ attribute name="multiple" type="java.lang.Boolean" required="false" %>
<%@ attribute name="disabled" type="java.lang.String" required="false"%>
<%@ attribute name="validator" type="java.lang.String" required="false"%>
<label><c:if test="${not empty label}">
		<spring:message code="${label}"></spring:message>
	</c:if> </label>

<c:choose>
	<c:when test="${multiple}">
		<select name="${name}" id="${id}" <c:if test="${not empty disabled}">disabled="${disabled}"</c:if>
			multiple="multiple" class="form-control select2 ${validator}" style="width: 100%;">
			<c:forEach var="item" items="${data}">
				<option value="${item.code}" <c:if test="${item.selected}">selected="selected"</c:if>>${item.text}</option>
			</c:forEach>
		</select>
	</c:when>
	<c:otherwise>
		<select name="${name}" id="${id}" <c:if test="${not empty disabled}">disabled="${disabled}"</c:if>
			class="form-control select2 ${validator}" style="width: 100%;">
			<c:if test="${empty value}"><option value=""></option></c:if>
			<c:forEach var="item" items="${data}">
				<option value="${item.code}" <c:if test="${value == item.code}">selected="selected"</c:if>>${item.text}</option>
			</c:forEach>
		</select>
	</c:otherwise>
</c:choose>

