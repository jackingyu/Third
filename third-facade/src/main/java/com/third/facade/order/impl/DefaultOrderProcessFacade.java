package com.third.facade.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.third.dao.util.PaginationSupport;
import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.exceptions.NotFoundException;
import com.third.facade.data.DTResults;
import com.third.facade.data.ListData;
import com.third.facade.data.OrderProcessRecordData;
import com.third.facade.data.TextMapper;
import com.third.facade.order.OrderProcessFacade;
import com.third.facade.populator.OrderProcessRecordDataPopulator;
import com.third.facade.utils.DTResultConvertor;
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
			orderProcessService.processOrder(order, currentUser, toStatus);
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
	
	@Override
	public DTResults  getOrderProcessRecords(final Date startDate,
            final Date endDate, final Integer startIndex,
            final Integer pageSize, final Map<String, String[]> sp)
	{
	    DTResults result = DTResultConvertor.convertPS2DT(orderProcessService.getOrderProcessRecords(startDate, endDate, startIndex, pageSize, sp));
        return result;
	}
	
	@Override
    public List<Object[]> exportOrderProcessRecords(Date startDate, Date endDate,
            int startIndex, int pageSize, Map<String, String[]> sp)
    {
        PaginationSupport ps = orderProcessService.getOrderProcessRecords(startDate, endDate, startIndex, pageSize, sp);

        List<Object[]> exportResults = ps.getItems();
//select e.itemCategory,e.externalId,e.deliveryDate,e.customerName,e.product.code,e.style,e.comment,e.sizeDetails
        Object[] title = {"门店","姓名","布料号","件数","量身单系统编号","纸质量身单号","记录发生时间"};
        exportResults.add(0, title);
        return exportResults;
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
