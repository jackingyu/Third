package com.third.service.customer.impl;

import com.third.dao.customer.SubscribeDao;
import com.third.model.SubscribeModel;
import com.third.service.customer.SubscribeService;

public class DefaultSubscribeService implements SubscribeService {
	private SubscribeDao subscribeDao;

	public void setSubscribeDao(SubscribeDao subscribeDao)
	{
		this.subscribeDao = subscribeDao;
	}

	@Override
	public SubscribeModel getSubscribeModel(String openId)
	{
		return subscribeDao.get(openId);
	}

	@Override
	public void updateSubscribeModel(SubscribeModel subscribeModel)
	{
		subscribeDao.update(subscribeModel);
	}

	@Override
	public void create(SubscribeModel subscribeModel)
	{
		subscribeDao.save(subscribeModel);
	}

	@Override
	public void save(SubscribeModel subscribeModel)
	{
		subscribeDao.saveOrUpdate(subscribeModel);
	}

}
