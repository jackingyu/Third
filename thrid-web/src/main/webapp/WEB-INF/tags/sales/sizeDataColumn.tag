<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ attribute name="sizeDatas" required="true" type="java.util.List"%>

<table cellspacing="5">
	<c:forEach var="sizeData" items="${sizeDatas}">
		<tr>
			<td>
				<input class="easyui-numberbox" value="${sizeData.value}" code="${sizeData.name}" name="${sizeData.group}-${sizeData.name}"
					data-options="width:200,label:'${sizeData.name}'"></input>
			</td>
		</tr>
	</c:forEach>
</table>