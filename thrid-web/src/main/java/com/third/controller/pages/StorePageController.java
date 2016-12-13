package com.third.controller.pages;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.facade.store.StoreFacade;


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
