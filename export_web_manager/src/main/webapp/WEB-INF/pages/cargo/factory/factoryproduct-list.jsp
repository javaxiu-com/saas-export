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
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            厂家管理
            <small>厂家的货物详情</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">基础信息</a></li>
            <li class="active">厂家管理</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">厂家信息</div>
            <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                <thead>
                <tr>
                    <th class="" style="padding-right:0px;">
                    </th>
                    <th class="sorting">厂家类型</th>
                    <th class="sorting">厂家全称</th>
                    <th class="sorting">厂家简称</th>
                    <th class="sorting">联系人</th>
                    <th class="sorting">电话</th>
                    <th class="sorting">手机</th>
                    <th class="sorting">传真</th>
                    <th class="sorting">地址</th>
                    <th class="sorting">验货员，杰信代表</th>
                    <th class="sorting">说明</th>
                    <th class="sorting">排序号</th>
                    <th class="sorting">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                <%--<c:forEach items="${factory}" var="o" varStatus="status">--%>
                    <tr>
                        <td><input type="checkbox" name="id" value="${o.id}"/></td>
                        <td>${o.ctype}</td>
                        <td>${o.fullName}</td>
                        <td>${o.factoryName}</td>
                        <td>${o.contacts}</td>
                        <td>${o.phone}</td>
                        <td>${o.mobile}</td>
                        <td>${o.fax}</td>
                        <td>${o.address}</td>
                        <td>${o.inspector}</td>
                        <td>${o.remark}</td>
                        <td>${o.orderNo}</td>
                        <td><c:if test="${o.state==0}">停用</c:if>
                            <c:if test="${o.state==1}"><font color="green">正常</font></c:if>
                        </td>
                        <
                    </tr>
                <%--</c:forEach>--%>
                </tbody>
                <!--
            <tfoot>
            <tr>
            <th>Rendering engine</th>
            <th>Browser</th>
            <th>Platform(s)</th>
            <th>Engine version</th>
            <th>CSS grade</th>
            </tr>
            </tfoot>-->
            </table>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <%--<button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>--%>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">该厂家对应的货物列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table id="dataList1" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <th class="" style="padding-right:0px;">

                            </th>
                            <th class="sorting">ID</th>
                            <th class="sorting">编号</th>
                            <th class="sorting">厂家简称</th>
                            <th class="sorting">市场价</th>
                            <th class="sorting">数量</th>
                            <th class="sorting">创建人</th>
                            <th class="sorting">创建日期</th>
                            <th class="sorting">描述</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="item">
                            <tr>
                                <td><input name="ids" value="${item.id}" type="checkbox"></td>
                                    <%--<td>${status.index+1}</td>--%>
                                <td><a href="${ctx}/system/user/toUpdate.do?id=${o.id}">${item.id}</a></td>
                                <td>${item.productNo }</td>
                                    <%--<td>${item.description }</td>--%>
                                <td>${item.factoryName }</td>
                                <td>${item.price }</td>
                                <td>${item.qty}</td>
                                <td>${item.createBy }</td>
                                <td>${item.createTime}</td>
                                <td>${item.description}</td>
                                <th class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/message/product/toUpdate.do?id=${item.id}"'>编辑</button>
                                </th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--数据列表/-->
                    <!--工具栏/-->
                </div>
                <!-- 数据表格 /-->
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="${ctx}/cargo/contractProduct/list.do?contractId=${contractId}" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->
        </div>

    </section>

</div>
<!-- 内容区域 /-->
</body>

</html>