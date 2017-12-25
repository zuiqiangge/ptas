package com.pengyue.ptas.action.xt;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pengyue.ptas.bean.Dictionary;
import com.pengyue.ptas.bean.DictionaryValue;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.bean.XtJsQx;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.service.xt.WeiHuService;
import com.pengyue.ptas.service.xt.XtJSAndUserService;
import com.pengyue.ptas.service.xt.XtJSService;
import com.pengyue.ptas.service.xt.XtJsQxService;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.service.xt.XtUserService;
import com.pengyue.ptas.util.TypecodeAndValue;
import com.pengyue.ptas.util.UserIdAndParentId;

@Controller
@Scope("prototype")
public class WeiHuAction extends ActionSupport{
	
	@Autowired
	private XtQuanXianService xtQuanXianService;
	@Autowired
	private WeiHuService weiHuService;
	private Dictionary dic = new Dictionary();
	private DictionaryValue value = new DictionaryValue();
	public Dictionary getDic() {
		return dic;
	}
	public void setDic(Dictionary dic) {
		this.dic = dic;
	}
	public DictionaryValue getValue() {
		return value;
	}
	public void setValue(DictionaryValue value) {
		this.value = value;
	}


	/***
	 * 跳转数据维护页面
	 */
	public String list(){
		try{
			//获取上级菜单id
			String id = ServletActionContext.getRequest().getParameter("id");
			//查询出id下级登陆用户的权限
			XtUser xtUser = (XtUser)ServletActionContext.getRequest().getSession().getAttribute("curUser");
			List<XtQuanXian> qxs = xtQuanXianService.selectByUserIdAndParentId(new UserIdAndParentId(xtUser.getId(),id));
			ServletActionContext.getRequest().setAttribute("qxs",qxs);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "list";
	}
	
	
	public void getData(){
				PrintWriter out = null;
				try{
					String id = ServletActionContext.getRequest().getParameter("id");
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("utf-8");
					out = response.getWriter();
					JSONArray reult = null;
					if(null==id)
						reult = setDic(weiHuService.getAll());
					else
						reult = setDicValue(weiHuService.selectValueByDicId(id));
					out.print(reult);
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(null!=out)
						out.close();
				}
	}
	
	public JSONArray setDic(List<Dictionary> dics){
		JSONArray list = new JSONArray();
		for(Dictionary dic:dics){
			JSONObject obj = new JSONObject();
			obj.put("id",dic.getId());
			obj.put("name",dic.getTypeName());
			obj.put("pId",null);
			obj.put("isParent",true);
			obj.put("dic",dic);
			list.add(obj);	
		}
		return list;
	}
	
	
	public JSONArray setDicValue(List<DictionaryValue> dicValues){
		JSONArray list = new JSONArray();
		for(DictionaryValue value:dicValues){
			JSONObject obj = new JSONObject();
			obj.put("id",value.getId());
			obj.put("name",value.getName());
			obj.put("pId",value.getDicId());
			obj.put("isParent",false);
			obj.put("value",value);
			list.add(obj);	
		}
		return list;
	}

	
	/***
	 * 添加字典
	 */
	public void addDic(){
		PrintWriter out = null;
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			int num = weiHuService.insertDic(dic);
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 添加参数
	 */
	public void addValue(){
		PrintWriter out = null;
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			int num = weiHuService.insertValue(value);
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}

	
	/***
	 * 修改字典
	 */
	public void editDic(){
		PrintWriter out = null;
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			int num = weiHuService.updateDic(dic);
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	
	/***
	 * 修改参数
	 */
	public void editValue(){
		PrintWriter out = null;
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			int num = weiHuService.updateValue(value);
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}

	
	/***
	 * 删除字典
	 */
	public void deleteDic(){
		PrintWriter out = null;
		try{
			String id = ServletActionContext.getRequest().getParameter("id");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			int num = weiHuService.deleteDic(Arrays.asList(id.split(",")));
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	public void deleteDicValue(){
		PrintWriter out = null;
		try{
			String id = ServletActionContext.getRequest().getParameter("id");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			int num = weiHuService.deleteValue(Arrays.asList(id.split(",")));
			out.print(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
	
	/**
	 * 获取对应字典的某个值得name
	 */
	public void getDictionaryName(){
		PrintWriter out = null;
		try{
			String typeCode = ServletActionContext.getRequest().getParameter("typeCodes");
			String value = ServletActionContext.getRequest().getParameter("values");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			DictionaryValue va =  weiHuService.getValuesByCodeAndValue(new TypecodeAndValue(typeCode,value));
			out.print(JSONObject.fromObject(va));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out)
				out.close();
		}
	}
}
