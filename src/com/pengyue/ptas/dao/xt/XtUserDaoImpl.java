package com.pengyue.ptas.dao.xt;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.XtUser;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
@Repository("xtUserDao")
public class XtUserDaoImpl implements XtUserDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}
	
	/***
	 * 批量id删除
	 */
	@Override
	public int deleteByPrimaryKey(List<String> ids) {
		return sqlSessionTemplate.delete("com.pengyue.ptas.dao.xt.XtUserDao.deleteByIds", ids);
	}

	/***
	 * 单个对象添加
	 */
	@Override
	public int insert(XtUser record) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.xt.XtUserDao.insert", record);
	}

	@Override
	public int insertSelective(XtUser record) {
		return 0;
	}

	@Override
	public XtUser selectByPrimaryKey(String id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(XtUser record) {
		return sqlSessionTemplate.update("com.pengyue.ptas.dao.xt.XtUserDao.updateByPrimaryKeySelective", record);
	}

	
	/***
	 * 单个修改对象
	 */
	@Override
	public int updateByPrimaryKey(XtUser record) {
		return sqlSessionTemplate.update("com.pengyue.ptas.dao.xt.XtUserDao.updateByPrimaryKey", record);
	}

	
	/***
	 * 根据属性查找对象
	 */
	@Override
	public List<XtUser> selectByEntity(XtUser record) {
		List<XtUser> result = sqlSessionTemplate.selectList("selectByEntity",record);
		return result;
	}

	
	/***
	 * 条件分页查询
	 */
	@Override
	public ResultListInfo<XtUser> getList(PageInfo<XtUser> pageInfo) {
		List<XtUser> result = sqlSessionTemplate.selectList("com.pengyue.ptas.dao.xt.XtUserDao.getList", pageInfo);
		Integer total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.xt.XtUserDao.getCount",pageInfo);
		return new ResultListInfo<XtUser>(result,total);
	}
	
	
	

}