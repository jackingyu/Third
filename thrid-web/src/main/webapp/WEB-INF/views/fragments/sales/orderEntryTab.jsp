<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ taglib prefix="sales" tagdir="/WEB-INF/tags/sales"%>

<div title="<spring:message code="orderpanel.entry.title"/>" style="height:auto;padding: 10px"
	data-options="disabled:true">
	 <sales:orderEntryList></sales:orderEntryList>
	<sales:orderEntryDetails></sales:orderEntryDetails>
<sales:sizeDataPanel></sales:sizeDataPanel>
</div>
	 
	    
	    
</div>