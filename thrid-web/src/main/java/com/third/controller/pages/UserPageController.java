package com.third.controller.pages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.UserService;
import com.third.web.interceptors.beforeview.UiThemeResourceBeforeViewHandler;


@Controller
public class UserPageController
{
	private static final Logger LOG = Logger.getLogger(UserPageController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/usergrouplistpage", method = RequestMethod.GET)
	public String getMasterPage()
	{
		return ControllerConstants.Fragements.USERGROULIST;
	}


}
