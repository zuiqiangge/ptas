package com.pengyue.ptas.action.yy;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.PlanObject;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.SxProject;
import com.pengyue.ptas.bean.Teacher;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.service.yy.OrganizationService;
import com.pengyue.ptas.service.yy.SxPlanService;
import com.pengyue.ptas.service.yy.SxProjectService;
import com.pengyue.ptas.service.yy.TeacherService;
import com.pengyue.ptas.service.yy.TrainedService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.yy.YyGzryxxbService;
import com.pengyue.ptas.util.ExportUtil;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.UserIdAndParentId;
@Controller
@Scope("prototype")
public class SxPlanAction extends ActionSupport {
	@Autowired
	private SxPlanService sxPlanService;
	@Autowired
	private XtQuanXianService qxService;
	@Autowired
	private SxProjectService sxProjectService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private TrainedService trainedService;
	@Autowired
	private TeacherService teacherService;
	private SxPlan sxPlan = new SxPlan();
	private PlanObject planObject = new PlanObject();
	private Integer start;
	private Integer length;
	private Integer draw;
	
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

	
	

	public PlanObject getPlanObject() {
		return planObject;
	}

	public void setPlanObject(PlanObject planObject) {
		this.planObject = planObject;
	}

	public SxPlan getSxPlan() {
		return sxPlan;
	}

	public void setSxPlan(SxPlan sxPlan) {
		this.sxPlan = sxPlan;
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
			
			//全部实训项目
			ServletActionContext.getRequest().setAttribute("projects",sxProjectService.selectAll());
			//全部受训对象
			ServletActionContext.getRequest().setAttribute("dxs",trainedService.selectAll());
			//全部受训单位
			ServletActionContext.getRequest().setAttribute("dws",organizationService.selectAll());
			//全部教员
			ServletActionContext.getRequest().setAttribute("teachers",teacherService.selectAll());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	
	/***
	 * 异步获取列表数据
	 */
	public void getData(){
		if(null!=sxPlan.getSxProject()&&!sxPlan.getSxProject().isEmpty())
			sxPlan.setSxProject("%"+sxPlan.getSxProject()+"%");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			ResultListInfo<SxPlan> result = sxPlanService.getList(new PageInfo<SxPlan>(sxPlan,start,length));
			JSONObject json = new JSONObject();
			json.put("draw",draw);
			json.put("recordsTotal",result.getTotal());
			json.put("recordsFiltered",result.getTotal());
			json.put("data",result.getResult());
			System.out.println(JSONArray.fromObject(result.getResult()));
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 异步插入数据
	 */
	public void insert(){
		PrintWriter out = null;
		try{
				out = ServletActionContext.getResponse().getWriter();
				int num = sxPlanService.insert(sxPlan);
				out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	
	
	/***
	 * 异步修改数据
	 */
	public void update(){
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try{
			out = response.getWriter();
			int effectCount = sxPlanService.update(sxPlan);
			out.print(effectCount);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 异步删除数据
	 */
	public void delete(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String str = request.getParameter("ids");
		System.out.println(Arrays.asList(str.split(",")));
		PrintWriter out = null;
		try{
			out = response.getWriter();
			int effectCount = sxPlanService.delete(Arrays.asList(str.split(",")));
			out.print(effectCount);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
		
	}
	
	/***
	 * 异步获取实训项目名称
	 */
	public void getSxProjectName(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		PrintWriter out = null;
		try{
			SxProject result = sxProjectService.selectByPrimaryKey(id);
			out = response.getWriter();
			out.print(JSONObject.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 异步获取受训单位名称
	 */
	public void getSxdwName(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		PrintWriter out = null;
		try{
			Organization result = organizationService.selectByPrimaryKey(id);
			out = response.getWriter();
			out.print(JSONObject.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 异步获取受训对象名称
	 */
	
	
	/***
	 * 异步获取教员名称
	 */
	public void getTeacherName(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		PrintWriter out = null;
		try{
			Teacher result = teacherService.selectByPrimarykey(id);
			out = response.getWriter();
			out.print(JSONObject.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	public void getSxdxName(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		PrintWriter out = null;
		try{
			Trained result = trainedService.selectByPrimaryKey(id);
			out = response.getWriter();
			out.print(JSONObject.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	public void getAllSxPlan(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try{
			List<SxPlan> result = sxPlanService.selectAll();
			out = response.getWriter();
			out.print(JSONArray.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 获取已配置的受训对象
	 */
	public void getObjected(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String planId = request.getParameter("planId");
		PrintWriter out = null;
		try{
			List<PlanObject> planObjects = sxPlanService.selectByPlanId(planId);
			out = response.getWriter();
			out.print(JSONArray.fromObject(planObjects));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 根据受训对象id查询配置
	 */
	public void getPlanObjectByObjectId(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String objectId = request.getParameter("objectId");
		PrintWriter out = null;
		try{
			List<PlanObject> planObjects = sxPlanService.selectByObjectId(objectId);
			out = response.getWriter();
			out.print(JSONArray.fromObject(planObjects));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 根据配置属性获取配置信息
	 */
	public void getPlanObjectByProperty(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String objectId = request.getParameter("objectId");
		PrintWriter out = null;
		try{
			List<PlanObject> planObjects = sxPlanService.selectByProperty(planObject);
			out = response.getWriter();
			out.print(JSONArray.fromObject(planObjects));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 提交配置信息
	 */
	public void setObject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String objectIds = request.getParameter("objectIds");
		PrintWriter out = null;
		try{
			List<String> ids =Arrays.asList(objectIds.split(","));
			String planId = request.getParameter("planId");
			sxPlanService.deleteAll();
			int num=0;
			for(String id:ids){
				PlanObject planObject = new PlanObject();
				planObject.setPlanId(planId);
				planObject.setObjectId(id);
				num+=sxPlanService.insertPlanObject(planObject);
			}
			out = response.getWriter();
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
}