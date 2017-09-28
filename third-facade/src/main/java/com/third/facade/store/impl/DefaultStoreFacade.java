package com.third.facade.store.impl;

import java.util.ArrayList;
import java.util.List;

import com.third.facade.data.AddressData;
import com.third.facade.data.StoreData;
import com.third.facade.populator.StoreDataPopulator;
import com.third.facade.store.StoreFacade;
import com.third.model.AddressModel;
import com.third.model.StoreModel;
import com.third.service.location.I18NService;
import com.third.service.store.StoreService;

public class DefaultStoreFacade implements StoreFacade {
	private StoreService storeService;
	private StoreDataPopulator storeDataPopulator;
	private I18NService i18NService;

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
	public List<StoreData> getAllStores()
	{
		List<StoreModel> storeModels = storeService.getAllStores();

		List<StoreData> stores = new ArrayList<StoreData>();

		storeModels.forEach(s -> {
			StoreData store = new StoreData();
			storeDataPopulator.populate(s, store);
			stores.add(store);
		});

		return stores;
	}

	@Override
	public StoreData getStoreForCode(String storeCode)
	{
		StoreModel storeModel = storeService.getStoreForCode(storeCode);
		StoreData store = new StoreData();
		storeDataPopulator.populate(storeModel, store);

		return store;
	}

	@Override
	public void createStore(StoreData store)
	{
		StoreModel storeModel = new StoreModel();
		storeModel.setId(store.getCode());
		storeModel.setName(store.getName());
		
		AddressModel addressModel = new AddressModel();
		AddressData address = store.getAddress();
		addressModel.setRegion(i18NService.getRegion(address.getRegion().getIsoCode()));
		addressModel.setCity(i18NService.getCity(address.getCity().getIsoCode()));
		addressModel.setDistrict(i18NService.getDistrict(address.getDistrict().getIsoCode()));
		addressModel.setAdr1(address.getAdr1());
		addressModel.setAdr2(address.getAdr2());
		addressModel.setTel1(address.getTel1());
		addressModel.setTel2(address.getTel2());
	
        storeService.createStore(storeModel);
		
    	
        i18NService.createAddress(addressModel);

        storeModel.setAddress(addressModel);
        
        storeService.saveStore(storeModel);
	}

	@Override
	public void updateStore(StoreData store)
	{
		StoreModel storeModel = storeService.getStoreForCode(store.getCode());
		storeModel.setName(store.getName());
		
		AddressModel addressModel = storeModel.getAddress();
		int flag = 0;
		if(addressModel==null)
		{
			addressModel = new AddressModel();
			flag = 1;
		}
		
		AddressData address = store.getAddress();
		addressModel.setRegion(i18NService.getRegion(address.getRegion().getIsoCode()));
		addressModel.setCity(i18NService.getCity(address.getCity().getIsoCode()));
		addressModel.setDistrict(i18NService.getDistrict(address.getDistrict().getIsoCode()));
		addressModel.setAdr1(address.getAdr1());
		addressModel.setAdr2(address.getAdr2());
		addressModel.setTel1(address.getTel1());
		addressModel.setTel2(address.getTel2());
		
		if(flag==1)
			i18NService.createAddress(addressModel);
		else
            i18NService.updateAddress(addressModel);
		
		storeModel.setAddress(addressModel);
		
		storeService.saveStore(storeModel);
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	public void setStoreDataPopulator(StoreDataPopulator storeDataPopulator)
	{
		this.storeDataPopulator = storeDataPopulator;
	}

	public void setI18NService(I18NService i18nService)
	{
		i18NService = i18nService;
	}

}
