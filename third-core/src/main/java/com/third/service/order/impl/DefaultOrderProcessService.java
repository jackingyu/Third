package com.third.service.order.impl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.order.OrderDao;
import com.third.dao.order.OrderProcessRecordDao;
import com.third.dao.util.PaginationSupport;
import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.model.OrderModel;
import com.third.model.OrderProcessRecordModel;
import com.third.model.CoreConstants.OrderStatus;
import com.third.model.UserModel;
import com.third.service.order.OrderProcessService;

public class DefaultOrderProcessService implements OrderProcessService
{
	private OrderDao orderDao;
	private OrderProcessRecordDao orderProcessRecordDao;

	@Override
	public void createOrderProcess(OrderProcessRecordModel orderProcessRecord)
	{
		orderProcessRecordDao.save(orderProcessRecord);
	}
	
	protected Integer getNextStatus(Integer currenctStatus)
	{
		Integer targetStatus = -1;
		
		for(int i = 0;i < OrderStatus.ALL.size() -1 ;i++)
		{
			if(OrderStatus.ALL.get(i).equals(currenctStatus))
				return OrderStatus.ALL.get(i+1);
		}
		
		return targetStatus;
	}

	@Override
	public OrderProcessRecordModel processOrder(OrderModel order,UserModel user,Integer targetStatus) throws NoQualifiedTargetStatusException
	{
		Integer currentStatus = order.getStatus();
		Integer nextStatus = getNextStatus(currentStatus);
		
		if(nextStatus < 0 || !targetStatus.equals(nextStatus))
		{
			NoQualifiedTargetStatusException ex = new NoQualifiedTargetStatusException();
			ex.setCurrentStatus(currentStatus.toString());
			throw ex;
		}
		
      OrderProcessRecordModel orderProcessRecordModel = new OrderProcessRecordModel();
      orderProcessRecordModel.setFromStatus(currentStatus.toString());
      orderProcessRecordModel.setToStatus(nextStatus.toString());
      orderProcessRecordModel.setOrderCode(order.getCode());
      orderProcessRecordModel.setCreatedBy(user);
      orderProcessRecordModel.setProcessTime(Calendar.getInstance().getTime());;
      
      createOrderProcess(orderProcessRecordModel);
      
		order.setStatus(nextStatus);
		
		orderDao.save(order);
		
		return orderProcessRecordModel;
	}

	@Override
	public PaginationSupport getOrderProcessRecords(Date startDate, Date endDate, Integer startIndex, Integer pageSize,
			Map<String, String> sp)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	public void setOrderProcessRecordDao(OrderProcessRecordDao orderProcessRecordDao)
	{
		this.orderProcessRecordDao = orderProcessRecordDao;
	}

	@Override
	public List<OrderProcessRecordModel> getOrderProcessRecordForOrder(String orderCode)
	{
		return orderProcessRecordDao.findOrderProcessForOrder(orderCode);
	}

	@Override
	public void updateOrderProcess(OrderProcessRecordModel orderProcessRecord)
	{
		orderProcessRecordDao.save(orderProcessRecord);
	}

}
