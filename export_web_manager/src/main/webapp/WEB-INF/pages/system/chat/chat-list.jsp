<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                location.href="${ctx}/system/chat/delete.do?id="+id;
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
        <small>消息通讯</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="${ctx}/system/feedback/home.do"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">收件箱</h3>
        </div>
        <div class="box-body">
            <div class="table-box">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="发送" onclick='location.href="${ctx}/system/chat/toAdd.do"'><i class="fa fa-file-o"></i>发送消息</button>
                            <button type="button" class="btn btn-default" title="已发送" onclick='location.href="${ctx}/system/chat/sentList.do"'><i class="fa fa-file-o"></i>已发送</button>
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
                        <th class="sorting">状态</th>
                        <th class="sorting">发件人</th>
                        <th class="sorting">标题</th>
                        <th class="sorting">内容</th>
                        <th class="sorting">部门</th>
                        <th class="sorting">发送时间</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="item" varStatus="status">
                    <tr>

                        <td><input name="ids" value="${item.chatId}" type="checkbox"></td>
                        <td>${status.index+1}</td>
                        <c:if test="${item.state=='0'}">
                            <td>未读</td>
                        </c:if>
                        <c:if test="${item.state=='1'}">
                            <td>已读</td>
                        </c:if>
                        <td>${item.sendName}</td>
                        <td><a href="${ctx}/system/chat/toView.do?id=${item.chatId}">${item.subject}</a></td>
                        <td>${item.content }</td>
                        <td>${item.sendDept}</td>
                        <td><fmt:formatDate value="${item.sendTime}" pattern="yyyy-MM-dd"/></td>
                        <th class="text-center">
                                <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/chat/toView.do?id=${item.chatId}"'>查看</button>

                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/chat/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>