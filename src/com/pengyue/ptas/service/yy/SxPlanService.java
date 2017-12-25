package com.pengyue.ptas.service.yy;

import java.util.List;

import com.pengyue.ptas.bean.PlanObject;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface SxPlanService {
	public ResultListInfo<SxPlan> getList(PageInfo<SxPlan> pageInfo);
	
	public ResultListInfo<SxPlan> getSXList(PageInfo<SxPlan> pageInfo);
	
	public int insert(SxPlan record);
	public int update(SxPlan record);
	public int delete(List<String> list);
	public List<SxPlan> selectAll();
	
	int insertPlanObject(PlanObject planObject);
	
	List<PlanObject> selectByPlanId(String planId);
	List<PlanObject> selectByObjectId(String objectId);
	public List<PlanObject> selectByProperty(PlanObject planObject);
	int deleteAll();
}