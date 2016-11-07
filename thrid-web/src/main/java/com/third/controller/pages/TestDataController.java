package com.third.controller.pages;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.third.facade.utils.TestDataGenerator;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.MenuService;
import com.third.service.user.RoleService;
import com.third.service.user.UserService;
import com.third.web.interceptors.beforeview.UiThemeResourceBeforeViewHandler;


@Controller
public class TestDataController
{
	private static final Logger LOG = Logger.getLogger(TestDataController.class);

	@Resource(name = "dataGenerator")
	private TestDataGenerator testDataGenerator;

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String testData()
	{
		testDataGenerator.generateData();
		return "redirect:/master";
	}

}
