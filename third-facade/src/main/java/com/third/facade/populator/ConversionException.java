package com.third.facade.populator;

public class ConversionException extends RuntimeException {

	private static final long serialVersionUID = -2280904009639576411L;

	public ConversionException(final String msg, final Throwable cause)
	{
		super(msg, cause);
	}

	public ConversionException(final String msg)
	{
		super(msg);
	}

}
