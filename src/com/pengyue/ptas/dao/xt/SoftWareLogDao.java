package com.pengyue.ptas.dao.xt;

import java.util.List;

import com.pengyue.ptas.bean.Dictionary;
import com.pengyue.ptas.bean.DictionaryValue;
import com.pengyue.ptas.bean.SoftWareLog;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface SoftWareLogDao {
	public int insert(SoftWareLog log);
	public List<SoftWareLog> getAll();
	public List<SoftWareLog> selectValueBySoftId(String softId);
	
}