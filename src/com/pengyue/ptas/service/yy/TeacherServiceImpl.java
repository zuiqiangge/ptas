package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.Teacher;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.SxPlanDao;
import com.pengyue.ptas.dao.yy.TeacherDao;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public ResultListInfo<Teacher> getList(PageInfo<Teacher> pageInfo) {
		return teacherDao.getList(pageInfo);
	}
	
	

	@Override
	public int insert(Teacher record) {
		return teacherDao.insert(record);
	}

	@Override
	public int update(Teacher record) {
		return teacherDao.update(record);
	}

	

	
	public int delete(List<String> list){
		return teacherDao.delete(list);
	}

	@Override
	public List<Teacher> selectAll(){
		return teacherDao.selectAll();
	}



	@Override
	public ResultListInfo<Teacher> getSXList(PageInfo<Teacher> pageInfo) {
		return null;
	}



	/***
	 * 根据主键查询
	 */
	@Override
	public Teacher selectByPrimarykey(String id) {
		return teacherDao.selectByPrimaryKey(id);
	}


	




	
}
