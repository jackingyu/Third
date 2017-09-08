package com.third.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginAuthenticationFailureHandler
		extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(final HttpServletRequest request,
			final HttpServletResponse response,
			final AuthenticationException exception)
			throws IOException, ServletException
	{
		request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME",
				request.getParameter("j_username"));
		// request.getSession().setAttribute("LOGIN_ERROR_MESSAGE", "");
		request.getSession().setAttribute("login_error_message",
				exception.getMessage());
		super.onAuthenticationFailure(request, response, exception);
	}

}
