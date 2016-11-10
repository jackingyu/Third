<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>

<div id="storeSelectorDialog" closed="true" class="easyui-dialog"
	title="<spring:message code="usergrouplist.title"/>">
	<div id="storeSelector-tb">
		<div class="searchcondition">
			<form id="storeSelectorForm">
				<span><spring:message code="storeselector.storename" /></span> <input
					name="storeName" style="width:50px;"></input> 
				<a href="#" class="easyui-linkbutton" plain="true"
					id="storeSelectorSearchBtn"><spring:message code="form.select" /></a>
			</form>
		</div>
</div>
	
	<table id="storeSelectorGrid" class="easyui-grid"
		style="width: 460px; height: 220px">
		<thead>
			<tr>
				<th field="name" class="column-150"><spring:message
						code="storeselector.column.name" /></th>
			</tr>
		</thead>
	</table>
	<div>
 <a id="selectStoreLink" href="#" style="float:right;margin:5px"><spring:message code="text.select"></spring:message></a>
</div>
</div>
