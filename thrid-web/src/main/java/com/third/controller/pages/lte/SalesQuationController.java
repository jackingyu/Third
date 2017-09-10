package com.third.controller.pages.lte;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.data.DTResults;
import com.third.facade.data.OrderData;
import com.third.facade.data.PaymentData;
import com.third.facade.data.StoreData;
import com.third.facade.data.TextMapper;
import com.third.facade.order.OrderFacade;
import com.third.facade.order.SalesQuationFacade;
import com.third.facade.populator.option.OrderOption;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.CoreConstants;

@Controller
public class SalesQuationController extends AbstractPageController {
	private static final Logger LOG = Logger.getLogger(
			com.third.controller.pages.lte.SalesQuationController.class);
	private static final String PK_PATH_VARIABLE_PATTERN = "/{PK:.*}";

	@Resource(name = "salesQuationFacade")
	private SalesQuationFacade salesQuationFacade;

	@RequestMapping(value = "/salesquation/salesquationlistpage", method = RequestMethod.GET)
	public String orderListPage(Model model)
	{
		return ControllerConstants.LTE.SALESQUATIONLISTPAGE;
	}

	@RequestMapping(value = "/salesquation/list")
	@ResponseBody
	public Object getSalesQuationList(
			@RequestParam(value = "exhibition", required = false) final String exhibition,
			@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			final DataTableCriterias criterias)
	{
		Map<String, String> sp = new HashMap<String, String>();
		sp.put("cellphone", cellphone);
		sp.put("exhibition", exhibition);

		DTResults r = salesQuationFacade.getSalesQuation(startDate, endDate, criterias.getStart(), criterias.getLength(), sp);

		return r;
	}

	@RequestMapping(value = "/salesquation/get/"
			+ PK_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String orderDetail(@PathVariable("PK") String PK,
			final Model model, final HttpServletRequest request,
			final HttpServletResponse response)
	{
		return ControllerConstants.LTE.ORDERDETAILSPAGE;
	}
	
}
