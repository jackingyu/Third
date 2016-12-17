package com.third.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class WeixinAccessDeniedHandler implements AccessDeniedHandler
{
	private static final Logger LOG = Logger.getLogger(WeixinAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException
	{
		//request.getRequestDispatcher("/login").forward(request, response);
		LOG.info("weixin access denied:"+accessDeniedException.getMessage());
	}

}
