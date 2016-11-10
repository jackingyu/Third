package com.third.service.store.impl;

import java.util.List;

import com.third.dao.store.StoreDao;
import com.third.model.StoreModel;
import com.third.service.store.StoreService;



public class DefaultStoreService implements StoreService
{
	private StoreDao storeDao;

	@Override
	public void createStore(StoreModel store)
	{
		storeDao.save(store);
	}

	public void setStoreDao(StoreDao storeDao)
	{
		this.storeDao = storeDao;
	}

	@Override
	public List<StoreModel> getStores(final String name)
	{
		return storeDao.findStoreByName(name);
	}

	@Override
	public StoreModel getStoreForCode(final String code)
	{
		return storeDao.getStoreForCode(code);
	}
}
