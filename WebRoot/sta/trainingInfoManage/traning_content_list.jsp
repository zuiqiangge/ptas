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
	href="<%=basePath%>sta/trainingInfoManage/css/content.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>sta/personal/css/user.css" />
<link
	href="<%=basePath%>common/js/DataTables-1.10.15/media/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.5.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.5.3/themes/icon.css">

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css"
	type="text/css">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"
	type="text/css">


<title>实训内容管理</title>
</head>

<body>

	<div id="saper-container">
		<div id="saper-hd"></div>
		<div id="saper-bd">
			<div class="subfiled clearfix">
				<h2>实训内容管理</h2>
			</div>
			<div class="subfiled-content">
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
								<th>实训项目名称</th>
								<th>持续时间</th>
								<th>实训地点</th>
								<th>教员</th>
								<th>受训单位</th>
								<th>受训对象</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<!--表格结束-->
			</div>
		</div>
	</div>
	
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
									<label>实训项目名称：</label>
									<div class="kv-item-content">
										<select style="width:130px" name="sxPlan.sxProject.name">
											<c:forEach items="${projects}" var="p">
												<option value="${p.id}">${p.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>持续 时间：</label>
									<div class="kv-item-content">
										<input type="text" name="sxPlan.keepTime" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>实训地点：</label>
									<div class="kv-item-content">
										<input type="text" name="sxPlan.place" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>教员：</label>
									<div class="kv-item-content">
										<select style="width:130px" name="sxPlan.teacher">
											<c:forEach items="${teachers}" var="teacher">
												<option value="${teacher.id}">${teacher.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>受训单位</label>
									<div class="kv-item-content">
										<select style="width:130px" name="sxPlan.sxdwId">
											<c:forEach items="${dws}" var="dw">
												<option value="${dw.o_id}">${dw.o_name}</option>
											</c:forEach>
										</select>
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
									<label>实训项目名称：</label>
									<div class="kv-item-content">
										<input type="hidden" name="sxPlan.id"/>
										<select style="width:130px" name="sxPlan.sxProject">
											<c:forEach items="${projects}" var="p">
												<option value="${p.id}">${p.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>持续 时间：</label>
									<div class="kv-item-content">
										<input type="text" name="sxPlan.keepTime" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>实训地点：</label>
									<div class="kv-item-content">
										<input type="text" name="sxPlan.place" />
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>教员：</label>
									<div class="kv-item-content">
										<select style="width:130px" name="sxPlan.teacher">
											<c:forEach items="${teachers}" var="teacher">
												<option value="${teacher.id}">${teacher.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="kv-item clearfix">
									<label>受训单位</label>
									<div class="kv-item-content">
										<select style="width:130px" name="sxPlan.sxdwId">
											<c:forEach items="${dws}" var="dw">
												<option value="${dw.o_id}">${dw.o_name}</option>
											</c:forEach>
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
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<!--编辑模态框end-->




	<!--设置受训对象模态框begin-->
	<div class="modal fade" id="setObjectModal" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="setObjectTitle">设置受训对象</h4>
				</div>
				<div class="modal-body">
					<table class="table">
						<tr>
							<td>
								 <div class="input-group">
							            <input type="text" class="form-control" placeholder="输入名称检索" onkeyup="getObjects(this)">
        						</div>
        						<br/>
								<table id="objectTable" class="table table-bordered" style="margin:0px 10px 10px 0px">
									<tbody>
									</tbody>
								</table>
							</td>
							<td>
          						 <a href="#" class="btn btn-info btn-lg" style="align:center" onclick="addObject()">
          							<span class="glyphicon glyphicon-chevron-right"></span>
        						</a>
							</td>
							<td>
								<ul id="ul" class="list-group" style="margin:10px 0px 0px 10px;width:200px;height:300px;OVERFLOW-Y:auto">
								   
								</ul>
								
							</td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="setObjects()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!--设置受训对象模态框end-->
	

	


	<script type="text/javascript">
		
	</script>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basePath%>sta/trainingInfoManage/js/traning_content_list.js">
</script>
<script type="text/javascript"
		src="<%=basePath%>jquery-easyui-1.5.3/jquery.easyui.min.js"></script>	
<script type="text/javascript"
	src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"
	type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
