package com.pengyue.ptas.dao.xt;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.XtJsQx;
@Repository("xtJsQxDao")
public class XtJsQxDaoImpl implements XtJsQxDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/***
	 * 根据主键删除
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.xt.XtJsQxDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(XtJsQx record) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.xt.XtJsQxDao.insert",record);
	}

	@Override
	public int insertSelective(XtJsQx record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/***
	 * 根据主键查询
	 */
	@Override
	public XtJsQx selectByPrimaryKey(String id) {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.xt.XtJsQxDao.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(XtJsQx record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(XtJsQx record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/***
	 * 根据属性获取对象集合
	 */
	@Override
	public List<XtJsQx> selectByProperty(XtJsQx record) {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.xt.XtJsQxDao.selectByProperty", record);
	}

	
	/***
	 * 删除某个角色的配置
	 */
	@Override
	public int deleteByJsId(String jsId) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.xt.XtJsQxDao.deleteByJsId", jsId);
	}

	
	/***
	 * 根据实体删除配置
	 */
	@Override
	public int deleteByProperty(XtJsQx record) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.xt.XtJsQxDao.deleteByProperty",record);
	}

}
