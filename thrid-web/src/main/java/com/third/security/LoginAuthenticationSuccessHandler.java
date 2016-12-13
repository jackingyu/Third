package com.third.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.third.facade.user.UserFacade;


public class LoginAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{
	private static final Logger LOG = Logger.getLogger(LoginAuthenticationSuccessHandler.class);
	private UserFacade userFacade;

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException
	{
		getUserFacade().loginSuccess(authentication.getName());
		super.onAuthenticationSuccess(request, response, authentication);
	}

	public UserFacade getUserFacade()
	{
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade)
	{
		this.userFacade = userFacade;
	}

}
