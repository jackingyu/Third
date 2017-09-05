package com.third.controller.pages.lte;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.data.ComboboxData;
import com.third.facade.data.DTResults;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;
import com.third.facade.data.ProductData;
import com.third.facade.data.SizeAttributeData;
import com.third.facade.data.SizeAttributeGroupData;
import com.third.facade.data.TextMapper;
import com.third.facade.order.OrderFacade;
import com.third.facade.populator.option.OrderOption;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.CoreConstants;


@Controller
public class SizeOrderListController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(com.third.controller.pages.lte.SizeOrderListController.class);
	
	@Resource(name="orderFacade")
	private OrderFacade orderFacade;
	
	@RequestMapping(value = "/orderentry/listpage", method = RequestMethod.GET)
	public String orderEntryListPage(Model model)
	{
		fillAllStore2View(model);
		fillOrderStatus2View(model);
		return ControllerConstants.LTE.ORDERENTRYLISTPAGE;
	}

	@RequestMapping(value = "/orderentry/list")
	@ResponseBody
	public Object getOrderEntryList(@RequestParam(value = "externalId", required = false) final String externalId,
			@RequestParam(value = "customerName", required = false) final String name,
			@RequestParam(value = "storeCodes", required = false) final String storeCodes,
			@RequestParam(value = "orderEntryStatus", required = false) final String orderEntryStatus,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			final DataTableCriterias criterias)
	{
		Map<String, String> sp = new HashMap<String, String>();
		sp.put("externalId", externalId);
		sp.put("name", name);
		sp.put("status", orderEntryStatus);
		sp.put("storeCodes", storeCodes);

		DTResults r = orderFacade.getOrderEntries(startDate, endDate,  criterias.getStart(), criterias.getLength(), sp);

		return r;
	}

}