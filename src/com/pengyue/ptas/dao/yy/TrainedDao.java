package com.pengyue.ptas.dao.yy;

import java.util.List;

import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface TrainedDao {
	public ResultListInfo<Trained> getList(PageInfo<Trained> pageInfo);
	
	public int insert(Trained bean);
	
	public int update(Trained bean);
	
	public int delete(List<String> ids);
	
	public Trained selectByPrimaryKey(String id);
	public List<Trained> selectAll();
	public List<Trained> getNoTranedList();
}