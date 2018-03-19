<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/lte/template"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/lte/sales"%>
<template:page>
	<jsp:attribute name="pageScripts">
	</jsp:attribute>
	<jsp:body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <spring:message code="lte.firstpage"></spring:message>
      </h1>
    </section>
    
    <!-- Main content -->
    <section class="content">
    <div class="row">
      <div class="box box-widget">
      <div class="box-header with-border">
       <h3 class="box-title">订单状态解释</h3>
      </div>
      <div class="box-body table-responsive no-padding">
        <table class="table table-hover">
          <tbody>
          <tr>
           <th>
             状态
           </th>
           <th>
             状态解释
           </th>
           <th>
              操作结点
           </th>
          </tr>
          <tr>
            <td>
             新建
            </td>
            <td>
            当销售员下达订单之后的订单状态,该状态表明该订单未新建,尚未提交设计师审批,设计师看不到此类订单,该订单状态下销售员可以修改订单内容
            </td>
            <td>
            通过新建销售订单功能下达的销售订单均为此状态
            </td>
          </tr>
          <tr>
            <td>
              门店确认
            </td>
            <td>
              销售通过列表确认订单,经过确认的订单,销售员不可以再进行任何修改,包括其后的量身单
            </td>
            <td>
              通过销售订单页门店确认按钮设置销售订单及下属量身单为此状态,设置为此状态后,门店不可修改销售订单内容,但是考虑到收尾款的问题如果订单是当前账户创建的可以增加付款记录同时忽略订单状态,
            </td>
          </tr>
          <tr>
            <td>
            财务审批通过
            </td>
            <td>
            财务会基于销售订单进行财务审批,财务审批通过之后,量身单会销售订单状态都会设置为财务审批通过,之后裁床师傅会基于财务审批通过的量身单去进行审批,如果财务审批不通过,需要由谁来进行审批?
            </td>
            <td>
            通过销售订单上的财务审批按钮进行设置,只有财务和管理员可以进行设置
            </td>
          </tr>
          <tr>
            <td>
            工厂排产
            </td>
            <td>
            裁床师傅对量身单进行审批修改备注,对审批修改备注完善,表明工厂确认开始生产,这个状态之后,文员会将量身单打印进行生产
            </td>
            <td>
            裁床师傅对所有量身单审批通过后,销售订单状态自动变更
            </td>
          </tr>
          <tr>
            <td>
            工厂发货
            </td>
            <td>
            仓库在衣服生产完毕之后,仓管人员使用扫描枪扫衣服上的条形码,扫描成功,系统将量身单更改成仓库出货状态,系统在屏幕上自动变更,当某张销售订单下的所有量身单状态变化之后,当该订单下的所有量身单都出货之后,销售订单自动变成出货状态
            </td>
            <td>
            扫码功能,扫描所有的量身单后,将量身单和销售订单状态统一设置为工厂发货
            </td>
          </tr>
          <tr>
            <td>
            门店收货
            </td>
            <td>
            店员对衣服进行扫码,当该销售订单下的所有量身单变为门店收货状态之后,店员手工确认,整张单据变为收货状态
            </td>
            <td>
            扫码功能,扫描所有的量身单后,将量身单和销售订单状态统一设置为门店收货
            </td>
          </tr>
          <tr>
            <td>
           取件成功
            </td>
            <td>
            店员针对销售订单进行确认客户取件,确认成功之后,系统每隔几分钟发送售后短信给顾客
            </td>
            <td>
            扫码功能,扫描所有的量身单后,将量身单和销售订单状态统一设置为顾客取件
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      </div>
    </div>
    <div class="row">
      <div class="box box-widget">
      <div class="box-header with-border">
       <h3 class="box-title">流程图</h3>
      </div>
      <div class="box-body">
      <img class="img-responsive pad" src="${contextPath}/_ui/images/bf.png"></img>
      </div>
      </div>
    </div>
        
        
        
    </section>
    
  </jsp:body>
</template:page>