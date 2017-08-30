package com.third.service.order.impl;

import com.third.dao.order.PaymentDao;
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

}
