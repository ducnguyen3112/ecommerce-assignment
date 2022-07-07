package com.nashtech.ecommerce.enums;

public enum RoleName {
	ROLE_USER(2), ROLE_ADMIN(1);
	private int value;
	private RoleName(int value) {
		this.setValue(value);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
