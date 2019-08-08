<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ include file="base.jsp"%>
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
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            500 错误页面
        </h1>

        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">500 错误</li>
        </ol>

    </section>

    <!-- Main content -->
    <section class="content">
        <div class="error-page">
            <h2 class="headline text-red"> 500</h2>

            <div class="error-content">
                <h3><i class="fa fa-warning text-red"></i> Oops! 页面程序有错误.</h3>

                <p>
                    你访问的页面发生错误, 你可以 <a href="all-admin-index.html">返回到后台首页</a> 或者
                </p>
                <p><a href="#" onclick="showDetail();">点击这里查看具体错误消息</a>,
                    <br/>
                </p>
            </div>
            <!-- /.error-content -->
        </div>
        <div class="box-body" id="detail_system_error_msg" style="display:none;text-align:left;padding-bottom:100px;">
            <br>
            <p>
            <%
                exception.printStackTrace(new java.io.PrintWriter(out));
            %>
            </p>
        </div>
        <script>
            function showDetail()
            {
                var elm = document.getElementById('detail_system_error_msg');
                if(elm.style.display == '') {
                    elm.style.display = 'none';
                }else {
                    elm.style.display = '';
                }
            }
        </script>
        <!-- /.error-page -->
    </section>
</div>
<!-- 内容区域 /-->
</body>

</html>