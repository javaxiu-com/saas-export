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
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>合同下货物详情</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">货物添加及列表</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">新增货物</div>
            <%--
                    enctype="multipart/form-data"
            --%>
            <form id="editForm" action="${ctx}/cargo/contractProduct/edit.do" method="post"  enctype="multipart/form-data" >
                <input type="text" name="contractId" value="${contractId}">
                <input type="text" name="factoryName" id="factoryName" value="${contractProduct.factoryName}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">生产厂家</div>
                    <div class="col-md-4 data">
                        <select class="form-control"  name="factoryId" id="factoryInfo" onchange="document.getElementById('factoryName').value=this.options[this.selectedIndex].text">
                            <option value="">请选择</option>
                            <c:forEach items="${factoryList}" var="factory">
                                <option value="${factory.id}">${factory.factoryName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-2 title">货号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="货号" name="productNo" value="${contractProduct.productNo}">
                    </div>

                    <div class="col-md-2 title">货物照片</div>
                    <div class="col-md-4 data">
                        <input type="file" class="form-control" placeholder="请选择" name="productPhoto" >
                    </div>

                    <div class="col-md-2 title">数量</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="数量" name="cnumber" value="${contractProduct.cnumber}">
                    </div>

                    <div class="col-md-2 title">包装单位</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${contractProduct.packingUnit=="PCS"?'checked':''} name="packingUnit" value="PCS">PCS</label></div>
                            <div class="radio"><label><input type="radio" ${contractProduct.packingUnit=="SETS"?'checked':''} name="packingUnit" value="SETS">SETS</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">装率</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="装率" name="loadingRate" value="${contractProduct.loadingRate}">
                    </div>

                    <div class="col-md-2 title">箱数</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="箱数" name="boxNum" value="${contractProduct.boxNum}">
                    </div>

                    <div class="col-md-2 title">单价</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="单价" name="price" value="${contractProduct.price}">
                    </div>
                    <div class="col-md-2 title">排序号</div>
                    <div class="col-md-10 data">
                        <input type="text" class="form-control" placeholder="排序号" name="orderNo" value="${contractProduct.orderNo}">
                    </div>
                    <div class="col-md-2 title rowHeight2x">货物描述</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="货物描述" name="productDesc">${contractProduct.productDesc}</textarea>
                    </div>

                    <div class="col-md-2 title rowHeight2x">要求</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="要求" name="productRequest">${contractProduct.productRequest}</textarea>
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">货物列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                            <td class="tableHeader">序号</td>
                            <td class="tableHeader">厂家</td>
                            <td class="tableHeader">货号</td>
                            <td class="tableHeader">装率</td>
                            <td class="tableHeader">箱数</td>
                            <td class="tableHeader">包装单位</td>
                            <td class="tableHeader">数量</td>
                            <td class="tableHeader">单价</td>
                            <td class="tableHeader">总金额</td>
                            <td class="tableHeader">操作</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody" >
                        ${links }
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="checkbox" name="id" value="${o.id}"/></td>
                                <td>${status.index+1}</td>
                                <td>${o.factoryName}</td>
                                <td>${o.productNo}</td>
                                <td>${o.loadingRate}</td>
                                <td>${o.boxNum}</td>
                                <td>${o.packingUnit}</td>
                                <td>${o.cnumber}</td>
                                <td>${o.price}</td>
                                <td>${o.amount}</td>
                                <td>
                                    <a href="${ctx}/cargo/contractProduct/toUpdate.do?id=${o.id}">[修改]</a>
                                    <a href="${ctx}/cargo/contractProduct/delete.do?id=${o.id}&contractId=${o.contractId}">[删除]</a>
                                    <a href="${ctx}/cargo/extCproduct/list.do?contractId=${o.contractId}&contractProductId=${o.id}">[附件]</a>
                                </td>
                            </tr>

                            <c:forEach items="${o.extCproducts}" var="ext" varStatus="status">
                                <tr height="40" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                    <td>&nbsp;</td>
                                    <td align="right"><font color="blue">附件：${status.index+1}&nbsp;</font></td>
                                    <td>${ext.factoryName}</td>
                                    <td>${ext.productNo}</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>${ext.packingUnit}</td>
                                    <td>${ext.cnumber}</td>
                                    <td>${ext.price}</td>
                                    <td>${ext.amount}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--数据列表/-->
                    <!--工具栏/-->
                </div>
                <!-- 数据表格 /-->
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="${ctx}/cargo/contractProduct/list.do?contractId=${contractId}" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->
        </div>

    </section>

</div>
<!-- 内容区域 /-->
</body>

</html>