package com.third.service.customer;

import com.third.dao.util.PaginationSupport;
import com.third.model.CustomerModel;


public interface CustomerService
{
	public void createCustomer(CustomerModel customer);

	PaginationSupport getCustomerList(final String cellphone, final String name, final Integer startIndex, final Integer pageSize);

	public CustomerModel getCustomerByCellphone(final String cellphone);

	public CustomerModel getCustomerByOpenId(final String openId);

	public void updateCustomer(CustomerModel customer);

}
