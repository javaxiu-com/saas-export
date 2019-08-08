<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            订单管理
            <small>订单表单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">订单管理</a></li>
            <li class="active">订单表单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">购销合同信息</div>
            <form id="editForm" action="${ctx}/cargo/contract/edit.do" method="post">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">收购方</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="收购方" name="offeror" value="${contract.offeror}">
                    </div>

                    <div class="col-md-2 title">合同号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="合同号" name="contractNo" value="${contract.contractNo}">
                    </div>

                    <div class="col-md-2 title">签单日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="签单日期"  name="signingDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${contract.signingDate}" pattern="yyyy-MM-dd"/>" id="signingDate">
                        </div>
                    </div>

                    <div class="col-md-2 title">制单人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="制单人" name="inputBy" value="${contract.inputBy}">
                    </div>

                    <div class="col-md-2 title">审单人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="审单人" name="checkBy" value="${contract.checkBy}">
                    </div>

                    <div class="col-md-2 title">验货员</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="验货员" name="inspector" value="${contract.inspector}">
                    </div>

                    <div class="col-md-2 title">要求</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="要求" name="crequest" value="${contract.crequest}">
                    </div>
                    <div class="col-md-2 title">客户名称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="客户名称" name="customName" value="${contract.customName}">
                    </div>

                    <div class="col-md-2 title">船期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="船期"  name="shipTime" class="form-control pull-right"
                                   value="<fmt:formatDate value="${contract.shipTime}" pattern="yyyy-MM-dd"/>" id="shipTime">
                        </div>
                    </div>

                    <div class="col-md-2 title">重要程度</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${contract.importNum==3?'checked':''} name="importNum" value="3">★★★</label></div>
                            <div class="radio"><label><input type="radio" ${contract.importNum==2?'checked':''} name="importNum" value="2">★★</label></div>
                            <div class="radio"><label><input type="radio" ${contract.importNum==1?'checked':''} name="importNum" value="1">★</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">交货期限</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="交货期限"  name="deliveryPeriod" class="form-control pull-right"
                                   value="<fmt:formatDate value="${contract.deliveryPeriod}" pattern="yyyy-MM-dd"/>" id="deliveryPeriod">
                        </div>
                    </div>

                    <div class="col-md-2 title">贸易条款</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="贸易条款" name="tradeTerms" value="${contract.tradeTerms}">
                    </div>

                    <div class="col-md-2 title">打印版式</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="打印版式" name="printStyle" value="${contract.printStyle}">
                    </div>
                    <div class="col-md-2 title">总金额</div>
                    <div class="col-md-4 data">
                        <input type="text" disabled class="form-control" placeholder="0" name="totalAmount" value="${contract.totalAmount}">
                    </div>
                    <div class="col-md-2 title rowHeight2x">备注</div>
                    <div class="col-md-10 data rowHeight2x">
                        <textarea class="form-control" rows="3" name="remark">${contract.remark}</textarea>
                    </div>
                </div>
          </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#signingDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#deliveryPeriod').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#shipTime').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>