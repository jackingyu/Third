package com.third.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.third.core.constants.CoreConstants;
import com.third.facade.user.UserFacade;
import com.third.service.user.SessionService;
import com.third.web.utils.DeviceUtils;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationSuccessHandler
		extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger LOG = Logger
			.getLogger(LoginAuthenticationSuccessHandler.class);
	private UserFacade userFacade;
	private SessionService sessionService;

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication)
			throws IOException, ServletException
	{
		sessionService.save(CoreConstants.Session.MOBILE, DeviceUtils.isMobile(request));;
		getUserFacade().loginSuccess(authentication.getName());
		request.setAttribute("login_error_message", "");
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

	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

}
