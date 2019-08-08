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
            系统管理
            <small>新增反馈</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/system/feedback/list.do">反馈列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">反馈信息</div>
            <form id="editForm" action="${ctx}/system/feedback/edit.do" method="post">
                <input type="hidden" id="feedbackId" name="id" value="${requestScope.feedback.feedbackId}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">反馈标题</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="反馈标题" name="title" value="${requestScope.feedback.title}">
                    </div>

                    <div class="col-md-2 title">反馈类型</div>
                    <div class="col-md-4 data">
                        <select class="form-control"  name="classType">
                            <option value="">请选择</option>
                            <option value="1">管理</option>
                            <option value="2">安全</option>
                            <option value="3">建议</option>
                            <option value="4">其他</option>
                        </select>
                    </div>


                    <div class="col-md-2 title">联系人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="联系人" name="inputBy" value="${requestScope.feedback.inputBy}">
                    </div>

                    <div class="col-md-2 title">联系方式</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="联系方式" name="tel" value="${requestScope.feedback.tel}">
                    </div>


                    <div class="col-md-2 title">难度</div>
                    <div class="col-md-4 data">
                        <select class="form-control"  name="difficulty">
                            <option value="">请选择</option>
                            <option value="1">极难</option>
                            <option value="2">比较难</option>
                            <option value="3">有难度</option>
                            <option value="4">一般</option>
                        </select>
                    </div>

                    <div class="col-md-2 title">是否公开</div>
                    <div class="col-md-4 data">
                        <select class="form-control"  name="isShare">
                            <option value="">请选择</option>
                            <option value="1">公开</option>
                            <option value="0">不公开</option>
                        </select>
                    </div>

                    <div class="col-md-2 title rowHeight2x">反馈内容</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="反馈内容" name="content">${requestScope.feedback.content}</textarea>
                    </div>

                    <div class="col-md-2 title rowHeight2x">反馈日期</div>
                    <div class="col-md-4 data rowHeight2x">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="反馈日期"  name="inputTime" class="form-control pull-right"
                                   value="<fmt:formatDate value="${requestScope.feedback.inputTime}" pattern="yyyy-MM-dd"/>" id="inputTime">
                        </div>
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
    $('#inputTime').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>