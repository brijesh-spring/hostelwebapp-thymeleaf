package com.hostel.domain;

public enum StudentStatus {

	Active("Active"),
	Left("Left"),
	Renewed("Renewed"),
	Pending("Pending");
	
	private String type;
	private StudentStatus( String type )
	{
		this.type = type;
	}
}

 