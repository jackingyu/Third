package com.third.controller.pages;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.facade.utils.TextMapperUtils;


@Controller
public class ComboboxDataController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(ComboboxDataController.class);

	@Resource(name = "textMapperUtils")
	private TextMapperUtils textMapperUtils;

	@RequestMapping(value = "/getPaymentTypes")
	@ResponseBody
	public Object getPaymentTypes()
	{
		return textMapperUtils.getPaymentTypes();
	}

	@RequestMapping(value = "/getPaymentMethods")
	@ResponseBody
	public Object getPaymentMethods()
	{
		return textMapperUtils.getPaymentMethods();
	}

	@RequestMapping(value = "/getItemCategories")
	@ResponseBody
	public Object getItemCategories()
	{
		return textMapperUtils.getItemCategories();
	}

}
