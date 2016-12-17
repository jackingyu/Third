package com.third.dao.customer;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.CustomerModel;


public interface CustomerDao extends IGenericDAO<CustomerModel, String>
{
	CustomerModel getCustomerByCellphone(final String cellphone);

	CustomerModel findCustomerByOpenId(final String openId);

	PaginationSupport findCustomer(String cellphone, String name, Integer startIndex, Integer pageSize);

}
