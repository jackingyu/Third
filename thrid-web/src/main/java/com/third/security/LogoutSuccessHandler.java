package com.third.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.third.facade.user.UserFacade;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	private UserFacade userFacade;

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException
	{
		userFacade.logout();
		super.handle(request, response, authentication);
	}

	public void setUserFacade(UserFacade userFacade)
	{
		this.userFacade = userFacade;
	}

}
