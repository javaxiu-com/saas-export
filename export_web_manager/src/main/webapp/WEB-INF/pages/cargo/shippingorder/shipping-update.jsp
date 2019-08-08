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
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>更新委托单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">更新委托单</li>
        </ol>
    </section>
    <!-- 内容头部 type="hidden"/-->
    <form id="editForm" action="${ctx}/cargo/shipping/edit.do" method="post">
        <input    name="装箱单id" value="${shippingOrder.shippingOrderId}" >
        <input    name="shippingOrderId" value="${shippingOrder.shippingOrderId}" >
        <input    name="不知道contractNo" value="${shippingOrder.shippingOrderId}">
    <!-- 正文区域 -->
    <section class="content">
        <div class="panel panel-default">
            <div class="panel-heading">修改委托单</div>

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">运输方式</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <%--<div class="radio"><label><input type="radio" ${shippingOrder.packingUnit=="PCS"?'checked':''} name="orderType" value="SEA">PCS</label></div>--%>
                            <div class="radio"><label><input type="radio" ${shippingOrder.orderType=="SEA"?'checked':''} name="orderType" value="SEA">SEA</label></div>
                            <div class="radio"><label><input type="radio" ${shippingOrder.orderType=="AIR"?'checked':''} name="orderType" value="AIR">AIR</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">货主</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="货主" name="shipper" value="${shippingOrder.shipper}">
                    </div>
                    <div class="col-md-2 title">提货抬头</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="提货抬头" name="consignee" value="${shippingOrder.consignee}">
                    </div>

                    <div class="col-md-2 title">正本通知人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="正本通知人" name="notifyParty" value="${shippingOrder.notifyParty}">
                    </div>
                    <div class="col-md-2 title">信用证号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="信用证号" name="lcNo" value="${shippingOrder.lcNo}"/>
                    </div>
                    <div class="col-md-2 title">装运港</div>
                    <div class="col-md-4 data">
                        <input type="text" name="portOfLoading" class="form-control" placeholder="装运港"value="${shippingOrder.portOfLoading}"/>
                    </div>
                    <div class="col-md-2 title">转船港</div>
                    <div class="col-md-4 data">
                        <input type="text" name="portOfTrans" class="form-control" placeholder="转船港" value="${shippingOrder.portOfTrans}"/>
                    </div>

                    <div class="col-md-2 title">卸货港</div>
                    <div class="col-md-4 data">
                        <input type="text" name="portOfDischarge" class="form-control" placeholder="卸货港" value="${shippingOrder.portOfDischarge}"/>
                    </div>
                    <div class="col-md-2 title">装运期限</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="装运期限"  name="loadingDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${shippingOrder.loadingDate}" pattern="yyyy-MM-dd"/>" id="loadingDate">
                        </div>
                    </div>
                    <div class="col-md-2 title">有效期限</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="有效期限"  name="limitDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${shippingOrder.limitDate}" pattern="yyyy-MM-dd"/>" id="limitDate">
                        </div>
                    </div>
                    <div class="col-md-2 title">是否分批</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${shippingOrder.isBatch==1?'checked':''} name="isBatch" value="1">是</label></div>
                            <div class="radio"><label><input type="radio" ${shippingOrder.isBatch==0?'checked':''} name="isBatch" value="0">否</label></div>
                        </div>
                    </div>
                    <div class="col-md-2 title">是否转船</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${shippingOrder.isTrans==1?'checked':''} name="isTrans" value="1">是</label></div>
                            <div class="radio"><label><input type="radio" ${shippingOrder.isTrans==0?'checked':''} name="isTrans" value="0">否</label></div>
                        </div>
                    </div>


                    <div class="col-md-2 title">份数</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="份数" name="copyNum" value="${shippingOrder.copyNum}">
                    </div>

                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" checked  ${shippingOrder.state==0?'checked':''} name="state" value="0">草稿</label></div>
                            <div class="radio"><label><input type="radio" disabled ${shippingOrder.state==1?'checked':''} name="state" value="1">已上报</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title rowHeight2x">提要说明</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="提要说明" name="remark">${shippingOrder.remark}</textarea>
                    </div>

                    <div class="col-md-2 title">运输要求</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="运输要求" name="specialCondition" value="${shippingOrder.specialCondition}">
                    </div>

                    <div class="col-md-2 title">运费说明</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="运费说明" name="freight" value="${shippingOrder.freight}">
                    </div>
                </div>

        </div>


        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">装箱单列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table class="table table-bordered table-striped table-hover dataTable" id="mRecordTable">
                        <tr>
                            <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                            <%--<td class="tableHeader">序号</td>--%>
                            <td class="tableHeader">卖方</td>
                            <td class="tableHeader">买方</td>
                            <td class="tableHeader">发票号</td>
                            <td class="tableHeader">发票日期</td>
                            <td class="tableHeader">唛头</td>
                            <td class="tableHeader">描述</td>
                            <td class="tableHeader">状态</td>
                            <td class="tableHeader">操作</td>
                        </tr>
                        <%--<c:forEach items="${page}" var="o" varStatus="status">--%>
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="checkbox" name="packingListId" value="${o.packingListId}"/></td>
                                <td>${page.seller}</td>
                                <td>${page.buyer}</td>
                                <td>${page.invoiceNo}</td>
                                <td><fmt:formatDate value="${page.invoiceDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${page.marks}</td>
                                <td>${page.descriptions}</td>

                                <td>
                                    <c:if test="${page.state==0}"><font color="green">草稿</font></c:if>
                                    <c:if test="${page.state==1}"><font color="green">已上报</font></c:if>
                                </td>
                                <td>
                                        <%--<a href="${ctx }/cargo/packing/commit.do?packingListId=${o.packingListId}">[提交]</a>--%>
                                    <a href="${ctx }/cargo/shipping/toView.do?packingListId=${page.packingListId}">[查看]</a>
                                        <%--<a href="${ctx }/cargo/packing/cancel.do?packingListId=${o.packingListId}">[取消]</a>--%>
                                </td>
                            </tr>
                        <%--</c:forEach>--%>
                    </table>
                    <!--数据列表/-->
                    <!--工具栏/-->
                </div>
                <!-- 数据表格 /-->
            </div>
            <!-- /.box-body -->

        </div>

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="submit"  class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->
    </section>
    </form>
</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#loadingDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#limitDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>

</html>