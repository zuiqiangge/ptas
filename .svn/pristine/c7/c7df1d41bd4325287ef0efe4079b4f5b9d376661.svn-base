package com.pengyue.ptas.dao.xt;

import java.util.List;

import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface XtJSDao {
    int deleteByPrimaryKey(String id);

    int insert(XtJS record);

    int insertSelective(XtJS record);

    XtJS selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(XtJS record);

    int updateByPrimaryKey(XtJS record);
    
    List<XtJS> selectAll();
    List<XtJS> selectByUserId(String userId);
    
    ResultListInfo<XtJS> getList(PageInfo<XtJS> pageInfo);
    
}