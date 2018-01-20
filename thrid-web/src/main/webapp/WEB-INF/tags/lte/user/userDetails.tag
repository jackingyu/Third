<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="paymentPK" type="java.lang.String" required="false"%>

<form id="userForm" action="${contextPath}/user/save" method="post">
<div class="row">
<div class="col-sm-12 col-xs-12 col-md-6 col-lg-6">
	<div class="box" id="">
		<div class="box-header">
		</div>

		<div class="box-body">
			<div class="form-group">
				<label><spring:message code="lte.user.id"></spring:message></label>
				<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12"">
					<input <c:if test="${not empty user.pk}">readonly="readonly"</c:if> id="userId" name="userId" value="${user.userId}" class="form-control" type="text">
				</div>
				<input type="hidden" name="userPK" value="${user.pk}">
			</div>

			<div class="form-group">
				<label><spring:message code="lte.user.name"></spring:message></label>
				<div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
					<input  name="name" value="${user.name}" class="form-control required" type="text">
				</div>
			</div>

			<div class="form-group">
				<label><spring:message code="lte.user.password"></spring:message></label>
                <div class="input-group col-sm-12 col-xs-12 col-md-12 col-lg-12">
					<input  name="password" value="${user.password}" class="form-control required" type="text">
				</div>
			</div>
			<div class="form-group">
				<common:selection2  validator="required" data="${userGroups}" value="${user.userGroup.pk}" label="lte.user.usergroup"  name="usergroupPK"></common:selection2>
			</div>
			
			<div class="form-group">
				<common:selection2 id="storeCodes"  data="${stores}" label="lte.user.store"  multiple="true" name="stores"></common:selection2>
			</div>
			<div class="form-group">
				<common:selection2  data="${stores}" label="lte.user.store1"  value="${user.store.code}" name="store"></common:selection2>
			</div>
			<div class="checkbox">
			    <label><input name="blocked"  <c:if test="${user.blocked}">checked="checked"</c:if> type="checkbox">
			    <spring:message code="lte.user.blocked"></spring:message></label>
			</div>
		</div>
	</div>
</div>
</div>
</form>
