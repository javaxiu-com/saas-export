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
<script>
    function view() {
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/cargo/contract/toView.do?id="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }


</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        货运管理
        <small>购销合同</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">购销合同列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="查看" onclick='view()'><i class="fa  fa-eye-slash"></i> 查看</button>
                            <button type="button" class="btn btn-default" title="打印" onclick='deleteById()'><i class="fa fa-print"></i> 打印</button>
                            <button type="button" class="btn btn-default" title="报运" onclick="document.getElementById('exportForm').submit()"><i class="fa fa-refresh"></i> 报运</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;"></th>
                        <th class="sorting">客户名称</th>
                        <th class="sorting">合同号</th>
                        <th class="sorting">货物/附件</th>
                        <th class="sorting">制单人</th>
                        <th class="sorting">验货员</th>
                        <th class="sorting">签单日期</th>
                        <th class="sorting">交货期限</th>
                        <th class="sorting">船期</th>
                        <th class="sorting">贸易条款</th>
                        <th class="sorting">总金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <form id="exportForm" action="${ctx}/cargo/export/toExport.do" method="post">
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr>
                                <td><input type="checkbox" name="id" value="${o.id}"/></td>
                                <td>${o.customName}</td>
                                <td><a href="${ctx}/cargo/contract/toView.do?id=${o.id}">${o.contractNo}</a></td>
                                <td>
                                    ${o.proNum}/${o.extNum}
                                </td>
                                <td>${o.inputBy}</td>
                                <td>${o.inspector}</td>
                                <td><fmt:formatDate value="${o.signingDate}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${o.deliveryPeriod}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${o.shipTime}" pattern="yyyy-MM-dd"/></td>
                                <td>${o.tradeTerms}</td>
                                <td>${o.totalAmount}</td>
                            </tr>
                        </c:forEach>
                    </form>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- /.box-body -->

        <!-- .box-footer-->
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/cargo/export/contractList.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>

</html>