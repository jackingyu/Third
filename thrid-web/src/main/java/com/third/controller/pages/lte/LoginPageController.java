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


@Controller
public class LoginPageController
{
	private static final Logger LOG = Logger.getLogger(LoginPageController.class);

	@Autowired
	private UserService userService;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage()
	{
		return ControllerConstants.LTE.LOGINPAGE;
	}

	

}
