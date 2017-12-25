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


<title>运维报表</title>
</head>

<body>

 <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>运维报表</h2>
            </div>
            <div class="subfiled-content">
                <div class="search-box clearfix">
                <form action="" id="conditionFrom" method="POST">
                    <div class="kv-item clearfix" >
                        <label >查询时间：</label >
                        <div class="kv-item-content">
                         <input type="text" name="trained.t_time" id="addttime"
                            onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"> -
                         <input type="text" name="trained.t_time" id="addttime"
                       		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                    	<a href="javascript:;" class="sapar-btn sapar-btn-recom query-btn">查询</a>
                    </div>
                </form>
                </div>
                
                
                <div class="table" style="width: 49%;float: left;">
	                <div id="ec1" style="width: 100%;height:400px;float: left;"></div>
                </div>
                
	            
                <div class="table" style="width: 49%; float: left;">
	                <div id="ec2" style="width: 100%;height:400px;float: left;"></div>
                </div>
                
                <div class="table" style="width: 49%;float: left;">
	                <div id="ec3" style="width: 100%;height:400px;float: left;"></div>
                </div>
                
	            
                <div class="table" style="width: 49%; float: left;">
	                <div id="ec4" style="width: 100%;height:400px;float: left;"></div>
                </div>
                
                <div class="table" style="width: 100%; ">
	                <div id="ec5" style="width: 100%;height:500px;float: left;"></div>
                </div>
            </div>
        </div>
    </div>
</body>


<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>sta/yunweiSIM/js/weihu_report_list_re.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/echarts.min.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT>
</html>
