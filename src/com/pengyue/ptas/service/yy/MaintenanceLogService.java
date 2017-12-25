package com.pengyue.ptas.service.yy;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.bean.MaintenanceLog;
import com.pengyue.ptas.dao.yy.MaintenanceDao;
import com.pengyue.ptas.dao.yy.MaintenanceLogDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service
@Transactional
public class MaintenanceLogService {
	@Autowired
	MaintenanceLogDao maintenanceLogDao;
	public ResultListInfo<MaintenanceLog> getList(PageInfo<MaintenanceLog> pageInfo){
		return maintenanceLogDao.getList(pageInfo);
	}
	
	public int  insert(MaintenanceLog bean){
		return maintenanceLogDao.insert(bean);
	}
	
	public int  update(MaintenanceLog bean){
		return maintenanceLogDao.update(bean);
	}
	
	public int  delete(List<String> ids){
		return maintenanceLogDao.delete(ids);
	}

	public MaintenanceLog selectByPrimaryKey(String id){
		return maintenanceLogDao.selectByPrimaryKey(id);
	}
	
	public MaintenanceLog selectLogin(Map<String,String> map){
		return maintenanceLogDao.selectLogin(map);
	}
	
	public List<Map<String,Object>> getMaintenanceLog(){
		return maintenanceLogDao.getMaintenanceLog();
	}
	
	public List<MaintenanceLog> selectAll(){
		return maintenanceLogDao.selectAll();
	}
}
