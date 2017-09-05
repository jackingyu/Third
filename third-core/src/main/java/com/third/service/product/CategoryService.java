package com.third.service.product;

import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.model.CategoryModel;


public interface CategoryService
{
	void createCategory(final CategoryModel category);
	
	CategoryModel getCategoryForCode(final String code);
	
	List<CategoryModel> getCategories();
}
