<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<!-- 订单 编辑页面 -->
<common:h10 />
<div id="orderpanel" class="easyui-tabs" style="width: 800px; height: 400px" title="test">
<jsp:include page="orderBasicTab.jsp" flush="true" />
<jsp:include page="orderEntryTab.jsp" flush="true" />
</div>
<script type="text/javascript">
	ACC.order.init();
</script>