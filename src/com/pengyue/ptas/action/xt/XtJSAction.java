package com.pengyue.ptas.action.xt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.service.xt.XtJSService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.UserIdAndParentId;
@Controller
@Scope("prototype")
public class XtJSAction extends ActionSupport{
	@Autowired
	private XtJSService xtJSService;
	@Autowired
	private XtQuanXianService qxService;
	private Integer start;
	private Integer length;
	private XtJS xtJs = new XtJS();
	private Integer draw;
	
	public Integer getDraw() {
		return draw;
	}




	public void setDraw(Integer draw) {
		this.draw = draw;
	}




	public XtJS getXtJs() {
		return xtJs;
	}




	public void setXtJs(XtJS xtJs) {
		this.xtJs = xtJs;
	}




	public XtJSService getXtJSService() {
		return xtJSService;
	}




	public void setXtJSService(XtJSService xtJSService) {
		this.xtJSService = xtJSService;
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
	 * 跳转角色管理页面
	 * @return
	 */
	public String list(){
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
		return "list";
	}
	
	
	
		
	/***
	 * 异步获取全部角色	
	 */
	public void getData(){
		PrintWriter out = null;
		try{
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("utf-8");
					out = response.getWriter();
					ResultListInfo<XtJS> result = xtJSService.getList(new PageInfo<XtJS>(xtJs,start,length));
					JSONObject json = new JSONObject();
					json.put("draw",draw);
					json.put("recordsTotal",result.getTotal());
					json.put("recordsFiltered",result.getTotal());
					json.put("data",result.getResult());
					out.print(json);
		}catch(Exception e){
				e.printStackTrace();	
		}finally{
			if(null!=out)
				out.close();
		}
	}	
	
	/***
	 * 添加角色
	 */
	public void insert(){
		PrintWriter out = null;
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			out = response.getWriter();
			int num = xtJSService.insert(xtJs);
			out.print(num);
		}catch(Exception e){
				e.printStackTrace();	
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 修改角色
	 */
	public void update(){
		PrintWriter out = null;
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			out = response.getWriter();
			int num = xtJSService.updateByPrimaryKey(xtJs);
			out.print(num);
		}catch(Exception e){
				e.printStackTrace();	
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	
}
