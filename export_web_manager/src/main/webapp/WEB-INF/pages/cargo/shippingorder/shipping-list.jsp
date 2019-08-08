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
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/cargo/shipping/delete.do?shippingOrderId="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function submit() {
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/cargo/shipping/submit.do?shippingOrderId="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function cancel() {
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/cargo/shipping/cancel.do?shippingOrderId="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function exportE() {
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/cargo/export/exportE.do?id="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        货运管理
        <small>委托管理</small>
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
            <h3 class="box-title">委托单列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/cargo/shipping/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="提交" onclick='submit()'><i class="fa fa-file-o"></i> 提交</button>
                            <button type="button" class="btn btn-default" title="取消" onclick='cancel()'><i class="fa fa-file-o"></i> 取消</button>
                            <%--<button type="button" class="btn btn-default" title="电子报运" onclick="exportE()"><i class="fa fa-refresh"></i> 电子报运</button>--%>
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
                        <%--<td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>--%>
                        <th class="sorting"></th>
                        <th class="sorting">委托单号</th>
                        <th class="sorting">运输方式</th>
                        <th class="sorting">提货抬头</th>
                        <th class="sorting">装运港</th>
                        <th class="sorting">卸货港</th>
                        <th class="sorting">装期</th>
                        <th class="sorting">效期</th>
                        <th class="sorting">份数</th>
                        <th class="sorting">运费说明</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                            <td><input type="checkbox" name="id" value="${o.shippingOrderId}"/></td>
                            <td>${o.shippingOrderId}</td>
                            <td>${o.orderType}</td>
                            <td>${o.consignee}</td>
                            <td>${o.portOfLoading}</td>
                            <td>${o.portOfDischarge}</td>
                            <td><fmt:formatDate value="${o.loadingDate}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${o.limitDate}" pattern="yyyy-MM-dd"/></td>
                            <td>${o.copyNum}</td>
                            <td>${o.freight}</td>
                            <%--<td>${o.state}</td>--%>
                            <td>
                                <c:if test="${o.state==0}">草稿</c:if>
                                <c:if test="${o.state==1}"><font color="green">已上报</font></c:if>
                                <%--<c:if test="${o.state==2}"><font color="red">已报运</font></c:if>
                                <c:if test="${o.state==3}"><font color="purple">已发送</font></c:if>--%>
                            </td>
                            <td>
                                <a href="${ctx }/cargo/shipping/toView.do?shippingOrderId=${o.shippingOrderId}">[查看]</a>
                                <a href="${ctx }/cargo/shipping/toUpdate.do?shippingOrderId=${o.shippingOrderId}">[编辑]</a>
                                <c:if test="${o.state==1}">
                                    <a href="${ctx}/cargo/export/shipingPdf.do?shippingOrderId=${o.shippingOrderId}">[下载]</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- /.box-body -->

        <!-- .box-footer-->
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="cargo/contract/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
        <!-- /.box-footer-->


    </div>

</section>
</div>
</body>

</html>