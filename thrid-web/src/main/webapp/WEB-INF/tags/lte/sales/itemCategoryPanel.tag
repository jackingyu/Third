<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="orderCode" required="true" type="java.lang.String"%>

<div class="modal fade" id="itemCategoryPanel">
	<div class="modal-dialog modal-lg"  role="dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<spring:message code="lte.orderentry.confirmitemcategory"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
			  <div class="row">
			   <c:forEach var="itemCategory" items="${itemCategories}">
			    <div class="col-lg-3 col-md-3">
                   <a href="${contextPath}/orderentry/createorderentrypage/${orderCode}?itemCategory=${itemCategory.code}" 
                   class="btn btn-app">
                   <i class="fa fa-save"></i>  <spring:message code="lte.create"></spring:message>${itemCategory.text}
                   </a>
                 </div>
			    </c:forEach>
                </div>
			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-default pull-left" data-dismiss="modal">
				<spring:message code="lte.close"></spring:message>
			</button>
		</div>
		</div>
	</div>
</div>