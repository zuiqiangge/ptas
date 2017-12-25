package com.pengyue.ptas.service.yy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.dao.yy.YyGzryxxbDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

@Service("yyGzryxxbService")
@Transactional
public class YyGzryxxbServiceImpl implements YyGzryxxbService {
	@Autowired
	private YyGzryxxbDao yyGzryxxbDao;

	@Override
	public ResultListInfo<YyGzryxxb> getList(PageInfo<YyGzryxxb> pageInfo) {
		return yyGzryxxbDao.getList(pageInfo);
	}

	@Override
	public int insert(YyGzryxxb record) {
		return yyGzryxxbDao.insert(record);
	}

	@Override
	public int update(YyGzryxxb record) {
		return yyGzryxxbDao.updateByPrimaryKey(record);
	}

	public int delete(List<String> ids){
		return yyGzryxxbDao.deleteByPrimaryKey(ids);
	}
}
