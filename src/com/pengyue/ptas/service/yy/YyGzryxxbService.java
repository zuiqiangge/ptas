package com.pengyue.ptas.service.yy;

import java.util.List;

import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface YyGzryxxbService {
	public ResultListInfo<YyGzryxxb> getList(PageInfo<YyGzryxxb> pageInfo);
	
	public int insert(YyGzryxxb record);
	public int update(YyGzryxxb record);
	public int delete(List<String> ids);
}
