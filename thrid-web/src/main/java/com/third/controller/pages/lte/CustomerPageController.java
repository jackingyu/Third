package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.customer.CustomerFacade;
import com.third.facade.data.AddressData;
import com.third.facade.data.CityData;
import com.third.facade.data.ComboboxData;
import com.third.facade.data.CustomerData;
import com.third.facade.data.ListData;
import com.third.facade.data.RegionData;
import com.third.facade.data.SourceData;
import com.third.facade.local.I18NFacade;

@Controller
public class CustomerPageController extends AbstractPageController {
	private static final Logger LOG = Logger
			.getLogger(CustomerPageController.class);
	private static final String CELLPHONE_PATH_VARIABLE_PATTERN = "/{cellphone:.*}";

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	@RequestMapping(value = "/customer/customerlistpage", method = RequestMethod.GET)
	public String getCustomerListPage(final Model model,
			final HttpServletRequest request,
			final HttpServletResponse response)
	{
		fillAuthorizedStoreInView(model);
		return ControllerConstants.LTE.CUSTOMERLISTPAGE;
	}

	@RequestMapping(value = "/customer/createcustomerpage", method = RequestMethod.GET)
	public String getCreateCustomerPage(final Model model,
			@ModelAttribute("message") final String message,
			final HttpServletRequest request,
			final HttpServletResponse response)
	{
		fillSourceInModel(model);
		fillAddressInModel(model, null);

		return ControllerConstants.LTE.CREATECUSTOMERPAGE;
	}

	@RequestMapping(value = "/customer/modifycustomerpage"
			+ CELLPHONE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String getModifyCustomerPage(
			@PathVariable("cellphone") final String cellphone,
			final Model model, @ModelAttribute("message") final String message,
			final HttpServletRequest request,
			final HttpServletResponse response)
	{
		CustomerData customer = customerFacade
				.getCustomerByCellphone(cellphone);

		fillSourceInModel(model);
		fillAddressInModel(model,
				customer.getAddress() != null
						? customer.getAddress().getRegion().getIsoCode()
						: null);
		model.addAttribute("customer", customer);
		return ControllerConstants.LTE.MODIFYCUSTOMERPAGE;
	}

	@RequestMapping(value = "/customer/customerlist", method = RequestMethod.GET)
	@ResponseBody
	public Object getCustomers(
			@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "customerName", required = false) final String customerName,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date endDate,
			final DataTableCriterias criterias)
	{
		ListData results = null;
		
		if (startDate != null && endDate != null)
			results = customerFacade.getCustomers(cellphone, customerName,
					startDate, endDate, getStartIndexForDT(criterias),
					getPagesizeForDT(criterias));

		if (startDate == null && endDate == null)
			results = customerFacade.getCustomers(cellphone, customerName,
					getStartIndexForDT(criterias), getPagesizeForDT(criterias));

		List<Object> customers = results.getRows();

		List<String[]> listDatas = new ArrayList<String[]>();

		DTResultsV dtResult = initDTResults(results);

		for (int i = 0; i < customers.size(); i++)
		{
			CustomerData od = (CustomerData) customers.get(i);
			String[] row = new String[5];
			row[0] = od.getCellphone();
			row[1] = od.getName();
			if (od.getWeddingdate() != null)
				row[2] = DateUtils.formatDate(od.getWeddingdate(),
						"yyyy-MM-dd");
			row[3] = od.getSource().getName();
			listDatas.add(row);
		}
		;

		dtResult.setData(listDatas);

		return dtResult;
	}

	@RequestMapping(value = "/customer/get", method = RequestMethod.GET)
	@ResponseBody
	public Object getCustomerDetail(
			@RequestParam(value = "cellphone", required = true) final String cellphone)
	{
		CustomerData customer = customerFacade
				.getCustomerByCellphone(cellphone);

		return customer;
	}

	@RequestMapping(value = "/customer/save", method = RequestMethod.POST)
	public String saveCustomer(
			@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "customerPK", required = false) final String customerPK,
			@RequestParam(value = "name") final String name,
			@RequestParam(value = "qq", required = false) final String QQ,
			@RequestParam(value = "email", required = false) final String email,
			@RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date birthday,
			@RequestParam(value = "weddingdate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date weddingdate,
			@RequestParam(value = "source", required = false) final String source,
			@RequestParam(value = "region", required = false) final String region,
			@RequestParam(value = "city", required = false) final String city,
			@RequestParam(value = "adr1", required = false) final String adr1,
			@RequestParam(value = "adr2", required = false) final String adr2,
			@RequestParam(value = "tel1", required = false) final String tel1,
			@RequestParam(value = "tel2", required = false) final String tel2,
			@RequestParam(value = "comment", required = false) final String comment,
			final Model model, final RedirectAttributes attr)
	{

		if (StringUtils.isEmpty(customerPK) && customerFacade
				.getCustomerByCellphone(cellphone).getPk() != null)
		{
			attr.addFlashAttribute("message", "手机号重复!");
			return REDIRECT_PREFIX + "/customer/createcustomerpage/";
		}
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
		address.setAdr2(adr2);
		address.setTel1(tel1);
		address.setTel2(tel2);

		CityData cityData = new CityData();
		cityData.setIsoCode(city);
		address.setCity(cityData);

		RegionData regionData = new RegionData();
		regionData.setIsoCode(region);
		address.setRegion(regionData);

		customer.setAddress(address);

		if (StringUtils.isEmpty(customerPK))
			customerFacade.createCustomer(customer);
		else
			customerFacade.updateCustomer(customer);

		attr.addFlashAttribute("message", "保存成功!");
		return REDIRECT_PREFIX + "/customer/modifycustomerpage/" + cellphone;
	}

}
