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
            基础信息
            <small>商品管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/message/product/list.do">商品列表</a></li>
            <li class="active">新增商品</li>i>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">商品信息</div>
            <form id="editForm" action="${ctx}/message/product/edit.do" method="post">
                <input type="hidden" id="deptName" name="deptName" value="${product.id}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">编号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="编号" name="productNo" value="${product.productNo}">
                    </div>

                    <div class="col-md-2 title">市场价</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="市场价" name="price" value="${product.price}">
                    </div>

                    <div class="col-md-2 title">生产厂家</div>
                    <div class="col-md-4 data">
                        <select class="form-control" name="factoryId" id="factoryInfo" onchange="document.getElementById('factoryName').value=this.options[this.selectedIndex].text">
                            <option value="">请选择</option>
                            <c:forEach items="${factoryList}" var="factory">
                                <option value="${factory.id}" ${product.factoryId eq factory.id ? "selected" : ""}>
                                        ${factory.factoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-2 title">商品照片</div>
                    <div class="col-md-4 data">
                        <input type="file" class="form-control" placeholder="请选择" name="productImage" >
                    </div>

                    <div class="col-md-2 title">尺寸长</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="尺寸长" name="sizeLenght" value="${product.sizeLenght}">
                    </div>

                    <div class="col-md-2 title">尺寸宽</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="尺寸宽" name="sizeWidth" value="${product.sizeWidth}">
                    </div>

                    <div class="col-md-2 title">尺寸高</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="尺寸高" name="sizeHeight" value="${product.sizeHeight}">
                    </div>

                    <div class="col-md-2 title">颜色</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="颜色" name="color" value="${product.color}">
                    </div>

                    <div class="col-md-2 title">包装</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="包装" name="color" value="${product.packing}">
                    </div>

                    <div class="col-md-2 title">包装单位</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="包装单位" name="packingUnit" value="${product.packingUnit}">
                    </div>

                    <div class="col-md-2 title">集装箱类别20</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="集装箱类别20" name="type20" value="${product.type20}">
                    </div>

                    <div class="col-md-2 title">集装箱类别40</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="集装箱类别40" name="type40" value="${product.type40}">
                    </div>

                    <div class="col-md-2 title">集装箱类别40HC</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="集装箱类别40HC" name="type40hc" value="${product.type40hc}">
                    </div>

                    <div class="col-md-2 title">数量</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="数量" name="qty" value="${product.qty}">
                    </div>

                    <div class="col-md-2 title">体积</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="体积" name="cbm" value="${product.cbm}">
                    </div>

                    <div class="col-md-2 title">大箱尺寸长</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="大箱尺寸长" name="mpsizeLenght" value="${product.mpsizeLenght}">
                    </div>

                    <div class="col-md-2 title">大箱尺寸宽</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="大箱尺寸宽" name="mpsizeWidth" value="${product.mpsizeWidth}">
                    </div>

                    <div class="col-md-2 title">大箱尺寸高</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="大箱尺寸高" name="mpsizeHeight" value="${product.mpsizeHeight}">
                    </div>

                    <div class="col-md-2 title rowHeight2x">备注</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="备注" name="remark">${product.remark}</textarea>
                    </div>

                    <div class="col-md-2 title rowHeight2x">描述</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="要求" name="productRequest">${product.description}</textarea>
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

</div>
<!-- 内容区域 /-->
</body>
<script src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${ctx}/css/style.css">
<script>
    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#datepicker1').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>