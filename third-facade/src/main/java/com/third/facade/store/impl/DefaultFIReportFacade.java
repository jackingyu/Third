package com.third.facade.store.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.facade.data.DTResults;
import com.third.facade.store.FIReportFacade;
import com.third.facade.utils.DTResultConvertor;
import com.third.facade.utils.TextMapperUtils;
import com.third.service.order.PaymentService;

public class DefaultFIReportFacade implements FIReportFacade {
	private PaymentService paymentService;

	@Override
	public DTResults getPaymentList(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String[]> sp)
	{
		DTResults result = DTResultConvertor.convertPS2DT(paymentService
				.getPayments(startDate, endDate, startIndex, pageSize, sp));

		return result;
	}

	public void setPaymentService(PaymentService paymentService)
	{
		this.paymentService = paymentService;
	}

}
