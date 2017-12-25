package com.pengyue.ptas.util;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.pengyue.ptas.bean.XtUser;

public class IsLoginInterceptor extends MethodFilterInterceptor{

	

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		ServletActionContext.getRequest().getRequestURI();
		XtUser user = (XtUser) session.getAttribute("curUser");
		if(null!=user)
			arg0.invoke();
		return "login";
	}

}
