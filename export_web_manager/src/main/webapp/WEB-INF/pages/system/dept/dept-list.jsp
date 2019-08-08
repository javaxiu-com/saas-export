<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    shiro提供了很多的jsp页面标签
        通过比较所有的权限以及当前指定的权限，控制按钮或菜单的展示
--%>
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
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/system/dept/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>部门管理</small>
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
            <h3 class="box-title">部门列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <shiro:hasPermission name="新增部门">
                                <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/system/dept/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="删除部门">
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            </shiro:hasPermission>
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
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">编号</th>
                        <th class="sorting">上级</th>
                        <th class="sorting">名称</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="dept"  varStatus="st">
                        <tr>
                            <td><input type="checkbox" name="id" value="${dept.id}"/></td>
                            <td>${st.count}</td>
                            <td>${dept.id}</td>
                            <td>${dept.parent.deptName}</td>
                            <td>
                                <shiro:lacksPermission name="修改部门">
                                    ${dept.deptName}
                                </shiro:lacksPermission>
                                <shrio:hasPermission name="修改部门">
                                    <a href="${ctx}/system/dept/toUpdate.do?id=${dept.id}">${dept.deptName }</a>
                                </shrio:hasPermission>


                            </td>
                            <th class="text-center">
                                <shiro:hasPermission name="修改部门">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/dept/toUpdate.do?id=${dept.id}"'>编辑</button>
                                </shiro:hasPermission>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/dept/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>