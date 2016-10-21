package com.third.controller.pages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.third.service.user.UserService;
import com.third.web.interceptors.beforeview.UiThemeResourceBeforeViewHandler;


@Controller
public class LoginPageController {
	private static final Logger LOG = Logger.getLogger(LoginPageController.class);
    @Autowired
	private UserService userService;
	@RequestMapping(value = "/master", method = RequestMethod.GET)
	public String getMasterPage(){
		LOG.info("welcome to master page controller");
		userService.getUserById("yuxiang");
		return ControllerConstants.Pages.MASTER;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(){
		LOG.info("welcome to master page controller");
		
		return ControllerConstants.Pages.LOGIN;
	}

}
