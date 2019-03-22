package com.hostel.domain;

public enum RoomStatus {

	SELECT("Select Type"),
	AC_ROOM("A/C"),
	NON_AC_ROOM("Non A/C");
	
	private String roomStatus;
	

	private RoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
}
