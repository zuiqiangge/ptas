package com.pengyue.ptas.util;

public class TypecodeAndValue {
	private String typeCode;
	private String value;
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public TypecodeAndValue(String typeCode, String value) {
		super();
		this.typeCode = typeCode;
		this.value = value;
	}
	public TypecodeAndValue() {
		super();
	}
	
}
