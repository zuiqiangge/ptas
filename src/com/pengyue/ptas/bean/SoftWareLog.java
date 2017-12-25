package com.pengyue.ptas.bean;

public class SoftWareLog {
  private String id;
  private String oName;
  private String createTime;
  private String softId;
  private XtUser xtUser;
  
public String getSoftId() {
	return softId;
}
public void setSoftId(String softId) {
	this.softId = softId;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getoName() {
	return oName;
}
public void setoName(String oName) {
	this.oName = oName;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}


public XtUser getXtUser() {
	return xtUser;
}
public void setXtUser(XtUser xtUser) {
	this.xtUser = xtUser;
}
public SoftWareLog(String oName, String createTime, XtUser xtUser,String softId) {
	super();
	this.oName = oName;
	this.createTime = createTime;
	this.xtUser = xtUser;
	this.softId = softId;
}
public SoftWareLog() {
	super();
}
  
  
}
