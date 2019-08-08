<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp" %>
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
            财务管理
            <small>财务报运单列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货物管理</a></li>
            <li class="active">财务管理</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">修改财务报运单</div>
            <%--
            --%>
            <form id="editForm" action="${ctx}/cargo/finance/edit.do" method="post">
                <input type="hidden" name="financeId" value="${finance.financeId}" >
                <div class="row data-type" style="margin: 0px">

                    <div class="col-md-2 title">制单日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="制单日期" name="inputDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${finance.inputDate}" pattern="yyyy-MM-dd"/>"
                                   id="inputDate"/>
                        </div>
                    </div>

                    <div class="col-md-2 title">制单人：</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="制单人" name="inputBy" value="${finance.inputBy}">
                    </div>

                    <div class="col-md-2 title">创建人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建人" name="createBy" value="${finance.createBy}">
                    </div>

                    <div class="col-md-2 title">创建部门</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建部门" name="createDept" value="${finance.createDept}">
                    </div>

                    <div class="col-md-2 title">创建日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="创建日期" name="createTime" class="form-control pull-right"
                                   value="<fmt:formatDate value="${finance.createTime}" pattern="yyyy-MM-dd"/>"
                                   id="createTime"/>
                        </div>
                    </div>

                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存
            </button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

    <script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <link rel="stylesheet" href="../../css/style.css">
    <script>
        $('#inputDate').datepicker({
            autoclose: true,
            format: 'yyyy-mm-dd'
        });
        $('#createTime').datepicker({
            autoclose: true,
            format: 'yyyy-mm-dd'
        });
    </script>
</div>
<!-- 内容区域 /-->
</body>

</html>