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


				<title>警示提醒</title>
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
				<h2>警示提醒</h2>
			</div>
			<div class="subfiled-content">
				<nav class="navbar navbar-default" role="navigation">
				<h2>未实训人员</h2>
				<ul class="list-group">
					<c:forEach items="${nos}" var="o">
						<li class="list-group-item">${o.t_name}</li>
					</c:forEach>
				</ul>
				<h2>已配置收件人</h2>
				<ul class="list-group">
					<c:forEach items="${receives}" var="r">
						<li class="list-group-item">${r}</li>
					</c:forEach>
				</ul>
				<div class="container-fluid">
					<form role="form" id="receiveForm">
						<div class="form-group">
							<label for="name">邮件收件人</label>
							<button type="button" class="btn btn-primary"
								onclick="setReceive()">设置收件人</button>
							<textarea class="form-control" name="shoujian" rows="3"></textarea>
						</div>
					</form>
					<h4>设置多个邮件时,请用,号隔开</h4>
				</div>
				</nav>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		
	</script>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basePath%>sta/comprehensiveApp/js/warning.js">
	
</script>
<script type="text/javascript"
	src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"
	type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
