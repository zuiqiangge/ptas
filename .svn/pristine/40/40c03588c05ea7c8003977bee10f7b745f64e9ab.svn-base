package com.pengyue.ptas.service.yy;

import java.util.List;

import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.SxProject;
import com.pengyue.ptas.bean.SxResource;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface SxResourceService {
	public ResultListInfo<SxResource> getList(PageInfo<SxResource> pageInfo);
	public ResultListInfo<SxResource> getListByChecked(PageInfo<SxResource> pageInfo);
	SxProject selectByPrimaryKey(String id);
	public int insert(SxResource record);
	int check(String id);
	/*public int update(YyGzryxxb record);
	public int delete(List<String> ids);*/
}
