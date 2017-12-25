package com.pengyue.ptas.action.yy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.SxProject;
import com.pengyue.ptas.bean.SxResource;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.service.yy.OrganizationService;
import com.pengyue.ptas.service.yy.SxOrderService;
import com.pengyue.ptas.service.yy.SxPlanService;
import com.pengyue.ptas.service.yy.SxProjectService;
import com.pengyue.ptas.service.yy.SxResourceService;
import com.pengyue.ptas.service.yy.TrainedService;
import com.pengyue.ptas.service.xt.XtJSService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.yy.YyGzryxxbService;
import com.pengyue.ptas.util.MailUtil;
import com.pengyue.ptas.util.MessageBox;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.UserIdAndParentId;
@Controller
@Scope("prototype")
public class TjFxAction extends ActionSupport {
	@Autowired
	private SxResourceService sxResourceService;
	@Autowired
	private XtQuanXianService qxService;
	@Autowired
	private SxProjectService sxProjectService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private XtJSService xtJSService;
	@Autowired
	private SxOrderService sxOrderService;
	@Autowired
	private TrainedService trainedService;
	//private Trani
	private SxOrder sxOrder = new SxOrder();
	private Integer start;
	private Integer length;
	private Integer draw;
    
   
	
	
    
	

	

	public SxOrder getSxOrder() {
		return sxOrder;
	}

	public void setSxOrder(SxOrder sxOrder) {
		this.sxOrder = sxOrder;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	
	



	

	

	

	/***
	 * 进入实训计划列表页
	 * @return
	 */
	public String index(){
		try{
			//获取上级菜单id
			String id = ServletActionContext.getRequest().getParameter("id");
			//查询出id下级登陆用户的权限
			XtUser xtUser = (XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser");
			List<XtQuanXian> qxs = qxService.selectByUserIdAndParentId(new UserIdAndParentId(xtUser.getId(),id));
			ServletActionContext.getRequest().setAttribute("qxs",qxs);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	
	
	
	
	 
}
