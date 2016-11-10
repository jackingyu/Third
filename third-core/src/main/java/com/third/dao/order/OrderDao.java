package com.third.dao.order;

import com.third.dao.generic.IGenericDAO;
import com.third.model.OrderModel;


public interface OrderDao extends IGenericDAO<OrderModel, String>
{
	OrderModel getOrder(final String orderNo);
}
