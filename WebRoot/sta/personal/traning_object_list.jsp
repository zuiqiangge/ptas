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
<link rel="stylesheet" type="text/css" href="css/personal_list.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
<link href="<%=basePath%>common/js/DataTables-1.10.15/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">


<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css" type="text/css">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" type="text/css">


<title>受训对象管理</title>
</head>

<body>

 <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>受训对象管理</h2>
            </div>
            <div class="subfiled-content">
                <div class="search-box clearfix">
                    <div class="kv-item clearfix">
                        <label>保险单号：</label>
                        <div class="kv-item-content">
                            <input type="text" placeholder="保险单号"/>
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                        <label>乘机人：</label>
                        <div class="kv-item-content">
                            <input type="text" placeholder="乘机人">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>航班日期：</label>
                        <div class="kv-item-content time-select-wrap">
                            <div class="time-select">
                                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="开始时间">
                                <i class="date-icon"></i>
                            </div>
                            <span class="line">-</span>
                            <div class="time-select">
                                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="结束时间">
                                <i class="date-icon"></i>
                            </div>
                        </div>
                    </div>
                    <a href="javascript:;" class="sapar-btn sapar-btn-recom query-btn">查询</a>
                </div>
                

                <!--表格开始-->
                <div class="table">
					<!--表格操作-->
                    <div class="table-operate ue-clear">
                    	<a href="javascript:;" class="add">添加</a>
                        <a href="javascript:;" class="edit">编辑</a>
                        <a href="javascript:;" class="del">删除</a>
                    </div>
                    <!--表格具体内容-->
                        <table id="traning_object_table">
                            <thead>
                                <tr>
                                    <th>订单号</th>
                                    <th>航班号</th>
                                    <th>起飞时间</th>
                                    <th>行程</th>
                                    <th>人数</th>
                                    <th>总价</th>
                                    <th>操作人</th>
                                    <th>申请时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- <tr class="no-data"><td colspan="10">暂时没有数据</td></tr> -->
                                <tr>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                </tr>
                                 <tr>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                </tr>
                                 <tr>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                </tr>
                                 <tr>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                </tr>
                                 <tr>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                	<td>ffsf</td>
                                </tr>
                            </tbody>
                        </table>
                </div><!--表格结束-->
            </div>
        </div>
    </div>
       
   <!--添加模态框begin-->
   <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="addModalTitle">添加</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
    	</div><!-- /.modal -->
	</div>
   <!--添加模态框end-->
   <!--编辑模态框begin-->
   <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="editModalTitle">修改</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
    	</div><!-- /.modal -->
	</div>
   <!--编辑模态框end-->
   
    <!--删除模态框begin-->
   <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="delModalTitle">删除</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
    	</div><!-- /.modal -->
	</div>
   <!--删除模态框end-->
	<script type="text/javascript">
	
</script>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript" src="js/traning_object_list.js">

</script>
<script type="text/javascript" src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
</html>
