package com.third.controller.pages.lte;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.third.controller.pages.ControllerConstants;

@Controller
public class HomePageController {
	private static final Logger LOG = Logger
			.getLogger(HomePageController.class);

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getLoginPage()
	{
		return ControllerConstants.LTE.HOMEPAGE;
	}

}
