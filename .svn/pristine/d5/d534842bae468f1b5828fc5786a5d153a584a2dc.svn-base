package com.pengyue.ptas.dao.xt;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
/**
 * 角色
 */
@Repository("xtJSDao")
public class XtJSDaoImpl implements XtJSDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(XtJS record) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.xt.XtJSDao.insert",record);
	}

	@Override
	public int insertSelective(XtJS record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public XtJS selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(XtJS record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(XtJS record) {
		return sqlSessionTemplate.update("com.pengyue.ptas.dao.xt.XtJSDao.updateByPrimaryKey", record);
	}

	
	/***
	 * 获取全部角色
	 */
	@Override
	public List<XtJS> selectAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.xt.XtJSDao.selectAll");
	}

	/***
	 * 获取某个用户的角色
	 */
	@Override
	public List<XtJS> selectByUserId(String userId) {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.xt.XtJSDao.selectByUserId",userId);
	}

	
	/***
	 * 条件分页查询
	 */
	@Override
	public ResultListInfo<XtJS> getList(PageInfo<XtJS> pageInfo) {
		List<XtJS> result = sqlSessionTemplate.selectList("com.pengyue.ptas.dao.xt.XtJSDao.getList",pageInfo);
		int total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.xt.XtJSDao.getCount",pageInfo);
		return new ResultListInfo<XtJS>(result,total);
	}

}
