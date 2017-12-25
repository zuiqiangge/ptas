package com.pengyue.ptas.dao.yy;

import java.util.List;

import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface SxOrderDao {
	public ResultListInfo<SxOrder> getList(PageInfo<SxOrder> pageInfo);
	int insert(SxOrder sxPlan);
	public int update(SxOrder record);
	int delete(List<String> list);
}