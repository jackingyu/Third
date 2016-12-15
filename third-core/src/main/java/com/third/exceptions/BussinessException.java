package com.third.exceptions;

public class BussinessException extends BasicExcpetion
{
	private String message = null;
	
	public BussinessException(final String message)
	{
		this.message = message;
	}

}
