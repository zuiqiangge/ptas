package com.pengyue.ptas.action.xt;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.service.xt.XtJSAndUserService;
import com.pengyue.ptas.service.xt.XtJSService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.xt.XtUserService;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.UserIdAndParentId;
@Controller
@Scope("prototype")
public class XtUserAction extends ActionSupport{
	@Autowired
	private XtUserService xtUserService;
	@Autowired
	private XtQuanXianService qxService;
	@Autowired
	private XtJSService xtJSService;
	@Autowired
	private XtJSAndUserService xtJSAndUserService;
	private XtUser xtUser = new XtUser();
	private Integer start;
	private Integer length;
	private Integer draw;
	
	
	public XtUser getXtUser() {
		return xtUser;
	}
	public void setXtUser(XtUser xtUser) {
		this.xtUser = xtUser;
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
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	
	
	
	/***
	 * 跳转系统用户列表页
	 */
	public String list(){
		try{
			//获取上级菜单id
			String id = ServletActionContext.getRequest().getParameter("id");
			//查询出id下级登陆用户的权限
			XtUser xtUser = (XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser");
			List<XtQuanXian> qxs = qxService.selectByUserIdAndParentId(new UserIdAndParentId(xtUser.getId(),id));
			ServletActionContext.getRequest().setAttribute("qxs",qxs);
			
			
			//角色集合
			ServletActionContext.getRequest().setAttribute("jss",xtJSService.selectAll());;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "list";
	}
	
	/***
	 * 异步获取系统用户数据
	 */
	public void getXtUserData(){
		PrintWriter out = null;
		try{
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			out = ServletActionContext.getResponse().getWriter();
			ResultListInfo<XtUser> result = xtUserService.getListData(new PageInfo<XtUser>(xtUser, start, length));
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
	 * 插入记录
	 */
	public void insert(){
		PrintWriter out = null;
		try{
			out = ServletActionContext.getResponse().getWriter();
			int effectNum = xtUserService.insert(xtUser);
			out.print(effectNum);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 修改记录
	 */
	public void update(){
		PrintWriter out = null;
		try{
			out = ServletActionContext.getResponse().getWriter();
			int effectNum = xtUserService.update(xtUser);
			out.print(effectNum);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 删除记录
	 */
	public void delete(){
		PrintWriter out = null;
		try{
			out = ServletActionContext.getResponse().getWriter();
			List<String> ids = Arrays.asList(ServletActionContext.getRequest().getParameter("ids").split(","));
			int effectNum = xtUserService.delete(ids);
			out.print(effectNum);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 查验用户和密码
	 */
	public void checkUser(){
		PrintWriter out = null;
		try{
			out = ServletActionContext.getResponse().getWriter();
			List<XtUser> result = xtUserService.isExist(xtUser);
			out.print(result.size());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 修改密码
	 */
	public void updatePwd(){
		PrintWriter out = null;
		try{
			out = ServletActionContext.getResponse().getWriter();
			String newPwd = ServletActionContext.getRequest().getParameter("xtUser.newPassWord");
			xtUser.setPassword(newPwd);
			int result = xtUserService.update(xtUser);
			out.print(result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	private String roles;
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	/***
	 * 设置角色
	 */
	public void setRole(){
		PrintWriter out = null;
		try{
			out = ServletActionContext.getResponse().getWriter();
			String id =ServletActionContext.getRequest().getParameter("id");
			System.out.println(id);
			System.out.println(roles);
			xtJSAndUserService.deleteAllByUserId(id);
			String[] roless = roles.split(",");
			int num=0;
			for(int i=0;i<roless.length;i++){
				XtJSAndUser jsUser = new XtJSAndUser();
				jsUser.setUserid(id);
				jsUser.setJsid(roless[i]);
				num+=xtJSAndUserService.insert(jsUser);
			}
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 设置角色
	 */
	public void getRole(){
		PrintWriter out = null;
		try{
			out = ServletActionContext.getResponse().getWriter();
			String id =ServletActionContext.getRequest().getParameter("id");
			List<XtJS> jss = xtJSService.selectByUserId(id);
			out.print(JSONArray.fromObject(jss));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
}
