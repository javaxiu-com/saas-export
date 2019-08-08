<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>角色管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">编辑角色</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/system/role/edit.do" method="post">
                            <input type="hidden" name="id" value="${role.id}">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">角色名</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="角色名" name="name" value="${role.name}">
                                    </div>
                                    <div class="col-md-2 title">备注</div>
                                    <div class="col-md-10 data line-height36">
                                        <input type="text" class="form-control" placeholder="备注" name="remark" value="${role.remark}">
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>

</html>