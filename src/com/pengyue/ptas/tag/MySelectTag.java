package com.pengyue.ptas.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.pengyue.ptas.bean.DictionaryValue;
import com.pengyue.ptas.service.xt.WeiHuService;


public class MySelectTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private String typeCode;
	private String name;
	private boolean query;
	private static WeiHuService weiHuService;
	
	public WeiHuService getWeiHuService() {
		return weiHuService;
	}

	public void setWeiHuService(WeiHuService weiHuService) {
		this.weiHuService = weiHuService;
	}

	public boolean isQuery() {
		return query;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		 try {

	            JspWriter out = this.pageContext.getOut();
	            List<DictionaryValue> values = weiHuService.getAllValuesByCode(typeCode);
	            String select = "<select style='width:130px' name='"+name+"'>";
	            if(query)
	            		select+="<option value=''>全部</option>";
	            for(DictionaryValue value:values){
	            	select+=" <option value='"+value.getValue()+"'>"+value.getName()+"</option>";
	            }
                select+="</select>";
                out.print(select);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	        return SKIP_BODY;

	    
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		super.release();
	}
}
