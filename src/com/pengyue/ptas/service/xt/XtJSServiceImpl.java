package com.pengyue.ptas.service.xt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.dao.xt.XtJSDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service("xtJSService")
@Transactional
public class XtJSServiceImpl implements XtJSService {
	@Autowired XtJSDao xtJSDao;

	@Override
	public List<XtJS> selectAll() {
		return xtJSDao.selectAll();
	}

	@Override
	public List<XtJS> selectByUserId(String userId) {
		return xtJSDao.selectByUserId(userId);
	}

	@Override
	public ResultListInfo<XtJS> getList(PageInfo<XtJS> pageInfo) {
		return xtJSDao.getList(pageInfo);
	}

	@Override
	public int insert(XtJS xtJs) {
		return xtJSDao.insert(xtJs);
	}

	@Override
	public int updateByPrimaryKey(XtJS record) {
		return xtJSDao.updateByPrimaryKey(record);
	}
}