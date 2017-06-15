package com.third.controller.pages;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.third.facade.utils.TestDataGenerator;


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
		return "redirect:/login";
	}

}
