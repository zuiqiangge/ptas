package com.pengyue.ptas.bean;

public class Maintenance {
	private String m_id;
	private String m_name;
	private String m_sex;
	private String m_card;
	private String m_call;
	private String m_jsxx_id;
	private String m_create_time;
	private String jsmc;
	private String m_username;
	private String m_password;
	
	public String getM_username() {
		return m_username;
	}
	public void setM_username(String m_username) {
		this.m_username = m_username;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_sex() {
		return m_sex;
	}
	public void setM_sex(String m_sex) {
		this.m_sex = m_sex;
	}
	public String getM_card() {
		return m_card;
	}
	public void setM_card(String m_card) {
		this.m_card = m_card;
	}
	public String getM_call() {
		return m_call;
	}
	public void setM_call(String m_call) {
		this.m_call = m_call;
	}
	public String getM_jsxx_id() {
		return m_jsxx_id;
	}
	public void setM_jsxx_id(String m_jsxx_id) {
		this.m_jsxx_id = m_jsxx_id;
	}
	public String getM_create_time() {
		return m_create_time;
	}
	public void setM_create_time(String m_create_time) {
		this.m_create_time = m_create_time;
	}
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	@Override
	public String toString() {
		return "Maintenance [m_id=" + m_id + ", m_name=" + m_name + ", m_sex="
				+ m_sex + ", m_card=" + m_card + ", m_call=" + m_call
				+ ", m_jsxx_id=" + m_jsxx_id + ", m_create_time="
				+ m_create_time + ", jsmc=" + jsmc + ", m_username="
				+ m_username + ", m_password=" + m_password + "]";
	}
	
}