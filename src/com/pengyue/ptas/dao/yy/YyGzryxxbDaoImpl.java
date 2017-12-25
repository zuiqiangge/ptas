package com.pengyue.ptas.dao.yy;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
@Repository("yyGzryxxbDao")
public class YyGzryxxbDaoImpl implements YyGzryxxbDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}
	
	@Override
	public int deleteByPrimaryKey(List<String> ids) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.yy.YyGzryxxbDao.deleteByIds", ids);
	}

	@Override
	public int insert(YyGzryxxb record) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.yy.YyGzryxxbDao.insert",record);
	}

	@Override
	public int insertSelective(YyGzryxxb record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public YyGzryxxb selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(YyGzryxxb record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/***
	 * 单个修改
	 */
	@Override
	public int updateByPrimaryKey(YyGzryxxb record) {
		return sqlSessionTemplate.update("com.pengyue.ptas.dao.yy.YyGzryxxbDao.updateByPrimaryKeySelective", record);
	}

	/**
	 * 条件分页查询
	 * */
	public ResultListInfo<YyGzryxxb> getList(PageInfo<YyGzryxxb> pageInfo){
		List<YyGzryxxb> result = sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.YyGzryxxbDao.getList", pageInfo);
		Integer total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.YyGzryxxbDao.getCount",pageInfo);
		return new ResultListInfo<YyGzryxxb>(result,total);
	}
}
