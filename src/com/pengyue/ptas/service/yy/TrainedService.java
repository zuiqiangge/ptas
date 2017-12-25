package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.dao.yy.TrainedDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service
@Transactional
public class TrainedService {
	@Autowired
	TrainedDao trainedDao;
	public ResultListInfo<Trained> getList(PageInfo<Trained> pageInfo){
		return trainedDao.getList(pageInfo);
	}
	
	public int  insert(Trained bean){
		return trainedDao.insert(bean);
	}
	
	public int  update(Trained bean){
		return trainedDao.update(bean);
	}
	
	public int  delete(List<String> ids){
		return trainedDao.delete(ids);
	}
	
	public Trained selectByPrimaryKey(String id){
		return trainedDao.selectByPrimaryKey(id);
	}
	public List<Trained> selectAll(){
		return trainedDao.selectAll();
	}
	
	public List<Trained> getNoTranedList(){
		return trainedDao.getNoTranedList();
	}
}