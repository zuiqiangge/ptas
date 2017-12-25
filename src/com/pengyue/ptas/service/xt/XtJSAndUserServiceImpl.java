package com.pengyue.ptas.service.xt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.dao.xt.XtJSAndUserDao;

@Service("xtJSAndUserService")
@Transactional
public class XtJSAndUserServiceImpl implements XtJSAndUserService {
	@Autowired
	private XtJSAndUserDao xtJSAndUserDao;

	@Override
	public List<XtJSAndUser> getListByProperty(XtJSAndUser record) {
		return xtJSAndUserDao.selectByProperty(record);
	}

	@Override
	public int insert(XtJSAndUser record) {
		return xtJSAndUserDao.insert(record);
	}

	@Override
	public int deleteAllByUserId(String userId) {
		return xtJSAndUserDao.deleteAllByUserId(userId);
	}
}
