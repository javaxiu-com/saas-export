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
            <small>反馈详情</small>
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
                <input type="hidden" id="feedbackId" name="feedbackId" value="${requestScope.feedback.feedbackId}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">反馈标题</div>
                    <div class="col-md-4 data">
                        ${requestScope.feedback.title}
                    </div>

                    <div class="col-md-2 title">反馈类型</div>
                    <div class="col-md-4 data">
                            ${requestScope.feedback.classType == '1' ?'管理':''}
                            ${requestScope.feedback.classType == '2' ?'安全':''}
                            ${requestScope.feedback.classType == '3' ?'建议':''}
                            ${requestScope.feedback.classType == '4' ?'其他':''}
                    </div>


                    <div class="col-md-2 title">联系人</div>
                    <div class="col-md-4 data">
                        ${requestScope.feedback.inputBy}
                    </div>

                    <div class="col-md-2 title">联系方式</div>
                    <div class="col-md-4 data">
                        ${requestScope.feedback.tel}
                    </div>


                    <div class="col-md-2 title">难度</div>
                    <div class="col-md-4 data">
                        ${requestScope.feedback.difficulty == '1' ?'极难':''}
                        ${requestScope.feedback.difficulty == '2' ?'比较难':''}
                        ${requestScope.feedback.difficulty == '3' ?'有难度':''}
                        ${requestScope.feedback.difficulty == '4' ?'一般':''}
                    </div>

                    <div class="col-md-2 title">是否公开</div>
                    <div class="col-md-4 data">
                        ${requestScope.feedback.isShare == '1' ?'公开':''}
                        ${requestScope.feedback.isShare == '0' ?'不公开':''}
                    </div>

                    <div class="col-md-2 title rowHeight2x">反馈内容</div>
                    <div class="col-md-4 data  rowHeight2x">
                        ${requestScope.feedback.content}
                    </div>

                    <div class="col-md-2 title rowHeight2x">反馈日期</div>
                    <div class="col-md-4 data rowHeight2x">
                        <fmt:formatDate value="${requestScope.feedback.inputTime}" pattern="yyyy-MM-dd"/>
                    </div>

                    <div class="col-md-2 title">处理人</div>
                    <div class="col-md-4 data">
                       ${requestScope.feedback.answerBy}
                    </div>

                    <div class="col-md-2 title">解决</div>
                    <div class="col-md-4 data">
                        ${requestScope.feedback.resolution == '1' ?'已修改':''}
                        ${requestScope.feedback.resolution == '2' ?'无须修改':''}
                        ${requestScope.feedback.resolution == '3' ?'重复问题':''}
                        ${requestScope.feedback.resolution == '4' ?'描述不完整':''}
                        ${requestScope.feedback.resolution == '5' ?'无法再现':''}
                        ${requestScope.feedback.resolution == '6' ?'其他':''}
                    </div>

                    <div class="col-md-2 title rowHeight2x">处理方式</div>
                    <div class="col-md-4 data  rowHeight2x">
                        ${requestScope.feedback.solveMethod}
                    </div>

                    <div class="col-md-2 title rowHeight2x">解决日期</div>
                    <div class="col-md-4 data rowHeight2x">
                        <fmt:formatDate value="${requestScope.feedback.answerTime}" pattern="yyyy-MM-dd"/>
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
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