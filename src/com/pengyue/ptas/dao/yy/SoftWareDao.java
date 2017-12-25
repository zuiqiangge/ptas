package com.pengyue.ptas.dao.yy;

import java.util.List;

import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.SoftWare;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface SoftWareDao {
	public ResultListInfo<SoftWare> getList(PageInfo<SoftWare> pageInfo);
	int insert(SoftWare softWare);
	public int update(SoftWare softWare);
	int delete(List<String> list);
	SoftWare selectByProperty(SoftWare softWare);
}