package com.third.controller.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.third.facade.store.StoreFacade;
import com.third.facade.user.UserFacade;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.UserService;
import com.third.web.interceptors.beforeview.UiThemeResourceBeforeViewHandler;


@Controller
public class StorePageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(StorePageController.class);
	@Resource(name = "storeFacade")
	private StoreFacade storeFacade;

	@RequestMapping(value = "/getStores")
	@ResponseBody
	public Object getStores(@RequestParam(value = "storeName", required = false) final String name)
	{
		return storeFacade.getStores(name);
	}
}
