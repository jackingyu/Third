package com.third.facade.store.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.facade.data.DTResults;
import com.third.facade.store.FIReportFacade;
import com.third.facade.utils.DTResultConvertor;
import com.third.facade.utils.TextMapperUtils;
import com.third.model.StoreModel;
import com.third.service.order.OrderService;
import com.third.service.order.PaymentService;
import com.third.service.store.StoreService;

public class DefaultFIReportFacade implements FIReportFacade {
	private PaymentService paymentService;
	private OrderService orderService;
	private StoreService storeService;

	@Override
	public DTResults getPaymentList(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String[]> sp)
	{
		DTResults result = DTResultConvertor.convertPS2DT(paymentService
				.getPayments(startDate, endDate, startIndex, pageSize, sp));

		return result;
	}
	
	@Override
	public DTResults getStoreDashboardResult1(Date startDate, Date endDate, Map<String, String[]> sp)
	{
		List<Object[]> queryResults = orderService.anlysisOrder(startDate, endDate, sp);
		List<Object[]> resultsWithStoreName = new ArrayList<Object[]>();

		queryResults.forEach( q->{
			Object[] arrayWithStoreName = new Object[q.length+1];
			System.arraycopy(q, 0, arrayWithStoreName, 0, q.length);
			arrayWithStoreName[q.length] = storeService.getStoreForCode(q[0].toString()).getName();
			resultsWithStoreName.add(arrayWithStoreName);
		});
		DTResults result = DTResultConvertor.convertPS2DT(resultsWithStoreName);
		
		return result;
	}
	
	@Override
	public DTResults getDashboardPaymentDetails(Date startDate, Date endDate, Map<String, String[]> sp,int startIndex,int pageSize)
	{
		DTResults result = DTResultConvertor.convertPS2DT(paymentService.getPaymentsByOrderDate(startDate, endDate, startIndex, pageSize, sp));;
		return result;
	}
	
	@Override
	public void storeHealthCheck(Date startDate, Date endDate, Map<String, String[]> sp)
	{
		orderService.anlysisOrder(startDate, endDate, sp);
		orderService.getOrdersByOrderDate(startDate, endDate, 0, 1000, sp);
	}

	public void setPaymentService(PaymentService paymentService)
	{
		this.paymentService = paymentService;
	}

	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	@Override
	public List<Object[]> getDashboardPaymentsByMethod(Date startDate,
			Date endDate, Map<String, String[]> sp)
	{
		List<Object[]> queryResults = paymentService.getTotalPaymentsByMethod(startDate, endDate, sp);
		List<Object[]> resultsWithMethodName = new ArrayList<Object[]>();

		queryResults.forEach( q->{
			Object[] arrayWithMethodName = new Object[q.length+1];
			System.arraycopy(q, 0, arrayWithMethodName, 0, q.length);
			arrayWithMethodName[q.length] = TextMapperUtils.getPaymentMethodText(q[0].toString());
			resultsWithMethodName.add(arrayWithMethodName);
		});
		
		return resultsWithMethodName;
	}
}
