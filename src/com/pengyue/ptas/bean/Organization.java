package com.pengyue.ptas.bean;

public class Organization {
	private int o_id;
	private String o_name;
	private String o_introduce;
	private String o_call;
	private String o_email;
	private int o_num;
	private String o_create_time;
	
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	public String getO_introduce() {
		return o_introduce;
	}
	public void setO_introduce(String o_introduce) {
		this.o_introduce = o_introduce;
	}
	public String getO_call() {
		return o_call;
	}
	public void setO_call(String o_call) {
		this.o_call = o_call;
	}
	public String getO_email() {
		return o_email;
	}
	public void setO_email(String o_email) {
		this.o_email = o_email;
	}
	public int getO_num() {
		return o_num;
	}
	public void setO_num(int o_num) {
		this.o_num = o_num;
	}
	public String getO_create_time() {
		return o_create_time;
	}
	public void setO_create_time(String o_create_time) {
		this.o_create_time = o_create_time;
	}
	@Override
	public String toString() {
		return "Organization [o_id=" + o_id + ", o_name=" + o_name
				+ ", o_introduce=" + o_introduce + ", o_call=" + o_call
				+ ", o_email=" + o_email + ", o_num=" + o_num
				+ ", o_create_time=" + o_create_time + "]";
	}
	
	
}
