package com.pengyue.ptas.service.xt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.Dictionary;
import com.pengyue.ptas.bean.DictionaryValue;
import com.pengyue.ptas.bean.SoftWareLog;
import com.pengyue.ptas.bean.XtJSAndUser;
import com.pengyue.ptas.bean.XtJsQx;
import com.pengyue.ptas.dao.xt.DictionaryDao;
import com.pengyue.ptas.dao.xt.SoftWareLogDao;
import com.pengyue.ptas.dao.xt.XtJsQxDao;
@Service("softWareLogService")
@Transactional
public class SoftWareLogServiceImpl implements SoftWareLogService {
	@Autowired
	private SoftWareLogDao softWareLogDao;

	@Override
	public int insert(SoftWareLog log) {
		return softWareLogDao.insert(log);
	}

	@Override
	public List<SoftWareLog> getAll() {
		return softWareLogDao.getAll();
	}

	@Override
	public List<SoftWareLog> selectValueBySoftId(String softId) {
		return softWareLogDao.selectValueBySoftId(softId);
	}

	

	

	
}
