package com.third.facade.customer;

import java.util.List;

import com.third.exceptions.BussinessException;
import com.third.exceptions.NotFoundException;
import com.third.exceptions.SubscribeException;
import com.third.facade.data.CustomerData;
import com.third.facade.data.ListData;
import com.third.facade.data.SourceData;


public interface CustomerFacade
{
	public void createCustomer(final CustomerData customer);

	public void updateCustomer(final CustomerData customer);

	public CustomerData getCustomerByCellphone(final String cellphone);

	ListData getCustomers(final String cellphone, final String name, final Integer startIndex, final Integer pageSize);

	List<SourceData> getSources();
	
	public CustomerData getCurrentCustomer();
	
	CustomerData loginCustomer(String openId);
	
	void loginCustomer(CustomerData customer);

	CustomerData registerCustomer(String openId, String cellphone, String name) throws SubscribeException;
}
