package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.PlanObject;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.SxPlanDao;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service("sxPlanService")
@Transactional
public class SxPlanServiceImpl implements SxPlanService {
	@Autowired
	private SxPlanDao sxPlanDao;

	@Override
	public ResultListInfo<SxPlan> getList(PageInfo<SxPlan> pageInfo) {
		return sxPlanDao.getList(pageInfo);
	}
	
	@Override
	public ResultListInfo<SxPlan> getSXList(PageInfo<SxPlan> pageInfo) {
		return sxPlanDao.getSXList(pageInfo);
	}

	@Override
	public int insert(SxPlan record) {
		return sxPlanDao.insert(record);
	}

	@Override
	public int update(SxPlan record) {
		return sxPlanDao.update(record);
	}

	

	
	public int delete(List<String> list){
		return sxPlanDao.delete(list);
	}

	@Override
	public List<SxPlan> selectAll(){
		return sxPlanDao.selectAll();
	}

	@Override
	public int insertPlanObject(PlanObject planObject) {
		return sxPlanDao.insertPlanObject(planObject);
	}

	@Override
	public List<PlanObject> selectByPlanId(String planId) {
		return sxPlanDao.selectByPlanId(planId);
	}

	@Override
	public List<PlanObject> selectByObjectId(String objectId) {
		return sxPlanDao.selectByObjectId(objectId);
	}

	public List<PlanObject> selectByProperty(PlanObject planObject) {
		return sxPlanDao.selectByProperty(planObject);
	}

	@Override
	public int deleteAll() {
		return sxPlanDao.deleteAll();
	}
	




	
}
