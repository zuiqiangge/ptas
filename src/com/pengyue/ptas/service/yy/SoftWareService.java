package com.pengyue.ptas.service.yy;

import java.util.List;

import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.SoftWare;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface SoftWareService {
	public ResultListInfo<SoftWare> getList(PageInfo<SoftWare> pageInfo);
	
	public int insert(SoftWare record);
	public int update(SoftWare record);
	public int delete(List<String> list);
	SoftWare selectByProperty(SoftWare softWare);
}
