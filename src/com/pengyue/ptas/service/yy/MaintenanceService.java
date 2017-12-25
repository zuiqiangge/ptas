package com.pengyue.ptas.service.yy;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.dao.yy.MaintenanceDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service
@Transactional
public class MaintenanceService {
	@Autowired
	MaintenanceDao maintenanceDao;
	public ResultListInfo<Maintenance> getList(PageInfo<Maintenance> pageInfo){
		return maintenanceDao.getList(pageInfo);
	}
	
	public int  insert(Maintenance bean){
		return maintenanceDao.insert(bean);
	}
	
	public int  update(Maintenance bean){
		return maintenanceDao.update(bean);
	}
	
	public int  delete(List<String> ids){
		return maintenanceDao.delete(ids);
	}

	public Maintenance selectByPrimaryKey(String id){
		return maintenanceDao.selectByPrimaryKey(id);
	}
	
	public Maintenance selectLogin(Map<String,String> map){
		return maintenanceDao.selectLogin(map);
	}
	
	public List<Map<String,Object>> getMaintenance(){
		return maintenanceDao.getMaintenance();
	}
	
	public List<Maintenance> selectAll(){
		return maintenanceDao.selectAll();
	}
}
