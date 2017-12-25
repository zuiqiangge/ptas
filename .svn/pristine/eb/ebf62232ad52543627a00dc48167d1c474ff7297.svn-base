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
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.5.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.5.3/themes/icon.css">
<link rel="stylesheet" href="zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css" type="text/css">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" type="text/css">

<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
<title>系统权限管理</title>
</head>

<body>

 <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>系统权限管理</h2>
            </div>
         </div>
   
 <div class="zTreeDemoBackground left">
   <ul id="treeDemo" class="ztree"></ul>
</div>
   
 
 <!--添加模态框begin-->
   <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="addModalTitle">添加</h4>
	            </div>
	             <div class="modal-body">
	         		<form id="addForm">
                    	<div class="subfiled-content">
                			<div class="search-box clearfix">
                    			<div class="kv-item clearfix">
                        			<label>权限名：</label>
                        			<div class="kv-item-content">
                            			<input type="text" name="xtQx.qxmc"/>
                            			<input type="hidden" name="xtQx.parentId"/>
                       				</div>
                    			</div>
			               </div>  
			               <div class="search-box clearfix">
                    			<div class="kv-item clearfix">
                        			<label>url：</label>
                        			<div class="kv-item-content">
                            			<input type="text" name="xtQx.url"/>
                       				</div>
                    			</div>
			               </div> 
			               
			               <div class="search-box clearfix">
                    			<div class="kv-item clearfix">
                        			<label>class样式：</label>
                        			<div class="kv-item-content">
                            			<input type="text" name="xtQx.classs"/>
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
	        </div><!-- /.modal-content -->
    	</div><!-- /.modal -->
	</div>
   <!--添加模态框end-->   
   
	
</body>

<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>sta/system/js/qx_manage.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
<script type="text/javascript" src="<%=basePath%>zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=basePath%>zTree_v3-master/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="<%=basePath%>zTree_v3-master/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript">



</script>


</html>
