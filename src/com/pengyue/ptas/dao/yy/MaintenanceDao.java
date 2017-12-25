package com.pengyue.ptas.dao.yy;

import java.util.List;
import java.util.Map;
import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface MaintenanceDao {
	public ResultListInfo<Maintenance> getList(PageInfo<Maintenance> pageInfo);
	
	public int insert(Maintenance bean);
	
	public int update(Maintenance bean);
	
	public int delete(List<String> ids);
	
	public Maintenance selectByPrimaryKey(String id);
	
	public List<Map<String,Object>> getMaintenance();
	
	public Maintenance selectLogin(Map<String,String> map);
	
	public List<Maintenance> selectAll();
}