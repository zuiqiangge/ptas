package com.pengyue.ptas.dao.yy;

import java.util.List;
import java.util.Map;
import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.bean.MaintenanceLog;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface MaintenanceLogDao {
	public ResultListInfo<MaintenanceLog> getList(PageInfo<MaintenanceLog> pageInfo);
	
	public int insert(MaintenanceLog bean);
	
	public int update(MaintenanceLog bean);
	
	public int delete(List<String> ids);
	
	public MaintenanceLog selectByPrimaryKey(String id);
	
	public List<Map<String,Object>> getMaintenanceLog();
	
	public MaintenanceLog selectLogin(Map<String,String> map);
	
	public List<MaintenanceLog> selectAll();
}