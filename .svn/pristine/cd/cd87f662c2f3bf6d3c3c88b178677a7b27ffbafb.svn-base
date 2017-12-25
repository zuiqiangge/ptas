package com.pengyue.ptas.service.xt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.bean.XtJsQx;
import com.pengyue.ptas.dao.xt.XtJsQxDao;
@Service("xtJsQxService")
@Transactional
public class XtJsQxServiceImpl implements XtJsQxService {
	@Autowired
	private XtJsQxDao xtJsQxDao;


	@Override
	public List<XtJsQx> getListByProperty(XtJsQx x) {
		return xtJsQxDao.selectByProperty(x);
	}

	@Override
	public int insert(XtJsQx x) {
		if(xtJsQxDao.selectByProperty(x).size()==0)
		return xtJsQxDao.insert(x);
		return 0;
	}

	@Override
	public int deleteByJsId(String jsId) {
		return xtJsQxDao.deleteByJsId(jsId);
	}

	@Override
	public int deleteByProperty(XtJsQx record) {
		return xtJsQxDao.deleteByProperty(record);
	}

	
}
