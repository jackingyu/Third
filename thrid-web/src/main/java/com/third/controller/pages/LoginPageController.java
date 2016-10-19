package com.third.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginPageController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		System.out.println("hit login controller");
		
		return ControllerConstants.Pages.LOGIN;
	}

}
