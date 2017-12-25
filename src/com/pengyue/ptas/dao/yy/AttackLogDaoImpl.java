package com.pengyue.ptas.dao.yy;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.AttackLog;
import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
@Repository("attackLogDao")
public class AttackLogDaoImpl implements AttackLogDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public ResultListInfo<AttackLog> getList(PageInfo<AttackLog> pageInfo) {
		List<AttackLog> result =  sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.AttackLogDao.getList",pageInfo);
		int total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.AttackLogDao.getCount",pageInfo);
		return new ResultListInfo<AttackLog>(result, total);
	}
	@Override
	public int insert(AttackLog attackLog) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.yy.HardWareDao.insert",attackLog);
	}
	@Override
	public int update(AttackLog attackLog) {
		return sqlSessionTemplate.update("com.pengyue.ptas.dao.yy.HardWareDao.updateByPrimaryKeySelective", attackLog);
	}
	@Override
	public int delete(List<String> list) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.yy.HardWareDao.delete", list);
	}
	@Override
	public AttackLog selectByPrimaryKey(String id) {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.HardWareDao.selectByPrimaryKey", id);
	}
	@Override
	public List<AttackLog> getAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.HardWareDao.getAll");
	}
	@Override
	public int getAllCount() {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.AttackLogDao.getCount",null);
	}
	@Override
	public List<String> getAttackType() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.AttackLogDao.getAttackType");
	}
	@Override
	public List<String> getAttackTypes() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.AttackLogDao.getAttackTypes");

	}
	@Override
	public List<Map<Object,Object>> getHardIps() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.AttackLogDao.getHardIps");
	}
	@Override
	public List<String> getRunStates() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.AttackLogDao.getRunStates");

	}
	@Override
	public List<String> getRunStatesAndValues() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.AttackLogDao.getRunStatesAndValues");
	}

}
