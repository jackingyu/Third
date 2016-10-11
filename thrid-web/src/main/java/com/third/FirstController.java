package com.third;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.third.domain.User;
import org.third.facade.UserServiceImpl;

import javax.annotation.Resource;

@Controller
public class FirstController {
	
	@Resource(name="userService")
	UserServiceImpl userService;
	
	@RequestMapping(value = "/p", method = RequestMethod.GET)
	public String testController(){
		System.out.println("hit test controller");
		User user =  userService.getUser("first");
		System.out.println(user.getName());
		return "/index.jsp";
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	
}
