package com.third.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class WeixinAuthenticationException extends AuthenticationException
{

	public WeixinAuthenticationException(String msg)
	{
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
