package com.third.service.order.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.order.PaymentDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.PaymentModel;
import com.third.service.order.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class DefaultPaymentService implements PaymentService {
	private PaymentDao paymentDao;

	public void setPaymentDao(PaymentDao paymentDao)
	{
		this.paymentDao = paymentDao;
	}

	@Override
	public void createPayment(PaymentModel payment)
	{
	    payment.setCreateTime(new Date());
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
	public PaginationSupport getPayments(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String[]> sp)
	{
		return paymentDao.findPayments(startDate, endDate, startIndex, pageSize,
				sp);
	}
	
	@Override
	public PaginationSupport getPaymentsByOrderDate(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String[]> sp)
	{
		return paymentDao.findPaymentsByOrderDate(startDate, endDate, startIndex, pageSize,
				sp);
	}

	@Override
	public List<Object[]> getTotalPaymentsByMethod(Date startDate,
			Date endDate, Map<String, String[]> sp)
	{
		return paymentDao.getTotalPaymentsByMethod(startDate,endDate,sp);
	}

    @Override
    public BigDecimal sumPaymentsByPaymentMethod(Date startDate, Date endDate,
            Map<String, String[]> sp, String paymentMethod)
    {
        return paymentDao.sumPayments(startDate, endDate, sp, paymentMethod);
    }
    
    @Override
    public BigDecimal[] sumPaymentsByOrder(Date startDate, Date endDate,Map<String, String[]> sp)
    {
        return paymentDao.sumPaymentsByOrder(startDate, endDate, sp);
    }

}
