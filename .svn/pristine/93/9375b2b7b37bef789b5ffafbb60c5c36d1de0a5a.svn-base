package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.SxOrderDao;
import com.pengyue.ptas.dao.yy.SxPlanDao;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service("sxOrderService")
@Transactional
public class SxOrderServiceImpl implements SxOrderService {
	@Autowired
	private SxOrderDao sxOrderDao;

	@Override
	public ResultListInfo<SxOrder> getList(PageInfo<SxOrder> pageInfo) {
		return sxOrderDao.getList(pageInfo);
	}

	@Override
	public int insert(SxOrder record) {
		return sxOrderDao.insert(record);
	}

	@Override
	public int update(SxOrder record) {
		return sxOrderDao.update(record);
	}

	

	
	public int delete(List<String> list){
		return sxOrderDao.delete(list);
	}




	




	
}
