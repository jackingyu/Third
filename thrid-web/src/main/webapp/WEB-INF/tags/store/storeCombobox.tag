<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ attribute name="swidth" required="true" type="java.lang.String"%>
<%@ attribute name="ifRequired" required="true" type="java.lang.Boolean"%>
<%@ attribute name="elementId" required="true" type="java.lang.String"%>
<%@ attribute name="elementName" required="true" type="java.lang.String"%>

<input type="text" class="easyui-combobox" id="${elementId}" name="${elementName}"
						data-options="required:${ifRequired},
						     width:${swidth},
						     valueField:'code',
                             textField:'text',
                             editable:false,
                             multiple:false,
                             panelHeight:'auto',
                             url:ACC.config.contextPath+'/getStoresForUser',
						     label:'<spring:message code="text.store"/>'"></input>