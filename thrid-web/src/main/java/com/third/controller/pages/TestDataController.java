package com.third.controller.pages;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.third.facade.testdata.builder.LotsOrderDataBuilder;
import com.third.facade.utils.TestDataGenerator;

@Controller
public class TestDataController {
	private static final Logger LOG = Logger
			.getLogger(TestDataController.class);

	@Resource(name = "dataGenerator")
	private TestDataGenerator testDataGenerator;

	@Resource(name = "lotsOrderDataBuilder")
	private LotsOrderDataBuilder lotsOrderDataBuilder;

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String testData()
	{
		testDataGenerator.generateData();
		return "redirect:/login";
	}

	@RequestMapping(value = "/lotsdata", method = RequestMethod.POST)
	public void lotsOfData(
			@RequestParam(value = "start", required = false) Integer start)
	{
		LOG.info("start create lots data " + start);
		int length = 3000;
		lotsOrderDataBuilder.buildData(start, length);
	}
}
