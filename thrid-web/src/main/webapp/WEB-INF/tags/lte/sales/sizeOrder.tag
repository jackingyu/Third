<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ taglib prefix="customer" tagdir="/WEB-INF/tags/lte/customer"%>
<%@ taglib prefix="sales" tagdir="/WEB-INF/tags/lte/sales"%>

<form id="orderEntryForm" action="${contextPath}/orderentry/saveorderentry" method="post">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">
				<spring:message code="lte.orderentry.basic"></spring:message>
			</h3>
			<div class="box-body">
				<div class="row">
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12">
						<label><spring:message code="lte.orderentry.itemcategory"></spring:message></label>
						<common:selection data="${itemCategorys}" name="itemCategory" disabled="disabled" />
					</div>
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.ordercode"></spring:message></label>
						<input readonly="readonly" name="orderCode" value="${orderEntry.orderCode}"
							class="form-control required" placeholder="" type="text">
					</div>
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.code"></spring:message></label>
						<input readonly="readonly" id="orderEntryPK" value="${orderEntry.pk}" name="entryPK"
							class="form-control required" placeholder="" type="text">
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.store"></spring:message></label>
						<common:selection data="${stores}" name="storeCode" disabled="disabled" />
					</div>
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.customername"></spring:message></label>
						<input value="${orderEntry.customerName }" name="customerName" class="form-control"
							placeholder="" type="text">
					</div>
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.externalId"></spring:message></label>
						<input value="${orderEntry.externalId}" name="externalId" class="form-control required"
							placeholder="" type="text">
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.designer"></spring:message></label>
						<input value="" readonly="readonly" class="form-control" placeholder="" type="text"
							value="${orderEntry.designer}">
					</div>
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.salesperson"></spring:message></label>
						<input value="" readonly="readonly" class="form-control" placeholder="" type="text"
							value="${orderEntry.designer}">
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12">
						<label><spring:message code="lte.orderentry.deliverydate"></spring:message></label>
						<common:datepicker value="${orderEntry.deliveryDate}" id="deliveryDate" name="deliveryDate" />
					</div>
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.sizedate"></spring:message></label>
						<common:datepicker value="${orderEntry.sizeDate}" id="sizeDate" name="sizeDate" />
					</div>
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.trydate"></spring:message></label>
						<common:datepicker value="${orderEntry.tryDate}" id="tryDate" name="tryDate" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 col-lg-8 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.style"></spring:message></label>
						<input value="${orderEntry.style}" name="style" class="form-control" placeholder=""
							type="text">
					</div>
					<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12">
						<label><spring:message code="lte.orderentry.quantity"></spring:message></label>
						<input name="quantity" value="${orderEntry.quantity}" class="form-control" placeholder=""
							type="text">
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-lg-6 col-xs-12 col-sm-12 ">
						<label><spring:message code="lte.orderentry.product"></spring:message></label>
						<input id="productInfo"  onclick="ACC.sizeorder.searchProduct()" readonly="readonly" value="${orderEntry.product.producttitle}" name="style"
								class="form-control" placeholder="" type="text">
						<input type="hidden" value="${orderEntry.product.code}" id="productCode" name="productCode">
					</div>
					<div class="col-md-6 col-lg-6 col-xs-12 col-sm-12">
						<label><spring:message code="lte.orderentry.producttitle"></spring:message></label>
						<input name="producttitle" value="${orderEntry.productTitle}" class="form-control" placeholder=""
							type="text">
					</div>
				</div>
				<div class="row">
					<div class="col-md-9 col-lg-9 col-xs-12 col-sm-12">
						<label><spring:message code="lte.orderentry.comment"></spring:message></label>
						<textarea name="comment" class="form-control" rows="3" placeholder="">${orderEntry.comment}</textarea>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="sizeDatas" name="sizeDetails"></input>
	</div>
</form>


<form id="sizeDataForm">
	<c:if test="${not empty orderEntry.sizeDatas}">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">
					<spring:message code="lte.orderentry.title1"></spring:message>
				</h3>
			</div>
			<div class="box-body">
				<c:forEach items="${orderEntry.sizeDatas}" var="sizeData">
					<sales:sizeDataColumn sizeDatas="${sizeData.value.attributes}"
						title="${sizeData.value.groupText}" />
				</c:forEach>
			</div>
		</div>
	</c:if>
</form>

<button type="submit" class="createSizeOrderBtn btn btn-primary hidden-md hidden-lg ">
	<spring:message code="lte.save"></spring:message>
</button>
