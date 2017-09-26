package com.third.service.order.impl;

import javax.annotation.Resource;

import com.third.core.util.MessageUtils;
import com.third.model.OrderModel;
import com.third.service.order.OrderStatusUpdateAction;

public class StoreReceiptAction implements OrderStatusUpdateAction {
	
	@Resource(name = "messageUtils")
	private MessageUtils messageUtils;

	@Override
	public void perform(OrderModel order)
	{
		final String message = new StringBuilder("您的订单已到达门店!").toString();
		messageUtils.createMessage(order.getCellphone(), message);

	}

}
