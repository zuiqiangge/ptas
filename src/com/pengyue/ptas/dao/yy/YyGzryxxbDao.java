package com.pengyue.ptas.dao.yy;

import java.util.List;

import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface YyGzryxxbDao {
    int deleteByPrimaryKey(String id);

    public int deleteByPrimaryKey(List<String> ids);
    
    int insert(YyGzryxxb record);

    int insertSelective(YyGzryxxb record);

    YyGzryxxb selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YyGzryxxb record);

    int updateByPrimaryKey(YyGzryxxb record);
    
    public ResultListInfo<YyGzryxxb> getList(PageInfo<YyGzryxxb> pageInfo);
}