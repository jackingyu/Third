package com.third.facade.populator;

import com.third.facade.data.ProductGroupData;
import com.third.model.ProductGroupModel;

public class ProductGroupDataPopulator
		implements Populator<ProductGroupModel, ProductGroupData> {

	@Override
	public void populate(ProductGroupModel source, ProductGroupData target)
	{
		target.setName(source.getName());
		target.setPk(source.getPk());
	}

}
