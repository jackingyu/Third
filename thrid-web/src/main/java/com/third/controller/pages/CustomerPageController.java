package com.third.controller.pages;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.facade.customer.CustomerFacade;
import com.third.facade.data.AddressData;
import com.third.facade.data.CityData;
import com.third.facade.data.CustomerData;
import com.third.facade.data.ListData;
import com.third.facade.data.RegionData;
import com.third.facade.data.SourceData;
import com.third.facade.local.I18NFacade;


@Controller
public class CustomerPageController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(CustomerPageController.class);
	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	@RequestMapping(value = "/getCustomerListPage", method = RequestMethod.GET)
	public String getCustomerListPage(Model model)
	{
		List<RegionData> regions = i18NFacade.getRegions();
		List<CityData> citys = i18NFacade.getCityForRegion(regions.get(0).getIsoCode());
		List<SourceData> sources = customerFacade.getSources();

		model.addAttribute("regions", regions);
		model.addAttribute("citys", citys);
		model.addAttribute("sources", sources);

		return ControllerConstants.Fragements.CUSTOMERLIST;
	}

	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	@ResponseBody
	public Object getCustomer(@RequestParam(value = "cellphone") final String cellphone)
	{
		CustomerData customer = customerFacade.getCustomerByCellphone(cellphone);
		return customer;
	}

	@RequestMapping(value = "/getCustomerList")
	@ResponseBody
	public Object getCustomerList(@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "name", required = false) final String name,
			@RequestParam(value = "page", required = false) final Integer page,
			@RequestParam(value = "rows", required = false) final Integer rows)
	{
		ListData customers = customerFacade.getCustomers(cellphone, name, getStartIndex(page, rows), rows);

		return customers;
	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	@ResponseBody
	public void createCustomer(@RequestParam(value = "cellphone") final String cellphone,
			@RequestParam(value = "name") final String name, @RequestParam(value = "QQ", required = false) final String QQ,
			@RequestParam(value = "email", required = false) final String email,
			@RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date birthday,
			@RequestParam(value = "weddingdate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date weddingdate,
			@RequestParam(value = "source", required = false) final String source,
			@RequestParam(value = "region", required = false) final String region,
			@RequestParam(value = "city", required = false) final String city,
			@RequestParam(value = "adr1", required = false) final String adr1,
			@RequestParam(value = "comment", required = false) final String comment)
	{

		CustomerData customer = new CustomerData();
		customer.setCellphone(cellphone);
		customer.setName(name);
		customer.setQQ(QQ);
		customer.setBirthday(birthday);
		customer.setEmail(email);
		customer.setComment(comment);
		customer.setWeddingdate(weddingdate);

		SourceData sourceData = new SourceData();
		sourceData.setPk(source);
		customer.setSource(sourceData);

		AddressData address = new AddressData();
		address.setAdr1(adr1);
		CityData cityData = new CityData();
		cityData.setIsoCode(city);
		address.setCity(cityData);

		RegionData regionData = new RegionData();
		regionData.setIsoCode(region);
		address.setRegion(regionData);

		customer.setAddress(address);
		customerFacade.createCustomer(customer);
	}

	@RequestMapping(value = "/modifyCustomer", method = RequestMethod.POST)
	@ResponseBody
	public void modifyCustomer(@RequestParam(value = "cellphone") final String cellphone,
			@RequestParam(value = "name") final String name, @RequestParam(value = "QQ", required = false) final String QQ,
			@RequestParam(value = "email", required = false) final String email,
			@RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date birthday,
			@RequestParam(value = "weddingdate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date weddingdate,
			@RequestParam(value = "source", required = false) final String source,
			@RequestParam(value = "region", required = false) final String region,
			@RequestParam(value = "city", required = false) final String city,
			@RequestParam(value = "adr1", required = false) final String adr1,
			@RequestParam(value = "comment", required = false) final String comment)
	{

		CustomerData customer = new CustomerData();
		customer.setCellphone(cellphone);
		customer.setName(name);
		customer.setQQ(QQ);
		customer.setBirthday(birthday);
		customer.setEmail(email);
		customer.setComment(comment);
		customer.setWeddingdate(weddingdate);

		SourceData sourceData = new SourceData();
		sourceData.setPk(source);
		customer.setSource(sourceData);

		AddressData address = new AddressData();
		address.setAdr1(adr1);
		CityData cityData = new CityData();
		cityData.setIsoCode(city);
		address.setCity(cityData);

		RegionData regionData = new RegionData();
		regionData.setIsoCode(region);
		address.setRegion(regionData);

		customer.setAddress(address);
		customerFacade.updateCustomer(customer);
	}
}
