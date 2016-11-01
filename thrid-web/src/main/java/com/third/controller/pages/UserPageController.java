package com.third.controller.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.ListData;
import com.third.facade.data.RoleData;
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

	@RequestMapping(value = "/getUserGroupListPage", method = RequestMethod.GET)
	public String getUserGroupListPage()
	{
		return ControllerConstants.Fragements.USERGROULIST;
	}

	@RequestMapping(value = "/getUserGroupList")
	@ResponseBody
	public Object getUserGroupList(@RequestParam(value = "userGroupName", required = false) final String userGroupName,
			@RequestParam(value = "userGroupId", required = false) final String userGroupId,
			@RequestParam(value = "page", required = false) final Integer page,
			@RequestParam(value = "rows", required = false) final Integer rows)
	{
		ListData userGroups = userFacade.getUserGroups(userGroupId, userGroupName, (page - 1) * rows, rows);

		return userGroups;
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

	@RequestMapping(value = "/modifyUserGroup")
	@ResponseBody
	public void modifyUserGroup(@RequestParam(value = "groupId", required = false) final String userGroupId,
			@RequestParam(value = "name", required = false) final String userGroupName,
			@RequestParam(value = "pk", required = false) final String pk,
			@RequestParam(value = "roleList", required = false) final String roleList)
	{
		UserGroupData userGroup = new UserGroupData();
		userGroup.setGroupId(userGroupId);
		userGroup.setName(userGroupName);
		userGroup.setPk(pk);

		String[] rolesPK = roleList.split(",");

		List<RoleData> roleDataList = new ArrayList<RoleData>();

		for (int i = 0; i < rolesPK.length; i++)
		{
			RoleData role = new RoleData();
			role.setPk(rolesPK[i]);
			roleDataList.add(role);
		}

		userGroup.setRoles(roleDataList);

		userFacade.updateUserGroup(userGroup);

	}

	@RequestMapping(value = "/getRoles")
	@ResponseBody
	public Object getRoles()
	{
		return userFacade.getRoles();
	}

	@RequestMapping(value = "/getRolesForUserGroup")
	@ResponseBody
	public Object getRoles(@RequestParam(value = "userGroupPK") final String userGroupPK)
	{
		return userFacade.getRolesForUserGroup(userGroupPK);
	}

}
