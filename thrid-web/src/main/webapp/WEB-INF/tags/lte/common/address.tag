<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/lte/common"%>
<%@ attribute name="address" type="com.third.facade.data.AddressData"%>


<div class="row">
  <div class="col-md-3">
    <common:selection2 label="lte.adr.region" validator="requied" id="region" name="region" data="${regions}" value="${address.region.isoCode}"></common:selection2>
  </div>
  <div class="col-md-3">
    <common:selection2 label="lte.adr.city" validator="requied" id="city" name="city" data="${citys}" value="${address.city.isoCode}"></common:selection2>
  </div>
  <div class="col-md-3">
    <common:selection2 label="lte.adr.district" validator="requied" id="district" name="district" data="${districts}" value="${address.district.isoCode}"></common:selection2>
  </div>
</div>
<div class="row">
  <div class="col-md-12">
     	<label><spring:message code="lte.adr.adr1"></spring:message></label>
		<input name="adr1" class="form-control required" value="${address.adr1}" type="text">
  </div>
</div>
<div class="row">
  <div class="col-md-12">
  <label><spring:message code="lte.adr.adr2"></spring:message></label>
		<input name="adr2" class="form-control" value="${address.adr2}" type="text">
  </div>
</div>
<div class="row">
   <div class="col-md-6">
   <label><spring:message code="lte.adr.tel1"></spring:message></label>
		<input name="tel1"  class="form-control" value="${address.tel1}" type="text">
   </div>
   <div class="col-md-6">
   <label><spring:message code="lte.adr.tel2"></spring:message></label>
		<input name="tel2" class="form-control" value="${address.tel2}" type="text">
   </div>
</div>
