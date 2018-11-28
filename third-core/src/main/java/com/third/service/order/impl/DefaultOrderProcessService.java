package com.third.service.order.impl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.order.OrderDao;
import com.third.dao.order.OrderEntryDao;
import com.third.dao.order.OrderProcessRecordDao;
import com.third.dao.util.PaginationSupport;
import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.OrderProcessRecordModel;
import com.third.core.constants.CoreConstants.OrderStatus;
import com.third.model.UserModel;
import com.third.service.order.OrderProcessService;
import com.third.service.order.OrderStatusUpdateAction;
import org.springframework.stereotype.Service;

@Service
public class DefaultOrderProcessService implements OrderProcessService {
	private OrderDao orderDao;
	private OrderEntryDao orderEntryDao;
	private OrderProcessRecordDao orderProcessRecordDao;
	private Map<Integer, OrderStatusUpdateAction> actions;
	
	@Override
	public void createOrderProcess(OrderProcessRecordModel orderProcessRecord)
	{
		orderProcessRecordDao.save(orderProcessRecord);
	}

	protected Integer getNextStatus(Integer currenctStatus)
	{
		Integer targetStatus = -1;

		for (int i = 0; i < OrderStatus.ALL.size() - 1; i++)
		{
			if (OrderStatus.ALL.get(i).equals(currenctStatus))
				return OrderStatus.ALL.get(i + 1);
		}

		return targetStatus;
	}

	@Override
	public void processOrder(OrderModel order,
			UserModel user, Integer targetStatus)
			throws NoQualifiedTargetStatusException
	{
		Integer currentStatus = order.getStatus();
		Integer nextStatus = getNextStatus(currentStatus);

		if (nextStatus < 0 || !targetStatus.equals(nextStatus))
		{
			NoQualifiedTargetStatusException ex = new NoQualifiedTargetStatusException();
			ex.setCurrentStatus(currentStatus.toString());
			throw ex;
		}


		List<OrderEntryModel> entries = order.getOrderEntries();
		String storeName = order.getStore().getName();
		String storeCode = order.getStore().getId();
		String customerName = order.getCustomer().getName();
		Date processTime = Calendar.getInstance().getTime();
		for(OrderEntryModel oem:entries)
		{

	        OrderProcessRecordModel orderProcessRecordModel = new OrderProcessRecordModel();
	        orderProcessRecordModel.setFromStatus(currentStatus.toString());
	        orderProcessRecordModel.setToStatus(nextStatus.toString());
	        orderProcessRecordModel.setOrderCode(order.getCode());
	        orderProcessRecordModel.setCreatedBy(user);
	        orderProcessRecordModel.setProcessTime(processTime);
		    orderProcessRecordModel.setName(customerName);
		    orderProcessRecordModel.setProductCode(oem.getProduct().getCode());
		    orderProcessRecordModel.setProductTitle(oem.getProduct().getProducttitle());
		    orderProcessRecordModel.setQuantity(oem.getQuantity());
		    orderProcessRecordModel.setSizeOrderExternalId(oem.getExternalId());
		    orderProcessRecordModel.setSizeOrderPk(oem.getPk());
		    orderProcessRecordModel.setStoreCode(storeCode);
		    orderProcessRecordModel.setStoreName(storeName);
		    orderProcessRecordModel.setProductTitle(oem.getProductTitle());
		    
		    createOrderProcess(orderProcessRecordModel);
		}
		

		order.setStatus(nextStatus);

		for (int i = 0; i < order.getOrderEntries().size(); i++)
		{
			OrderEntryModel e = (OrderEntryModel) order.getOrderEntries()
					.get(i);
			e.setStatus(targetStatus);
		}

		orderDao.save(order);
		
		if(actions.containsKey(targetStatus))
			actions.get(targetStatus).perform(order);
		
	}

	@Override
	public OrderProcessRecordModel processOrderEntry(OrderEntryModel orderEntry,
			UserModel user, Integer targetStatus)
			throws NoQualifiedTargetStatusException
	{
		Integer currentStatus = orderEntry.getStatus();
		Integer nextStatus = getNextStatus(currentStatus);

		if (nextStatus < 0 || !targetStatus.equals(nextStatus))
		{
			NoQualifiedTargetStatusException ex = new NoQualifiedTargetStatusException();
			ex.setCurrentStatus(currentStatus.toString());
			throw ex;
		}

		orderEntry.setStatus(targetStatus);
		//设置的目标状态为工厂已排产
        if(targetStatus ==30)
        {
            orderEntry.setScheduleDate(new Date());
        }
        
		orderEntryDao.save(orderEntry);

		OrderModel order = orderEntry.getOrder();
		List<OrderEntryModel> entries = order.getOrderEntries();
		boolean ifAllApproved = true;

		for (int j = 0; j < entries.size(); j++)
		{
			if (!entries.get(j).getStatus().equals(targetStatus))
				ifAllApproved = false;
		}

		OrderProcessRecordModel orderProcessRecordModel = new OrderProcessRecordModel();

		if (ifAllApproved)
		{
			processOrder(order, user, targetStatus);
		}
      
		return orderProcessRecordModel;
	}

	@Override
	public PaginationSupport getOrderProcessRecords(Date startDate,
			Date endDate, Integer startIndex, Integer pageSize,
			Map<String, String[]> sp)
	{
		return orderProcessRecordDao.findOrderProcessRecord(startDate, endDate, startIndex, pageSize, sp);
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	public void setOrderProcessRecordDao(
			OrderProcessRecordDao orderProcessRecordDao)
	{
		this.orderProcessRecordDao = orderProcessRecordDao;
	}

	@Override
	public List<OrderProcessRecordModel> getOrderProcessRecordForOrder(
			String orderCode)
	{
		return orderProcessRecordDao.findOrderProcessForOrder(orderCode);
	}

	@Override
	public void updateOrderProcess(OrderProcessRecordModel orderProcessRecord)
	{
		orderProcessRecordDao.save(orderProcessRecord);
	}

	public void setOrderEntryDao(OrderEntryDao orderEntryDao)
	{
		this.orderEntryDao = orderEntryDao;
	}
	
	public void setActions(Map<Integer, OrderStatusUpdateAction> actions)
	{
		this.actions = actions;
	}

}
