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
    <!-- 页面meta /-->
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>出口报运单</small>
        </h1>
        <ol class="breadcrumb"></ol>
    </section>

    <!-- 正文区域 -->
    <section class="content">
        <div class="panel panel-default">
            <div class="panel-heading">报运单详情</div>

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">信用证号</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.lcno}
                    </div>

                    <div class="col-md-2 title">收货人及地址</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.consignee}
                    </div>

                    <div class="col-md-2 title">唛头</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.marks}
                    </div>

                    <div class="col-md-2 title">装运港</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.shipmentPort}
                    </div>

                    <div class="col-md-2 title">目的港</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.destinationPort}
                    </div>

                    <div class="col-md-2 title">运输方式</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.transportMode}
                    </div>

                    <div class="col-md-2 title">价格条件</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.priceCondition}
                    </div>

                    <div class="col-md-2 title">总箱数</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.boxNums}
                    </div>

                    <div class="col-md-2 title">总毛重</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.grossWeights}
                    </div>
                    <div class="col-md-2 title">体积</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${export.measurements}
                    </div>
                    <div class="col-md-2 title ">备注</div>
                    <div class="col-md-10 data " style="line-height:34px">
                        ${export.remark}
                    </div>
                </div>
        </div>
    </section>
</div>
<!-- 内容区域 /-->
</body>

</html>