package com.pengyue.ptas.action.xt;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.bean.MaintenanceLog;
import com.pengyue.ptas.bean.MaintenanceRecord;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.xt.XtUserService;
import com.pengyue.ptas.service.yy.MaintenanceLogService;
import com.pengyue.ptas.service.yy.MaintenanceRecordService;
import com.pengyue.ptas.service.yy.MaintenanceService;
import com.pengyue.ptas.util.ExportUtil;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.UserIdAndParentId;
@Controller
@Scope("prototype")
public class YunWeiAction extends ActionSupport{
	@Autowired
	private XtUserService xtUserService;
	@Autowired
	private XtQuanXianService qxService;
	
	private Integer start;
	private Integer length;
	private Integer draw;
	
	
	
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
	 * 跳转运维管理导航页
	 */
	public String index(){
		try{
			//获取上级菜单id
			String id = ServletActionContext.getRequest().getParameter("id");
			//查询出id下级登陆用户的权限
			if(ServletActionContext.getRequest().getSession().getAttribute("userType")!=null){
				if(ServletActionContext.getRequest().getSession().getAttribute("userType").toString().equals("1")){
					//维护人员登录
				}
			}else{
				XtUser xtUser = (XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser");
				List<XtQuanXian> qxs = qxService.selectByUserIdAndParentId(new UserIdAndParentId(xtUser.getId(),id));
				ServletActionContext.getRequest().setAttribute("qxs",qxs);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	/***
	 * 跳转运维管理导航页
	 */
	public String preWeiHu(){
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
	
	@Autowired
	MaintenanceService maintenanceService;
	
	Maintenance maintenance = new Maintenance();
	
	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}

	/***
	 * 异步获取列表数据
	 */
	public void getMaintenanceListData(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		//模糊查询
		if(null!=maintenance.getM_name()&&!maintenance.getM_name().isEmpty())
			maintenance.setM_name("%"+maintenance.getM_name()+"%");
		try{
			out = response.getWriter();
			ResultListInfo<Maintenance> result = maintenanceService.getList(new PageInfo<Maintenance>(maintenance,start,length));
			JSONObject json = new JSONObject();
			json.put("draw",draw);
			json.put("recordsTotal",result.getTotal());
			json.put("recordsFiltered",result.getTotal());
			json.put("data",result.getResult());
			System.out.println("------"+result.getResult());
			//保存数据 用作导出
			//ExportUtil.listYyGzryxxb = result.getResult();
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 异步添加维护人员信息
	 */
	public void insertMaintenance(){
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date();
			String str = format.format(date);
			maintenance.setM_create_time(str);
			System.out.println("maintenance = "+maintenance.toString());
			out = response.getWriter();
			int effectCount = maintenanceService.insert(maintenance);
			out.print(effectCount);
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
	public void updateMaintenance(){
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try{
			out = response.getWriter();
			System.out.println("maintenance = "+maintenance.toString());
			int effectCount = maintenanceService.update(maintenance);
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
	public void deleteMaintenance(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String str = request.getParameter("ids");
		System.out.println(Arrays.asList(str.split(",")));
		PrintWriter out = null;
		try{
			out = response.getWriter();
			int effectCount = maintenanceService.delete(Arrays.asList(str.split(",")));
			out.print(effectCount);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
		
	}
	
	
	/***
	 * 跳转运维记录页面
	 */
	public String preJiLu(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sType="1";
		try{
			if(ServletActionContext.getRequest().getParameter("sType")!=null){
				sType = ServletActionContext.getRequest().getParameter("sType");
			}else{
				sType = "1";
			}
			//获取上级菜单id
			String id = ServletActionContext.getRequest().getParameter("id");
			//查询出id下级登陆用户的权限
			XtUser xtUser = (XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser");
			List<XtQuanXian> qxs = qxService.selectByUserIdAndParentId(new UserIdAndParentId(xtUser.getId(),id));
			list = maintenanceService.getMaintenance();
			ServletActionContext.getRequest().setAttribute("list",list);
			ServletActionContext.getRequest().setAttribute("qxs",qxs);
			ServletActionContext.getRequest().setAttribute("sType", sType);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(sType.equals("1")){
			return "record";
		}else{
			return "log";
		}
		
	}
	
	@Autowired
	MaintenanceRecordService maintenanceRecordService;
	
	MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
	
	public MaintenanceRecord getMaintenanceRecord() {
		return maintenanceRecord;
	}

	public void setMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
		this.maintenanceRecord = maintenanceRecord;
	}

	/***
	 * 异步获取列表数据
	 */
	public void getMaintenanceRecordListData(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		//模糊查询
		if(null!=maintenanceRecord.getM_name()&&!maintenanceRecord.getM_name().isEmpty())
			maintenanceRecord.setM_name("%"+maintenanceRecord.getM_name()+"%");
		try{
			out = response.getWriter();
			ResultListInfo<MaintenanceRecord> result = maintenanceRecordService.getList(new PageInfo<MaintenanceRecord>(maintenanceRecord,start,length));
			JSONObject json = new JSONObject();
			json.put("draw",draw);
			json.put("recordsTotal",result.getTotal());
			json.put("recordsFiltered",result.getTotal());
			json.put("data",result.getResult());
			System.out.println("------"+result.getResult());
			//保存数据 用作导出
			//ExportUtil.listYyGzryxxb = result.getResult();
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 异步添加报表信息
	 */
	public void insertMaintenanceRecord(){
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try{
			System.out.println("maintenanceRecord = "+maintenanceRecord.toString());
			out = response.getWriter();
			int effectCount = maintenanceRecordService.insert(maintenanceRecord);
			out.print(effectCount);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 异步修改报表数据
	 */
	public void updateMaintenanceRecord(){
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try{
			out = response.getWriter();
			System.out.println("maintenanceRecord = "+maintenanceRecord.toString());
			int effectCount = maintenanceRecordService.update(maintenanceRecord);
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
	public void deleteMaintenanceRecord(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String str = request.getParameter("ids");
		System.out.println(Arrays.asList(str.split(",")));
		PrintWriter out = null;
		try{
			out = response.getWriter();
			int effectCount = maintenanceRecordService.delete(Arrays.asList(str.split(",")));
			out.print(effectCount);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
		
	}
	
	MaintenanceLog maintenanceLog = new MaintenanceLog();
	
	public MaintenanceLog getMaintenanceLog() {
		return maintenanceLog;
	}
	public void setMaintenanceLog(MaintenanceLog maintenanceLog) {
		this.maintenanceLog = maintenanceLog;
	}

	@Autowired
	MaintenanceLogService maintenanceLogService;
	
	/***
	 * 异步获取列表数据
	 */
	public void getMaintenanceLogListData(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		//模糊查询
		if(null!=maintenanceLog.getMl_name()&&!maintenanceLog.getMl_name().isEmpty()){
			maintenanceLog.setMl_name("%"+maintenanceLog.getMl_name()+"%");
		}
		if(null!=maintenanceLog.getMl_card()&&!maintenanceLog.getMl_card().isEmpty()){
			maintenanceLog.setMl_card("%"+maintenanceLog.getMl_card()+"%");
		}
		try{
			out = response.getWriter();
			ResultListInfo<MaintenanceLog> result = maintenanceLogService.getList(new PageInfo<MaintenanceLog>(maintenanceLog,start,length));
			JSONObject json = new JSONObject();
			json.put("draw",draw);
			json.put("recordsTotal",result.getTotal());
			json.put("recordsFiltered",result.getTotal());
			json.put("data",result.getResult());
			System.out.println("------"+result.getResult());
			//保存数据 用作导出
			//ExportUtil.listYyGzryxxb = result.getResult();
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/***
	 * 跳转运维报表
	 */
	public String preBaoBiao(){
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
		return "report";
	}
	
	/**
	 * 根据时间获取分布结果（饼状图）
	 */
	public void selectDealBytime(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> mapDate = new HashMap<String, String>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> listValue = new ArrayList<Map<String,Object>>();
		Map<String, Object> valueMap =null;
		List<String> listStr=new  ArrayList<String>();
		//系统时间
		Calendar now = Calendar.getInstance();
		//当前月份
		int yue = (now.get(Calendar.MONTH) + 1);
		//获得当前年份
		int nian = now.get(Calendar.YEAR);
		//判断查询时间
		if(ServletActionContext.getRequest().getParameter("timeType")!=null){
			String type = ServletActionContext.getRequest().getParameter("timeType");
			if(type.equals("1")){
				//查询本季度
				//获取本季度的时间
				mapDate = getSelectDate(yue, nian ,type);
			}
			if(ServletActionContext.getRequest().getParameter("timeType").equals("2")){
				//查询本月
				mapDate = getSelectDate(yue, nian ,type);
			}
		}
		map.put("stime", mapDate.get("stime"));
		map.put("etime", mapDate.get("etime"));
		try {
			out = response.getWriter();
			JSONObject json = new JSONObject();
			list = maintenanceRecordService.selectBytime(map);
			for(int i=0; i<list.size(); i++ ){
				listStr.add(list.get(i).get("MD_SPECIES").toString());
				valueMap = new HashMap<String, Object>();
				valueMap.put("value", list.get(i).get("NU").toString());
				valueMap.put("name", list.get(i).get("MD_SPECIES").toString());
				listValue.add(valueMap);
			}
			json.put("listValue",listValue);
			json.put("listStr",listStr);
			out.print(json);
		} catch (Exception e) {
			System.out.println("【YunWeiAction】【selectDealBytime】:"+e);
		} finally{
			if(null!=out)
				out.close();
		}
	}
	
	/**
	 * 维护统计（柱状图）
	 */
	public void selectDealBytimeGroup(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> mapDate = new HashMap<String, String>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> listStr=new  ArrayList<String>();
		List<Integer> listInt=new  ArrayList<Integer>();
		//系统时间
		Calendar now = Calendar.getInstance();
		//当前月份
		int yue = (now.get(Calendar.MONTH) + 1);
		//获得当前年份
		int nian = now.get(Calendar.YEAR);
		//判断查询时间
		String type = "";
		if(ServletActionContext.getRequest().getParameter("timeType")!=null){
			type = ServletActionContext.getRequest().getParameter("timeType");
			if(type.equals("1")){
				//查询本季度
				//获取本季度的时间
				mapDate = getSelectDate(yue, nian ,type);
			}
			if(type.equals("2")||type.equals("3")){
				//查询本月
				mapDate = getSelectDate(yue, nian ,type);
			}
		}
		map.put("stime", mapDate.get("stime"));
		map.put("etime", mapDate.get("etime"));
		try {
			int max=0;
			out = response.getWriter();
			JSONObject json = new JSONObject();
			if(type.equals("1")||type.equals("2")){
				//季度统计/月份统计
				list = maintenanceRecordService.selectBytimeGroup(map);
				for(int i=0; i<list.size(); i++ ){
					if(Integer.parseInt(list.get(i).get("NU").toString())>max){
						//最大值 统计图表的最大值
						max = Integer.parseInt(list.get(i).get("NU").toString());
					}
					listStr.add(list.get(i).get("M_NAME").toString());
					listInt.add(Integer.parseInt(list.get(i).get("NU").toString()));
				}
				json.put("listInt",listInt);
				json.put("listStr",listStr);
				json.put("max",max);
			}
			if(type.equals("3")){
				//本月统计
				list = maintenanceRecordService.selectBytimeMonth(map);
				int month = 0;
				if(yue==2){
					month = 29;
					//2月份 先默认28天(具体闰年以后在修改详细)
				}else{
					//其他月份默认30天；
					month = 31;
				}
				/*for(int i=0; i<list.size(); i++ ){
					int k = Integer.parseInt(list.get(i).get("MD_TIME").toString().substring(8, 10));
					int count = 0;
					String days ="";
					
					
					if(Integer.parseInt(list.get(i).get("NU").toString())>max){
						//最大值 统计图表的最大值
						max = Integer.parseInt(list.get(i).get("NU").toString());
					}
				}
				*/
				int count = 0;
				for(int i=1 ; i<month; i++){
					for(int j=1; j <list.size(); j++){
						int k = Integer.parseInt(list.get(i).get("MD_TIME").toString().substring(8, 10));
						//把日期中的天取出来遍历1-30号
						count = 0;
						if(k == j){
							//如果找到了救赋值，没找到默认当天0次
							count = Integer.parseInt(list.get(i).get("NU").toString());
						}
						listStr.add(j+"");
						listInt.add(count);
					}
				}
				json.put("listInt",listInt);
				json.put("listStr",listStr);
				json.put("max",max);
			}
			out.print(json);
		} catch (Exception e) {
			System.out.println("【YunWeiAction】【selectDealBytime】:"+e);
		} finally{
			if(null!=out)
				out.close();
		}
	}
	
	public Map<String,String> getSelectDate(int yue,int nian,String type){
		Map<String,String> map=new HashMap<String, String>();
		if(type.equals("1")){
			if(yue>=1 && yue<=3){
				map.put("stime", nian+"-01");
				map.put("etime", nian+"-04");
			}
			if(yue>=4 && yue<=6){
				map.put("stime", nian+"-04");
				map.put("etime", nian+"-07");
			}
			if(yue>=7 && yue<=9){
				map.put("stime", nian+"-07");
				map.put("etime", nian+"-10");
			}
			if(yue>=10 && yue<=12){
				map.put("stime", nian+"-10");
				map.put("etime", (nian+1)+"-1");
			}
		}
		if(type.equals("2")||type.equals("3")){
			map.put("stime", nian+"-"+yue+"-01");
			//12月特殊情况
			if(yue==12){
				map.put("etime", (nian+1)+"-01-01");
			}else{
				map.put("etime", nian+"-"+(yue+1)+"-01");
			}
		}
		return map;
	}
	
	public static void main(String[] args) throws Exception {  
        Calendar now = Calendar.getInstance();  
        System.out.println("年: " + now.get(Calendar.YEAR));  
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));  
        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));  
        System.out.println("分: " + now.get(Calendar.MINUTE));  
        System.out.println("秒: " + now.get(Calendar.SECOND));  
        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());  
        System.out.println(now.getTime());  
  
        Date d = new Date();  
        System.out.println(d);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateNowStr = sdf.format(d);  
        System.out.println("格式化后的日期：" + dateNowStr);  
          
        String str = "2012-1-13 17:26:33";  //要跟上面sdf定义的格式一样  
        Date today = sdf.parse(str);  
        System.out.println("字符串转成日期：" + today);  
    }  
}
