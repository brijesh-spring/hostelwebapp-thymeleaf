package com.hostel.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -7280026652314124220L;

	public NotFoundException( String message )
	{
		super( message );
	}
	
	public NotFoundException( String message, Throwable cause )
	{
		super( message, cause );
	}
	
	
}
