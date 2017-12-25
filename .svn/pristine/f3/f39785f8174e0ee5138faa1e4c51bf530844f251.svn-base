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
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>sta/personal/css/personal_list.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>sta/personal/css/user.css" />
<link href="<%=basePath%>common/js/DataTables-1.10.15/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">


<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css" type="text/css">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" type="text/css">


<title>实训资源管理</title>
</head>

<body>
<form id="downloadForm" action="sxResource_download" method="post">
	<input type="hidden" value="" id="fileName" name="fileName"/>
</form>
 <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>实训资源管理</h2>
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
                                    <th>资源名称</th>
                                    <th>所属项目</th>
                                    <th>url</th>
                                    <th>类型</th>
                                    <th>状态</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                </div><!--表格结束-->
            </div>
        </div>
    </div>
   <!--审核模态框begin-->
   <div class="modal fade" id="checkModal" role="dialog" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="checkModalTitle">是否确定审核?</h4>
	            </div>
	            <div class="modal-body">
	            	<form id="checkForm">
	            		<input type="hidden" name="id"/>
	            	</form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="check()">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
    	</div><!-- /.modal -->
	</div>
   <!--审核模态框end-->
      
    
    
   <!--上传模态框begin-->
   <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="addModalTitle">添加</h4>
	            </div>
	             <div class="modal-body">
	         		<form id="uploadForm" action="" method="post" enctype="multipart/form-data">
        				<input type="file" name="file"><br>
        				<select name="sxResource.sxxmId" class="form-control">
	        				<c:forEach items="${projects}" var="p">
	        					<option value="${p.id}">${p.name}</option>
	        				</c:forEach>
        				</select>
    				</form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="upload()">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
    	</div><!-- /.modal -->
	</div>
   <!--上传模态框end-->
   
  
   
   
	<script type="text/javascript">
	
</script>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>sta/trainingInfoManage/js/traning_resources_list.js">

</script>
<script type="text/javascript" src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
