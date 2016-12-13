package com.third.controller.weixin;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.third.controller.pages.ControllerConstants;
import com.third.facade.customer.CustomerFacade;
import com.third.facade.data.CustomerData;
import com.third.facade.data.OrderData;
import com.third.facade.order.OrderFacade;
import com.third.facade.populator.option.OrderOption;


@Controller
@RequestMapping("/wx")
public class WeixinOrderController extends AbstractWeixinController
{
	private static final Logger LOG = Logger.getLogger(WeixinOrderController.class);
	
	@Resource(name="orderFacade")
	private OrderFacade orderFacade;
	
	@Resource(name="customerFacade")
	private CustomerFacade customerFacade;

	@RequestMapping(value = "/getRegisterPage")
	public String getRegisterPage()
	{
		return ControllerConstants.WeiXin.REGISTERPAGE;
	}
	
	@RequestMapping(value = "/getOrderList")
	public String getOrderList(final Model model)
	{
		CustomerData customer = customerFacade.getCurrentCustomer();
		List<OrderData> orders = orderFacade.getOrdersForCustomer(customer.getCellphone());
		
		model.addAttribute("orderList",orders);
		return ControllerConstants.WeiXin.ORDERLISTPAGE;
	}
	
	@RequestMapping(value = "/getOrderDetail")
	public String getOrderDetail(@RequestParam(value ="orderCode",required=true)final String orderCode,final Model model)
	{
		OrderData order = orderFacade.getOrderForOptions(orderCode, Arrays.asList(OrderOption.BASIC));
		
		model.addAttribute("order",order);
		return ControllerConstants.WeiXin.ORDERDETAILPAGE;
	}

}
