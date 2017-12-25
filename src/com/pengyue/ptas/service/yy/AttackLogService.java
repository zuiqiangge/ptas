package com.pengyue.ptas.service.yy;

import java.util.List;
import java.util.Map;

import com.pengyue.ptas.bean.AttackLog;
import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface AttackLogService {
	public ResultListInfo<AttackLog> getList(PageInfo<AttackLog> pageInfo);
	
	public int insert(AttackLog record);
	public int update(AttackLog record);
	public int delete(List<String> list);
	public AttackLog selectByPrimaryKey(String id);
	public List<AttackLog> getALl();
	/*public int delete(List<String> ids);*/
	int getAllCount();
	List<String> getAttackType();
	List<String> getAttackTypes();
	public List<Map<Object,Object>> getHardIps();
	List<String> getRunStates();
	List<String> getRunStatesAndValues();
}
