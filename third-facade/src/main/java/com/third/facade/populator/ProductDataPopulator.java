package com.third.facade.populator;

import com.third.facade.data.ProductData;
import com.third.model.ProductModel;


public class ProductDataPopulator implements Populator<ProductModel, ProductData>
{

	@Override
	public void populate(ProductModel source, ProductData target)
	{
		target.setCode(source.getCode());
		target.setProducttitle(source.getProducttitle());
		target.setProducttype(source.getProducttype());
	}

}
