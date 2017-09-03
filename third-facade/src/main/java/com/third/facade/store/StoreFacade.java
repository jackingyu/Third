package com.third.facade.store;

import java.util.List;

import com.third.facade.data.StoreData;


public interface StoreFacade
{

	void createStore(StoreData store);

	void updateStore(StoreData store);

	List<StoreData> getStores(String storeName);
	
	List<StoreData> getAllStores();
	
	StoreData getStoreForCode(final String storeCode);
	
}
