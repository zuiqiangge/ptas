package com.pengyue.ptas.dao.yy;

import com.pengyue.ptas.bean.SxResource;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface SxResourceDao {
   public ResultListInfo<SxResource> getList(PageInfo<SxResource> pageINfo);
   public int insert(SxResource record);
   public ResultListInfo<SxResource> getListByChecked(PageInfo<SxResource> pageInfo);
   int check(String id);
}