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
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/basics/factory/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }


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
        基础管理
        <small>厂家信息</small>
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
            <h3 class="box-title">厂家信息列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/basics/factory/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="查看" onclick='view()'><i class="fa  fa-eye-slash"></i> 查看</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
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
                        <th class="" style="padding-right:0px;">

                        </th>
                        <th class="sorting">厂家类型</th>
                        <th class="sorting">厂家全称</th>
                        <th class="sorting">厂家简称</th>
                        <th class="sorting">联系人</th>
                        <th class="sorting">电话</th>
                        <th class="sorting">手机</th>
                        <%--<th class="sorting">传真</th>--%>
                        <th class="sorting">地址</th>
                        <th class="sorting">验货员</th>
                        <th class="sorting">说明</th>
                        <th class="sorting">排序号</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="o" varStatus="status">
                        <tr>
                            <td><input type="checkbox" name="id" value="${o.id}"/></td>
                            <td>${o.ctype}</td>
                            <td>${o.fullName}</td>
                            <td>${o.factoryName}</td>
                            <td>${o.contacts}</td>
                            <td>${o.phone}</td>
                            <td>${o.mobile}</td>
                            <%--<td>${o.fax}</td>--%>
                            <td>${o.address}</td>
                            <td>${o.inspector}</td>
                            <td>${o.remark}</td>
                            <td>${o.orderNo}</td>
                            <%--<td><a href="${ctx}/basics/factory/toView.do?id=${o.id}">${o.contractNo}</a></td>--%>
                            <%--<td>
                                ${o.proNum}/${o.extNum}
                            </td>--%>
                            <%--<td><fmt:formatDate value="${o.deliveryPeriod}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${o.shipTime}" pattern="yyyy-MM-dd"/></td>--%>

                            <td><c:if test="${o.state==0}">停用</c:if>
                                <c:if test="${o.state==1}"><font color="green">正常</font></c:if>
                                <%--<c:if test="${o.state==2}"><font color="red">已报运</font></c:if>--%>
                            </td>
                            <td>
                                <a href="${ctx }/basics/factory/toView.do?id=${o.id}">[查看详情]</a>
                                <a href="${ctx }/basics/factory/toUpdate.do?id=${o.id}">[编辑]</a>
                                <%--<a href="${ctx }/cargo/contractProduct/list.do?contractId=${o.id}">[货物]</a>
                                <a href="${ctx }/cargo/contractProduct/toImport.do?contractId=${o.id}">[上传货物]</a>--%>
                            </td>
                        </tr>
                    </c:forEach>
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
                <!--数据列表/-->

                <!--工具栏/-->

            </div>
            <!-- 数据表格 /-->


        </div>
        <!-- /.box-body -->

        <!-- .box-footer-->
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/cargo/contract/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
        <!-- /.box-footer-->


    </div>

</section>
</div>
</body>

</html>