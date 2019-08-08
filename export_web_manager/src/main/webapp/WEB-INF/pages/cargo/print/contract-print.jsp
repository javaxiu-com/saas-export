<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<section class="content-header">
    <h1>
        货运管理
        <small>购销合同月统计</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">
    <div class="box box-primary" style="height: 200px;">
        <div class="box-header with-border">
            <h3 class="box-title">购销合同月统计（出货表）</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form role="form" action="${ctx}/cargo/contract/printExcelTemplate.do">
            <div class="input-group input-group-sm" >
                <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                </div>
                <input type="text" name="inputDate" class="form-control pull-right" id="datepicker">
                <span class="input-group-btn">
                    <button type="submit"   class="btn btn-info btn-flat">submit</button>
                </span>
            </div>
        </form>
    </div>
</section>
</div>
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#datepicker').datepicker({
        language: "zh-CN",
        autoclose: true,
        format: 'yyyy-mm',
        startView: 'months', //开始视图层，为月视图层
        maxViewMode:'years', //最大视图层，为年视图层
        minViewMode:'months', //最小视图层，为月视图层
    });
</script>

</html>