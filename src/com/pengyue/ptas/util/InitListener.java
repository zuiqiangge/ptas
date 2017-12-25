package com.pengyue.ptas.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.pengyue.ptas.service.xt.SoftWareLogService;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try{
			String root = arg0.getServletContext().getRealPath("");
			ContextUtil.basePath = root;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}   
