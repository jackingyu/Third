package com.third.service.order.impl;

import java.util.Date;
import java.util.Map;

import com.third.dao.order.OrderDao;
import com.third.dao.order.OrderEntryDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.service.order.OrderService;


public class DefaultOrderService implements OrderService
{
	private OrderDao orderDao;
	private OrderEntryDao orderEntryDao;

	@Override
	public OrderModel getOrderForCode(final String orderCode)
	{
		return orderDao.findOrder(orderCode);
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

	@Override
	public PaginationSupport getOrders(Date startDate, Date endDate, Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		return orderDao.findOrders(startDate, endDate, startIndex, pageSize, sp);
	}

	@Override
	public void createOrderEntry(OrderEntryModel orderEntry)
	{
		String orderPK = orderEntry.getOrder().getPk();
		Integer numberOfEntry = orderEntryDao.countOrderEntryByOrder(orderPK);
		orderEntry.setEntryNo(numberOfEntry);
		orderEntryDao.save(orderEntry);
	}

	@Override
	public void updateOrderEntry(OrderEntryModel orderEntry)
	{
		orderEntryDao.update(orderEntry);
	}


	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}


	public void setOrderEntryDao(OrderEntryDao orderEntryDao)
	{
		this.orderEntryDao = orderEntryDao;
	}


	@Override
	public void removeOrderEntry(String orderEntryPK)
	{
		OrderEntryModel orderEntry = new OrderEntryModel();
		orderEntry.setPk(orderEntryPK);
		orderEntryDao.delete(orderEntry);
	}


	@Override
	public OrderEntryModel getOrderEntry(String entryPK)
	{
		return orderEntryDao.get(entryPK);
	}
}
