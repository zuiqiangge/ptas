<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/mytaglib" prefix="lq"%>
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


<title>工作人员管理</title>
</head>

<body>

 <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>工作人员管理</h2>
            </div>
            <div class="subfiled-content">
                <div class="search-box clearfix">
                <form action="" id="conditionFrom" method="POST">
                    <div class="kv-item clearfix">
                        <label>姓名：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.name"/>
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                        <label>性别：</label>
                        <div class="kv-item-content">
                        	<lq:mySelect name="yyGzryxxb.xb" query="true" typeCode="sex"/>
                        </div>
                    </div>
                    <a href="javascript:;" class="sapar-btn sapar-btn-recom query-btn">查询</a>
                </form>
                </div>
                

                <!--表格开始-->
                <div class="table">
					<!--表格操作-->
                    <div class="table-operate ue-clear">
                    <!--三级菜单管理-->
                	<s:iterator var="qx" value="#request.qxs" status="statu">
								<a href="javascript:;" class="${qx.classs}">${qx.qxmc}</a>
					</s:iterator>
                        <a href="javascript:;" class="selected">整页选中</a>
                        <a href="javascript:;" class="unselected">取消选中</a>
                    </div>
                    <!--表格具体内容-->
                        <table id="myTable" class="cell-border">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>在编情况</th>
                                    <th>擅长领域</th>
                                    <th>联系方式</th>
                                    <th>部门代码</th>
                                    <th>年龄</th>
                                    <th>身份证号</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                </div><!--表格结束-->
            </div>
        </div>
    </div>
    <!--删除模态框begin-->
   <div class="modal fade" id="delModal" role="dialog" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
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
	        </div><!-- /.modal-content -->
    	</div><!-- /.modal -->
	</div>
   <!--删除模态框end-->
      
    <!--编辑模态框begin-->
   <div class="modal fade" id="editModal" role="dialog" aria-hidden="true">
    	<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="addModalTitle">编辑</h4>
	            </div>
	             <div class="modal-body">
	          <form id="editForm">
               <div class="subfiled-content">
                 <div class="search-box clearfix">
                    <div class="kv-item clearfix">
                        <label>姓名：</label>
                        <div class="kv-item-content">
                            <input type="hidden" name="yyGzryxxb.id"/>
                            <input type="text" name="yyGzryxxb.name"/>
                        </div>
                    </div>
                        <div class="kv-item clearfix">
                        <label>性别：</label>
                        <div class="kv-item-content">
                        	<lq:mySelect name="yyGzryxxb.xb" query="false" typeCode="sex"/>
                        </div>
                    </div>
                <div class="kv-item clearfix">
                        <label>在编情况：</label>
                        <div class="kv-item-content">
			                <lq:mySelect name="yyGzryxxb.zbflag" query="false" typeCode="zbflag"/>
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>擅长领域：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.scly">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>联系方式：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.lxfs">
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                        <label>部门代码：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.bm">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>年龄：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.age">
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                        <label>身份证号：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.sfzh">
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
   <!--编辑模态框end-->
    
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
                        			<label>姓名：</label>
                        			<div class="kv-item-content">
                            			<input type="text" name="yyGzryxxb.name"/>
                       				</div>
                    			</div>
			                    <div class="kv-item clearfix">
			                        <label>性别：</label>
			                        <div class="kv-item-content">
			                            <lq:mySelect name="yyGzryxxb.xb" query="false" typeCode="sex"/>
			                        </div>
			                    </div>
                  <div class="kv-item clearfix">
                        <label>在编情况：</label>
                        <div class="kv-item-content">
                        	 <lq:mySelect name="yyGzryxxb.zbflag" query="false" typeCode="zbflag"/>
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>擅长领域：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.scly">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>联系方式：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.lxfs">
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                        <label>部门代码：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.bm">
                        </div>
                    </div>
                  <div class="kv-item clearfix">
                        <label>年龄：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.age">
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                        <label>身份证号：</label>
                        <div class="kv-item-content">
                            <input type="text" name="yyGzryxxb.sfzh">
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
   
  
   
   
	<script type="text/javascript">
	
</script>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>sta/personal/js/personal_list.js">

</script>
<script type="text/javascript" src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
