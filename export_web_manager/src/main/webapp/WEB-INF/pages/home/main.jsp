<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
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
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>

<body class="hold-transition skin-purple sidebar-mini" >
<div class="wrapper">
    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->
    <!-- 内容区域 -->
    <div class="content-wrapper">
        <iframe id="iframe" name="iframe"
                style="overflow:visible;"
                scrolling="auto"
                frameborder="no" height="100%" width="100%"
                src="${ctx}/home.do"></iframe>
    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- 底部导航 /-->
</div>


<script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="${ctx}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/plugins/raphael/raphael-min.js"></script>
<script src="${ctx}/plugins/morris/morris.min.js"></script>
<script src="${ctx}/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="${ctx}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${ctx}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${ctx}/plugins/knob/jquery.knob.js"></script>
<script src="${ctx}/plugins/daterangepicker/moment.min.js"></script>
<script src="${ctx}/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${ctx}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="${ctx}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${ctx}/plugins/fastclick/fastclick.js"></script>
<script src="${ctx}/plugins/iCheck/icheck.min.js"></script>
<script src="${ctx}/plugins/adminLTE/js/app.min.js"></script>
<script src="${ctx}/plugins/treeTable/jquery.treetable.js"></script>
<script src="${ctx}/plugins/select2/select2.full.min.js"></script>
<script src="${ctx}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="${ctx}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="${ctx}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script src="${ctx}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="${ctx}/plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="${ctx}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="${ctx}/plugins/ckeditor/ckeditor.js"></script>
<script src="${ctx}/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${ctx}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${ctx}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="${ctx}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctx}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${ctx}/plugins/chartjs/Chart.min.js"></script>
<script src="${ctx}/plugins/flot/jquery.flot.min.js"></script>
<script src="${ctx}/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="${ctx}/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="${ctx}/plugins/flot/jquery.flot.categories.min.js"></script>
<script src="${ctx}/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="${ctx}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script src="${ctx}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            var height = iframeWin.document.getElementById('frameContent').scrollHeight +20 ;
            iframe.height =height;
        }
    };

    var iframe= document.getElementById("iframe");
    iframe.onload = function () {
        setIframeHeight(iframe);
    };
</script>
</body>

</html>
<!---->
