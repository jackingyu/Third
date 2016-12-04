<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/sales"%>

 <form class="easyui-form" id="sizeDatasForm">
           <c:choose> 
             <c:when test="${itemCategory == '10'}">
               <order:sizeDataShirt></order:sizeDataShirt>
             </c:when>
             <c:when test="${itemCategory == '20'}">
               <order:sizeDataTrousers></order:sizeDataTrousers>
             </c:when>
             <c:when test="${itemCategory == '30'}">
               <order:sizeDataSkirt></order:sizeDataSkirt>
             </c:when>
             <c:when test="${itemCategory == '40'}">
               <order:sizeDataVest></order:sizeDataVest>
             </c:when>
           </c:choose>
		</form>
