package com.pengyue.ptas.action.yy;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.pengyue.ptas.bean.AttackLog;
import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.SxProject;
import com.pengyue.ptas.bean.Teacher;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.service.yy.AttackLogService;
import com.pengyue.ptas.service.yy.HardWareService;
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
public class HardAction extends ActionSupport {
	@Autowired
	private XtQuanXianService qxService;
	@Autowired
	private HardWareService hardWareService;
	@Autowired
	private AttackLogService attackLogService;
	//收集表单对象的属性
	private HardWare hardWare = new HardWare();
	private AttackLog attackLog = new AttackLog();
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

	
	public HardWare getHardWare() {
		return hardWare;
	}

	public void setHardWare(HardWare hardWare) {
		this.hardWare = hardWare;
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
			
			ServletActionContext.getRequest().setAttribute("count",attackLogService.getAllCount());
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
			ResultListInfo<HardWare> result = hardWareService.getList(new PageInfo<HardWare>(hardWare,start,length));
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
				int num = hardWareService.insert(hardWare);
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
			int effectCount = hardWareService.update(hardWare);
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
			int effectCount =hardWareService.delete(Arrays.asList(str.split(",")));
			out.print(effectCount);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
		
	}
	
	
	
	
	/***
	 * 异步获取攻击日志列表数据
	 */
	public void getLogList(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			ResultListInfo<AttackLog> result = attackLogService.getList(new PageInfo<AttackLog>(attackLog,start,length));
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
	 * 获取攻击类型
	 */
	public void getAttackType(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			List<String> result = attackLogService.getAttackType();
			out.print(JSONArray.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	

	/***
	 * 获取攻击类型数量
	 */
	public void getAttackTypes(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			List<String> result = attackLogService.getAttackTypes();
			out.print(JSONArray.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 获取攻击类型数量
	 */
	public void getHardIps(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			List<Map<Object,Object>> result = attackLogService.getHardIps();
			List<String> names = new ArrayList<String>();
			List<String> values = new ArrayList<String>();
			for(Map<Object,Object> map:result){
				names.add(map.get("name").toString());
				values.add(map.get("value").toString());
			}
			JSONObject json = new JSONObject();
			json.put("names", names);
			json.put("values", values);
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 获取攻击类型数量
	 */
	public void getRunStates(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			List<String> result = attackLogService.getRunStates();
			out.print(JSONArray.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 获取攻击类型数量
	 */
	public void getRunStatesAndValues(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			List<String> result = attackLogService.getRunStatesAndValues();
			out.print(JSONArray.fromObject(result));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		AttackLogService attack = context.getBean(AttackLogService.class);
		List<Map<Object,Object>> sts = attack.getHardIps();
		for(Map<Object,Object> map:sts){
			System.out.println(map.get("name"));
			System.out.println(map.get("value").toString());
		}
	}
	
	
}
