package com.pengyue.ptas.dao.yy;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.SxProject;
@Repository("sxProjectDao")
public class SxPorjectImpl implements SxProjectDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public SxProject selectByPrimaryKey(String id) {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.SxProjectDao.selectByPrimaryKey",id);
	}

	@Override
	public List<SxProject> selectAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.SxProjectDao.selectAll");
	}

}
