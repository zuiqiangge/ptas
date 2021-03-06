<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="<%=basePath%>common/css/sapar.css" />
	<link rel="stylesheet" type="text/css"
		href="<%=basePath%>common/css/common.css" />
	<link rel="stylesheet" type="text/css"
		href="<%=basePath%>sta/personal/css/personal_list.css" />
	<link rel="stylesheet" type="text/css"
		href="<%=basePath%>sta/personal/css/user.css" />
	<link
		href="<%=basePath%>common/js/DataTables-1.10.15/media/css/jquery.dataTables.min.css"
		rel="stylesheet" type="text/css">


		<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet"
			href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css"
			type="text/css">

			<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
			<link rel="stylesheet"
				href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"
				type="text/css">


				<title>信息查询</title>
				<style>
/* Custom Styles */
ul.nav-tabs {
	width: 140px;
	margin-top: 20px;
	border-radius: 4px;
	border: 1px solid #ddd;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
}

ul.nav-tabs li {
	margin: 0;
	border-top: 1px solid #ddd;
}

ul.nav-tabs li:first-child {
	border-top: none;
}

ul.nav-tabs li a {
	margin: 0;
	padding: 8px 16px;
	border-radius: 0;
}

ul.nav-tabs li.active a,ul.nav-tabs li.active a:hover {
	color: #fff;
	background: #0088cc;
	border: 1px solid #0088cc;
}

ul.nav-tabs li:first-child a {
	border-radius: 4px 4px 0 0;
}

ul.nav-tabs li:last-child a {
	border-radius: 0 0 4px 4px;
}

ul.nav-tabs.affix {
	top: 30px; /* Set the top position of pinned element */
}
</style>
</head>

<body data-spy="scroll" data-target="#myScrollspy">

	<div id="saper-container">
		<div id="saper-hd"></div>
		<div id="saper-bd">
			<div class="subfiled clearfix">
				<h2>信息查询</h2>
			</div>
			<div class="subfiled-content">
				<nav class="navbar navbar-default" role="navigation">
				<div class="container-fluid">
					<div>
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">受训对象</a></li>
							<li><a href="#">受训组织</a></li>
							<li><a href="#">实训内容</a></li>
							<li><a href="#">讲解员</a></li>
						</ul>
					</div>
				</div>
				
				</nav>
			</div>
			 <!--表格开始-->
                <div class="table">
					<!--表格操作-->
                    <div class="table-operate ue-clear">
                    <!--三级菜单管理-->
                	<s:iterator var="qx" value="#request.qxs" status="statu">
						<a href="javascript:;" class="${qx.classs}">${qx.qxmc}</a>
					</s:iterator>
                </div>
                    <div class="search-box clearfix">
					 <form action="" id="conditionFrom" method="POST">
                    <div class="kv-item clearfix">
                        <label>名称：</label>
                        <div class="kv-item-content">
                            <input type="text" name="trained.t_name" id="queryName"/>
                        </div>
                    </div>
                    <a href="javascript:;" class="sapar-btn sapar-btn-recom query-btn">查询</a>
                </form>
                </div>
               
                	
                    <!--表格具体内容-->
                        <table id="myTable" class="cell-border">
                            <thead>
                               
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                </div><!--表格结束-->
		</div>
	</div>


	<!--查看模态框begin-->
   <div class="modal fade" id="viewModal" role="dialog" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="viewModalTitle">查看</h4>
	            </div>
	             <div class="modal-body">
	          <form id="viewForm">
               <div class="subfiled-content">
                 <div class="search-box clearfix" id="viewDiv">
                    <div class="kv-item clearfix" >
                        <label>姓名：</label>
                        <div class="kv-item-content">
                            <input type="hidden" name="yyGzryxxb.id"/>
                            <input type="text" name="yyGzryxxb.name"/>
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>选择类型：</label>
                        <div class="kv-item-content">
                            <select style="width:130px" name="yyGzryxxb.xb">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
               
                </div>
                </div>
                </form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="update()">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
    	</div><!-- /.modal -->
	</div>
   <!--查看模态框end-->

	<script type="text/javascript">
		
	</script>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basePath%>sta/comprehensiveApp/js/personal_list.js">
	
</script>
<script type="text/javascript"
	src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"
	type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
