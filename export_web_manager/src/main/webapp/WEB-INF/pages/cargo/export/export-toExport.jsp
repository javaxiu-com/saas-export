<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>新增出口报运单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">新增出口报运单</li>
        </ol>
    </section>
    <!-- 正文区域 -->
    <section class="content">
        <form action="${ctx}/cargo/export/edit.do" method="post">
        <div class="panel panel-default">
            <div class="panel-heading">对【${id}】出口报运</div>
            <input type="text" name="contractIds" value="${id}">
            <div class="row data-type" style="margin: 0px">
                <div class="col-md-2 title">信用证号</div>
                <div class="col-md-4 data">
                    <input type="text" class="form-control" placeholder="信用证号" name="lcno" value="${export.lcno}"/>
                </div>

                <div class="col-md-2 title">收货人及地址</div>
                <div class="col-md-4 data">
                    <input type="text" class="form-control" placeholder="收货人及地址" name="consignee" value="${export.consignee}"/>
                </div>

                <div class="col-md-2 title">唛头</div>
                <div class="col-md-4 data">
                    <input type="text" class="form-control" placeholder="唛头" name="marks" value="${export.marks}"/>
                </div>

                <div class="col-md-2 title">装运港</div>
                <div class="col-md-4 data">
                    <input type="text" name="shipmentPort" class="form-control" placeholder="装运港"value="${export.shipmentPort}"/>
                </div>

                <div class="col-md-2 title">目的港</div>
                <div class="col-md-4 data">
                    <input type="text" name="destinationPort" class="form-control" placeholder="目的港" value="${export.destinationPort}"/>
                </div>

                <div class="col-md-2 title">运输方式</div>
                <div class="col-md-4 data">
                    <input type="text" name="transportMode" class="form-control" placeholder="运输方式" value="${export.transportMode}">
                </div>

                <div class="col-md-2 title">价格条件</div>
                <div class="col-md-4 data">
                    <input type="text" name="priceCondition" class="form-control" placeholder="价格条件" value="${export.priceCondition}"/>
                </div>

                <div class="col-md-2 title">总箱数</div>
                <div class="col-md-4 data">
                    <input type="text" name="boxNums" class="form-control" placeholder="总箱数" value="${export.boxNums}"/>
                </div>

                <div class="col-md-2 title">总毛重</div>
                <div class="col-md-4 data">
                    <input type="text" name="grossWeights" class="form-control" placeholder="总毛重"  value="${export.grossWeights}">

                </div>
                <div class="col-md-2 title">体积</div>
                <div class="col-md-4 data">
                    <input type="text" name="measurements" class="form-control" placeholder="体积" value="${export.measurements}">
                </div>

                <div class="col-md-2 title rowHeight2x">备注</div>
                <div class="col-md-10 data rowHeight2x">
                    <textarea class="form-control" rows="3" name="remark">${export.remark}</textarea>
                </div>
            </div>
        </div>
        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="submit"  class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        </form>
        <!--工具栏/-->
    </section>
</div>
<!-- 内容区域 /-->
</body>

</html>