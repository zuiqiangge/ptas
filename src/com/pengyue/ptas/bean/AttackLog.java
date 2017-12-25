package com.pengyue.ptas.bean;

public class AttackLog {
	private String id;
	private String attackIp;
	private String port;
	private String protocol;
	private HardWare sourceHost;
	private String sourcePort;
	private String attckType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttackIp() {
		return attackIp;
	}
	public void setAttackIp(String attackIp) {
		this.attackIp = attackIp;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public HardWare getSourceHost() {
		return sourceHost;
	}
	public void setSourceHost(HardWare sourceHost) {
		this.sourceHost = sourceHost;
	}
	public String getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}
	public String getAttckType() {
		return attckType;
	}
	public void setAttckType(String attckType) {
		this.attckType = attckType;
	}
	
	
}
