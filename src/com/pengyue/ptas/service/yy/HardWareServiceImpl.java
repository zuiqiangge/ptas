package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.HardWareDao;
import com.pengyue.ptas.dao.yy.SxOrderDao;
import com.pengyue.ptas.dao.yy.SxPlanDao;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service("hardWareService")
@Transactional
public class HardWareServiceImpl implements HardWareService {
	@Autowired
	private HardWareDao hardWareDao;

	@Override
	public ResultListInfo<HardWare> getList(PageInfo<HardWare> pageInfo) {
		return hardWareDao.getList(pageInfo);
	}

	@Override
	public int insert(HardWare record) {
		return hardWareDao.insert(record);
	}

	@Override
	public int update(HardWare record) {
		return hardWareDao.update(record);
	}

	

	
	public int delete(List<String> list){
		return hardWareDao.delete(list);
	}

	@Override
	public HardWare selectByPrimaryKey(String id) {
		return hardWareDao.selectByPrimaryKey(id);
	}

	@Override
	public List<HardWare> getALl() {
		return hardWareDao.getAll();
	}


	

	




	
}
