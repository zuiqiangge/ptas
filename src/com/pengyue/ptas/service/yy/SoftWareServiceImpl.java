package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.SoftWare;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.HardWareDao;
import com.pengyue.ptas.dao.yy.SoftWareDao;
import com.pengyue.ptas.dao.yy.SxOrderDao;
import com.pengyue.ptas.dao.yy.SxPlanDao;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service("softWareService")
@Transactional
public class SoftWareServiceImpl implements SoftWareService {
	@Autowired
	private SoftWareDao softWareDao;

	@Override
	public ResultListInfo<SoftWare> getList(PageInfo<SoftWare> pageInfo) {
		return softWareDao.getList(pageInfo);
	}

	@Override
	public int insert(SoftWare record) {
		return softWareDao.insert(record);
	}

	@Override
	public int update(SoftWare record) {
		return softWareDao.update(record);
	}

	

	
	public int delete(List<String> list){
		return softWareDao.delete(list);
	}

	@Override
	public SoftWare selectByProperty(SoftWare softWare) {
		return softWareDao.selectByProperty(softWare);
	}




	




	
}
