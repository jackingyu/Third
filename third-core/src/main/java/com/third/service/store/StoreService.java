package com.third.service.store;

import java.util.List;

import com.third.model.StoreModel;


public interface StoreService
{
	void createStore(StoreModel store);

	List<StoreModel> getStores(final String name);

	StoreModel getStoreForCode(final String code);
}
