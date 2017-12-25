package com.pengyue.ptas.dao.xt;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.XtJSAndUser;
/***
 * 用户与角色关联
 */
@Repository("xtJSAndUserDao")
public class XtJSAndUserDaoImpl implements XtJSAndUserDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(XtJSAndUser record) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.xt.XtJSAndUserDao.insert",record);
	}

	@Override
	public int insertSelective(XtJSAndUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public XtJSAndUser selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(XtJSAndUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(XtJSAndUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/***
	 * 根据属性获取集合
	 */
	@Override
	public List<XtJSAndUser> selectByProperty(XtJSAndUser record) {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.xt.XtJSAndUserDao.selectByProperty", record);
	}

	@Override
	public int deleteAllByUserId(String userId) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.xt.XtJSAndUserDao.deleteAllByUserId", userId);
	}

}
