package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.SxProject;
import com.pengyue.ptas.bean.SxResource;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.SxPlanDao;
import com.pengyue.ptas.dao.yy.SxResourceDao;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service("sxResourceService")
@Transactional
public class SxResourceServiceImpl implements SxResourceService {
	@Autowired
	private SxResourceDao sxResourceDao;

	@Override
	public ResultListInfo<SxResource> getList(PageInfo<SxResource> pageInfo) {
		return sxResourceDao.getList(pageInfo);
	}

	@Override
	public int insert(SxResource record) {
		return sxResourceDao.insert(record);
	}

	@Override
	public SxProject selectByPrimaryKey(String id) {
		return null;
	}

	@Override
	public ResultListInfo<SxResource> getListByChecked(
			PageInfo<SxResource> pageInfo) {
		return sxResourceDao.getListByChecked(pageInfo);
	}

	@Override
	public int check(String id) {
		return sxResourceDao.check(id);
	}

	

	
	/*public int delete(List<String> ids){
		return sxPlanDao.deleteByPrimaryKey(ids);
	}




	@Override
	public ResultListInfo<YyGzryxxb> getList(PageInfo<YyGzryxxb> pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public int insert(YyGzryxxb record) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int update(YyGzryxxb record) {
		// TODO Auto-generated method stub
		return 0;
	}*/
}
