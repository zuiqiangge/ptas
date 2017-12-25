package com.pengyue.ptas.dao.xt;

import java.util.List;

import com.pengyue.ptas.bean.XtJSAndUser;

public interface XtJSAndUserDao {
    int deleteByPrimaryKey(String id);

    int insert(XtJSAndUser record);

    int insertSelective(XtJSAndUser record);

    XtJSAndUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(XtJSAndUser record);

    int updateByPrimaryKey(XtJSAndUser record);
    
    List<XtJSAndUser> selectByProperty(XtJSAndUser record);
    
    public int deleteAllByUserId(String userId);
}