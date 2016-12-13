/*
 * [y] anguo project
 * Copyright (c) 2015-2016 
 * All rights reserved.
 *
 */
package com.third.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * create time: Apr 19, 2016 10:42:58 AM
 *
 * author: Yu Jack
 *
 * description:支持Ajax的LoginUrlAuthenticationEntryPoint
 *
 * version:1.0
 */
public class ThirdLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint
{

	public ThirdLoginUrlAuthenticationEntryPoint(String loginFormUrl)
	{
		super(loginFormUrl);
		// TODO Auto-generated constructor stub
	}


	public static final String AJAX_REQUEST_HEADER_NAME = "X-Requested-With";

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException
	{

		if (StringUtils.isBlank(request.getHeader(AJAX_REQUEST_HEADER_NAME)))
		{
			super.commence(request, response, authException);
		}
		else
		{
			response.sendError(HttpServletResponse.SC_GATEWAY_TIMEOUT);
		}
	}

}
