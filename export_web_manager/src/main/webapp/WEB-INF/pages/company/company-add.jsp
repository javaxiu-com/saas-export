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
</head>
<body>
<div id="frameContent">
    <!-- 内容头部 -->
    <section class="content-header" class="content-wrapper" style="margin-left:0px;">
        <h1>
            企业管理
            <small>企业表单</small>
        </h1>
        <ol class="breadcrumb">
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">企业信息</div>
            <form id="editForm" action="/company/edit.do" method="post">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">企业名称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="企业名称" name="name" value="${company.name}">
                    </div>

                    <div class="col-md-2 title">营业执照</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="营业执照" name="licenseId" value="${company.licenseId}">
                    </div>

                    <div class="col-md-2 title">所在城市</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="所在地" name="city" value="${company.city}">
                    </div>

                    <div class="col-md-2 title">企业地址</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="企业地址" name="address" value="${company.address}">
                    </div>

                    <div class="col-md-2 title">法人代表</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="法人代表" name="representative" value="${company.representative}">
                    </div>

                    <div class="col-md-2 title">联系电话</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="联系电话" name="phone" value="${company.phone}">
                    </div>

                    <div class="col-md-2 title">公司规模</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="公司规模" name="companySize" value="${company.companySize}">
                    </div>

                    <div class="col-md-2 title">所属行业</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="所属行业" name="industry" value="${company.industry}">
                    </div>
                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <select class="form-control select2" name="state" style="width: 100%;">
                            <option value="0" ${company.state==0 ? 'selected':''}>未审核</option>
                            <option value="1" ${company.state==1 ? 'selected':''}>已审核</option>
                        </select>
                        <input type="text" class="form-control" placeholder="状态" name="state" value="${company.state}">
                    </div>

                    <div class="col-md-2 title">余额</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="余额" name="balance" value="${company.balance}">
                    </div>

                    <div class="col-md-2 title rowHeight2x">备注</div>
                    <div class="col-md-10 data rowHeight2x">
                        <textarea class="form-control" rows="3" name="remarks">${company.remarks}</textarea>
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