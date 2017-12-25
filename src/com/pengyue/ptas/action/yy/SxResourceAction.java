package com.pengyue.ptas.action.yy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.SxProject;
import com.pengyue.ptas.bean.SxResource;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.service.yy.OrganizationService;
import com.pengyue.ptas.service.yy.SxPlanService;
import com.pengyue.ptas.service.yy.SxProjectService;
import com.pengyue.ptas.service.yy.SxResourceService;
import com.pengyue.ptas.service.xt.XtJSService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.yy.YyGzryxxbService;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.UserIdAndParentId;
@Controller
@Scope("prototype")
public class SxResourceAction extends ActionSupport {
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
	//private Trani
	private SxResource sxResource = new SxResource();
	private Integer start;
	private Integer length;
	private Integer draw;
	private File file;
    private String fileFileName;
    
    public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	//提交过来的file的名字
    private String fileName;
    
    //提交过来的file的MIME类型
    private String fileContentType;
    private InputStream fileInputSteam;
    
	public InputStream getFileInputSteam() {
		return fileInputSteam;
	}

	public void setFileInputSteam(InputStream fileInputSteam) {
		this.fileInputSteam = fileInputSteam;
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

	
	



	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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
			
			//实训项目集合
			ServletActionContext.getRequest().setAttribute("projects", sxProjectService.selectAll());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	
	/***
	 * 异步获取列表数据
	 */
	public void getData(){
		HttpServletResponse response = ServletActionContext.getResponse();
		//当前登陆用户
		XtUser user = (XtUser) ServletActionContext.getRequest().getSession().getAttribute("curUser");
		//当前登陆用户的角色
		List<XtJS> jss = xtJSService.selectByUserId(user.getId());
		boolean f = false;
		ResultListInfo<SxResource> result = null;
		for(XtJS js:jss){
			if(js.getJsmc().equals("审核员"))
					f = true;
		}
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = null;
		try{
			out = response.getWriter();
			//审核员登陆
			if(f)
				result = sxResourceService.getList(new PageInfo<SxResource>(sxResource,start,length));
			else
				result = sxResourceService.getListByChecked(new PageInfo<SxResource>(sxResource,start,length));
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
	
	
	public void upload(){
		try{
			 String root = ServletActionContext.getServletContext().getRealPath("/upload");
		     InputStream is = new FileInputStream(file);
		     OutputStream os = new FileOutputStream(new File(root,fileFileName));
		     System.out.println("fileFileName: " + fileFileName);
		     // 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
		     //System.out.println("file: " + file.getName());
		     //System.out.println("file: " + file.getPath());
		     SxResource sxResource = new SxResource();
		     sxResource.setName(fileFileName);
		     sxResource.setSxxmId(ServletActionContext.getRequest().getParameter("sxResource.sxxmId"));
		     sxResource.setStatus(0);
		     String prefix=fileFileName.substring(fileFileName.lastIndexOf(".")+1);
		     sxResource.setType(prefix);
		     sxResource.setUrl(file.getAbsolutePath());
		     sxResourceService.insert(sxResource);
		     byte[] buffer = new byte[500];
		     int length = 0;
		     while(-1 != (length = is.read(buffer, 0, buffer.length)))
		     {
		        os.write(buffer);
		     }
		     os.close();
		     is.close();
		}
		   catch(Exception e){
			   e.printStackTrace();
		   }
	}
	
	
	
	public String download(){
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		File file = new File(root+"/"+fileName);
		fileName = file.getName();
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			fileInputSteam = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/***
	 * 异步获取列表数据
	 */
	public void insert(){
		PrintWriter out = null;
		try{
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
			//int effectCount = yyGzryxxbService.update(yyGzryxxb);
			//out.print(effectCount);
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
			//int effectCount = yyGzryxxbService.delete(Arrays.asList(str.split(",")));
			//out.print(effectCount);
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
	 * 异步获取受训对象名称
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
	public void getSxdxName(){
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
	public void check(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try{
			out = response.getWriter();
			int num = sxResourceService.check(request.getParameter("id"));
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
}
