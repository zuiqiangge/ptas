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
                	<!-- <li class="office current"><div class="nav-header"><a href="<%=basePath%>weiHu_preWeiHu" data-src="weihu_user_list.jsp" class="clearfix"><span>维护人员管理</span><i class="icon"></i></a></div></li>
                    <li class="gongwen"><div class="nav-header"><a href="javascript:;" data-src="weihu_log_list.jsp" class="clearfix"><span>维护记录管理</span><i class="icon"></i></a></div></li>
                    <li class="gongwen"><div class="nav-header"><a href="javascript:;" data-src="weihu_report_list.jsp" class="clearfix"><span>运维报表</span><i class="icon"></i></a></div></li>  -->
                    <!--二级菜单管理 -->
                	<s:set name="isfirst" value="true"/>
            		<s:set name="qxs" value=""/>
            		<c:choose>
            			<c:when test="${userType=='1'}">
            				<li class="gongwen"><div class="nav-header">
            				<a href="javascript:;" data-src="yunWei_preBaoBiao" 
            				class="clearfix">
            				<span>运维报表</span><i class="icon"></i></a></div></li>
            			</c:when>
            			<c:otherwise>
            				<s:iterator var="qx" value="#request.qxs" status="statu">
								<s:if test="%{#isfirst}">
						 			<s:set name="qxs" value="#qx"/>
						 			<s:set name="isfirst" value="false"/>
						 			<li class=""><div class="nav-header"><a href="javascript:;" data-src="${qx.url}?id=${qx.id}" class="clearfix"><span>${qx.qxmc}</span><i class="icon"></i></a></div></li>
								</s:if>
								<s:else>
									<li class="${qx.classs}"><div class="nav-header"><a href="javascript:;" data-src="${qx.url}?id=${qx.id}" class="clearfix"><span>${qx.qxmc}</span><i class="icon"></i></a></div></li>
								</s:else>
							</s:iterator>
            			</c:otherwise>
            		</c:choose>
                </ul>
            </div>
            <div class="content">
            	<!-- <iframe src="preBaoBiao" id="iframe" width="100%" height="100%" frameborder="0"></iframe>  -->
            	<c:choose>
            			<c:when test="${userType=='1'}">
            				<iframe src="yunWei_preJiLu" id="iframe" width="100%" height="100%" frameborder="0"></iframe>
            			</c:when>
            			<c:otherwise>
            				<iframe src="${qxs.url}?id=${qxs.id}" id="iframe" width="100%" height="100%" frameborder="0"></iframe>
            			</c:otherwise>
            	</c:choose>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/sapar.js"></script>
<script type="text/javascript" src="<%=basePath%>sta/js/index_inner.js"></script>
</html>