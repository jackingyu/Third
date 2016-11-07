package com.third.facade.store.impl;

import java.util.ArrayList;
import java.util.List;

import com.third.facade.data.StoreData;
import com.third.facade.populator.StoreDataPopulator;
import com.third.facade.store.StoreFacade;
import com.third.model.StoreModel;
import com.third.service.store.StoreService;


public class DefaultStoreFacade implements StoreFacade
{
	private StoreService storeService;
	private StoreDataPopulator storeDataPopulator;

	@Override
	public List<StoreData> getStores(final String name)
	{
		List<StoreModel> storeModels = storeService.getStores(name);

		List<StoreData> stores = new ArrayList<StoreData>();

		storeModels.forEach(s -> {
			StoreData store = new StoreData();
			storeDataPopulator.populate(s, store);
			stores.add(store);
		});

		return stores;
	}

	@Override
	public void createStore(StoreData store)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStore(StoreData store)
	{
		// TODO Auto-generated method stub

	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	public void setStoreDataPopulator(StoreDataPopulator storeDataPopulator)
	{
		this.storeDataPopulator = storeDataPopulator;
	}

}
