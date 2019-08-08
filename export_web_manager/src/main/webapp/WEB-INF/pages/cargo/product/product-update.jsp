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
            <li class="active">货物编辑</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">编辑货物</div>
            <%--
                enctype="multipart/form-data"
            --%>
            <form id="editForm" action="${ctx}/cargo/contractProduct/edit.do" method="post" >
                <input type="text" name="id" value="${contractProduct.id}">
                <input type="text" name="contractId" value="${contractProduct.contractId}">
                <input type="text"  name="factoryName" id="factoryName" value="${contractProduct.factoryName}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">生产厂家</div>
                    <div class="col-md-4 data">
                        <select class="form-control" name="factoryId" id="factoryInfo" onchange="document.getElementById('factoryName').value=this.options[this.selectedIndex].text">
                            <option value="">请选择</option>
                            <c:forEach items="${factoryList}" var="factory">
                                <option value="${factory.id}" ${contractProduct.factoryId eq factory.id ? "selected" : ""}>
                                        ${factory.factoryName}
                                </option>
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

                    <c:if test="${not empty contractProduct.productImage && !(contractProduct.productImage eq null) }">
                    <div class="col-md-2 title">货物照片</div>
                    <div class="col-md-4 data">
                        <c:if test="${not empty contractProduct.productImage && !(contractProduct.productImage eq null) }">
                            <img src="${contractProduct.productImage}" width="300px" height="180px">
                            <input type="hidden" name="productImage" value="${contractProduct.productImage}">
                        </c:if>

                    </div>
                    </c:if>
                    <c:if test="${empty contractProduct.productImage || (contractProduct.productImage eq null) }">
                    <div class="col-md-2 title">货物照片</div>
                    <div class="col-md-4 data">
                        还没有照片
                    </div>
                    </c:if>

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
                    <div class="col-md-4 data">
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


</div>
<!-- 内容区域 /-->
</body>

</html>