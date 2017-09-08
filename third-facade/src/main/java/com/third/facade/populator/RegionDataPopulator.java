package com.third.facade.populator;

import com.third.facade.data.RegionData;
import com.third.model.RegionModel;

public class RegionDataPopulator implements Populator<RegionModel, RegionData> {

	@Override
	public void populate(RegionModel source, RegionData target)
	{
		target.setIsoCode(source.getIsoCode());
		target.setName(source.getName());
	}

}
