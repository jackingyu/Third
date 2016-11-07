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
	public List<StoreModel> getStores(String name)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
