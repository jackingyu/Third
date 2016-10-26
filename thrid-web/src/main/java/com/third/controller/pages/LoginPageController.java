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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.third.facade.data.MenuData;
import com.third.facade.user.UserFacade;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.UserService;
import com.third.web.interceptors.beforeview.UiThemeResourceBeforeViewHandler;


@Controller
public class LoginPageController
{
	private static final Logger LOG = Logger.getLogger(LoginPageController.class);

	@Autowired
	private UserService userService;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@RequestMapping(value = "/master", method = RequestMethod.GET)
	public String getMasterPage()
	{
		LOG.info("welcome to master page controller");
		//		// userService.getUserById("yuxiang");
		//		UserModel user = new UserModel();
		//		user.setUserId("yuxiang");
		//		user.setName("yuxiang");
		//		user.setPassword("dddd");
		//		UserGroupModel userGroup = new UserGroupModel();
		//		userGroup.setGroupId("admin");
		//		user.setUserGroup(userGroup);
		//		LOG.info(userService.isExist(user.getUserId()));
		//userService.createUser(user);
		ObjectMapper mapper = new ObjectMapper();

		LOG.info("start get menu data");
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage()
	{
		LOG.info("welcome to master page controller");

		return ControllerConstants.Pages.LOGIN;
	}

}
