<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ attribute name="sizeDatas" required="true" type="java.util.List"%>
<%@ attribute name="title" required="false" type="java.lang.String"%>
<div class="col-md-3 col-lg-3 col-xs-12 col-sm-12">
    <h3>${title}</h3>
	<c:forEach var="sizeData" items="${sizeDatas}">
	            <label>${sizeData.name}</label>
				<input value="${sizeData.value}" name="${sizeData.group}-${sizeData.name}" class="form-control required" placeholder="" 
				   type="text" value="${orderData.orderCode}">
	</c:forEach>
</div>