package com.pengyue.ptas.dao.yy;

import java.util.List;
import java.util.Map;
import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.bean.MaintenanceRecord;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface MaintenanceRecordDao {
	public ResultListInfo<MaintenanceRecord> getList(PageInfo<MaintenanceRecord> pageInfo);
	
	public int insert(MaintenanceRecord bean);
	
	public int update(MaintenanceRecord bean);
	
	public int delete(List<String> ids);
	
	public MaintenanceRecord selectByPrimaryKey(String id);
	
	public List<Map<String,Object>> getMaintenanceRecord();
	
	public MaintenanceRecord selectLogin(Map<String,String> map);
	
	public List<MaintenanceRecord> selectAll();
	
	public List<Map<String,Object>> selectBytime(Map<String,Object> str);
	
	public List<Map<String,Object>> selectBytimeGroup(Map<String,Object> str);
	
	public List<Map<String,Object>> selectBytimeMonth(Map<String,Object> str);
}