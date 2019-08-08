<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ include file="../base.jsp"%>
<!‐‐ 1. 导入bootstrap中的css样式文件 ‐‐>
<link href="/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!‐‐ 2. 导入jQuery框架 ‐‐>
<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!‐‐ 3. 导入bootstrap的js文件 ‐‐>
<%--<script src="/plugins/bootstrap/js/bootstrap.min.js"></script>--%>

<script>
    $(function () {
        //这块是反馈相关
        var degree= ${loginUser.degree}
        if(degree==0){
            var url="system/feedback/remind.do"
            $.get(url,function(data){

                $("#remind").html(data);
                $("#msg").html("你有"+data+"个新反馈");
                $("#skipList").text("点击查看待处理反馈")
            })
        }

        //这块是消息相关
        var url="system/chat/msgNumber.do";
        $.get(url,function(data){
            $("#msgNumber").html(data)
        })


        $("#recipient-name").blur(
            function () {
                if ($("#recipient-name").val()!=$("#newPassword").val()){
                    alert("两次密码不一致!")
                }

            }
        )

        $("#save").click(function(){
            //alert(1)
            var oldPassword=$("#oldPassword").val();
            var newPassword=$("#newPassword").val();
            var url="changePassword.do";
            var data = {"oldPassword":oldPassword,"newPassword":newPassword};
            $.post(url,data,function(d){
                if(d==1){
                    $("#close").click();
                    alert("密码修改成功!")
                    window.location.href = "login.do";
                }else {
                    alert("旧密码输入错误!")
                }
            })
        });


    })

   /* function changeState() {
        //alert(1)

    }*/
</script>
<header class="main-header">








    <a href="all-admin-index.html" class="logo">
        <span class="logo-mini"><img src="${ctx}/img/logo.png"></span>
        <span class="logo-lg">
                    <img src="${ctx}/img/export.png">
                    <i> SaaS外贸进出口平台</i>
                </span>
    </a>

    <nav class="navbar navbar-static-top">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown messages-menu">

                    <a href="${pageContext.request.contextPath}/system/chat/list.do" class="dropdown-toggle" <%--data-toggle="dropdown"--%>>
                        <i class="fa fa-envelope-o"></i>
                        <span class="label label-success" id="msgNumber"></span>
                    </a>
                    <%--  <ul class="dropdown-menu">
                         <li class="header">你有4个邮件</li>
                          <li>
                               <ul class="menu">
                                   <li>
                                       <a href="#">
                                           <div class="pull-left">
                                               <img src="${ctx}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                           </div>
                                           <h4>
                                               系统消息
                                               <small><i class="fa fa-clock-o"></i> 5 分钟前</small>
                                           </h4>
                                           <p>欢迎登录系统?</p>
                                       </a>
                                   </li>
                                   <li>
                                       <a href="#">
                                           <div class="pull-left">
                                               <img src="${ctx}/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                                           </div>
                                           <h4>
                                               团队消息
                                               <small><i class="fa fa-clock-o"></i> 2 小时前</small>
                                           </h4>
                                           <p>你有新的任务了</p>
                                       </a>
                                   </li>
                                   <li>
                                       <a href="#">
                                           <div class="pull-left">
                                               <img src="${ctx}/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                           </div>
                                           <h4>
                                               Developers
                                               <small><i class="fa fa-clock-o"></i> Today</small>
                                           </h4>
                                           <p>Why not buy a new awesome theme?</p>
                                       </a>
                                   </li>
                                   <li>
                                       <a href="#">
                                           <div class="pull-left">
                                               <img src="${ctx}/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                                           </div>
                                           <h4>
                                               Sales Department
                                               <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                           </h4>
                                           <p>Why not buy a new awesome theme?</p>
                                       </a>
                                   </li>
                                   <li>
                                       <a href="#">
                                           <div class="pull-left">
                                               <img src="${ctx}/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                           </div>
                                           <h4>
                                               Reviewers
                                               <small><i class="fa fa-clock-o"></i> 2 days</small>
                                           </h4>
                                           <p>Why not buy a new awesome theme?</p>
                                       </a>
                                   </li>
                               </ul>
                         </li>
                        <li class="footer"><a href="#">See All Messages</a></li>
                    </ul>--%>
                </li>
                <!-- Notifications: style can be found in dropdown.less -->
                <li class="dropdown notifications-menu">
                    <a href="${pageContext.request.contextPath}/system/feedback/list.do" class="dropdown-toggle" <%--data-toggle="dropdown"--%>>
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning" id="remind"></span>
                    </a>
                    <%-- <ul class="dropdown-menu">
                       <li class="header" id="msg">你有1个新消息</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li>
                                    <a href="${pageContext.request.contextPath}/system/feedback/changeState.do" id="skipList" onclick="changeState()">
                                        <i class="fa fa-users text-aqua"></i>
                                    </a>
                                </li>
                             <li>
                                   <a href="#">
                                       <i class="fa fa-warning text-yellow"></i> Very long description here that may not
                                       fit into the page and may cause design problems
                                   </a>
                               </li>
                               <li>
                                   <a href="#">
                                       <i class="fa fa-users text-red"></i> 5 new members joined
                                   </a>
                               </li>
                               <li>
                                   <a href="#">
                                       <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                                   </a>
                               </li>
                               <li>
                                   <a href="#">
                                       <i class="fa fa-user text-red"></i> You changed your username
                                   </a>
                               </li>
                            </ul>
                        </li>
                        <li class="footer"><a href="#">View all</a></li>
                    </ul>--%>
                </li>
                <!-- Tasks: style can be found in dropdown.less -->
                <li class="dropdown tasks-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-flag-o"></i>
                        <span class="label label-danger">9</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有9个新任务</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Design some buttons
                                            <small class="pull-right">20%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Create a nice theme
                                            <small class="pull-right">40%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">40% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Some task I need to do
                                            <small class="pull-right">60%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">60% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Make beautiful transitions
                                            <small class="pull-right">80%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">80% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                            </ul>
                        </li>
                        <li class="footer">
                            <a href="#">View all tasks</a>
                        </li>
                    </ul>
                </li>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${ctx}/img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs"> ${sessionScope.user.userName}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="${ctx}/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                            <p>
                                ${sessionScope.user.userName}
                            </p>
                        </li>
                        <!-- Menu Body
                <li class="user-body">
                    <div class="row">
                        <div class="col-xs-4 text-center">
                            <a href="#">Followers</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="#">Sales</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="#">Friends</a>
                        </div>
                    </div>
                </li>-->
                        <!-- Menu Footer-->

                        <!-- Button trigger modal -->

                        <li class="user-footer">
                            <div class="pull-left">

                                <%--<a href="${pageContext.request.contextPath}/changepassword.jsp" class="btn btn-default btn-flat">修改密码</a>--%>
                                    <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">修改密码</button>
                            </div>
                            <div class="pull-right">
                                <a href="/logout.do" class="btn btn-default btn-flat">注销</a>
                            </div>
                        </li>
                    </ul>
                </li>

            </ul>
        </div>
    </nav>


</header>
<!-- 页面头部 /-->
<body>
<!-- Modal -->



<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <form <%--id="editForm" action="${ctx}/cargo/packing/edit.do"--%> method="post">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">旧密码:</label>
                        <input type="password" class="form-control" id="oldPassword" name="oldPassword">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">新密码:</label>
                        <input type="password" class="form-control" name="newPassword" value="" id="newPassword">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">确认密码:</label>
                        <input type="password" class="form-control" id="recipient-name" name="confirmPassword" value="">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                <button type="button" class="btn btn-primary" id="save" <%--onclick="document.getElementById('editForm').submit()"--%> >保存</button>
            </div>
        </div>
    </div>
</div>
</body>

