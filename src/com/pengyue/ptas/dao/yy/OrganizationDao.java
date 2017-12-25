package com.pengyue.ptas.dao.yy;

import java.util.List;
import java.util.Map;

import com.pengyue.ptas.bean.Organization;
import com.pengyue.ptas.bean.YyGzryxxb;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;

public interface OrganizationDao {
	public ResultListInfo<Organization> getList(PageInfo<Organization> pageInfo);
	
	public int insert(Organization bean);
	
	public int update(Organization bean);
	
	public int delete(List<String> ids);
	
	public Organization selectByPrimaryKey(String id);
	
	public List<Map<String,Object>> getOrganization();
	
	public List<Organization> selectAll();
}