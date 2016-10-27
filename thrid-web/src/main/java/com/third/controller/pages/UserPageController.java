package com.third.controller.pages;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.third.facade.user.UserFacade;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.UserService;
import com.third.web.interceptors.beforeview.UiThemeResourceBeforeViewHandler;


@Controller
public class UserPageController
{
	private static final Logger LOG = Logger.getLogger(UserPageController.class);
	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value = "/usergrouplistpage", method = RequestMethod.GET)
	public String getUserGroupListPage()
	{
		return ControllerConstants.Fragements.USERGROULIST;
	}

	@RequestMapping(value = "/getUserGroupList", method = RequestMethod.GET)
	public String getUserGroupList(@RequestParam(value = "userGroupName") final String userGroupName,
			@RequestParam(value = "userGroupId") final String userGroupId)
	{
		userFacade.getUserGroups(userGroupId, userGroupName);
		return null;
	}


}
