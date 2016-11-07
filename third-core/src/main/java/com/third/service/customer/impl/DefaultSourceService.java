package com.third.service.customer.impl;

import java.util.List;

import com.third.dao.customer.CustomerDao;
import com.third.dao.customer.SourceDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.CustomerModel;
import com.third.model.SourceModel;
import com.third.model.UserModel;
import com.third.service.customer.CustomerService;
import com.third.service.customer.SourceService;


public class DefaultSourceService implements SourceService
{
	private SourceDao sourceDao;

	@Override
	public List<SourceModel> getSources()
	{
		return sourceDao.list();
	}

	public void setSourceDao(SourceDao sourceDao)
	{
		this.sourceDao = sourceDao;
	}

	@Override
	public void createSource(SourceModel source)
	{
		sourceDao.save(source);
	}

	@Override
	public SourceModel getSource(String pk)
	{
		return sourceDao.get(pk);
	}

}
