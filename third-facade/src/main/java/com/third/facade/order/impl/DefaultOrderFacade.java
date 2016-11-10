package com.third.facade.order.impl;

import com.third.facade.data.OrderData;
import com.third.facade.order.OrderFacade;
import com.third.model.OrderModel;
import com.third.service.order.OrderService;


public class DefaultOrderFacade implements OrderFacade
{
	private OrderService orderService;

	@Override
	public void createOrder(OrderData orderData)
	{
		OrderModel order = new OrderModel();

		orderService.createOrder(order);
	}

	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}

}
