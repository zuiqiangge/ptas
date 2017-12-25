package com.pengyue.ptas.dao.xt;

import java.util.List;

import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface XtUserDao {
    int deleteByPrimaryKey(String id);
    /***
     * 批量id删除
     * @param ids
     * @return
     */
    public int deleteByPrimaryKey(List<String> ids);

    int insert(XtUser record);

    int insertSelective(XtUser record);

    XtUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(XtUser record);

    int updateByPrimaryKey(XtUser record);
    
    /**
     * 根据对象属性查找对象
     * @param record
     * @return
     */
    List<XtUser> selectByEntity(XtUser record);
    
    
    /***
     * 条件分页查询
     * @param pageInfo
     * @return
     */
    ResultListInfo<XtUser> getList(PageInfo<XtUser> pageInfo);
}