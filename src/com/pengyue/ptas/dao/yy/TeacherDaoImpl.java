package com.pengyue.ptas.dao.yy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.Teacher;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Repository("teacherDao")
public class TeacherDaoImpl implements TeacherDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public ResultListInfo<Teacher> getList(PageInfo<Teacher> pageInfo){
		ResultListInfo<Teacher> map = new ResultListInfo<Teacher>();
		try {
			List<Teacher> result = sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.TeacherDao.getList", pageInfo);
			Integer total = sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.TeacherDao.getCount",pageInfo);
			map.setTotal(total);
			map.setResult(result);
		} catch (Exception e) {
			System.out.println("【TrainedDaoImpl】【getList】:"+e);
		}
		return map;
	}
	
	@Override
	public int insert(Teacher bean){
		int i=0;
		try {
			i = sqlSessionTemplate.insert(this.getClass().getName()+".insert", bean);
		} catch (Exception e) {
			System.out.println("【TrainedDaoImpl】【insert】:"+e);
		}
		return i;
	}
	
	@Override
	public int update(Teacher bean){
		int i=0;
		try {
			i = sqlSessionTemplate.update(this.getClass().getName()+".update", bean);
		} catch (Exception e) {
			System.out.println("【TrainedDaoImpl】【update】:"+e);
		}
		return i;
	}
	
	@Override
	public int delete(List<String> ids){
		int i=0;
		try {
			i = sqlSessionTemplate.delete(this.getClass().getName()+".delete", ids);
		} catch (Exception e) {
			System.out.println("【TrainedDaoImpl】【delete】:"+e);
		}
		return i;
	}

	/***
	 * 根据主键查询
	 */
	@Override
	public Teacher selectByPrimaryKey(String id) {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.TeacherDao.selectByPrimaryKey",id);
	}

	@Override
	public List<Teacher> selectAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.TeacherDao.selectAll");
	}
	
	public List<Teacher> getNoTranedList(){
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.TrainedDaoImpl.getNoTranedList");
	}
	
}
