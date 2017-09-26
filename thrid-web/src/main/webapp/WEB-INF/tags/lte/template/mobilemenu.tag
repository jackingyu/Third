<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="menu" required="true" type="com.third.facade.data.MenuData"%>

<div class="row">
         <c:forEach var="childMenu" items="${menu.menus}">
         <div class="col-lg-3 col-md-3">
		     <a href="${contextPath}${childMenu.url}" class="btn btn-app-lg">
				<i class="fa ${childMenu.icon }"></i> ${childMenu.menuname }
			</a>
		   </div>
		</c:forEach>
		</div>