package com.third.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.third.model.User;
import com.third.service.impl.user.DefaultUserService;
import com.third.service.user.UserService;

import javax.annotation.Resource;

@Controller
public class FirstController {
	
	@Resource(name="userService")
	UserService userService;
	
	@RequestMapping(value = "/p", method = RequestMethod.GET)
	public String testController(){
		System.out.println("hit test controller");
		User user = userService.getCurrentUser();
		if(user!=null)
		System.out.println("current user:"+user.getUsername());
		return "/index.jsp";
	}
	
}
