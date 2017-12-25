package com.pengyue.ptas.bean;

public class MaintenanceLog {
	private String ml_id;
	private String ml_mid;
	private String ml_name;
	private String ml_card;
	private String ml_caozuo;
	private String ml_ip;
	private String ml_url;
	private String ml_time;
	
	public String getMl_id() {
		return ml_id;
	}
	public void setMl_id(String ml_id) {
		this.ml_id = ml_id;
	}
	public String getMl_mid() {
		return ml_mid;
	}
	public void setMl_mid(String ml_mid) {
		this.ml_mid = ml_mid;
	}
	public String getMl_name() {
		return ml_name;
	}
	public void setMl_name(String ml_name) {
		this.ml_name = ml_name;
	}
	public String getMl_card() {
		return ml_card;
	}
	public void setMl_card(String ml_card) {
		this.ml_card = ml_card;
	}
	public String getMl_caozuo() {
		return ml_caozuo;
	}
	public void setMl_caozuo(String ml_caozuo) {
		this.ml_caozuo = ml_caozuo;
	}
	public String getMl_ip() {
		return ml_ip;
	}
	public void setMl_ip(String ml_ip) {
		this.ml_ip = ml_ip;
	}
	public String getMl_url() {
		return ml_url;
	}
	public void setMl_url(String ml_url) {
		this.ml_url = ml_url;
	}
	public String getMl_time() {
		return ml_time;
	}
	public void setMl_time(String ml_time) {
		this.ml_time = ml_time;
	}
	@Override
	public String toString() {
		return "MaintenanceLog [ml_id=" + ml_id + ", ml_mid=" + ml_mid
				+ ", ml_name=" + ml_name + ", ml_card=" + ml_card
				+ ", ml_caozuo=" + ml_caozuo + ", ml_ip=" + ml_ip + ", ml_url="
				+ ml_url + ", ml_time=" + ml_time + "]";
	}
	
	
}
