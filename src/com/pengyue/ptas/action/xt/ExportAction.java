package com.pengyue.ptas.action.xt;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pengyue.ptas.bean.Export;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.bean.XtJsQx;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.service.xt.XtJSAndUserService;
import com.pengyue.ptas.service.xt.XtJSService;
import com.pengyue.ptas.service.xt.XtJsQxService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.xt.XtUserService;
import com.pengyue.ptas.service.yy.SxPlanService;
import com.pengyue.ptas.util.ExportUtil;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.UserIdAndParentId;

@Controller
@Scope("prototype")
public class ExportAction extends ActionSupport{
	@Autowired
	private XtQuanXianService xtQuanXianService;
	
	private XtUser xtUser;
	public XtUser getXtUser() {
		return xtUser;
	}
	public void setXtUser(XtUser xtUser) {
		this.xtUser = xtUser;
	}
	
	/***
	 * 跳转导出页面
	 */
	public String list(){
		try{
			//String url = ServletActionContext.getRequest().getRequestURI();
			String sType="";
			if(ServletActionContext.getRequest().getParameter("sType")!=null){
				sType = ServletActionContext.getRequest().getParameter("sType");
			}else{
				sType = "1";
			}
			//获取上级菜单id
			String id = ServletActionContext.getRequest().getParameter("id");
			//查询出id下级登陆用户的权限
			XtUser xtUser = (XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser");
			List<XtQuanXian> qxs = xtQuanXianService.selectByUserIdAndParentId(new UserIdAndParentId(xtUser.getId(),id));
			ServletActionContext.getRequest().setAttribute("qxs",qxs);
			ServletActionContext.getRequest().setAttribute("sType", sType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "list";
	}
	
	private SxPlan sxPlan = new SxPlan();
	
	public SxPlan getSxPlan() {
		return sxPlan;
	}
	public void setSxPlan(SxPlan sxPlan) {
		this.sxPlan = sxPlan;
	}

	Export export = new Export();
	public Export getExport() {
		return export;
	}
	public void setExport(Export export) {
		this.export = export;
	}
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

	@Autowired
	SxPlanService sxPlanService;
	
	/**
	 * 获取实训信息
	 */
	public void getSXData(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			ResultListInfo<SxPlan> result = sxPlanService.getSXList(new PageInfo<SxPlan>(sxPlan,start,length));
			JSONObject json = new JSONObject();
			json.put("draw",draw);
			json.put("recordsTotal",result.getTotal());
			json.put("recordsFiltered",result.getTotal());
			json.put("data",result.getResult());
			//保存实训信息用作导出
			System.out.println("---"+result.getResult());
			ExportUtil.listSxPlan=result.getResult();
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/**
	 * 导出excel
	 */
	public void mesDetails(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		String sType="";
		String exportPath="";
		int i=0;
		if(ServletActionContext.getRequest().getParameter("sType")!=null){
			sType = ServletActionContext.getRequest().getParameter("sType");
		}
		if(ServletActionContext.getRequest().getParameter("exportPath")!=null){
			exportPath = ServletActionContext.getRequest().getParameter("exportPath");
		}
		try{
			out = response.getWriter();
			if(sType.equals("1")){
				//导出受训对象信息
				i = ExportUtil.exportExcelTrained(ExportUtil.listTrained, exportPath);
			}
			if(sType.equals("2")){
				//导出工作人员
				i = ExportUtil.exportExcelYyGzryxxb(ExportUtil.listYyGzryxxb, exportPath);
			}
			if(sType.equals("3")){
				//导出工作人员
				i = ExportUtil.exportExcelSxPlan(ExportUtil.listSxPlan, exportPath);
			}
			JSONObject json = new JSONObject();
			json.put("mes", i);
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
}
