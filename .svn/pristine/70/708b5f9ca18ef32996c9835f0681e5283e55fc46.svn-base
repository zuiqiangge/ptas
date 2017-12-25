package com.pengyue.ptas.dao.yy;

import java.util.List;
import java.util.Map;

import com.pengyue.ptas.bean.AttackLog;
import com.pengyue.ptas.bean.HardWare;
import com.pengyue.ptas.bean.SxOrder;
import com.pengyue.ptas.bean.SxPlan;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface AttackLogDao {
	public ResultListInfo<AttackLog> getList(PageInfo<AttackLog> pageInfo);
	int insert(AttackLog attackLog);
	public int update(AttackLog attackLog);
	int delete(List<String> list);
	AttackLog selectByPrimaryKey(String id);
	List<AttackLog> getAll();
	int getAllCount();
	List<String> getAttackType();
	List<String> getAttackTypes();
	List<Map<Object,Object>> getHardIps();
	List<String> getRunStates();
	List<String> getRunStatesAndValues();
}