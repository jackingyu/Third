package com.third.facade.populator;

import com.third.facade.data.SizeAttributeData;
import com.third.model.SizeAttributeModel;


public class SizeAttributeDataPopulator implements Populator<SizeAttributeModel, SizeAttributeData>
{

	@Override
	public void populate(SizeAttributeModel source, SizeAttributeData target)
	{
		target.setName(source.getName());
		target.setPk(source.getPk());
		target.setCode(source.getCode());
		target.setGroup(source.getGroup().toString());
	}


}
