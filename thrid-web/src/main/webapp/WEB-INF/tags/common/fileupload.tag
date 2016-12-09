<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>

<div class="easyui-window" id="upload-file-window"
    data-options="onBeforeClose:ACC.uploadfile.reset"
    title='<spring:message code="file.fileupload"/>'  modal="true" style="width:400px;height:160px;padding:2px;" closed="true">
    <form id="uploadFileForm" method="post" enctype="multipart/form-data">
        <input id="extraParameter" name="key" type="hidden"></input>
        <table style="margin:5px;height:70px;">
            <tr>
                <td><spring:message code="file.selectfile"></spring:message></td>
                <td width="5px;"></td>
                <td><input class="easyui-filebox" id="fileUpload" name="fileUpload" style="width:260px;"></td>
                <td></td></tr>
            <tr>
                <td colspan="4"><label id="fileName" /></td>
            </tr>
            <tr>
                <td colspan="4">
                    <label id="uploadInfo" />
                </td>
            </tr>
        </table><div style="text-align:center;clear:both;margin:5px;">
            <a id="uploadFile" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="ACC.uploadfile.upload()"  href="javascript:void(0)">
              <spring:message code="file.upload"/>
            </a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="ACC.uploadfile.close()" href="javascript:void(0)">
              <spring:message code="file.close"/>
            </a>
        </div>
    </form>
</div>