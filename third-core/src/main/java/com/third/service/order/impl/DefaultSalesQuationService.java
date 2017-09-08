package com.third.service.order.impl;

import java.util.Date;
import java.util.Map;

import com.third.dao.order.SalesQuationDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.SalesQuationModel;
import com.third.service.order.SalesQuationService;

public class DefaultSalesQuationService implements SalesQuationService {
	private SalesQuationDao salesQuationDao;

	@Override
	public void saveSalesQuation(SalesQuationModel salesQuation)
	{
		this.salesQuationDao.save(salesQuation);
	}

	@Override
	public PaginationSupport getSalesQuations(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		return salesQuationDao.findSalesQuations(startDate, endDate, startIndex,
				pageSize, sp);
	}

	@Override
	public SalesQuationModel getSalesQuation(String pk)
	{
		return this.salesQuationDao.get(pk);
	}

	public void setSalesQuationDao(SalesQuationDao salesQuationDao)
	{
		this.salesQuationDao = salesQuationDao;
	}

}
