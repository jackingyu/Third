package com.third.facade.customer;

import java.util.List;

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
}
