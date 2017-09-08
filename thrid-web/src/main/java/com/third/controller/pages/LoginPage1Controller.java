package com.third.controller.pages;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.third.facade.data.MenuData;
import com.third.facade.data.UserData;
import com.third.facade.user.UserFacade;
import com.third.service.user.UserService;

@Controller
public class LoginPage1Controller {
	private static final Logger LOG = Logger
			.getLogger(LoginPage1Controller.class);

	@Autowired
	private UserService userService;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@RequestMapping(value = "/master", method = RequestMethod.GET)
	public String getMasterPage()
	{
		UserData user = userFacade.getCurrentUser();
		List<MenuData> menus = userFacade.getMenuData();
		String result = JSON.toJSONString(menus);
		LOG.info(result);

		return ControllerConstants.Pages.MASTER;
	}

	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	@ResponseBody
	public Object getMenu()
	{
		return userFacade.getMenuData();
	}

	@RequestMapping(value = "/login1", method = RequestMethod.GET)
	public String getLoginPage()
	{
		LOG.info("welcome to master page controller");

		return ControllerConstants.Pages.LOGIN;
	}

}
