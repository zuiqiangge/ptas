package com.pengyue.ptas.service.xt;

import java.util.List;

import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.bean.XtJsQx;


public interface XtJsQxService {
	public List<XtJsQx> getListByProperty(XtJsQx x);
	int insert(XtJsQx x);
	public int deleteByJsId(String jsId);
	 int deleteByProperty(XtJsQx record);
}