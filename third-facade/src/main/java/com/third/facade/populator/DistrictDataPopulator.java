package com.third.facade.populator;

import com.third.facade.data.DistrictData;
import com.third.model.DistrictModel;

public class DistrictDataPopulator
		implements Populator<DistrictModel, DistrictData> {

	@Override
	public void populate(DistrictModel source, DistrictData target)
	{
		if (source != null)
		{
			target.setIsoCode(source.getIsoCode());
			target.setName(source.getName());
		}
	}

}
