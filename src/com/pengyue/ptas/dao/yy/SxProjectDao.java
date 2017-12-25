package com.pengyue.ptas.dao.yy;

import java.util.List;

import com.pengyue.ptas.bean.SxProject;

public interface SxProjectDao {
   SxProject selectByPrimaryKey(String id);
   
   
   List<SxProject> selectAll();
}