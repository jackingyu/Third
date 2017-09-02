package com.third.controller.pages.lte;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.controller.pages.AbstractPageController;
import com.third.controller.pages.ControllerConstants;
import com.third.core.util.DataTableCriterias;
import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.order.OrderFacade;
import com.third.facade.order.OrderProcessFacade;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.CoreConstants;


@Controller
public class ScanOrderController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(com.third.controller.pages.lte.ScanOrderController.class);

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;
	
	@Resource(name = "textMapperUtils")
	private TextMapperUtils textMapperUtils;

	
	@RequestMapping(value = "/factory/orderdeliver", method = RequestMethod.GET)
	public String getFactoryDeliverOrderPage(Model model)
	{
		//TODO need to change to the right status
		model.addAttribute("status",CoreConstants.OrderStatus.FACTORY_DELIVERED);
		model.addAttribute("currentStatus",CoreConstants.OrderStatus.FACTORY_APPROVE);
		model.addAttribute("successmessage","工厂发货成功");
		model.addAttribute("title","工厂发货");
		return ControllerConstants.LTE.SCANORDERPAGE;
	}
	
	@RequestMapping(value = "/store/orderdeliver", method = RequestMethod.GET)
	public String getStoreOrderDeliverPage(Model model)
	{
		//TODO need to change to the right status
		model.addAttribute("status",CoreConstants.OrderStatus.STORE_DELIVERED);
		model.addAttribute("currentStatus",CoreConstants.OrderStatus.STORE_RECEIPT);
		model.addAttribute("successmessage","门店发货成功");
		model.addAttribute("title","顾客取件");
		return ControllerConstants.LTE.SCANORDERPAGE;
	}
	
	@RequestMapping(value = "/store/orderreceipt", method = RequestMethod.GET)
	public String getStoreOrderReceiptPage(Model model)
	{
		//TODO need to change to the right status
		model.addAttribute("status",CoreConstants.OrderStatus.STORE_RECEIPT);
		model.addAttribute("successmessage","门店收货成功");
		model.addAttribute("currentStatus",CoreConstants.OrderStatus.FACTORY_DELIVERED);
		model.addAttribute("title","门店收货");
		return ControllerConstants.LTE.SCANORDERPAGE;
	}
	
	@RequestMapping(value = "/orderscan/getentrylist", method = RequestMethod.GET)
	@ResponseBody
	public Object getEntryList(@RequestParam(value = "entryPK", required = false) final String entryPK,
			@RequestParam(value = "externalId", required = false) final String externalId,
			@RequestParam(value = "currentStatus", required = false) final Integer status,
			Model model)
	{
		return orderFacade.getOrderEntriesByPKorId(entryPK, externalId,status);
	}
}
