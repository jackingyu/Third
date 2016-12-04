<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ taglib prefix="sales" tagdir="/WEB-INF/tags/sales"%>

<div class="easyui-layout" style="height:700px">
<div data-options="region:'west',split:true" title="<spring:message code="orderentrypanel.sizedata.liang"></spring:message>" style="width: 250px; height: auto; padding: 10px;">
	<sales:sizeDataColumn sizeDatas="${lSizeDatas}"/>
</div>


<div data-options="region:'center',split:true" title="<spring:message code="orderentrypanel.sizedata.cai"></spring:message>" style="width:250px; height:auto; padding:10px;">
	<sales:sizeDataColumn sizeDatas="${cSizeDatas}"/>
</div>

<div data-options="region:'east',split:true" title="<spring:message code="orderentrypanel.sizedata.shi"></spring:message>" style="width:250px; height:auto; padding:10px;">
	<sales:sizeDataColumn sizeDatas="${sSizeDatas}"/>
</div>
</div>
