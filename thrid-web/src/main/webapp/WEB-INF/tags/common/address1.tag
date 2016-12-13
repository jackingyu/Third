<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ attribute name="address" required="true" type="com.third.facade.data.AddressData"%>

${address.region.name} ${address.city.name} ${address.adr1}