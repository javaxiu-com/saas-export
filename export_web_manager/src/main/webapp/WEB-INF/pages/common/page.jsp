<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<%
    String currentURL = null;
    if( request.getAttribute("javax.servlet.forward.request_uri") != null ){
        currentURL = (String)request.getAttribute("javax.servlet.forward.request_uri");
    }
    if( currentURL != null && request.getQueryString() != null ){
        currentURL += "?" + request.getQueryString();
    }
%>

<body>
<div class="pull-left">
    <form id="pageForm" action="<%=currentURL%>" method="post">
        <div class="form-group form-inline">
            总共${page.pages} 页，共${page.total} 条数据。每页
            <select onchange="goPage(1)" name="size" class="form-control">
                <option ${page.pageSize==5?'selected':''}>5</option>
                <option ${page.pageSize==10?'selected':''}>10</option>
            </select> 条
        </div>
        <input type="hidden" name="page" id="pageNum">
    </form>
</div>

<div class="box-tools pull-right">
    <ul class="pagination" style="margin: 0px;">
        <li >
            <a href="javascript:goPage(1)" aria-label="Previous">首页</a>
        </li>
        <c:if test="${page.pageNum!=1}">
            <li><a href="javascript:goPage(${page.prePage})">上一页</a></li>
        </c:if>
        <c:if test="${page.pageNum==1}">
            <li><a href="javascript:void(0)">上一页</a></li>
        </c:if>

        <c:forEach begin="${page.navigateFirstPage}" end="${page.navigateLastPage}" var="i">
            <li class="paginate_button ${page.pageNum==i ? 'active':''}"><a href="javascript:goPage(${i})">${i}</a></li>
        </c:forEach>
        <c:if test="${page.pageNum!=page.pages}">
            <li><a href="javascript:goPage(${page.nextPage})">下一页</a></li>
        </c:if>
        <c:if test="${page.pageNum==page.pages}">
            <li><a href="javascript:void(0)">下一页</a></li>
        </c:if>

        <li>
            <a href="javascript:goPage(${page.pages})" aria-label="Next">尾页</a>
        </li>
    </ul>
</div>
<script>
    function goPage(page) {
        document.getElementById("pageNum").value = page
        document.getElementById("pageForm").submit()
    }
</script>
</body>
</html>
