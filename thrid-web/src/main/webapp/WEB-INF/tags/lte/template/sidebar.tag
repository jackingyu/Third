<%@ tag trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>

  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel" style="padding:0px">
            <img width="100%" height="43px" src="${contextPath}/_ui/images/ERPLOGO.jpg"  alt="blaimar">
<!--         <div class="pull-left image"> -->
<%--           <img src="${contextPath}/_ui/images/favicon.png"  alt="blaimar"> --%>
<!--         </div> -->
<!--         <div class="pull-left info"> -->
<%--           <p><spring:message code="lte.subtitle"/></p> --%>
<!--         </div> -->
      </div>
  
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header"><spring:message code="lte.nav.mainnav"></spring:message></li>
        <c:forEach var="menu" items="${menus}">
         <template:menu menu="${menu}" ></template:menu>
        </c:forEach>
      </ul>
    </section>
    </aside>
    <!-- /.sidebar -->