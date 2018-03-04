package com.third.service.order.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import com.third.dao.order.OrderDao;
import com.third.dao.order.OrderEntryDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.service.order.OrderService;

public class DefaultOrderService implements OrderService {
	private OrderDao orderDao;
	private OrderEntryDao orderEntryDao;

	@Override
	public OrderModel getOrderForCode(final String orderCode)
	{
		return orderDao.findOrder(orderCode);
	}
	
	@Override
	public OrderModel getOrderForPk(final String orderPK)
	{
	    return orderDao.get(orderPK);
	}

	@Override
	public void createOrder(OrderModel orderModel)
	{
		orderDao.save(orderModel);
	}

	@Override
	public void upateOrder(OrderModel orderModel)
	{
	    orderModel.setModificationTime(new Date());
		orderDao.update(orderModel);
	}
	
	@Override
	public boolean isExist(final String code)
	{
	    Integer i = orderDao.checkOrderByCode(code);
	    return i > 0;
	}

	@Override
	public PaginationSupport getOrders(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		return orderDao.findOrders(startDate, endDate, startIndex, pageSize,
				sp);
	}
	
	@Override
	public PaginationSupport getOrdersByOrderDate(final Date startDate, final Date endDate,
			final Integer startIndex, final Integer pageSize,
			final Map<String, String[]> sp)
	{
		return orderDao.findOrdersByOrderDate(startDate, endDate, startIndex, pageSize, sp);
	}

	@Override
	public void createOrderEntry(OrderEntryModel orderEntry)
	{
		String orderPK = orderEntry.getOrder().getPk();
		Integer numberOfEntry = orderEntryDao.countOrderEntryByOrder(orderPK);
		orderEntry.setPk(Long.valueOf(System.currentTimeMillis())+Integer.toString(RandomUtils.nextInt(0, 1000)));
		orderEntry.setEntryNo(numberOfEntry);
		orderEntry.setCreateTime(new Date());
		orderEntryDao.save(orderEntry);
	}

	@Override
	public void updateOrderEntry(OrderEntryModel orderEntry)
	{
	    orderEntry.setModificationTime(new Date());
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

	@Override
	public List<OrderModel> getOrdersForCustomer(String customerPK)
	{
		return orderDao.findOrdersForCustomer(customerPK);
	}

	@Override
	public OrderEntryModel getOrderEntryForExternalId(String externalId)
	{
		return orderEntryDao.findOrderEntryByExternalId(externalId);
	}

	@Override
	public PaginationSupport getOrderEntries(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		return orderEntryDao.findOrderEntries(startDate, endDate, startIndex,
				pageSize, sp);
	}

	@Override
	public List<OrderModel> getOrders(Map<String, String> sp)
	{
		return orderDao.findOrders(sp);
	}

	@Override
	public List<Object[]> anlysisOrder(Date startDate, Date endDate,
			Map<String, String[]> sp)
	{
		return orderDao.anlysisOrder(startDate, endDate,sp);
	}

	@Override
	public PaginationSupport getOrderEntriesWithSizeData(Date startDate,
			Date endDate, Integer startIndex, Integer pageSize,
			Map<String, String> sp)
	{
		return orderEntryDao.findOrderEntriesWithSizeData(startDate, endDate, startIndex, pageSize, sp);
	}
	
	@Override
	public void updateOrderEntryExportFlag(String[] orderEntriesPK)
	{
	    orderEntryDao.updateOrderEntriesExportFlag(orderEntriesPK);
	}

}
