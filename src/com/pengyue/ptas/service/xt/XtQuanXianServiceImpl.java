package com.pengyue.ptas.service.xt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.XtJsQx;
import com.pengyue.ptas.bean.XtQuanXian;
import com.pengyue.ptas.dao.xt.XtQuanXianDao;
import com.pengyue.ptas.util.UserIdAndParentId;

@Service("xtQuanXianService")
@Transactional
public class XtQuanXianServiceImpl implements XtQuanXianService {
	@Autowired XtQuanXianDao xtQuanXianDao;


	@Override
	public List<XtQuanXian> selectByFirstMenu() {
		return xtQuanXianDao.selectByFirstMenu();
	}



	@Override
	public List<XtQuanXian> selectByParentId(String id) {
		return xtQuanXianDao.selectByParentId(id);
	}


	/***
	 * 获取角色对应的权限
	 */
	@Override
	public List<XtQuanXian> selectByRoleId(String id) {
		return xtQuanXianDao.selectByRoleId(id);
	}


	/***
	 * 获取用户对应的所有权限
	 */
	@Override
	public List<XtQuanXian> selectByUserId(String userId) {
		return xtQuanXianDao.selectByUserId(userId);
	}



	@Override
	public XtQuanXian selectByPrimaryKey(String id) {
		return xtQuanXianDao.selectByPrimaryKey(id);
	}


	
	/***
	 * 获取所有的权限
	 */
	@Override
	public List<XtQuanXian> selectAll() {
		return xtQuanXianDao.selectAll();
	}



	@Override
	public int updateByPrimaryKeySelective(XtQuanXian record) {
		return xtQuanXianDao.updateByPrimaryKeySelective(record);
	}



	@Override
	public int deleteByPrimaryKey(String id) {
		return xtQuanXianDao.deleteByPrimaryKey(id);
	}



	@Override
	public String selectMaxOrdersByParentId(String parentId) {
		return xtQuanXianDao.selectMaxOrdersByParentId(parentId);
	}



	@Override
	public int insert(XtQuanXian record) {
		return xtQuanXianDao.insert(record);
	}



	@Override
	public List<XtQuanXian> selectByUserIdAndParentId(UserIdAndParentId uIdPid) {
		return xtQuanXianDao.selectByUserIdAndParentId(uIdPid);
	}

	

	
}