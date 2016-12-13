package com.third.facade.populator;

import com.third.facade.data.AddressData;
import com.third.facade.data.StoreData;
import com.third.model.StoreModel;


public class StoreDataPopulator implements Populator<StoreModel, StoreData>
{
	private AddressDataPopulator addressDataPopulator;

	@Override
	public void populate(StoreModel source, StoreData target)
	{
		target.setPk(source.getPk());
		target.setCode(source.getId());
		target.setName(source.getName());
		
		AddressData address = new AddressData();
		addressDataPopulator.populate(source.getAddress(), address);
		target.setAddress(address);
	}

	public void setAddressDataPopulator(AddressDataPopulator addressDataPopulator)
	{
		this.addressDataPopulator = addressDataPopulator;
	}

}
