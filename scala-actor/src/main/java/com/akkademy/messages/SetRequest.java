package com.akkademy.messages;

public class SetRequest {
	
	private String key;
	private Object value;
	
	public void set(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}
	
	public Object getValue() {
		return value;
	}
}
