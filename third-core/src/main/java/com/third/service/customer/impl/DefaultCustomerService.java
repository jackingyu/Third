package com.third.service.customer.impl;

import java.util.Date;

import com.third.dao.customer.CustomerDao;
import com.third.dao.customer.ReservationDao;
import com.third.dao.order.OrderDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.CustomerModel;
import com.third.model.UserModel;
import com.third.service.customer.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class DefaultCustomerService implements CustomerService {
	private CustomerDao customerDao;
	private ReservationDao reservationDao;
	private OrderDao orderDao;

	@Override
	public void createCustomer(CustomerModel customer)
	{
	    customer.setCreateTime(new Date());
		customerDao.save(customer);
	}

	@Override
	public PaginationSupport getCustomerList(String cellphone, String name,
			Integer startIndex, Integer pageSize)
	{
		return customerDao.findCustomer(cellphone, name, startIndex, pageSize);
	}

	@Override
	public CustomerModel getCustomerByCellphone(String cellphone)
	{
		return customerDao.getCustomerByCellphone(cellphone);
	}

	@Override
	public void updateCustomer(CustomerModel customer)
	{
	    customer.setModificationTime(new Date());
		customerDao.update(customer);
	}

	@Override
	public CustomerModel getCustomerByOpenId(String openId)
	{
		return customerDao.findCustomerByOpenId(openId);
	}

	public void setCustomerDao(CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}

	public void setReservationDao(ReservationDao reservationDao)
	{
		this.reservationDao = reservationDao;
	}

	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}

	@Override
	public Integer getNumberOfSalesOrder(String cellphone)
	{
		return orderDao.countOrderForCustomer(cellphone);
	}

	@Override
	public Integer getNumberOfReservation(String cellphone)
	{
		return reservationDao.countReservationForCustomer(cellphone);
	}

	@Override
	public PaginationSupport getCustomerList(String cellphone, String name,
			Date startDate, Date endDate, Integer startIndex, Integer pageSize)
	{
		return customerDao.findCustomer(cellphone, name,startDate,endDate, startIndex, pageSize);
	}

}
