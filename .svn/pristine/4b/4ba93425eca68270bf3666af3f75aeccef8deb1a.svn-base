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


<title>运维管理</title>
</head>

<body>

 <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>运维管理</h2>
            </div>
            <div class="subfiled-content">
                <div class="search-box clearfix">
                <form action="" id="conditionFrom" method="POST">
                    <div class="kv-item clearfix">
                        <label>姓名：</label>
                        <div class="kv-item-content">
                            <input type="text" name="maintenance.m_name"/>
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                        <!-- <label>创建时间：</label>
                        <div class="kv-item-content">
                        	<select name="yyGzryxxb.xb">
                        		<option value="">全部</option>
                        		<option value="男">男</option>
                        		<option value="女">女</option>
                        	</select>
                        </div> 
                        <input type="text" id="createtime" name="createtime" 
                        style="width: 200px;"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>-->
                    </div>
                    <a href="javascript:;" class="sapar-btn sapar-btn-recom query-btn">查询</a>
                </form>
                </div>
                

                <!--表格开始-->
                <div class="table">
					<!--表格操作-->
                    <div class="table-operate ue-clear">
                    	<!--三级菜单管理-->
                	<s:iterator var="qx" value="#session.xtQxs" status="statu">
						<s:if test="%{#qx.levels==3}">
							<s:if test="%{#qx.parentId==#request.id}">
								<a href="javascript:;" class="${qx.classs}">${qx.qxmc}</a>
							</s:if>
						</s:if>
					</s:iterator>
                    	<a href="javascript:;" class="add">添加</a>
                        <a href="javascript:;" class="edit">编辑</a>
                        <a href="javascript:;" class="del">删除</a> 
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
                                    <th>身份证</th>
                                    <th>电话</th>
                                    <th>登录名</th>
                                    <th>创建时间</th>
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
                 <div class="search-box clearfix" >
                    <div class="kv-item clearfix">
                        <label>姓名：</label>
                        <div class="kv-item-content">
                            <input type="hidden" name="maintenance.m_id"/>
                            <input id="updatename" type="text" name="maintenance.m_name" maxlength="100" placeholder="*必填" style="width:150px;"/>
                        </div>
                    </div>
                        <div class="kv-item clearfix" >
                        <label>性别：</label>
                        <div class="kv-item-content">
                            <select name="maintenance.m_sex" style="width:150px;" id="updatesex">
	                        	<option value="男">男</option>
	                        	<option value="女">女</option>
			              </select>
                        </div>
                    </div>
                <div class="kv-item clearfix">
                        <label>身份证：</label>
                        <div class="kv-item-content">
                          <input type="text"  name="maintenance.m_card" id="updatecard"
                          onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                          maxlength="20" placeholder="*必填" style="width:150px;"/>
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>手机：</label>
                        <div class="kv-item-content">
                           <input  type="text" id="updatecall"
                           onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
                             name="maintenance.m_call" maxlength="20" placeholder="*必填" style="width:150px;">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>登录名：</label>
                        <div class="kv-item-content">
                           <input  type="text" id="updateusername"
                           onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                           name="maintenance.m_username" maxlength="20" placeholder="*输入字母和数字" style="width:150px;">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>密码：</label>
                        <div class="kv-item-content">
                           <input  type="text" id="updatepwd"
                             name="maintenance.m_password" maxlength="30" placeholder="*必填" style="width:150px;">
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
                    			<div class="kv-item clearfix" >
                    				<input type="hidden" name="maintenance.m_jsxx_id" value="7549433975A74E4EBC8CE2A4D235A821"/>
                        			<label>姓名：</label>
                        			<div class="kv-item-content">
                            			<input id="addname" type="text" name="maintenance.m_name" maxlength="100" placeholder="*必填" style="width:150px;"/>
                       				</div>
                    			</div>
                    			
			                    <div class="kv-item clearfix">
			                        <label>性别：</label>
			                        <select id="addsex" name="maintenance.m_sex" style="width: 150px;">
			                        	<option value="男" selected="selected">男</option>
			                        	<option value="女">女</option>
			                        </select>
			                    </div>
                  <div class="kv-item clearfix">
                        <label>身份证：</label>
                        <div class="kv-item-content">
                        	<input type="text"  id="addcard"
                        	 name="maintenance.m_card" 
                        	onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                        	maxlength="20" placeholder="*必填" style="width:150px;"/>
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>手机：</label>
                        <div class="kv-item-content">
                            <input  type="text" id="addcall"
                             onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
                             name="maintenance.m_call" maxlength="20" placeholder="*必填" style="width:150px;">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>登录名：</label>
                        <div class="kv-item-content">
                           <input  type="text" id="addusername"
                           	onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                            name="maintenance.m_username" maxlength="20" placeholder="*输入字母和数字" style="width:150px;">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>密码：</label>
                        <div class="kv-item-content">
                           <input  type="text" id="addpwd"
                             name="maintenance.m_password" maxlength="30" placeholder="*必填" style="width:150px;">
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
<script type="text/javascript" src="<%=basePath%>sta/yunweiSIM/js/weihu_user_list.js">
</script>
<script type="text/javascript" src="<%=basePath%>common/js/md5.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
