package com.pengyue.ptas.dao.xt;

import java.util.List;

import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.util.UserIdAndParentId;

public interface XtQuanXianDao {
    int deleteByPrimaryKey(String id);

    int insert(XtQuanXian record);

    int insertSelective(XtQuanXian record);

    XtQuanXian selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(XtQuanXian record);

    int updateByPrimaryKey(XtQuanXian record);
    
    public List<XtQuanXian> selectByFirstMenu();
    
    public List<XtQuanXian> selectByParentId(String id);
    
    public List<XtQuanXian> selectByRoleId(String id);
    
    public List<XtQuanXian> selectByUserId(String userId);
    
    List<XtQuanXian> selectAll();
    
    String selectMaxOrdersByParentId(String parentId);
    
    List<XtQuanXian> selectByUserIdAndParentId(UserIdAndParentId uIdPid);
    
}