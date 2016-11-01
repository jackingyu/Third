<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <template:css/>
    <script type="text/javascript" src="/thrid-web/_ui/js/jquery.min.js"></script>
</head>
<body class="easyui-layout" class="layout"  fit="true"   scroll="no">
<noscript>
<div class="loading-tips">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div>
</noscript>

<div id="loading-mask" class="loading-mask">
<div id="pageloading" class="loading-page"> 
    <img src="images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
</div>

    <div region="north" split="true" border="false" class="layout-north">
        <span style="float:right; padding-right:20px;" class="head">welcome! <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
        <span class="north-title"><img src="images/blocks.gif" width="20" height="20" align="absmiddle" /> Blaimar</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer"></div>
    </div>
    <div region="west" split="true"  title="<spring:message code="nav.tree"/>" style="width:180px;" id="west">
			<div id="nav">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
				
			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
<!--     <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" -->
<!--         maximizable="false" icon="menu-icon-save"  style="width: 300px; height: 150px; padding: 5px; -->
<!--         background: #fafafa;"> -->
<!--         <div class="easyui-layout" fit="true"> -->
<!--             <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;"> -->
<!--                 <table cellpadding=3> -->
<!--                     <tr> -->
<!--                         <td>新密码：</td> -->
<!--                         <td><input id="txtNewPass" type="password" class="txt01" /></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td>确认密码：</td> -->
<!--                         <td><input id="txtRePass" type="password" class="txt01" /></td> -->
<!--                     </tr> -->
<!--                 </table> -->
<!--             </div> -->
<!--             <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;"> -->
<!--                 <a id="btnEp" class="easyui-linkbutton" icon="menu-icon-ok" href="javascript:void(0)" > -->
<!--                     确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
 
    <template:js/>
   
    <jsp:include page="${jucierTplBasePath}/nav/menu.jsp" />
</body>
</html>