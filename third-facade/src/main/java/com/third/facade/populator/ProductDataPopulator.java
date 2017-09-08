package com.third.facade.populator;

import com.third.facade.data.CategoryData;
import com.third.facade.data.ProductData;
import com.third.facade.data.ProductGroupData;
import com.third.model.CategoryModel;
import com.third.model.ProductGroupModel;
import com.third.model.ProductModel;

public class ProductDataPopulator
		implements Populator<ProductModel, ProductData> {

	@Override
	public void populate(ProductModel source, ProductData target)
	{
		target.setCode(source.getCode());
		target.setProducttitle(source.getProducttitle());
		target.setProducttype(source.getProducttype());
		ProductGroupData productGroup = new ProductGroupData();
		ProductGroupModel productGroupModel = source.getProductGroup();
		productGroup.setName(productGroupModel.getName());
		productGroup.setPk(productGroupModel.getPk());

		CategoryData category = new CategoryData();
		CategoryModel categoryModel = source.getCategory();
		category.setCode(categoryModel.getCode());
		category.setName(categoryModel.getName());

		target.setCategory(category);
		target.setProductGroup(productGroup);
	}

}
