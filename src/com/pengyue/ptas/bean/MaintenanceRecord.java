package com.pengyue.ptas.bean;

public class MaintenanceRecord {
	private String  md_id;
	private String  md_mid;
	private String  md_context;
	private String  md_species;
	private String  md_results;
	private String  md_object;
	private String  md_time;
	
	private String  m_name;
	private String  m_card;
	
	
	
	public String getMd_id() {
		return md_id;
	}
	public void setMd_id(String md_id) {
		this.md_id = md_id;
	}
	public String getMd_mid() {
		return md_mid;
	}
	public void setMd_mid(String md_mid) {
		this.md_mid = md_mid;
	}
	public String getMd_context() {
		return md_context;
	}
	public void setMd_context(String md_context) {
		this.md_context = md_context;
	}
	public String getMd_species() {
		return md_species;
	}
	public void setMd_species(String md_species) {
		this.md_species = md_species;
	}
	public String getMd_results() {
		return md_results;
	}
	public void setMd_results(String md_results) {
		this.md_results = md_results;
	}
	public String getMd_object() {
		return md_object;
	}
	public void setMd_object(String md_object) {
		this.md_object = md_object;
	}
	public String getMd_time() {
		return md_time;
	}
	public void setMd_time(String md_time) {
		this.md_time = md_time;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_card() {
		return m_card;
	}
	public void setM_card(String m_card) {
		this.m_card = m_card;
	}
	@Override
	public String toString() {
		return "MaintenanceRecord [md_id=" + md_id + ", md_mid=" + md_mid
				+ ", md_context=" + md_context + ", md_species=" + md_species
				+ ", md_results=" + md_results + ", md_object=" + md_object
				+ ", md_time=" + md_time + ", m_name=" + m_name + ", m_card="
				+ m_card + "]";
	}
	
	
}
