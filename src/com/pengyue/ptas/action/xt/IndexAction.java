package com.pengyue.ptas.action.xt;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.bean.XtJsQx;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.service.xt.XtJSAndUserService;
import com.pengyue.ptas.service.xt.XtJSService;
import com.pengyue.ptas.service.xt.XtJsQxService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.xt.XtUserService;
import com.pengyue.ptas.service.yy.SxPlanService;
import com.pengyue.ptas.service.yy.TrainedService;
import com.pengyue.ptas.util.MessageBox;

@Controller
@Scope("prototype")
public class IndexAction extends ActionSupport{
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
	private TrainedService trainedService;
	private XtUser xtUser;
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
	
	
	/***
	 * 登陆
	 * @return
	 */
	public String index(){
		try{
			boolean flag = false;
			HttpSession session = ServletActionContext.getRequest().getSession();
			//获取登陆用户
			XtUser xtUser = (XtUser) session.getAttribute("curUser");
			//登陆用户的角色集
			List<XtJS> xtJSs = xtJSService.selectByUserId(xtUser.getId());
			for(XtJS xtJs:xtJSs){
				if(xtJs.getJsmc().equals("系统管理员"))
					flag = true;
					
			}
			//角色对应的权限
			List<XtQuanXian> qxs = new ArrayList<XtQuanXian>();
			//过滤后的一级菜单权限
			List<XtQuanXian> qxlevel1s = new ArrayList<XtQuanXian>();
			if(flag)
				qxs = xtQuanXianService.selectAll();
			else
				qxs = xtQuanXianService.selectByUserId(xtUser.getId());
			//遍历,加入等级为一的权限
			for(XtQuanXian qx:qxs){
				if(qx.getLevels().equals("1"))
					qxlevel1s.add(qx);
			}
			ServletActionContext.getRequest().setAttribute("qxs", qxlevel1s);
			
			
			//系统消息收集
			MessageBox.messages.clear();
			MessageBox.messages.addAll(trainedService.getNoTranedList());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	
	public void clearMessage(){
		MessageBox.messages.clear();
	}
	
	
	
	
}
