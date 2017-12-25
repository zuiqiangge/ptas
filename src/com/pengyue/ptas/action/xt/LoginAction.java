package com.pengyue.ptas.action.xt;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.bean.MaintenanceLog;
import com.pengyue.ptas.bean.SoftWareLog;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.bean.XtJsQx;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.service.xt.SoftWareLogService;
import com.pengyue.ptas.service.xt.XtJSAndUserService;
import com.pengyue.ptas.service.xt.XtJSService;
import com.pengyue.ptas.service.xt.XtJsQxService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.xt.XtUserService;
import com.pengyue.ptas.service.yy.MaintenanceLogService;
import com.pengyue.ptas.service.yy.MaintenanceService;
import com.pengyue.ptas.service.yy.SxPlanService;

@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport{
	@Autowired
	private XtUserService xtUserService;
	@Autowired
	private XtJSAndUserService xtJSAndUserService;
	@Autowired
	private XtJsQxService xtJsQxService;
	@Autowired
	private XtQuanXianService xtQuanXianService;
	@Autowired
	private XtJSService xtJSService;
	@Autowired
	private SoftWareLogService logService;
	private XtUser xtUser ;
	public XtUser getXtUser() {
		return xtUser;
	}
	public void setXtUser(XtUser xtUser) {
		this.xtUser = xtUser;
	}
	
	@Override
	public String execute() throws Exception {
		return null;
	}
	
	@Autowired
	MaintenanceService maintenanceService;
	
	Maintenance maintenance = new Maintenance();
	
	
	MaintenanceLog maintenanceLog = new MaintenanceLog();
	
	@Autowired
	MaintenanceLogService maintenanceLogService;
	
	/***
	 * 登陆
	 * @return
	 */
	public void login(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		PrintWriter out = null;
		Map<String, String> map =new HashMap<String, String>();
		
		try{
			out = response.getWriter();
			//先判断是否是维护人员
			if(xtUser.getLoginid()!=null){
				map.put("m_username", xtUser.getLoginid());
			}else{
				map.put("m_username", "");
			}
			if(xtUser.getPassword()!=null){
				map.put("m_password", xtUser.getPassword());
			}else{
				map.put("m_password", "");
			}
			maintenance = maintenanceService.selectLogin(map);
			if(maintenance!=null){
				//添加登录日志
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = new Date();
				String str = format.format(date);
				String url = ServletActionContext.getRequest().getRequestURI();
				String ip = getRemortIP(ServletActionContext.getRequest());
				System.out.println("uri = "+url);
				System.out.println("ip = "+ip);
				//设置参数
				maintenanceLog.setMl_ip(ip);
				maintenanceLog.setMl_url(url);
				maintenanceLog.setMl_name(maintenance.getM_name());
				maintenanceLog.setMl_mid(maintenance.getM_id());
				maintenanceLog.setMl_card(maintenance.getM_card());
				maintenanceLog.setMl_caozuo("登录");
				maintenanceLog.setMl_time(str);
				maintenanceLogService.insert(maintenanceLog);
				//维护人员登录 保存维护人员标识
				out.print(true);
				session.setAttribute("userType","1");
			}else{
				//系统用户登录
				List<XtUser> result = xtUserService.isExist(xtUser);
				if(result.size()>0){
					session.setAttribute("curUser", result.get(0));
					out.print(true);
				}else
					out.print(false);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 登出
	 * @return
	 */
	public String logout(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("curUser")!=null){
			session.removeAttribute("curUser");
		}
		if(session.getAttribute("userType")!=null){
			session.removeAttribute("userType");
			//添加登录日志
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date();
			String str = format.format(date);
			String url = ServletActionContext.getRequest().getRequestURI();
			String ip = getRemortIP(ServletActionContext.getRequest());
			System.out.println("uri = "+url);
			System.out.println("ip = "+ip);
			//设置参数
			maintenanceLog.setMl_ip(ip);
			maintenanceLog.setMl_url(url);
			maintenanceLog.setMl_name(maintenance.getM_name());
			maintenanceLog.setMl_mid(maintenance.getM_id());
			maintenanceLog.setMl_card(maintenance.getM_card());
			maintenanceLog.setMl_caozuo("退出");
			maintenanceLog.setMl_time(str);
			maintenanceLogService.insert(maintenanceLog);
		}
		
		
		return "login";
	}
	
	
	//获取客户端的真实IP
	public String getRemortIP(HttpServletRequest request) {  
	    if (request.getHeader("x-forwarded-for") == null) {  
	        return request.getRemoteAddr();  
	    }  
	    return request.getHeader("x-forwarded-for");  
	}  
	
	
	
}
