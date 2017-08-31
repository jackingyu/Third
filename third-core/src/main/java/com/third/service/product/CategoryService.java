package com.third.service.product;

import com.third.dao.util.PaginationSupport;
import com.third.model.CategoryModel;


public interface CategoryService
{
	void createCategory(final CategoryModel category);
	
	void getCategoryForCode(final String code);
}
