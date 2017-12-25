<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="/mytaglib" prefix="lq"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mytaglib" prefix="cc"%>
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
<!--easyui样式-->
<link href="<%=basePath%>jquery-easyui-1.5.3/themes/default/easyui.css"
	rel="stylesheet" type="text/css">
<link href="<%=basePath%>jquery-easyui-1.5.3/themes/icon.css"
	rel="stylesheet" type="text/css">


<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css"
	type="text/css">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"
	type="text/css">


<title>硬件配置管理</title>
</head>

<body>

	<div id="saper-container">
		<div id="saper-hd"></div>
		<div id="saper-bd">
			<div class="subfiled clearfix">
				<h2>硬件配置管理</h2>
			</div>
			<div class="subfiled-content">
				<div class="search-box clearfix"></div>

				<div class="easyui-tabs" style="width:1150px;height:800px">
					<div title="基本信息" style="padding:10px">
						<!--表格开始-->
						<div class="table">
							<!--表格操作-->
							<div class="table-operate ue-clear">
								<!--三级菜单管理-->
								<s:iterator var="qx" value="#request.qxs" status="statu">
									<a href="javascript:;" class="${qx.classs}">${qx.qxmc}</a>
								</s:iterator>
							</div>
							<!--表格具体内容-->
							<table id="myTable" class="cell-border">
								<thead>
									<tr>
										<th>序号</th>
										<th>机器码</th>
										<th>设备名</th>
										<th>ip</th>
										<th>操作系统</th>
										<th>品牌</th>
										<th>运行是否正常</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
						<!--表格结束-->
					</div>
					<div title="平台安全分析" style="padding:10px">
						<table class="table">
							<tbody>
								<tr>
									<td>
										<div class="well well-sm"><h4>总共日志条数:${count}</h4></div>
										<div id="pie1" style="width:350px;height:250px;"></div>
									</td>
									<td><div id="pie" style="width:400px;height:300px;"></div></td>
								</tr>
								<tr>
									<td>
										<table id="leftDown" style="margin:0px 10px 10px 0px">
											<thead>
	    										<tr>
		      										<th>序号</th>
		      										<th>攻击ip</th>
		      										<th>端口</th>
		      										<th>协议</th>
		      										<th>源主机</th>
		      										<th>源端口</th>
		      										<th>攻击类型</th>
	    										</tr>
  											</thead>
											<tbody>
											</tbody>
										</table>
									</td>
									<td><div id="bar" style="width:400px;height:300px;"></div></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div title="设备统一管理">
						<canvas width="1024" height="768" id="canvas"
							style=" background-color: #ffffff; border:1px solid #444;"></canvas>
					</div>
				</div>


			</div>
		</div>
	</div>
	<!--删除模态框begin-->
	<div class="modal fade" id="delModal" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="delModalTitle">删除</h4>
				</div>
				<div class="modal-body">
					<ul class="list-group">
					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="del()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!--删除模态框end-->






	<!--添加模态框begin-->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="addModalTitle">添加</h4>
				</div>
				<div class="modal-body">
					<form id="addForm">
						<div class="subfiled-content">
							<div class="search-box clearfix">
								<div class="kv-item clearfix">
									<label>机器码：</label>
									<div class="kv-item-content">
										<input type="text" name="hardWare.mac" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>设备名：</label>
									<div class="kv-item-content">
										<input type="text" name="hardWare.name" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>ip*：</label>
									<div class="kv-item-content">
										<input type="text" name="hardWare.ip" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>操作系统*：</label>
									<div class="kv-item-content">
										<lq:mySelect name="hardWare.os" query="false" typeCode="os" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>品牌：</label>
									<div class="kv-item-content">
										<lq:mySelect name="hardWare.pinpai" query="false"
											typeCode="pinpai" />
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="add()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!--添加模态框end-->

	<!--编辑模态框begin-->
	<div class="modal fade" id="editModal" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="editModalTitle">编辑</h4>
				</div>
				<div class="modal-body">
					<form id="editForm">
						<div class="subfiled-content">
							<div class="search-box clearfix">
								<div class="kv-item clearfix">
									<label>机器码：</label>
									<div class="kv-item-content">
										<input type="hidden" name="hardWare.id" /> <input type="text"
											name="hardWare.mac" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>设备名：</label>
									<div class="kv-item-content">
										<input type="text" name="hardWare.name" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>ip：</label>
									<div class="kv-item-content">
										<input type="text" name="hardWare.ip" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>操作系统：</label>
									<div class="kv-item-content">
										<lq:mySelect name="hardWare.os" query="false" typeCode="os" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>品牌：</label>
									<div class="kv-item-content">
										<lq:mySelect name="hardWare.pinpai" query="false"
											typeCode="pinpai" />
									</div>
								</div>
							</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="update()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!--编辑模态框end-->
	<script type="text/javascript">
		
	</script>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/echarts.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>sta/platformREManage/js/hard_list.js">
	
</script>
<script type="text/javascript"
	src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>

<script type="text/javascript"
	src="<%=basePath%>common/js/jtopo-0.4.8-min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"
	type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>

<!--easyui js-->
<script type="text/javascript"
	src="<%=basePath%>jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
</html>
