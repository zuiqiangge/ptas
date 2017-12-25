package com.pengyue.ptas.bean;

public class XtJSAndUser {
    private String id;

    private String jsid;

    private String userid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getJsid() {
        return jsid;
    }

    public void setJsid(String jsid) {
        this.jsid = jsid == null ? null : jsid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

	public XtJSAndUser(String id, String jsid, String userid) {
		super();
		this.id = id;
		this.jsid = jsid;
		this.userid = userid;
	}

	public XtJSAndUser() {
		super();
	}
    
	
    
}