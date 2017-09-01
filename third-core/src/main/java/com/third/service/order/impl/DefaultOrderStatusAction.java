package com.third.service.order.impl;

import java.util.Map;

import com.third.dao.order.OrderDao;
import com.third.exceptions.OrderStatusException;
import com.third.model.CoreConstants;
import com.third.model.OrderModel;
import com.third.service.order.OrderStatusAction;
import com.third.service.order.OrderStatusUpdateAction;


public class DefaultOrderStatusAction implements OrderStatusAction
{
	private OrderDao orderDao;
	private Map<Integer,OrderStatusUpdateAction> actions;

	@Override
	public void processOrder(String orderCode, Integer status) throws OrderStatusException
	{
		OrderModel order = orderDao.findOrder(orderCode);
		final Integer currentStatus = order.getStatus();

		for (int i = 1; i < CoreConstants.OrderStatus.ALL.size() - 1; i++)
		{
			Integer orderStatus = CoreConstants.OrderStatus.ALL.get(i);
			
			if (currentStatus == orderStatus)
				if (status != CoreConstants.OrderStatus.ALL.get(i + 1))
				{
					throw new OrderStatusException(orderCode);
				}
				else
				{
					order.setStatus(status);
					orderDao.update(order);
					actions.get(status).afterUpdate(orderCode);
					return;
				}
		}

	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	public void setActions(Map<Integer, OrderStatusUpdateAction> actions)
	{
		this.actions = actions;
	}

}
