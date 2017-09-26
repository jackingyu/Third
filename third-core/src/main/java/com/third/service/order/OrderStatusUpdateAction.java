package com.third.service.order;

import com.third.model.OrderModel;

public interface OrderStatusUpdateAction {
	public void perform(OrderModel order);
}
