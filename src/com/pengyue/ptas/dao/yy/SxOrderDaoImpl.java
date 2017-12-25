package com.pengyue.ptas.dao.yy;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
@Repository("sxOrderDao")
public class SxOrderDaoImpl implements SxOrderDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public ResultListInfo<SxOrder> getList(PageInfo<SxOrder> pageInfo) {
		List<SxOrder> result =  sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.SxOrderDao.getList",pageInfo);
		int total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.SxOrderDao.getCount",pageInfo);
		return new ResultListInfo<SxOrder>(result, total);
	}
	@Override
	public int insert(SxOrder sxOrder) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.yy.SxOrderDao.insert",sxOrder);
	}
	@Override
	public int update(SxOrder sxOrder) {
		return sqlSessionTemplate.update("com.pengyue.ptas.dao.yy.SxOrderDao.updateByPrimaryKeySelective", sxOrder);
	}
	@Override
	public int delete(List<String> list) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.yy.SxOrderDao.delete", list);
	}

}
