package com.third.service.order;

import com.third.model.OrderModel;


public interface OrderService
{
	OrderModel getOrder(final String orderNo);

	void createOrder(final OrderModel order);

	void upateOrder(final OrderModel order);



}
