package com.third.service.customer;

import java.util.Date;

import com.third.dao.util.PaginationSupport;
import com.third.model.CustomerModel;

public interface CustomerService {
	public void createCustomer(CustomerModel customer);

	PaginationSupport getCustomerList(final String cellphone, final String name,
			final Integer startIndex, final Integer pageSize);

	public CustomerModel getCustomerByCellphone(final String cellphone);

	public CustomerModel getCustomerByOpenId(final String openId);

	public void updateCustomer(CustomerModel customer);

	Integer getNumberOfSalesOrder(final String cellphone);

	Integer getNumberOfReservation(final String cellphone);

	public PaginationSupport getCustomerList(String cellphone, String name,
			Date startDate, Date endDate, Integer startIndex, Integer pageSize);

}
