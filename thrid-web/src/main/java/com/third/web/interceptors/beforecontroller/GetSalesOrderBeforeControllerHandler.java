/*
 * [y] anguo project
 * Copyright (c) 2015-2016
 * All rights reserved.
 *
 */
package com.third.web.interceptors.beforecontroller;

import java.lang.annotation.Annotation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.method.HandlerMethod;

import com.third.controller.annotation.GetSalesOrderValidator;
import com.third.facade.user.UserFacade;
import com.third.web.interceptors.BeforeControllerHandler;


public class GetSalesOrderBeforeControllerHandler implements BeforeControllerHandler
{
	private static final Logger LOG = Logger.getLogger(GetSalesOrderBeforeControllerHandler.class);

	private String redirectUrl = "/anguo-sellercenter/error";

	@Resource
	private UserFacade userFacade;

	@Resource
	private RedirectStrategy redirectStrategy;


	@Override
	public boolean beforeController(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler)
			throws Exception
	{
		final GetSalesOrderValidator annotation = findAnnotation(handler, GetSalesOrderValidator.class);
		if (annotation == null)
		return true;
		
		return false;
	}

	protected <T extends Annotation> T findAnnotation(final HandlerMethod handlerMethod, final Class<T> annotationType)
	{
		// Search for method level annotation
		final T annotation = handlerMethod.getMethodAnnotation(annotationType);
		if (annotation != null)
		{
			return annotation;
		}

		// Search for class level annotation
		return AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), annotationType);
	}
}
