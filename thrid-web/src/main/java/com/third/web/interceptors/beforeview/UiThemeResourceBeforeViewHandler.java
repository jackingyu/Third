package com.third.web.interceptors.beforeview;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.third.facade.data.MenuData;
import com.third.model.CoreConstants;
import com.third.service.user.SessionService;
import com.third.web.interceptors.BeforeViewHandler;


/**
 * Interceptor to setup the paths to the UI resource paths in the model before passing it to the view. Sets up the path
 * to the web accessible UI resources for the following: * The current site * The current theme * The common resources
 * All of these paths are qualified by the current UiExperienceLevel
 */
public class UiThemeResourceBeforeViewHandler implements BeforeViewHandler
{
	private static final Logger LOG = Logger.getLogger(UiThemeResourceBeforeViewHandler.class);
   @Resource(name="sessionService")
	private SessionService sessionService;
	
	@Override
	public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView)
	{
		modelAndView.addObject("jucierTplBasePath", "/WEB-INF/views/juicer");
		modelAndView.addObject("framentsPath", "/WEB-INF/views/fragments");
		modelAndView.addObject("contextPath", "/thrid-web");
		modelAndView.addObject("WXImagePath", "/thrid-web/_ui/images/weixin");
		modelAndView.addObject("WXJsPath", "/thrid-web/_ui/js/weixin");
		modelAndView.addObject("WXCssPath", "/thrid-web/_ui/css/weixin");
		modelAndView.addObject("lteResourcePath","/thrid-web/_ui/lte");
		
		List<MenuData> menus = (List<MenuData>) sessionService.get(CoreConstants.Session.MENU);
		
		if(menus!=null)
		{
			confirmActiveMenu(menus, request.getServletPath());
			modelAndView.addObject("menus",menus);
		}
			
	}
	
	protected void confirmActiveMenu(List<MenuData> menus,final String path){
		
		for(int i = 0; i < menus.size();i++)
		{  
			MenuData parent = menus.get(i);
			List<MenuData> children = parent.getMenus();
			parent.setActive(false);
			
			for(int j = 0; j < children.size();j++)
			{
				MenuData child = children.get(j);
				child.setActive(false);
				
			   //再次active
				if(path.equals(child.getUrl()))
				{
					child.setActive(true);
					parent.setActive(true);
				}
			}
		}
	}

}
