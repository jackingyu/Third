package com.third.service.order.impl;

import com.third.dao.order.OrderDao;
import com.third.model.OrderModel;
import com.third.service.order.OrderService;


public class DefaultOrderService implements OrderService
{
	private OrderDao orderDao;

	@Override
	public OrderModel getOrder(final String orderNo)
	{
		return orderDao.getOrder(orderNo);
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	@Override
	public void createOrder(OrderModel orderModel)
	{
		orderDao.save(orderModel);
	}

	@Override
	public void upateOrder(OrderModel orderModel)
	{
		orderDao.update(orderModel);
	}

}
