package com.third.controller.pages.lte;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.facade.customer.CustomerFacade;
import com.third.facade.local.I18NFacade;

@Controller
public class AddressDataController extends AbstractPageController {
	private static final Logger LOG = Logger
			.getLogger(AddressDataController.class);

	@Autowired
	private CustomerFacade customerFacade;

	@Autowired
	private I18NFacade i18NFacade;

	@RequestMapping(value = "/address/getcityforregion", method = RequestMethod.GET)
	@ResponseBody
	public Object getCity(
			@RequestParam(value = "regionISOCode", required = true) final String regionISOCode)
	{
		return getCityForRegion(regionISOCode);
	}
	
	@RequestMapping(value = "/address/getdistrictforcity", method = RequestMethod.GET)
	@ResponseBody
	public Object getDistrict(
			@RequestParam(value = "cityISOCode", required = true) final String cityISOCode)
	{
		return getDistrictForCity(cityISOCode);
	}

}
