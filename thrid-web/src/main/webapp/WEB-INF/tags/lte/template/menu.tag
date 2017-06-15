<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="menu" required="true" type="com.third.facade.data.MenuData"%>

<li class="treeview <c:if test="${menu.active}">active</c:if>">
    <a href="#">
		<i class="fa ${menu.icon}"></i> <span>${menu.menuname}</span> <span class="pull-right-container"> <i
			class="fa fa-angle-left pull-right"></i>
		</span>
	</a>
	<ul class="treeview-menu">
	  <c:forEach var="childMenu" items="${menu.menus}">
		<li <c:if test="${childMenu.active}">class="active"</c:if> ><a href="${contextPath}${childMenu.url}">
				<i class="fa ${childMenu.icon }"></i> ${childMenu.menuname }
			</a></li>
		</c:forEach>
	</ul>
</li>