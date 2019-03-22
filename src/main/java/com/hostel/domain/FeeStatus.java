package com.hostel.domain;

public enum FeeStatus {
	
	PAID_IN_FULL("Paid in Full"),
	PARTIAL("Partial"),
	NOT_PAID("Not Paid");
	
	private String feeStatus;

	private FeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
	}
}
