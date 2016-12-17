package com.third.service.customer.impl;

import com.third.dao.customer.CustomerDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.CustomerModel;
import com.third.model.UserModel;
import com.third.service.customer.CustomerService;


public class DefaultCustomerService implements CustomerService
{
	private CustomerDao customerDao;

	@Override
	public void createCustomer(CustomerModel customer)
	{
		customerDao.save(customer);
	}

	@Override
	public PaginationSupport getCustomerList(String cellphone, String name, Integer startIndex, Integer pageSize)
	{
		return customerDao.findCustomer(cellphone, name, startIndex, pageSize);
	}

	@Override
	public CustomerModel getCustomerByCellphone(String cellphone)
	{
		return customerDao.getCustomerByCellphone(cellphone);
	}

	public void setCustomerDao(CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}

	@Override
	public void updateCustomer(CustomerModel customer)
	{
		customerDao.update(customer);
	}

	@Override
	public CustomerModel getCustomerByOpenId(String openId)
	{
		return customerDao.findCustomerByOpenId(openId);
	}

}
