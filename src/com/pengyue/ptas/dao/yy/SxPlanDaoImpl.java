package com.pengyue.ptas.dao.yy;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.PlanObject;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
@Repository("sxPlanDao")
public class SxPlanDaoImpl implements SxPlanDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public ResultListInfo<SxPlan> getList(PageInfo<SxPlan> pageInfo) {
		List<SxPlan> result =  sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.SxPlanDao.getList",pageInfo);
		int total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.SxPlanDao.getCount",pageInfo);
		return new ResultListInfo<SxPlan>(result, total);
	}
	
	@Override
	public ResultListInfo<SxPlan> getSXList(PageInfo<SxPlan> pageInfo) {
		List<SxPlan> result =  sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.SxPlanDao.getSXList",pageInfo);
		int total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.SxPlanDao.getCount",pageInfo);
		return new ResultListInfo<SxPlan>(result, total);
	}
	
	@Override
	public int insert(SxPlan sxPlan) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.yy.SxPlanDao.insert",sxPlan);
	}
	@Override
	public int update(SxPlan record) {
		return sqlSessionTemplate.update("com.pengyue.ptas.dao.yy.SxPlanDao.updateByPrimaryKeySelective", record);
	}
	@Override
	public int delete(List<String> list) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.yy.SxPlanDao.delete", list);
	}

	@Override
	public List<SxPlan> selectAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.SxPlanDao.selectAll");
	}

	
	/***
	 * 计划和受训对象的配置表插入
	 */
	@Override
	public int insertPlanObject(PlanObject planObject) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.yy.SxPlanDao.insertPlanObject", planObject);
	}

	
	/***
	 * 根据计划id查询配置
	 */
	@Override
	public List<PlanObject> selectByPlanId(String planId) {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.SxPlanDao.selectByPlanId", planId);
	}

	
	/***
	 * 根据受训对象id查询配置
	 */
	@Override
	public List<PlanObject> selectByObjectId(String objectId) {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.SxPlanDao.selectByObjectId", objectId);
	}

	
	/***
	 * 根据配置属性获取配置
	 */
	@Override
	public List<PlanObject> selectByProperty(PlanObject planObject) {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.SxPlanDao.selectByProperty",planObject);
	}

	@Override
	public int deleteAll() {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.yy.SxPlanDao.deleteAll");
	}
	

}