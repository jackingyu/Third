package com.third.service.order.impl;

import java.util.Date;
import java.util.Map;

import com.third.dao.order.PaymentDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.PaymentModel;
import com.third.service.order.PaymentService;


public class DefaultPaymentService implements PaymentService
{
	private PaymentDao paymentDao;

	public void setPaymentDao(PaymentDao paymentDao)
	{
		this.paymentDao = paymentDao;
	}

	@Override
	public void createPayment(PaymentModel payment)
	{
		paymentDao.save(payment);
	}

	@Override
	public void removePayment(String paymentPK)
	{
		PaymentModel t = paymentDao.get(paymentPK);
		paymentDao.delete(t);
	}

	@Override
	public PaymentModel getPayment(String paymentPK)
	{
		return paymentDao.get(paymentPK);
	}

	@Override
	public PaginationSupport getPayments(Date startDate, Date endDate, Integer startIndex, Integer pageSize, Map<String, String[]> sp)
	{
		return paymentDao.findPayments(startDate, endDate, startIndex, pageSize, sp);
	}

}
