package com.third.controller.pages.lte;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.third.controller.pages.ControllerConstants;
import com.third.facade.user.UserFacade;
import com.third.service.user.UserService;

import java.net.URL;
import java.net.URLClassLoader;

@Controller
public class LoginPageController {
	private static final Logger LOG = Logger
			.getLogger(LoginPageController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage()
	{
		System.out.println(LoginPageController.class.getResource("").getPath());
		return "index";
//		return ControllerConstants.LTE.LOGINPAGE;
	}

}
