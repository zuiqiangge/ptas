package com.pengyue.ptas.dao.yy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.Trained;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Repository
public class TrainedDaoImpl implements TrainedDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public ResultListInfo<Trained> getList(PageInfo<Trained> pageInfo){
		ResultListInfo<Trained> map = new ResultListInfo<Trained>();
		try {
			List<Trained> result = sqlSessionTemplate.selectList(this.getClass().getName()+".selectList", pageInfo);
			Integer total = sqlSessionTemplate.selectOne(this.getClass().getName()+".selectCount",pageInfo);
			map.setTotal(total);
			map.setResult(result);
		} catch (Exception e) {
			System.out.println("【TrainedDaoImpl】【getList】:"+e);
		}
		return map;
	}
	
	@Override
	public int insert(Trained bean){
		int i=0;
		try {
			i = sqlSessionTemplate.insert(this.getClass().getName()+".insert", bean);
		} catch (Exception e) {
			System.out.println("【TrainedDaoImpl】【insert】:"+e);
		}
		return i;
	}
	
	@Override
	public int update(Trained bean){
		int i=0;
		try {
			i = sqlSessionTemplate.update(this.getClass().getName()+".update", bean);
		} catch (Exception e) {
			System.out.println("【TrainedDaoImpl】【update】:"+e);
		}
		return i;
	}
	
	@Override
	public int delete(List<String> ids){
		int i=0;
		try {
			i = sqlSessionTemplate.delete(this.getClass().getName()+".delete", ids);
		} catch (Exception e) {
			System.out.println("【TrainedDaoImpl】【delete】:"+e);
		}
		return i;
	}

	@Override
	public Trained selectByPrimaryKey(String id) {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.TrainedDaoImpl.selectByPrimaryKey",id);
	}

	@Override
	public List<Trained> selectAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.TrainedDaoImpl.selectAll");
	}
	
	public List<Trained> getNoTranedList(){
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.TrainedDaoImpl.getNoTranedList");
	}
	
}
