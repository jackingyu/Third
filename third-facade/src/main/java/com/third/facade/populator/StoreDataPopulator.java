package com.third.facade.populator;

import com.third.facade.data.StoreData;
import com.third.model.StoreModel;


public class StoreDataPopulator implements Populator<StoreModel, StoreData>
{

	@Override
	public void populate(StoreModel source, StoreData target)
	{
		target.setName(source.getName());
	}

}
