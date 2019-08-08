<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>

<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>系统日志</small>
    </h1>
</section>

<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">日志列表</h3>
        </div>

        <div class="box-body">
            <div class="table-box">
                <div class="pull-left">
                    <div class="form-group form-inline"></div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="sorting">序号</th>
                        <th class="sorting">用户名</th>
                        <th class="sorting">ip</th>
                        <th class="sorting">方法</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="log"  varStatus="st">
                        <tr>
                            <td>${st.count }</td>
                            <td>${log.userName }</td>
                            <td>${log.ip}</td>
                            <td>${log.action}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/log/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>