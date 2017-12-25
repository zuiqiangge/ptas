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


<title>运维日志记录</title>
</head>

<body>

 <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>运维日志记录</h2>
            </div>
            <div class="subfiled-content">
                <div class="search-box clearfix">
                <form action="" id="conditionFrom" method="POST">
                    <div class="kv-item clearfix" style="width: 400px;">
                        <label style="width: 120px;">选择列表：</label >
                        <div class="kv-item-content">
                            <select name="export.type" id="selecttype">
                            	<c:if test="${ sType=='1'}">
                            		<option selected="selected" value="1">维护记录</option>
                            		<option value="2">日志记录</option>
                            	</c:if>
                            	<c:if test="${ sType=='2'}">
                            		<option  value="1">维护记录</option>
                            		<option selected="selected" value="2">日志记录</option>
                            	</c:if>
                        	</select>
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                    	<a href="javascript:;" class="sapar-btn sapar-btn-recom query-btn">查询</a>
                    </div>
                </form>
                </div>
                
                <input id="sType" type="hidden" value="${sType}"/>

                <!--表格开始-->
                <div class="table">
	                <div class="table-operate ue-clear">
	                </div>
                    <!--表格具体内容-->
                        <table id="myTable" class="cell-border">
                            <thead>
                                <tr>
                                  <th>序号</th>
                                  <th>姓名</th>
                                  <th>身份证</th>
                                  <th>操作</th>
                                  <th>IP</th>
                                  <th>URL</th>
                                  <th>时间</th> 
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                </div><!--表格结束-->
            </div>
        </div>
    </div>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>sta/yunweiSIM/js/weihu_record_log.js">

</script>
<script type="text/javascript" src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
