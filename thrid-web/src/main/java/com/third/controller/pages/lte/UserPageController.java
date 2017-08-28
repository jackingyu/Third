package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.data.ComboboxData;
import com.third.facade.data.ListData;
import com.third.facade.data.StoreData;
import com.third.facade.data.UserData;
import com.third.facade.data.UserGroupData;
import com.third.facade.user.UserFacade;


@Controller
public class UserPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(UserPageController.class);
	private static final String USERID_PATH_VARIABLE_PATTERN = "/{userId:.*}";

	@Autowired
	private UserFacade userFacade;



	@RequestMapping(value = "/user/userlistpage", method = RequestMethod.GET)
	public String getUserListPage()
	{
		return ControllerConstants.LTE.USERLISTPAGE;
	}

	@RequestMapping(value = "/user/createuserpage", method = RequestMethod.GET)
	public String getCreatePage(final Model model)
	{
		fillAllStoreInView(model, null);
		fillUserGroupInView(model, null);
		return ControllerConstants.LTE.USERDETAILSPAGE;
	}

	@RequestMapping(value = "/user/modifyuserpage" + USERID_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String getModifyUserPage(@PathVariable(value = "userId") final String userId, final Model model)
	{
		UserData user = userFacade.getUserById(userId);

		model.addAttribute("user", user);
		List<StoreData> selectedStores = user.getStores();
		fillAllStoreInView(model, selectedStores);
		fillUserGroupInView(model, user.getUserGroup().getPk());
		return ControllerConstants.LTE.USERDETAILSPAGE;
	}



	@RequestMapping(value = "/user/userlist")
	@ResponseBody
	public Object getUserList(@RequestParam(value = "userName", required = false) final String userName,
			@RequestParam(value = "userId", required = false) final String userId, final DataTableCriterias criterias)
	{
		ListData results = userFacade.getUsers(userId, userName, getStartIndexForDT(criterias), getPagesizeForDT(criterias));

		List<Object> users = results.getRows();
		List<String[]> arrayDatas = new ArrayList<String[]>();

		DTResultsV dtResult = initDTResults(results);

		for (int i = 0; i < users.size(); i++)
		{
			UserData userData = (UserData) users.get(i);
			String[] row = new String[5];
			row[0] = userData.getUserId();
			row[1] = userData.getName();
			row[2] = userData.getUserGroup() != null ? userData.getUserGroup().getName() : "";

			StringBuilder stores = new StringBuilder();

			if (CollectionUtils.isNotEmpty(userData.getStores()))
				for (int j = 0; j < userData.getStores().size(); j++)
				{
					stores = stores.append(userData.getStores().get(j).getName()).append(" ");
				}

			row[3] = stores.toString();

			arrayDatas.add(row);
		}

		dtResult.setData(arrayDatas);

		return dtResult;
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String saveUser(@RequestParam(value = "userId") final String userId,
			@RequestParam(value = "userPK") final String userPK, @RequestParam(value = "name") final String name,
			@RequestParam(value = "blocked", required = false) final boolean blocked,
			@RequestParam(value = "password", required = false) final String password,
			@RequestParam(value = "stores", required = false) final List<String> storeCodes,
			@RequestParam(value = "usergroupPK") final String userGroupPk, final Model model)
	{
		UserData user = new UserData();
		user.setName(name);
		user.setUserId(userId);
		UserGroupData userGroup = new UserGroupData();
		userGroup.setPk(userGroupPk);
		user.setUserGroup(userGroup);
		user.setPassword(password);
		user.setBlocked(blocked);

		if (CollectionUtils.isNotEmpty(storeCodes))
		{
			List<StoreData> stores = new ArrayList<StoreData>();
			for (int i = 0; i < storeCodes.size(); i++)
			{
				StoreData store = new StoreData();
				store.setCode(storeCodes.get(i));
				stores.add(store);
			}
			user.setStores(stores);
		}


		if (StringUtils.isEmpty(userPK))
			userFacade.createUser(user);
		else
			userFacade.updateUser(user);


		model.addAttribute("message", "保存成功!");

		return REDIRECT_PREFIX + "/user/modifyuserpage/" + userId;
	}



	private void fillUserGroupInView(final Model model, final String selectedGroup)
	{
		List<Object> groups = userFacade.getUserGroups("", "", 0, 9999).getRows();

		List<ComboboxData> results = new ArrayList<ComboboxData>();
		for (int i = 0; i < groups.size(); i++)
		{
			ComboboxData c = new ComboboxData();
			UserGroupData u = (UserGroupData) groups.get(i);

			c.setCode(u.getPk());
			c.setText(u.getName());

			if (StringUtils.isNotEmpty(selectedGroup) && c.getCode().equals(selectedGroup))
				c.setSelected(true);

			results.add(c);
		}

		model.addAttribute("userGroups", results);
	}
}
