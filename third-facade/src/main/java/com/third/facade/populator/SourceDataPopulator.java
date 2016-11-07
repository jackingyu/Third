package com.third.facade.populator;

import com.third.facade.data.SourceData;
import com.third.model.SourceModel;


public class SourceDataPopulator implements Populator<SourceModel, SourceData>
{

	@Override
	public void populate(SourceModel source, SourceData target)
	{
		target.setPk(source.getPk());
		target.setName(source.getName());
	}

}
