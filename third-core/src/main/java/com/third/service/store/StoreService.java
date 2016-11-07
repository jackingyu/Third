package com.third.service.store;

import java.util.List;

import com.third.model.StoreModel;


public interface StoreService
{
	public void createStore(StoreModel store);

	public List<StoreModel> getStores(final String name);
}
