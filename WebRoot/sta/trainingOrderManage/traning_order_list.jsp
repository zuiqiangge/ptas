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


<title>组织实训预约</title>
</head>

<body>

	<div id="saper-container">
		<div id="saper-hd"></div>
		<div id="saper-bd">
			<div class="subfiled clearfix">
				<h2>组织实训预约</h2>
			</div>
			<div class="subfiled-content">
				<button type="button" class="btn btn-primary btn-lg"
					onclick="newOrder()">新建预约</button>
			</div>
		</div>
	</div>

	<!--添加模态框begin-->
	<div class="modal fade" id="newOrderModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="newOrderModalTitle">新建预约</h4>
				</div>
				<div class="modal-body">
					<form id="newOrderForm">
						<div class="subfiled-content">
							<div class="search-box clearfix">
								<div class="kv-item clearfix">
									<label>受训单位</label>
									<div class="kv-item-content">
										<select style="width:130px" name="SxOrder.sxzzId">
											<c:forEach items="${dws}" var="dw">
												<option value="${dw.o_id}">${dw.o_name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>实训内容：</label>
									<div class="kv-item-content">
										<input type="text" name="SxOrder.sxContent" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>预约时间：</label>
									<div class="kv-item-content">
										<input type="text" name="sxOrder.time"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> <input
											type="hidden" name="sxOrder.status" value="0" />
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="submitOrder()">提交预约申请</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!--添加模态框end-->

	<script type="text/javascript">
		
	</script>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basePath%>sta/trainingOrderManage/js/traning_order_list.js">
	
</script>
<script type="text/javascript"
	src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"
	type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
