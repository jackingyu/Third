package com.third.controller.pages.lte;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.facade.customer.SourceFacade;
import com.third.facade.data.DTResults;
import com.third.facade.data.SourceData;
import com.third.facade.store.FIReportFacade;


@Controller
public class FIReportController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(com.third.controller.pages.lte.FIReportController.class);

	@Resource(name="FIReportFacade")
   private FIReportFacade fiReportFacade;
	
	@RequestMapping(value = "/payment/listpage", method = RequestMethod.GET)
	public String paymentListPage(Model model)
	{
	
		fillAllStore2View(model);
		fillSourceInModel(model);
		fillPaymentMethods2View(model);
		fillOrderStatus2View(model);
		model.addAttribute("salesPersons",getSalesPerson());
		return ControllerConstants.LTE.PAYMENTLISTPAGE;
	}




	@RequestMapping(value = "/payment/getlist")
	@ResponseBody
	public Object getPaymentList(@RequestParam(value = "storeCodes", required = false) final String[] storeCodes,
			@RequestParam(value = "sourcePKs", required = false) final String[] sourcePKs,
			@RequestParam(value = "paymentMethods", required = false) final String[] paymentMethods,
			@RequestParam(value = "orderStatus", required = false) final String[] orderStatus,
			@RequestParam(value = "salesPersons", required = false) final String[] salesPersons,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			final DataTableCriterias criterias)
	{

		Map<String, String[]> sp = new HashMap<String, String[]>();
		sp.put("storeCodes", storeCodes);
		sp.put("sourcePKs", sourcePKs);
		sp.put("paymentMethods", paymentMethods);
		sp.put("orderStatus", orderStatus);
		sp.put("salesPersons", salesPersons);
		DTResults r = fiReportFacade.getPaymentList(startDate, endDate,criterias.getStart(), criterias.getLength(), sp);
		
		return r;
	}
	

}
