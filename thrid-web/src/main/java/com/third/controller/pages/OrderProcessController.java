package com.third.controller.pages;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.data.ResponseMessage;
import com.third.facade.order.OrderFacade;
import com.third.facade.order.OrderProcessFacade;
import com.third.facade.utils.TextMapperUtils;


@Controller
public class OrderProcessController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(OrderProcessController.class);

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@Resource(name = "textMapperUtils")
	private TextMapperUtils textMapperUtils;
	
	@Resource(name = "orderProcessFacade")
	private OrderProcessFacade orderProcessFacade;

	@RequestMapping(value = "/getOrderProcessPage", method = RequestMethod.GET)
	public String getOrderProcessPage(Model model)
	{
		return ControllerConstants.Fragements.ORDERPROCESS;
	}
	
	@RequestMapping(value = "/processOrder", method = RequestMethod.POST)
	@ResponseBody
	public Object processOrder(@RequestParam(value = "orderCode") final String orderCode,
			@RequestParam(value = "status") final Integer status,
			final HttpServletRequest request,
			final HttpServletResponse response) 
	{
		try
		{
			orderProcessFacade.processOrder(orderCode, status);
		}
		catch (NoQualifiedTargetStatusException e)
		{
			response.setStatus(500);
			return new ResponseMessage("目标状态不合法,当前订单状态为"+e.getCurrentStatus());
		}
		catch (NotFoundException e)
		{
			response.setStatus(500);
			return new ResponseMessage("订单不存在");
		}
		
		return orderProcessFacade.getProcessRecordForOrder(orderCode);
	}

}
