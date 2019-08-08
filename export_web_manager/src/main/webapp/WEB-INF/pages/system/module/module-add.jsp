<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>部门管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="panel panel-default">
                <div class="panel-heading">用户信息</div>
                <form id="editForm" action="${ctx}/system/module/edit.do" method="post">
                    <input type="hidden" id="parentName" name="parentName" value="${module.parentName}">
                    <div class="row data-type" style="margin: 0px">
                        <div class="col-md-2 title">模块名</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="模块名" name="name" value="${module.name}">
                        </div>

                        <div class="col-md-2 title">上级模块</div>
                        <div class="col-md-4 data">
                            <select class="form-control" onchange="document.getElementById('parentName').value=this.options[this.selectedIndex].text" name="parentId">
                                <option value="">请选择</option>
                                <c:forEach items="${menus}" var="item">
                                        <c:if test="${item.ctype != 2}">
                                            <option ${module.parentId == item.id ?'selected':''} value="${item.id}">${item.name}</option>
                                        </c:if>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-2 title">权限标识</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="权限标识" name="cpermission" value="${module.cpermission}">
                        </div>

                        <div class="col-md-2 title">链接</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="链接" name="curl" value="${module.curl}">
                        </div>



                        <div class="col-md-2 title">类型</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio" ${module.ctype==0?'checked':''} name="ctype" value="0">主菜单</label></div>
                                <div class="radio"><label><input type="radio" ${module.ctype==1?'checked':''} name="ctype" value="1">二级菜单</label></div>
                                <div class="radio"><label><input type="radio" ${module.ctype==2?'checked':''} name="ctype" value="2">按钮</label></div>
                            </div>
                        </div>

                        <div class="col-md-2 title">状态</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio" ${module.state==0?'checked':''} name="state" value="0">停用</label></div>
                                <div class="radio"><label><input type="radio" ${module.state==1?'checked':''} name="state" value="1">启用</label></div>
                            </div>
                        </div>

                        <div class="col-md-2 title">从属</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${module.belong==0?'checked':''} name="belong" value="0">sass内部菜单</label></div>
                            <div class="radio"><label><input type="radio" ${module.belong==1?'checked':''} name="belong" value="1">租用企业菜单</label></div>
                            </div>
                        </div>

                        <div class="col-md-2 title">排序号</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="排序号" name="orderNo" value="${module.orderNo}">
                        </div>


                        <div class="col-md-2 title rowHeight2x">备注</div>
                        <div class="col-md-4 data rowHeight2x">
                            <textarea class="form-control" rows="3" name="remark">${module.remark}</textarea>
                        </div>


                    </div>
                </form>
            </div>
            <!--订单信息/-->
            <div class="box-tools text-center">
                <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
        </section>
    </div>
</body>

</html>