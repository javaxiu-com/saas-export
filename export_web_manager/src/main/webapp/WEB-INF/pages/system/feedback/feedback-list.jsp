<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                location.href="${ctx}/system/feedback/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
    function updateById(){
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/system/feedback/toUpdate.do?id="+id;
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
        <small>意见反馈</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${ctx}/system/feedback/home.do"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">反馈列表</h3>
        </div>
        <div class="box-body">
            <div class="table-box">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/system/feedback/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="编辑" onclick='updateById()'><i class="fa fa-file-o"></i> 编辑</button>
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
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">

                        </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">标题</th>
                        <th class="sorting">内容</th>
                        <th class="sorting">类别</th>
                        <th class="sorting">发布者</th>
                        <th class="sorting">电话</th>
                        <th class="sorting">难度</th>
                        <th class="sorting">是否公开</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="item" varStatus="status">
                    <tr>
                        <td><input name="ids" value="${item.feedbackId}" type="checkbox"></td>
                        <td>${status.index+1}</td>
                        <td><a href="${ctx}/system/feedback/toView.do?id=${item.feedbackId}">${item.title}</a></td>
                        <td>${item.content }</td>
                        <td>${item.classType }</td>
                        <td>${item.inputBy}</td>
                        <td>${item.tel }</td>
                        <td>${item.difficulty }</td>
                        <td>${item.isShare=='0'?'不公开':'公开'}</td>
                        <c:if test="${item.state=='0'}">
                            <td>未处理</td>
                        </c:if>
                        <c:if test="${item.state=='1'}">
                            <td>已处理</td>
                        </c:if>
                        <%--<c:if test="${item.state=='2'}">
                            <td>待处理</td>
                        </c:if>--%>
                        <%--<td>${item.state=='0'?'未处理':'已处理'}</td>--%>
                        <th class="text-center">
                            <%--<c:if test="${item.state=='0'}">--%>
                                <%--<button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/feedback/toUpdate.do?id=${item.feedbackId}"'>编辑</button>--%>
                            <%--</c:if>--%>
                            <c:if test="${loginUser.degree!=0}">
                                <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/feedback/toView.do?id=${item.feedbackId}"'>查看</button>
                            </c:if>
                            <c:if test="${loginUser.degree==0}">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/feedback/toProcess.do?id=${item.feedbackId}"'>处理</button>
                            </c:if>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/feedback/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>