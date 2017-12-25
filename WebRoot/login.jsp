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
<link rel="stylesheet" href="<%=basePath%>sta/css/login.css" />
<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>layer/mobile/need/layer.css">--%>

<title>登陆页面</title>
</head>
<body onkeydown="keyLogin();">
	<div id="container">
		<div id="bd">
			<div class="login">
				<form action="login" id="loginForm" method="post">
            	<div class="login-top"><h1 class="logo"></h1></div>
                <div class="login-input">
                	<p class="user ue-clear">
                    	<label>用户名</label>
                        <input type="text" name="xtUser.loginid" id="loginId"/>
                    </p>
                    <p class="password ue-clear">
                    	<label>密&nbsp;&nbsp;&nbsp;码</label>
                        <input type="password" name="xtUser.password" id="password"/>
                    </p>
                </div>
                <div class="login-btn ue-clear">
                	<a href="#" class="btn" onclick="submit()">登录</a>
                    <div class="remember ue-clear">
                    	<input type="checkbox" id="remember" />
                        <em></em>
                        <label for="remember">记住密码</label>
                    </div>
                </div>
                </form>
            </div>
		</div>
	</div>
    <div id="ft">CopyRight&nbsp;2016&nbsp;&nbsp;版权所有&nbsp;&nbsp;皖ICP备12345678号 更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a></div>
</body>
<script type="text/javascript" src="<%=basePath%>common/js/jquery.js"></script>
<SCRIPT src="<%=basePath%>layer/layer.js"></SCRIPT> 
<script type="text/javascript" src="<%=basePath%>common/js/md5.js"></script>
<script type="text/javascript">
var height = $(window).height();
$("#container").height(height);
$("#bd").css("padding-top",height/2 - $("#bd").height()/2);

$(window).resize(function(){
	var height = $(window).height();
	$("#bd").css("padding-top",$(window).height()/2 - $("#bd").height()/2);
	$("#container").height(height);
	
});

$('#remember').focus(function(){
   $(this).blur();
});

$('#remember').click(function(e) {
	checkRemember($(this));
});

function checkRemember($this){
	if(!-[1,]){
		 if($this.prop("checked")){
			$this.parent().addClass('checked');
		}else{
			$this.parent().removeClass('checked');
		}
	}
}
function submit(){
	if($("#loginId").val()==""){
		layer.tips("用户名不能为空","#loginId",{
			tips: [2, '#22B8DD']
		});
		return;
	}	
	if($("#password").val()==""){
		layer.tips("密码不能为空","#password",{
			tips: [2, '#22B8DD']
		});
		return;
	}
	
	//将密码改成md5的值
	var formData = $("#loginForm").serializeArray();
	for(var i=0;i<formData.length;i++){
		if(formData[i].name=="xtUser.password")
			formData[i].value=hex_md5(formData[i].value);
	}
	
	 $.ajax({
     	type:'post',
     	url:"<%=basePath%>login_login",
     	data:formData,
     	success:function (result){
     		if (result=="true") {
     			window.location.href = "index_index";
			}else{
				layer.tips("账号或密码不匹配！请检查重新登录","#loginId",{
					tips: [2, '#22B8DD']
				});
				return;
			}
     	},
     	error:function(jqXHR, textStatus, errorThrown) {
     		//layer.msg('系统繁忙！', {icon: 2});
 		}
     })
}
function keyLogin(){
	 if (event.keyCode==13)  //回车键的键值为13
		 submit();
	}

</script>
</html>