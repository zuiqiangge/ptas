package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.SxProject;
import com.pengyue.ptas.dao.yy.SxProjectDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
@Service("sxProjectService")
@Transactional
public class SxProjectServiceImpl implements SxProjectService{
	@Autowired
	private SxProjectDao sxProjectDao;
	@Override
	public ResultListInfo<SxProject> getList(PageInfo<SxProject> pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(SxProject record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SxProject selectByPrimaryKey(String id) {
		return sxProjectDao.selectByPrimaryKey(id);
	}

	@Override
	public List<SxProject> selectAll() {
		return sxProjectDao.selectAll();
	}

}
