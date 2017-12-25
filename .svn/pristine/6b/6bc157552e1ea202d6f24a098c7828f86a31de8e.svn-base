package com.pengyue.ptas.dao.xt;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pengyue.ptas.bean.Dictionary;
import com.pengyue.ptas.bean.DictionaryValue;
import com.pengyue.ptas.bean.SoftWareLog;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
/**
 * 数据表
 */
@Repository("softWareLogDao")
public class SoftWareLogDaoImpl implements SoftWareLogDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insert(SoftWareLog log) {
		return sqlSessionTemplate.insert("com.pengyue.ptas.dao.xt.SoftWareLogDao.insert",log);
	}

	@Override
	public List<SoftWareLog> getAll() {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.xt.SoftWareLogDao.getAll");
	}

	@Override
	public List<SoftWareLog> selectValueBySoftId(String softId) {
		return sqlSessionTemplate.selectList("com.pengyue.ptas.dao.xt.SoftWareLogDao.selectValueBySoftId", softId);
	}

	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		SoftWareLogDao s = context.getBean(SoftWareLogDao.class);
		List<SoftWareLog>  logs = s.selectValueBySoftId("0324D7342E3A4A1B8F60F60116CF7794");
		System.out.println(logs);
	}
	
}
