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
            <small>消息通讯</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/system/feedback/home.do"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/system/feedback/list.do">发送信息</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">发送信息</div>
            <form id="editForm" action="${ctx}/system/chat/edit.do" method="post">
               <%-- <input type="hidden" id="chatId" name="chatId" value="${requestScope.chat.chatId}">--%>
                <input type="hidden" id="receive" name="receiveName">

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">收件人</div>
                    <div class="col-md-4 data">
                        <select class="form-control" name="receiveId" id="userInfo" onchange="document.getElementById('receive').value=this.options[this.selectedIndex].text">
                            <option value="">请选择</option>
                            <c:forEach items="${userList}" var="value">
                                <option value="${value.id}">
                                        ${value.userName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">主题</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="主题" name="subject" value="${requestScope.chat.subject}">
                    </div>
                </div>
                <div class="row data-type" style="margin: 0px" >

                    <div class="col-md-2 title">内容</div>
                    <div class="col-md-10 data  rowHeight2x">
                        <textarea  class="form-control" rows="3" placeholder="内容" name="content">${requestScope.chat.content}</textarea>
                    </div>
                </div>

            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">发送</button>
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