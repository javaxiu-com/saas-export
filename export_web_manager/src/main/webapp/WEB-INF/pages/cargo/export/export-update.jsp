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
            <small>新增出口报运单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">新增出口报运单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->
    <form id="editForm" action="${ctx}/cargo/export/edit.do" method="post">
        <input type="hidden" name="contractIds" value="${export.contractIds}" >
        <input type="hidden" name="id" value="${export.id}" >
        <input type="hidden" name="contractNo" value="${export.customerContract}">
    <!-- 正文区域 -->
    <section class="content">
        <div class="panel panel-default">
            <div class="panel-heading">新增货物</div>

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">信用证号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="信用证号" name="lcno" value="${export.lcno}"/>
                    </div>

                    <div class="col-md-2 title">收货人及地址</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="收货人及地址" name="consignee" value="${export.consignee}"/>
                    </div>

                    <div class="col-md-2 title">唛头</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="唛头" name="marks" value="${export.marks}"/>
                    </div>

                    <div class="col-md-2 title">装运港</div>
                    <div class="col-md-4 data">
                        <input type="text" name="shipmentPort" class="form-control" placeholder="装运港"value="${export.shipmentPort}"/>
                    </div>

                    <div class="col-md-2 title">目的港</div>
                    <div class="col-md-4 data">
                        <input type="text" name="destinationPort" class="form-control" placeholder="目的港" value="${export.destinationPort}"/>
                    </div>

                    <div class="col-md-2 title">运输方式</div>
                    <div class="col-md-4 data">
                        <input type="text" name="transportMode" class="form-control" placeholder="运输方式" value="${export.transportMode}">
                    </div>

                    <div class="col-md-2 title">价格条件</div>
                    <div class="col-md-4 data">
                        <input type="text" name="priceCondition" class="form-control" placeholder="价格条件" value="${export.priceCondition}"/>
                    </div>

                    <div class="col-md-2 title">总箱数</div>
                    <div class="col-md-4 data">
                        <input type="text" name="boxNums" class="form-control" placeholder="总箱数" value="${export.boxNums}"/>
                    </div>

                    <div class="col-md-2 title">总毛重</div>
                    <div class="col-md-4 data">
                        <input type="text" name="grossWeights" class="form-control" placeholder="总毛重"  value="${export.grossWeights}">

                    </div>
                    <div class="col-md-2 title">体积</div>
                    <div class="col-md-4 data">
                        <input type="text" name="measurements" class="form-control" placeholder="体积" value="${export.measurements}">
                    </div>

                    <div class="col-md-2 title rowHeight2x">备注</div>
                    <div class="col-md-10 data rowHeight2x">
                        <textarea class="form-control" rows="3" name="remark">${export.remark}</textarea>
                    </div>
                </div>

        </div>


        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">报运单货物列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table class="table table-bordered table-striped table-hover dataTable" id="mRecordTable">
                        <tr class="rowTitle" align="middle">
                            <td width="33">序号</td>
                            <td width="60px">货号</td>
                            <td width="90px">数量</td>
                            <td width="90px">毛重</td>
                            <td width="90px">净重</td>
                            <td width="90px">长</td>
                            <td width="90px">宽</td>
                            <td width="90px">高</td>
                            <td width="90px">出口单价</td>
                            <td width="90px">含税</td>
                        </tr>
                        <c:forEach items="${eps}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <input type="hidden" name="exportProducts[${status.index}].id" value="${o.id}"/>
                                <td>${status.index+1}</td>
                                <td>${o.productNo}</td>
                                <td>
                                    <input style="width: 90px" name="exportProducts[${status.index}].cnumber" value="${o.boxNum}">
                                </td>
                                <td>
                                    <input style="width: 90px" name="exportProducts[${status.index}].grossWeight" value="${o.grossWeight}">
                                </td>
                                <td>
                                    <input style="width: 90px" name="exportProducts[${status.index}].netWeight" value="${o.netWeight}">
                                </td>
                                <td>
                                    <input style="width: 90px" name="exportProducts[${status.index}].sizeLength" value="${o.sizeLength}">
                                </td>
                                <td>
                                    <input style="width: 90px" name="exportProducts[${status.index}].sizeWidth" value="${o.sizeWidth}">
                                </td>
                                <td>
                                    <input style="width: 90px" name="exportProducts[${status.index}].sizeHeight" value="${o.sizeHeight}">
                                </td>
                                <td>
                                    <input style="width: 90px" name="exportProducts[${status.index}].exPrice" value="${o.exPrice}">
                                </td>
                                <td>
                                    <input style="width: 90px" name="exportProducts[${status.index}].tax" value="${o.tax}">
                                </td>
                            </tr>
                        </c:forEach>
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

</html>