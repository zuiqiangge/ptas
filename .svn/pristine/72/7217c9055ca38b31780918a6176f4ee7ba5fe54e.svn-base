package com.pengyue.ptas.dao.yy;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
@Repository("hardWareDao")
public class HardWareDaoImpl implements HardWareDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public ResultListInfo<HardWare> getList(PageInfo<HardWare> pageInfo) {
		List<HardWare> result =  sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.HardWareDao.getList",pageInfo);
		int total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.HardWareDao.getCount",pageInfo);
		return new ResultListInfo<HardWare>(result, total);
	}
	@Override
	public int insert(HardWare sxOrder) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.yy.HardWareDao.insert",sxOrder);
	}
	@Override
	public int update(HardWare sxOrder) {
		return sqlSessionTemplate.update("com.pengyue.ptas.dao.yy.HardWareDao.updateByPrimaryKeySelective", sxOrder);
	}
	@Override
	public int delete(List<String> list) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.yy.HardWareDao.delete", list);
	}
	@Override
	public HardWare selectByPrimaryKey(String id) {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.HardWareDao.selectByPrimaryKey", id);
	}
	@Override
	public List<HardWare> getAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.HardWareDao.getAll");
	}

}
