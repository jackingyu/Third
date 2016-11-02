package com.third.controller.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.ListData;
import com.third.facade.data.RoleData;
import com.third.facade.data.UserData;
import com.third.facade.data.UserGroupData;
import com.third.facade.user.UserFacade;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.UserService;
import com.third.web.interceptors.beforeview.UiThemeResourceBeforeViewHandler;


@Controller
public class UserPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(UserPageController.class);
	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value = "/getUserListPage", method = RequestMethod.GET)
	public String getUserListPage()
	{
		return ControllerConstants.Fragements.USERLIST;
	}



	@RequestMapping(value = "/getUserList")
	@ResponseBody
	public Object getUserList(@RequestParam(value = "userName", required = false) final String userName,
			@RequestParam(value = "userId", required = false) final String userId,
			@RequestParam(value = "page", required = false) final Integer page,
			@RequestParam(value = "rows", required = false) final Integer rows)
	{
		ListData users = userFacade.getUsers(userId, userName, (page - 1) * rows, rows);

		return users;
	}


	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public void createUser(@RequestParam(value = "userId") final String userId, @RequestParam(value = "name") final String name,
			@RequestParam(value = "blocked", required = false) final boolean blocked,
			@RequestParam(value = "password", required = false) final String password,
			@RequestParam(value = "usergroup") final String userGroupPk)
	{
		UserData user = new UserData();
		user.setName(name);
		user.setUserId(userId);
		UserGroupData userGroup = new UserGroupData();
		userGroup.setPk(userGroupPk);
		user.setUserGroup(userGroup);

		if (!StringUtils.isEmpty(password))
			user.setPassword(password);

		userFacade.createUser(user);
	}

	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
	@ResponseBody
	public void modifyUser(@RequestParam(value = "userId") final String userId, @RequestParam(value = "name") final String name,
			@RequestParam(value = "blocked", required = false) final String blockedString,
			@RequestParam(value = "password", required = false) final String password,
			@RequestParam(value = "usergroup") final String userGroupPk)
	{
		boolean blocked = blockedString.equals("on");
		UserData user = new UserData();
		user.setName(name);
		user.setUserId(userId);
		UserGroupData userGroup = new UserGroupData();
		userGroup.setPk(userGroupPk);
		user.setUserGroup(userGroup);

		if (!StringUtils.isEmpty(password))
			user.setPassword(password);

		userFacade.updateUser(user);
	}

	@RequestMapping(value = "/getDetailsForUser", method = RequestMethod.GET)
	@ResponseBody
	public Object getUser(@RequestParam(value = "userId", required = false) final String userId,
			@RequestParam(value = "pk", required = false) final String userPk)
	{
		if (!StringUtils.isEmpty(userId))
		{
			return userFacade.getUserById(userId);
		}

		return null;
	}

}
