/*
 * [y] anguo project
 * Copyright (c) 2015-2016 
 * All rights reserved.
 *
 */
package com.third.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.third.core.util.WXConstant;

/**
 * create time: Apr 19, 2016 10:42:58 AM
 *
 * author: Yu Jack
 *
 * description:支持Ajax的LoginUrlAuthenticationEntryPoint
 *
 * version:1.0
 */
public class WeixinLoginUrlAuthenticationEntryPoint
		extends LoginUrlAuthenticationEntryPoint {
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public WeixinLoginUrlAuthenticationEntryPoint(String loginFormUrl)
	{
		super(loginFormUrl);
		// TODO Auto-generated constructor stub
	}

	public static final String AJAX_REQUEST_HEADER_NAME = "X-Requested-With";

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException
	{
		if (WXConstant.WX_ERR_NOT_BIND_CUST.equals(authException.getMessage()))
			request.getRequestDispatcher("/weixin/member/getRegisterPage").forward(request, response);
		//	redirectStrategy.sendRedirect(request, response, "/weixin/member/getRegisterPage");
		// redirectStrategy
		// .sendRedirect(
		// request,
		// response,
		// "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx095f3e1260a6b827&redirect_uri=http%3A%2F%2F106.14.65.76%2Fthrid-web%2Fwx%2Fmember%2FgetRegisterPage&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
	}
}
