package com.third.controller.pages;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class OrderPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(OrderPageController.class);

	@RequestMapping(value = "/getOrderListPage", method = RequestMethod.GET)
	public String getCustomerListPage(Model model)
	{
		return ControllerConstants.Fragements.ORDERLIST;
	}

}
