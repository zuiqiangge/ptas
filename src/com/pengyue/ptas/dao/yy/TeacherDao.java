package com.pengyue.ptas.dao.yy;

import java.util.List;

import com.pengyue.ptas.bean.Teacher;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface TeacherDao {
	public ResultListInfo<Teacher> getList(PageInfo<Teacher> pageInfo);
	
	public int insert(Teacher bean);
	
	public int update(Teacher bean);
	
	public int delete(List<String> ids);
	
	public Teacher selectByPrimaryKey(String id);
	public List<Teacher> selectAll();
	public List<Teacher> getNoTranedList();
}