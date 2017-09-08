package com.third.facade.order.impl;

import java.util.ArrayList;
import java.util.List;

import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.data.ListData;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderProcessRecordData;
import com.third.facade.data.TextMapper;
import com.third.facade.order.OrderProcessFacade;
import com.third.facade.populator.OrderProcessRecordDataPopulator;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.OrderProcessRecordModel;
import com.third.model.UserModel;
import com.third.service.order.OrderProcessService;
import com.third.service.order.OrderService;
import com.third.service.user.UserService;

public class DefaultOrderProcessFacade implements OrderProcessFacade {
	private OrderService orderService;
	private OrderProcessService orderProcessService;
	private UserService userService;
	private OrderProcessRecordDataPopulator orderProcessRecordDataPopulator;

	@Override
	public void processOrder(String orderCode, Integer toStatus)
			throws NoQualifiedTargetStatusException, NotFoundException
	{
		OrderModel order = orderService.getOrderForCode(orderCode);

		if (order == null)
			throw new NotFoundException();

		UserModel currentUser = userService.getCurrentUser();
		try
		{
			Integer currentStatus = order.getStatus();
			OrderProcessRecordModel processRecord = orderProcessService
					.processOrder(order, currentUser, toStatus);
			StringBuilder sb = new StringBuilder("订单:");
			sb.append(processRecord.getOrderCode()).append("从状态")
					.append(TextMapper.OrderStatus
							.get(currentStatus.toString()))
					.append(" 至状态")
					.append(TextMapper.OrderStatus.get(toStatus.toString()));
			processRecord.setMessage(sb.toString());
			orderProcessService.updateOrderProcess(processRecord);
		} catch (NoQualifiedTargetStatusException ex)
		{
			String currentStatus = TextMapper.OrderStatus
					.get(ex.getCurrentStatus());
			ex.setCurrentStatus(currentStatus);
			throw ex;
		}
	}

	@Override
	public ListData getProcessRecordForOrder(String orderCode)
	{
		List<OrderProcessRecordModel> records = orderProcessService
				.getOrderProcessRecordForOrder(orderCode);

		List<Object> recordDatas = new ArrayList<Object>();

		ListData result = new ListData();
		records.forEach(r -> {
			OrderProcessRecordData recordData = new OrderProcessRecordData();
			orderProcessRecordDataPopulator.populate(r, recordData);
			recordDatas.add(recordData);
		});

		result.setRows(recordDatas);
		result.setTotal(recordDatas.size());

		return result;
	}

	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}

	public void setOrderProcessService(OrderProcessService orderProcessService)
	{
		this.orderProcessService = orderProcessService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public void setOrderProcessRecordDataPopulator(
			OrderProcessRecordDataPopulator orderProcessRecordDataPopulator)
	{
		this.orderProcessRecordDataPopulator = orderProcessRecordDataPopulator;
	}

	@Override
	public void processOrderEntry(String entryPK, Integer toStatus)
			throws NoQualifiedTargetStatusException, NotFoundException
	{
		OrderEntryModel orderEntry = orderService.getOrderEntry(entryPK);
		orderProcessService.processOrderEntry(orderEntry,
				userService.getCurrentUser(), toStatus);
	}

}
