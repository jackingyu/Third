package com.third.service.order.impl;

import javax.annotation.Resource;

import com.third.core.util.MessageUtils;
import com.third.model.OrderModel;
import com.third.service.order.OrderStatusUpdateAction;

public class StoreDeliveredAction implements OrderStatusUpdateAction {

	      @Resource(name="messageUtils")
	    private MessageUtils messageUtils;
	    
		@Override
		public void perform(OrderModel order)
		{
			final String message = new StringBuilder("您的订单已确认收货!").toString();
			messageUtils.createMessage(order.getCellphone(), message);
			
		}

}
