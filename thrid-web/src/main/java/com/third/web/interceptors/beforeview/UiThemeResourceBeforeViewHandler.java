package com.third.web.interceptors.beforeview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.third.web.interceptors.BeforeViewHandler;


/**
 * Interceptor to setup the paths to the UI resource paths in the model before passing it to the view. Sets up the path
 * to the web accessible UI resources for the following: * The current site * The current theme * The common resources
 * All of these paths are qualified by the current UiExperienceLevel
 */
public class UiThemeResourceBeforeViewHandler implements BeforeViewHandler
{
	private static final Logger LOG = Logger.getLogger(UiThemeResourceBeforeViewHandler.class);

	public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView)
	{
		modelAndView.addObject("jucierTplBasePath", "/WEB-INF/views/juicer");
	}

}
