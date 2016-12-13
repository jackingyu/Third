package com.third.service.user.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.third.service.user.SessionService;

public class DefaultSessionService implements SessionService
{
	@Autowired 
	private HttpSession httpSession;

	@Override
	public void save(String key, Object value)
	{
		httpSession.setAttribute(key, value);
	}

	@Override
	public Object get(String key)
	{
		return httpSession.getAttribute(key);
	}

	@Override
	public void clear(String key)
	{
		httpSession.removeAttribute(key);
	}
}
