package com.third.controller.pages.lte;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.third.controller.pages.ControllerConstants;
import com.third.service.user.SessionService;

@Controller
public class HomePageController {
	private static final Logger LOG = Logger
			.getLogger(HomePageController.class);

	@Resource(name="sessionService")
	private SessionService sessionService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getLoginPage()
	{
		if(sessionService.isMobile())
			return ControllerConstants.LTE.MHOMEPAGE;
		else
		    return ControllerConstants.LTE.HOMEPAGE;
	}

}
