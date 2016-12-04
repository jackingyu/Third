<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ taglib prefix="sales" tagdir="/WEB-INF/tags/sales"%>

<div class="easyui-layout" style="height:200px">
<div data-options="region:'center',split:true" >
<sales:sizeDataColumn sizeDatas="${sSizeDatas}"/>
</div>
</div>