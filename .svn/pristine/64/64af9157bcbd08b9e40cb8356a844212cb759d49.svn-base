package com.pengyue.ptas.service.xt;

import java.util.List;

import com.pengyue.ptas.bean.XtJsQx;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.util.UserIdAndParentId;

public interface XtQuanXianService {
	public List<XtQuanXian> selectByFirstMenu() ;
	public List<XtQuanXian> selectByParentId(String id) ;
	public List<XtQuanXian> selectByRoleId(String id);
	
	public List<XtQuanXian> selectByUserId(String userId);
	
	public XtQuanXian selectByPrimaryKey(String id);
	
	public List<XtQuanXian> selectAll();
	
	public int updateByPrimaryKeySelective(XtQuanXian record);
	int deleteByPrimaryKey(String id);
	public String selectMaxOrdersByParentId(String parentId) ;
	
	public int insert(XtQuanXian record);
	
	List<XtQuanXian> selectByUserIdAndParentId(UserIdAndParentId uIdPid);
}
