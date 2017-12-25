package com.pengyue.ptas.service.yy;

import java.util.List;

import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface SxOrderService {
	public ResultListInfo<SxOrder> getList(PageInfo<SxOrder> pageInfo);
	
	public int insert(SxOrder record);
	public int update(SxOrder record);
	public int delete(List<String> list);
	/*public int delete(List<String> ids);*/
}
