package com.pengyue.ptas.service.yy;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.bean.MaintenanceRecord;
import com.pengyue.ptas.dao.yy.MaintenanceDao;
import com.pengyue.ptas.dao.yy.MaintenanceRecordDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service
@Transactional
public class MaintenanceRecordService {
	@Autowired
	MaintenanceRecordDao maintenanceRecordDao;
	
	public ResultListInfo<MaintenanceRecord> getList(PageInfo<MaintenanceRecord> pageInfo){
		return maintenanceRecordDao.getList(pageInfo);
	}
	
	public int  insert(MaintenanceRecord bean){
		return maintenanceRecordDao.insert(bean);
	}
	
	public int  update(MaintenanceRecord bean){
		return maintenanceRecordDao.update(bean);
	}
	
	public int  delete(List<String> ids){
		return maintenanceRecordDao.delete(ids);
	}

	public MaintenanceRecord selectByPrimaryKey(String id){
		return maintenanceRecordDao.selectByPrimaryKey(id);
	}
	
	public MaintenanceRecord selectLogin(Map<String,String> map){
		return maintenanceRecordDao.selectLogin(map);
	}
	
	public List<Map<String,Object>> getMaintenanceRecord(){
		return maintenanceRecordDao.getMaintenanceRecord();
	}
	
	public List<MaintenanceRecord> selectAll(){
		return maintenanceRecordDao.selectAll();
	}
	
	public List<Map<String,Object>> selectBytime(Map<String,Object> str){
		return maintenanceRecordDao.selectBytime(str);
	}
	
	public List<Map<String,Object>> selectBytimeGroup(Map<String,Object> str){
		return maintenanceRecordDao.selectBytimeGroup(str);
	}
	
	public List<Map<String,Object>> selectBytimeMonth(Map<String,Object> str){
		return maintenanceRecordDao.selectBytimeMonth(str);
	}
}
