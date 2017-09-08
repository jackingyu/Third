package com.third.facade.populator;

import com.third.facade.data.CityData;
import com.third.model.CityModel;

public class CityDataPopulator implements Populator<CityModel, CityData> {

	@Override
	public void populate(CityModel source, CityData target)
	{
		target.setIsoCode(source.getIsoCode());
		target.setName(source.getName());
	}

}
