<%@ page contentType="text/html;charset=utf-8" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title><spring:message code="main.login"/></title>
<!-- Bootstrap core CSS -->
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="resources/core/css/signin.css" rel="stylesheet">
<spring:url value="/resources/core/js/ie-emulation-modes-warning.js" var="ieemulation" />
<script src="${ieemulation}"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<c:url value="/login" var="loginUrl" />
		<form action="${loginUrl}" method="post" class="form-signin">
			<c:if test="${param.error != null}">
				<div class="alert alert-danger">
					<spring:message code="main.invalidlog"/>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">
					<spring:message code="main.logout"/>
				</div>
			</c:if>
			<h2 class="form-signin-heading"><spring:message code="main.signin"/></h2>
			<label for="inputEmail" class="sr-only"><spring:message code="main.username"/></label> 
			<input id="inputEmail" name="username" class="form-control" placeholder="<spring:message code="main.username"/>" required autofocus>
			<label for="inputPassword" class="sr-only"><spring:message code="main.password"/></label> 
			<input type="password" name="password" id="inputPassword" class="form-control" placeholder="<spring:message code="main.password"/>" required>
			<div class="dropdown">
			  <button class="btn btn-lg btn-primary btn-block dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			    <spring:message code="main.language"/>
			    <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			    <li><a href="?lang=en">English</a></li>
			    <li><a href="?lang=cn">中文简体</a></li>
			  </ul>
			</div>
			<div class="checkbox">
				<label><input name="remember-me" type="checkbox"><spring:message code="main.rememberme"/></label>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-lg btn-primary btn-block" id="embed-submit" type="submit"><spring:message code="main.logon"/></button>
			<div id="embed-captcha"></div>
		    <p id="wait" class="show"><spring:message code="main.loadingcaptcha"/></p>
		    <p id="notice" class="hide"><spring:message code="main.captchahint"/></p>
		</form>
	</div>
	<!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<spring:url value="/resources/core/js/ie10-viewport-bug-workaround.js" var="ie10viewport" />
	<script src="${ie10viewport}"></script>
	<spring:url value="/resources/core/js/jquery.1.9.1.js" var="jqueryjs" />
	<script src="${jqueryjs}"></script>
	<spring:url value="/resources/core/js/jquery.url.js" var="urljs" />
	<script src="${urljs}"></script>
	<spring:url value="/resources/core/js/gt.js" var="gtjs" />
	<script src="${gtjs}"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script>
	    var handlerEmbed = function (captchaObj) {	
	        $("#embed-submit").click(function (e) {
	            var validate = captchaObj.getValidate();
	            if (!validate) {
	                $("#notice")[0].className = "show";
	                setTimeout(function () {
	                    $("#notice")[0].className = "hide";
	                }, 2000);
	                e.preventDefault();
	            }
	        });	
	        // 将验证码加到id为captcha的元素里
	        captchaObj.appendTo("#embed-captcha");
	        captchaObj.onReady(function () {
	            $("#wait")[0].className = "hide";
	        });	
	        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
	    };
	    $.ajax({
	        // 获取id，challenge，success（是否启用failback）
	        url: "StartCaptcha",
	        type: "get",
	        dataType: "json",
	        success: function (data) {
	            // 使用initGeetest接口
	            // 参数1：配置参数
	            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
	            initGeetest({
	                gt: data.gt,
	                challenge: data.challenge,
	                product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
	                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
	                lang: (typeof jQuery.url.param("lang") == 'undefined') ? 'en' : jQuery.url.param("lang")
	            }, handlerEmbed);
	        }
	    });
	</script>
</body>
</html>