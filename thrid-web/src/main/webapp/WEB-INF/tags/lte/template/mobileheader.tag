<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>


<header class="main-header">
    <!-- Logo -->
    <a href="${contextPath}/home" class="logo">
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg" ><b>T</b>hird</span>
    </a>
    <nav class="navbar navbar-static-top navbar-barmobile" onclick="home()">
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
            <a href="#" data-toggle="control-sidebar"><spring:message code="lte.homepage"></spring:message></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>