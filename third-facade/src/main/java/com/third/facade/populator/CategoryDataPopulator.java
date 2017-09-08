package com.third.facade.populator;

import com.third.facade.data.CategoryData;
import com.third.model.CategoryModel;

public class CategoryDataPopulator
		implements Populator<CategoryModel, CategoryData> {

	@Override
	public void populate(CategoryModel source, CategoryData target)
	{
		target.setCode(source.getCode());
		target.setPk(source.getPk());
		target.setName(source.getName());
	}

}
