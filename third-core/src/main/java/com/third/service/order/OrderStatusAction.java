package com.third.service.order;

import com.third.exceptions.OrderStatusException;

public interface OrderStatusAction {
	void processOrder(String orderCode, Integer status)
			throws OrderStatusException;
}
