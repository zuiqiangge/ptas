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
<link rel="stylesheet" href="<%=basePath%>sta/css/index_inner.css" />

<title>首页</title>
</head>
<body>
	<div id="container">
    <div id="bd">
    	<div class="wrap clearfix">
        	<div class="sidebar">
            	<h2 class="sidebar-header"><p>功能导航</p></h2>
                <ul class="nav">
                	<!--二级菜单管理 -->
                	<s:set name="isfirst" value="true"/>
            		<s:set name="qxs" value=""/>
                	<s:iterator var="qx" value="#request.qxs" status="statu">
								<s:if test="%{#isfirst}">
						 			<s:set name="qxs" value="#qx"/>
						 			<s:set name="isfirst" value="false"/>
						 			<li class="current"><div class="nav-header"><a href="javascript:;" data-src="${qx.url}?id=${qx.id}" class="clearfix"><span>${qx.qxmc}</span><i class="icon"></i></a></div>
						 <!-- 				<ul class="subnav">
                            <li><a href="javascript:;" data-src="my_order.html">待付款订单</a></li>
                            <li><a href="javascript:;" data-src="my_order.html">处理中订单</a></li>
                            <li><a href="javascript:;" data-src="my_order.html">已出票订单</a></li>
                            <li><a href="javascript:;" data-src="my_order.html">已取消订单</a></li>
                            <li><a href="javascript:;" data-src="my_order.html">问题订单</a></li>
                        </ul> -->
						 			</li>
								</s:if>
								<s:else>
									<li class="${qx.classs}">
										<div class="nav-header">
											<a href="javascript:;" data-src="${qx.url}?id=${qx.id}" class="clearfix"><span>${qx.qxmc}</span><i class="icon"></i></a>
										</div>
									</li>
								</s:else>
					</s:iterator>
                	<%-- <li class="office current"><div class="nav-header"><a href="javascript:;" data-src="hard_set_list.jsp" class="clearfix"><span>硬件配置管理</span><i class="icon"></i></a></div></li>
                    <li class="gongwen"><div class="nav-header"><a href="javascript:;" data-src="soft_version_list.jsp" class="clearfix"><span>软件版本管理</span><i class="icon"></i></a></div></li> --%>
                </ul>
            </div>
            <div class="content">
            	<iframe src="${qxs.url}?id=${qxs.id}" id="iframe" width="100%" height="100%" frameborder="0"></iframe>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript" src="<%=basePath%>sta/js/index_inner.js"></script>
</html>