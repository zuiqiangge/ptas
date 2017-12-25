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


<title>信息导出</title>
</head>

<body>
 <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>信息导出</h2>
            </div>
            <div class="subfiled-content">
                <div class="search-box clearfix">
                <form action="" id="conditionFrom" method="POST">
                    <div class="kv-item clearfix" style="width: 420px;">
                        <label style="width: 120px;">选择需要导出信息：</label >
                        <div class="kv-item-content">
                            <select name="export.type" id="selecttype">
                            	<c:if test="${ sType=='1'}">
                            		<option selected="selected" value="1">受训对象</option>
                            		<option value="2">工作人员</option>
                            		<option value="3">实训信息</option>
                            	</c:if>
                            	<c:if test="${ sType=='2'}">
                            		<option  value="1">受训对象</option>
                            		<option selected="selected" value="2">工作人员</option>
                            		<option value="3">实训信息</option>
                            	</c:if>
                            	<c:if test="${ sType=='3'}">
                            		<option  value="1">受训对象</option>
                            		<option  value="2">工作人员</option>
                            		<option  selected="selected" value="3">实训信息</option>
                            	</c:if>
                        	</select>
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                    	<a href="javascript:;" class="sapar-btn sapar-btn-recom query-btn">查询</a>
                    </div>
                    <a style="margin-left: 20px;" class="sapar-btn" onclick="exportMes()">导出到</a>
                     <div class="kv-item clearfix" style="margin-left: 10px;">
                     	<input  style="margin-top: 5px; width: 20px;" type="text" id="sysPath" name="sysPath" value="D"/>:/xxx.xls
                     </div>
                </form>
                </div>
                
                <input id="sType" type="hidden" value="${sType}"/>

                <!--表格开始-->
                <div class="table">
                    <!--表格具体内容-->
                        <table id="myTable" class="cell-border">
                            <thead>
                                <tr id="tabhead">
                                   
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
                 <div class="search-box clearfix" style="width: 400px;">
                    <div class="kv-item clearfix">
                        <label>单位名称：</label>
                        <div class="kv-item-content">
                            <input type="hidden" name="organization.o_id"/>
                            <input id="updatename" type="text" name="organization.o_name" placeholder="*必填" maxlength="100"/>
                        </div>
                    </div>
                        <div class="kv-item clearfix" style="width: 400px;">
                        <label>介绍：</label>
                        <div class="kv-item-content">
                            <textarea id="updateintroduce" name="organization.o_introduce" rows="5" cols="" style="width: 320px;" maxlength="500"></textarea>
                        </div>
                    </div>
                <div class="kv-item clearfix">
                        <label>电话：</label>
                        <div class="kv-item-content">
                            <input id="updatecall" type="text" name="organization.o_call" placeholder="*必填"
                            onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                            maxlength="20"/>
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>邮件：</label>
                        <div class="kv-item-content">
                            <input onblur="validateemailUpdate()" id="updateemail" type="text" name="organization.o_email"
                            maxlength="100">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>涉密人数：</label>
                        <div class="kv-item-content">
                            <input id="updatenum" type="text" name="organization.o_num"
                            onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                            maxlength="10">
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
                    			<div class="kv-item clearfix" style="width: 400px;">
                        			<label>单位名称：</label>
                        			<div class="kv-item-content">
                            			<input type="text" id="addname" name="organization.o_name" maxlength="100" placeholder="*必填"/>
                       				</div>
                    			</div>
                    			
			                    <div class="kv-item clearfix" style="width: 400px;">
			                        <label>介绍：</label>
			                        <textarea id="addintroduce" name="organization.o_introduce" rows="5" style="width: 320px;" maxlength='500'></textarea>
			                    </div>
                  <div class="kv-item clearfix">
                        <label>电话：</label>
                        <div class="kv-item-content">
                        	<input type="text" id="addcall" name="organization.o_call"
                        	onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                        	maxlength="20" placeholder="*必填"/>
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>邮件：</label>
                        <div class="kv-item-content">
                            <input onblur="validateemailAdd()" id="addemail" type="text" name="organization.o_email" maxlength="100">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>涉密人数：</label>
                        <div class="kv-item-content">
                            <input type="text" name="organization.o_num" id="addnum"
                            onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                            maxlength="10">
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
<script type="text/javascript" src="<%=basePath%>sta/system/js/data_export_list.js">

</script>
<script type="text/javascript" src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
