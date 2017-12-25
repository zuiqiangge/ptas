package com.pengyue.ptas.dao.yy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.Maintenance;
import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Repository
public class MaintenanceDaoImpl implements MaintenanceDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public ResultListInfo<Maintenance> getList(PageInfo<Maintenance> pageInfo){
		ResultListInfo<Maintenance> map = new ResultListInfo<Maintenance>();
		try {
			List<Maintenance> result = sqlSessionTemplate.selectList(this.getClass().getName()+".selectList", pageInfo);
			Integer total = sqlSessionTemplate.selectOne(this.getClass().getName()+".selectCount",pageInfo);
			map.setTotal(total);
			map.setResult(result);
		} catch (Exception e) {
			System.out.println("【MaintenanceDaoImpl】【getList】:"+e);
		}
		return map;
	}
	
	@Override
	public int insert(Maintenance bean){
		int i=0;
		try {
			i = sqlSessionTemplate.insert(this.getClass().getName()+".insert", bean);
		} catch (Exception e) {
			System.out.println("【MaintenanceDaoImpl】【insert】:"+e);
		}
		return i;
	}
	
	@Override
	public int update(Maintenance bean){
		int i=0;
		try {
			i = sqlSessionTemplate.update(this.getClass().getName()+".update", bean);
		} catch (Exception e) {
			System.out.println("【MaintenanceDaoImpl】【update】:"+e);
		}
		return i;
	}
	
	@Override
	public int delete(List<String> ids){
		int i=0;
		try {
			i = sqlSessionTemplate.delete(this.getClass().getName()+".delete", ids);
		} catch (Exception e) {
			System.out.println("【MaintenanceDaoImpl】【delete】:"+e);
		}
		return i;
	}
	
	@Override
	public List<Map<String,Object>> getMaintenance(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = sqlSessionTemplate.selectList(this.getClass().getName()+".selectAll");
		} catch (Exception e) {
			System.out.println("【MaintenanceDaoImpl】【getMaintenance】:"+e);
		}
		return list;
	}

	@Override
	public Maintenance selectByPrimaryKey(String id) {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.MaintenanceDaoImpl.selectByPrimaryKey", id);
	}
	
	@Override
	public Maintenance selectLogin(Map<String,String> map) {
		return sqlSessionTemplate.selectOne("com.pengyue.ptas.dao.yy.MaintenanceDaoImpl.selectLogin", map);
	}

	@Override
	public List<Maintenance> selectAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.yy.MaintenanceDaoImpl.selectAll");
	}

	
	
}
