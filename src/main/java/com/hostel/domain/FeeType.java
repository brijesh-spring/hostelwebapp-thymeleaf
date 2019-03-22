package com.hostel.domain;

public enum FeeType {

	REGISTRATION_FEE("Registration Fee"),
	HOSTEL_FEE("Hostel Fee"),
	SECURITY_FEE("Security Fee");
	
	private String feeType;

	private FeeType(String feeType) {
		this.feeType = feeType;
	}
	
	
}
