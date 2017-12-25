package com.pengyue.ptas.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.service.xt.XtQuanXianService;
import com.pengyue.ptas.util.UserIdAndParentId;

public class Menu1 extends TagSupport {
	@Autowired
	private XtQuanXianService qxService;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		 try {

	            JspWriter out = this.pageContext.getOut();
	            //获取已登陆用户
	            XtUser xtUser = (XtUser)this.pageContext.getSession().getAttribute("curUser");
	            List<XtQuanXian> qxs = qxService.selectByUserIdAndParentId(new UserIdAndParentId(xtUser.getId(),id));
	            out.println("<a href='#'>fdsfsdf</a>");
	        } catch(Exception e) {
	            throw new JspException(e.getMessage());
	        }
	        return SKIP_BODY;

	    
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		super.release();
	}
	
}
