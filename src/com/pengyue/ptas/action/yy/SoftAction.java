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
import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.SoftWare;
import com.pengyue.ptas.bean.SoftWareLog;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.SxProject;
import com.pengyue.ptas.bean.Teacher;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.service.yy.HardWareService;
import com.pengyue.ptas.service.yy.OrganizationService;
import com.pengyue.ptas.service.yy.SoftWareService;
import com.pengyue.ptas.service.yy.SxPlanService;
import com.pengyue.ptas.service.yy.SxProjectService;
import com.pengyue.ptas.service.yy.TeacherService;
import com.pengyue.ptas.service.yy.TrainedService;
import com.pengyue.ptas.service.xt.SoftWareLogService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.yy.YyGzryxxbService;
import com.pengyue.ptas.util.ExportUtil;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.UserIdAndParentId;
@Controller
@Scope("prototype")
public class SoftAction extends ActionSupport {
	@Autowired
	private XtQuanXianService qxService;
	@Autowired
	private SoftWareService softWareService;
	@Autowired
	private HardWareService hardWareService;
	@Autowired
	private SoftWareLogService logService;
	//收集表单对象的属性
	private SoftWare softWare = new SoftWare();
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

	

	public SoftWare getSoftWare() {
		return softWare;
	}

	public void setSoftWare(SoftWare softWare) {
		this.softWare = softWare;
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
			
			//全部硬件
			List<HardWare> hards = hardWareService.getALl();
			ServletActionContext.getRequest().setAttribute("hards",hards);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	
	/***
	 * 异步获取列表数据
	 */
	public void getList(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			ResultListInfo<SoftWare> result = softWareService.getList(new PageInfo<SoftWare>(softWare,start,length));
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
	 * 异步插入数据
	 */
	public void insert(){
		PrintWriter out = null;
		try{
				out = ServletActionContext.getResponse().getWriter();
				int num = softWareService.insert(softWare);
				
				//日志
				/*num+=logService.insert(new SoftWareLog("添加",
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
				((XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser")).getId(),
				softWareService.selectByProperty(softWare).getId()));*/
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
			int effectCount = softWareService.update(softWare);
			//日志
			effectCount+=logService.insert(new SoftWareLog("修改",
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
					((XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser")),
					softWare.getId()));
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
			int effectCount =softWareService.delete(Arrays.asList(str.split(",")));
		//日志
			effectCount+=logService.insert(new SoftWareLog("删除",
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
					((XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser")),
					str));
			out.print(effectCount);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
		
	}
	
	
	/***
	 * 根据id异步获取硬件
	 */
	public void getHardWareName(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("id");
		PrintWriter out = null;
		try{
			out = response.getWriter();
			HardWare hard =hardWareService.selectByPrimaryKey(id);
			out.print(JSONObject.fromObject(hard));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
		
		
		
	}
	
	
	/***
	 * 获取软件所有日志
	 */
	public void getLogList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String softId = request.getParameter("softId");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try{
			out = response.getWriter();
			List<SoftWareLog> result =logService.selectValueBySoftId(softId);
			out.print(JSONArray.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	public static void main(String[] args) {
		JSONObject s = JSONObject.fromObject(new SoftWare());
		System.out.println(s);
		s.accumulate("aa", "aa");
		System.out.println(s);
	}
	
}
