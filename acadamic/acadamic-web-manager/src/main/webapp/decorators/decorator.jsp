<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>学术葩管理平台</title>
	<meta charset="utf-8">
	<link rel="shortcut icon" type="image/x-icon" href="/images/login-logo.png">
	<link href="/css/style.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/widgets/dingui/themes/default/ding.css" />
	<link href="/css/style-reset.css" rel="stylesheet">
	<!-- jquery confirm -->
	<link href="/widgets/jquery/jquery-confirm/css/jquery-confirm.css" rel="stylesheet">
	
	<style>
        .headroom {
            transition: transform 200ms linear;
        }
        .headroom--pinned {
            transform: translateY(0%);
        }
        .headroom--unpinned {
            transform: translateY(-100%);
        }
	</style>
</head>

<body class="sticky-header">
<section>
    <!-- left side start-->
    <div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            <a href="<%=request.getContextPath() %>/manager/index.shtml"><img src="/images/logo-index.png" alt="" width="140px"></a>
        </div>

        <div class="logo-icon text-center">
            <a href="<%=request.getContextPath() %>/manager/index.shtml"><img src="/images/logo-index-s.png" alt="" width="35px"></a>
        </div>
        <!--logo and iconic logo end-->

        <div class="left-side-inner">

            <!-- visible to small devices only -->
<!--             <div class="visible-xs hidden-sm hidden-md hidden-lg"> -->
<!--                 <div class="media logged-user"> -->
<!--                     <img alt="" src="/images/photos/user-avatar.png" class="media-object"> -->
<!--                     <div class="media-body"> -->
<!--                         <h4><a href="#">John Doe</a></h4> -->
<!--                         <span>"Hello There..."</span> -->
<!--                     </div> -->
<!--                 </div> -->

<!--                 <h5 class="left-nav-title">Account Information</h5> -->
<!--                 <ul class="nav nav-pills nav-stacked custom-nav"> -->
<%--                   <li><a href="<%=request.getContextPath() %>/system/personal/index.shtml"><i class="fa fa-user"></i> <span>个人信息</span></a></li> --%>
<!--                   <li><a href="#"><i class="fa fa-cog"></i> <span>Settings</span></a></li> -->
<!--                   <li><a href="#"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li> -->
<!--                 </ul> -->
<!--             </div> -->
            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav" id="mNavMenu">
				<li>
					<a href="<%=request.getContextPath() %>/manager/index.shtml"> 
						<i class="fa fa-home"></i> 
						<span>Home</span>
					</a>
				</li>
            </ul>
            <!--sidebar nav end-->
		</div>
		<!--sidebar nav end-->
    </div>
    <!-- left side end-->
    
    <!-- main content start-->
    <div class="main-content" >
    	<!-- header section start-->
        <div class="header-section" id="mHeadRoom">

            <!--toggle button start-->
            <a class="toggle-btn"><i class="fa fa-bars"></i></a>
            <!--toggle button end-->

            <!--search start-->
<!--             <form class="searchform" action="index.html" method="post"> -->
<!--                 <input type="text" class="form-control" name="keyword" placeholder="Search here..." /> -->
<!--             </form> -->
            <!--search end-->
            
            <!--notification menu start -->
            <div class="menu-right">
                <ul class="notification-menu">
<!--                     <li> -->
<!--                         <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown"> -->
<!--                             <i class="fa fa-tasks"></i> -->
<!--                             <span class="badge">8</span> -->
<!--                         </a> -->
<!--                         <div class="dropdown-menu dropdown-menu-head pull-right"> -->
<!--                             <h5 class="title">You have 8 pending task</h5> -->
<!--                             <ul class="dropdown-list user-list"> -->
<!--                                 <li class="new"> -->
<!--                                     <a href="#"> -->
<!--                                         <div class="task-info"> -->
<!--                                             <div>Database update</div> -->
<!--                                         </div> -->
<!--                                         <div class="progress progress-striped"> -->
<!--                                             <div style="width: 40%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-warning"> -->
<!--                                                 <span class="">40%</span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li class="new"> -->
<!--                                     <a href="#"> -->
<!--                                         <div class="task-info"> -->
<!--                                             <div>Dashboard done</div> -->
<!--                                         </div> -->
<!--                                         <div class="progress progress-striped"> -->
<!--                                             <div style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class="progress-bar progress-bar-success"> -->
<!--                                                 <span class="">90%</span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li> -->
<!--                                     <a href="#"> -->
<!--                                         <div class="task-info"> -->
<!--                                             <div>Web Development</div> -->
<!--                                         </div> -->
<!--                                         <div class="progress progress-striped"> -->
<!--                                             <div style="width: 66%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="66" role="progressbar" class="progress-bar progress-bar-info"> -->
<!--                                                 <span class="">66% </span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li> -->
<!--                                     <a href="#"> -->
<!--                                         <div class="task-info"> -->
<!--                                             <div>Mobile App</div> -->
<!--                                         </div> -->
<!--                                         <div class="progress progress-striped"> -->
<!--                                             <div style="width: 33%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="33" role="progressbar" class="progress-bar progress-bar-danger"> -->
<!--                                                 <span class="">33% </span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li> -->
<!--                                     <a href="#"> -->
<!--                                         <div class="task-info"> -->
<!--                                             <div>Issues fixed</div> -->
<!--                                         </div> -->
<!--                                         <div class="progress progress-striped"> -->
<!--                                             <div style="width: 80%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="80" role="progressbar" class="progress-bar"> -->
<!--                                                 <span class="">80% </span> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li class="new"><a href="">See All Pending Task</a></li> -->
<!--                             </ul> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown"> -->
<!--                             <i class="fa fa-envelope-o"></i> -->
<!--                             <span class="badge" id="mSystemInfoCount"></span> -->
<!--                         </a> -->
<!--                         <div class="dropdown-menu dropdown-menu-head pull-right"> -->
<!--                             <h5 class="title" id="mSystemInfoCountHtml"></h5> -->
<!--                             <ul class="dropdown-list normal-list" id="mSystemInfo"> -->
<!--                                 <li class="new"> -->
<%--                                 	<a href="<%=request.getContextPath() %>/public/data/system/user/info/index.shtml">查看更多消息</a> --%>
<!--                                 </li> -->
<!--                             </ul> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown"> -->
<!--                             <i class="fa fa-bell-o"></i> -->
<!--                             <span class="badge">4</span> -->
<!--                         </a> -->
<!--                         <div class="dropdown-menu dropdown-menu-head pull-right"> -->
<!--                             <h5 class="title">Notifications</h5> -->
<!--                             <ul class="dropdown-list normal-list"> -->
<!--                                 <li class="new"> -->
<!--                                     <a href=""> -->
<!--                                         <span class="label label-danger"><i class="fa fa-bolt"></i></span> -->
<!--                                         <span class="name">Server #1 overloaded.  </span> -->
<!--                                         <em class="small">34 mins</em> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li class="new"> -->
<!--                                     <a href=""> -->
<!--                                         <span class="label label-danger"><i class="fa fa-bolt"></i></span> -->
<!--                                         <span class="name">Server #3 overloaded.  </span> -->
<!--                                         <em class="small">1 hrs</em> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li class="new"> -->
<!--                                     <a href=""> -->
<!--                                         <span class="label label-danger"><i class="fa fa-bolt"></i></span> -->
<!--                                         <span class="name">Server #5 overloaded.  </span> -->
<!--                                         <em class="small">4 hrs</em> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li class="new"> -->
<!--                                     <a href=""> -->
<!--                                         <span class="label label-danger"><i class="fa fa-bolt"></i></span> -->
<!--                                         <span class="name">Server #31 overloaded.  </span> -->
<!--                                         <em class="small">4 hrs</em> -->
<!--                                     </a> -->
<!--                                 </li> -->
<!--                                 <li class="new"><a href="">See All Notifications</a></li> -->
<!--                             </ul> -->
<!--                         </div> -->
<!--                     </li> -->
                    <li>
                        <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <img src="${requestScope.userHead }" alt="" />
                            ${requestScope.userNickName }
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                            <li><a href="<%=request.getContextPath() %>/system/personal/index.shtml"><i class="fa fa-user"></i>  个人信息</a></li>
<!--                             <li><a href="#"><i class="fa fa-cog"></i>  Settings</a></li> -->
                            <li><a href="#" onclick="logout()"><i class="fa fa-sign-out"></i> 注销</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
            <!--notification menu end -->
		</div>
		<!-- header section end-->
		
		<!-- page heading start-->
        <div class="panel">
            <ul class="breadcrumb" id="mBreadCrumb">
                <li id="pMenuText"></li>
                <li class="active"><a id="mMenuText"></a></li>
                <li id="requestMenuName">${requestScope.authorityName }</li>
            </ul>
        </div>
        <!-- page heading end-->
        
        <!--body wrapper start-->
        <div class="wrapper">
        	<sitemesh:write property='body'/>
        </div>
        <!--body wrapper end-->
        
        <!--footer section start-->
        <footer>
            2017 &copy; 学术葩管理平台  by 北京默存网络科技有限公司
        </footer>
        <!--footer section end-->
    </div>
    <!-- main content end-->
</section>
	<div class="modal fade" id="mModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
				  <h4 class="modal-title">Title</h4>
				</div>
				<div class="modal-body">
					
				</div>
				<div class="modal-footer">
				  <button type="button" class="btn btn-default" id="mCancelBtn" data-dismiss="modal">取消</button>
				  <button type="button" class="btn btn-success" id="mSureBtn">保存</button>
				  <button type="button" class="btn btn-primary" style="display:none" id="mSureNextBtn">保存后添加下一个</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

	<!-- info-modal -->
	<div class="modal fade" id="mSystemInfoModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
				  <h4 class="modal-title">Title</h4>
				</div>
				<div class="modal-body">
					<section class="mail-list">
						<div class="mail-sender">
							<div class="row">
								<div class="col-md-8">
		                            <img id="mSystemInfoSenderHeader">
		                            <strong id="mSystemInfoSender"></strong>
		                        </div>
		                        <div class="col-md-4">
		                            <p class="date" id="mSystemInfoTime">
		                            	
		                            </p>
		                        </div>
							</div>
						</div>
						<div class="view-mail">
						</div>
					</section>
				</div>
				<div class="modal-footer">
				  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div>
	<!-- info-modal -->
	
	<scripts>
		<!-- Placed js at the end of the document so the pages load faster -->
		<script src="/widgets/jquery/jquery-3.2.1.min.js"></script>
		<script src="/widgets/jquery-ui/jquery-ui.min.js"></script>
		
		<!-- 	<script src="/widgets/bootstrap/js/bootstrap.min.js"></script> -->
		<script src="/widgets/bootstrap/js/bootstrap.js"></script>
		
		<!-- jquery common -->
		<script src="/widgets/jquery/jquery-common/jquery.common.js"></script>	
		<!-- jquery validate -->
		<script src="/widgets/jquery/jquery-validation/jquery.validation.js"></script>
		
		<script src="/widgets/jquery/jquery.nicescroll.js"></script>
		<!-- jquery cookie -->
		<script src="/widgets/jquery/jquery.cookie.js"></script>
		
		<!-- jquery confirm -->
		<link href="/widgets/jquery/jquery-confirm/css/jquery-confirm.css" rel="stylesheet">
		<script src="/widgets/jquery/jquery-confirm/js/jquery-confirm.js"></script>	
		
		<!-- bootstrap headroom -->
		<script src="/widgets/bootstrap/headroom/headroom.js"></script>
		
		<!-- import Ding widget -->
		<script src="/widgets/dingui/Ding.js"></script>
		
		<script src="/js/common.js"></script>
		
		<!--common scripts for all pages-->
		<script src="/js/scripts.js"></script>
		<!-- decorate menu -->
		<script src="/js/decorator.js"></script>
		<!-- loading -->
		<script src="/widgets/whir/whir.js"></script>
		
		<sitemesh:write property='head'></sitemesh:write>
	</scripts>
</body>
</html>
