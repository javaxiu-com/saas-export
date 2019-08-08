<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp"%>
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
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/company/delete.do?id="+id;
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
            企业管理
            <small>企业列表</small>
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
                <h3 class="box-title">列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">

                    <!--工具栏-->
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/company/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
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
                    <!--工具栏/-->

                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <th class="" style="padding-right:0px;">

                            </th>
                            <th class="sorting">企业名称</th>
                            <th class="sorting">所在地</th>
                            <th class="sorting">地址</th>
                            <th class="sorting">企业法人</th>
                            <th class="sorting">联系方式</th>
                            <th class="sorting">所属行业</th>
                            <th class="sorting">状态</th>
                            <th class="sorting">余额</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="item">
                            <tr>
                                <td><input name="ids" value="${item.id}" type="checkbox"></td>
                                <td>
                                        ${item.name}
                                </td>
                                <td>${item.city}</td>
                                <td>${item.address}</td>
                                <td>${item.representative}</td>
                                <td>${item.phone}</td>
                                <td>${item.industry}</td>
                                <td>${item.state ==0?'未审核':'已审核'}</td>
                                <td class="text-center">${item.balance}</td>
                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/company/toUpdate.do?id=${item.id}"'>编辑</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->

            <%--<script>
                function goPage(page) {
                    $("#page").val(page);
                    $("#pageForm").submit();
                }
            </script>
            <div class="box-footer">
                <form action="/company/list.do" id="pageForm">
                    <input type="hidden" name="page" id="page" value="">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            总共${page.pages}页，共${page.total} 条数据。 每页
                            <select class="form-control"  onchange="goPage(1)" name="size">
                                <option ${page.pageSize==3?'selected':''}>3</option>
                                <option ${page.pageSize==10?'selected':''}>10</option>
                                <option ${page.pageSize==15?'selected':''}>15</option>
                                <option ${page.pageSize==20?'selected':''}>20</option>
                                <option ${page.pageSize==50?'selected':''}>50</option>
                            </select> 条
                        </div>
                    </div>

                    <div class="box-tools pull-right">
                        <ul class="pagination">
                            <li>
                                <a onclick="goPage(1)" href="#" aria-label="Previous">首页</a>
                            </li>
                            <li><a onclick="goPage(${page.prePage})" href="#">上一页</a></li>
                            <c:forEach begin="${page.navigateFirstPage}" end="${page.navigateLastPage}" var="num">
                                <li><a onclick="goPage(${num})" href="#">${num}</a></li>
                            </c:forEach>
                            <li><a onclick="goPage(${page.nextPage})" href="#">下一页</a></li>
                            <li>
                                <a href="#" onclick="goPage(${page.pages})" aria-label="Next">尾页</a>
                            </li>
                        </ul>
                    </div>
                </form>


            </div>--%>



                    <div class="box-footer">
                        <jsp:include page="../common/page.jsp">
                            <jsp:param value="${ctx}/company/list.do" name="pageUrl"/>
                        </jsp:include>
                    </div>
            <!-- /.box-footer-->

        </div>
    </section>
</div>
</body>

</html>