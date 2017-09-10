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
import com.third.facade.data.ComboboxData;
import com.third.facade.data.DTResults;
import com.third.facade.data.OrderData;
import com.third.facade.data.PaymentData;
import com.third.facade.data.SalesQuationData;
import com.third.facade.data.SourceData;
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
	private static final String PK_PATH_VARIABLE_PATTERN = "/{pk:.*}";

	@Resource(name = "salesQuationFacade")
	private SalesQuationFacade salesQuationFacade;

	@RequestMapping(value = "/salesquation/salesquationlistpage", method = RequestMethod.GET)
	public String orderListPage(Model model)
	{
		model.addAttribute("exhibitions", getExhibition());
		return ControllerConstants.LTE.SALESQUATIONLISTPAGE;
	}

	@RequestMapping(value = "/salesquation/createpage")
	public Object getCreateSalesQuationPage(final Model model)
	{
		model.addAttribute("paymentMethods", getPaymentMethods());
		model.addAttribute("exhibitions", getExhibition());

		return ControllerConstants.LTE.SALESQUATIONPAGE;
	}

	@RequestMapping(value = "/salesquation/modify/" + PK_PATH_VARIABLE_PATTERN)
	public Object getSalesQuationPage(@PathVariable("pk") String pk,
			final Model model)
	{
		SalesQuationData sqData = salesQuationFacade.getSalesQuation(pk);

		model.addAttribute("sq", sqData);
		model.addAttribute("paymentMethods", getPaymentMethods());
		List<ComboboxData> exhibitions = getExhibition();

		// for(int i = 0;i < exhibitions.size();i++)
		// {
		// if(exhibitions.get(i).getCode().equals(sqData.getSource().getPk()))
		// {
		// exhibitions.get(i).setSelected(true);
		// break;
		// }
		// }

		model.addAttribute("exhibitions", exhibitions);

		return ControllerConstants.LTE.SALESQUATIONPAGE;
	}

	@RequestMapping(value = "/salesquation/list")
	@ResponseBody
	public Object getSalesQuationList(
			@RequestParam(value = "exhibitions", required = false) final String exhibitions,
			@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			final DataTableCriterias criterias)
	{
		Map<String, String> sp = new HashMap<String, String>();
		sp.put("cellphone", cellphone);
		sp.put("exhibitions", exhibitions);
		;

		DTResults r = salesQuationFacade.getSalesQuation(startDate, endDate,
				criterias.getStart(), criterias.getLength(), sp);

		return r;
	}

	@RequestMapping(value = "/salesquation/save", method = RequestMethod.POST)
	public String saveSalesQuation(
			@RequestParam(value = "pk", required = false) final String pk,
			@RequestParam(value = "sourcePK", required = false) final String sourcePK,
			@RequestParam(value = "cellphone", required = false) final String cellphone,
			@RequestParam(value = "tryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date tryDate,
			@RequestParam(value = "photoDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date photoDate,
			@RequestParam(value = "deliveryDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date deliveryDate,
			@RequestParam(value = "coSalesperson", required = false) final String coSalesperson,
			@RequestParam(value = "customerName", required = false) final String customerName,
			@RequestParam(value = "weddingDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date weddingDate,
			@RequestParam(value = "paidamount", required = false) final String paidamount,
			@RequestParam(value = "paymentMethod", required = false) final String paymentMethod,
			@RequestParam(value = "continuecreate", required = true) final boolean continuecreate,
			final Model model, final HttpServletRequest request,
			final HttpServletResponse response)
	{
		SalesQuationData salesQuation = new SalesQuationData();
		salesQuation.setPk(pk);
		salesQuation.setCellphone(cellphone);
		salesQuation.setCustomerName(customerName);
		salesQuation.setCoSalesperson(coSalesperson);
		salesQuation.setPaidamount(paidamount);
		salesQuation.setPaymentMethod(paymentMethod);
		salesQuation.setDeliveryDate(deliveryDate);
		salesQuation.setWeddingDate(weddingDate);
		salesQuation.setPhotoDate(photoDate);
		SourceData source = new SourceData();
		source.setPk(sourcePK);
		salesQuation.setSource(source);
		salesQuation.setTryDate(tryDate);

		String pk1 = this.salesQuationFacade.saveSalesQuation(salesQuation);

		if (continuecreate)
			return REDIRECT_PREFIX + "/salesquation/createpage";

		return this.REDIRECT_PREFIX + "/salesquation/modify/" + pk1;
	}

}
