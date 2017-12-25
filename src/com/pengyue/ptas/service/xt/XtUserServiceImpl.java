package com.pengyue.ptas.service.xt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengyue.ptas.dao.xt.XtUserDao;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.bean.XtUser;
@Service("xtUserService")
@Transactional
public class XtUserServiceImpl implements XtUserService {
	@Autowired
	private XtUserDao XtUserDao;
	
	/***
	 * 判断用户是否存在
	 */
	@Override
	public List<XtUser> isExist(XtUser xtUser) {
		return XtUserDao.selectByEntity(xtUser);
	}

	
	/***
	 * 获取条件分页查询数据
	 */
	@Override
	public ResultListInfo<XtUser> getListData(PageInfo<XtUser> pageInfo) {
		return XtUserDao.getList(pageInfo);
	}

	/***
	 * 插入记录
	 */
	@Override
	public int insert(XtUser xtUser) {
		return XtUserDao.insert(xtUser);
	}

	/***
	 * 修改记录
	 */
	@Override
	public int update(XtUser xtUser) {
		return XtUserDao.updateByPrimaryKeySelective(xtUser);
	}


	@Override
	public int delete(List<String> ids) {
		return XtUserDao.deleteByPrimaryKey(ids);
	}
	
	
	
}
