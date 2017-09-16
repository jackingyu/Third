<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="orderData" required="false" type="com.third.facade.data.OrderData"%>

<div class="box">
	<div class="box-header">
		<h3 class="box-title">
			<spring:message code="lte.orderentry.title"></spring:message>
		</h3>
	</div>
	<div class="box-body">
		<div class="row">
			<div class="col-lg-3 cl-md-3 cl-sm-12 cl-xs-12">
			  <c:if test="${editable}">
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target="#itemCategoryPanel">
					<spring:message code="lte.orderentry.create"></spring:message>
				</button>
			   </c:if>
			</div>
		</div>
		<hr>
		<table id="entryGrid" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><spring:message code="lte.orderentry.itemcategory"></spring:message></th>
					<th><spring:message code="lte.orderentry.quantity"></spring:message></th>
					<th><spring:message code="lte.orderentry.customername"></spring:message></th>
					<th><spring:message code="lte.orderentry.producttitle"></spring:message></th>
					<th><spring:message code="lte.orderentry.operation"></spring:message></th>
				</tr>
			</thead>
			<tbody>
			   <c:forEach var="entry" items="${orderData.entries}">
			   <tr>
			   <td>${entry.itemCategoryText}</td>
			   <td>${entry.quantity}</td>
			   <td>${entry.customerName}</td>
			   <td>${entry.productTitle}</td>
			   <td>${entry.pk}</td>
			   </tr>
			   </c:forEach>
			</tbody>
		</table>
	</div>
</div>

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
                   <a href="${contextPath}/orderentry/createorderentrypage/${orderData.orderCode}?itemCategory=${itemCategory.code}" 
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
