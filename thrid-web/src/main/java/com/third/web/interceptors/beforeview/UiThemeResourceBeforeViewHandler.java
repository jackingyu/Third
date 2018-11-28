package com.third.web.interceptors.beforeview;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.third.core.constants.CoreConstants;
import com.third.facade.data.MenuData;
import com.third.service.user.SessionService;
import com.third.service.user.UserService;
import com.third.web.interceptors.BeforeViewHandler;

/**
 * Interceptor to setup the paths to the UI resource paths in the model before
 * passing it to the view. Sets up the path to the web accessible UI resources
 * for the following: * The current site * The current theme * The common
 * resources All of these paths are qualified by the current UiExperienceLevel
 */

@Component
public class UiThemeResourceBeforeViewHandler implements BeforeViewHandler {
	private static final Logger LOG = Logger
			.getLogger(UiThemeResourceBeforeViewHandler.class);
	@Autowired
	private SessionService sessionService;
	@Autowired
	private UserService userService;

	@Override
	public void beforeView(final HttpServletRequest request,
			final HttpServletResponse response, final ModelAndView modelAndView)
	{
		modelAndView.addObject("jucierTplBasePath", "/views/juicer");
		modelAndView.addObject("framentsPath", "/views/fragments");
		modelAndView.addObject("contextPath", "/thrid-web");
		modelAndView.addObject("WXImagePath", "/thrid-web/_ui/images/weixin");
		modelAndView.addObject("WXJsPath", "/thrid-web/_ui/js/weixin");
		modelAndView.addObject("WXCssPath", "/thrid-web/_ui/css/weixin");
		modelAndView.addObject("lteResourcePath", "/thrid-web/_ui/lte");

		List<MenuData> menus = (List<MenuData>) sessionService
				.get(CoreConstants.Session.MENU);

		if (menus != null)
		{
		   if(!sessionService.isMobile())
			confirmActiveMenu(menus, request.getServletPath());
		    
			modelAndView.addObject("menus", menus);
		}
		
		if(userService.getCurrentUser()!=null)
	 	modelAndView.addObject("userName", userService.getCurrentUser().getName());
		
		if(sessionService.isMobile())
			modelAndView.addObject("isMobile", true);
		else
			modelAndView.addObject("isMobile", false);

	}

	protected void confirmActiveMenu(List<MenuData> menus, final String path)
	{

		for (int i = 0; i < menus.size(); i++)
		{
			MenuData parent = menus.get(i);
			List<MenuData> children = parent.getMenus();
			parent.setActive(false);

			for (int j = 0; j < children.size(); j++)
			{
				MenuData child = children.get(j);
				child.setActive(false);

				// 再次active
				if (path.equals(child.getUrl()))
				{
					child.setActive(true);
					parent.setActive(true);
				}
			}
		}
	}

}
