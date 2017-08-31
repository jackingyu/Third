package com.third.service.product.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.third.dao.product.CategoryDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.CategoryModel;
import com.third.service.product.CategoryService;


public class DefaultCategoryService implements CategoryService
{
	private CategoryDao categoryDao;

	@Override
	public void createCategory(CategoryModel category)
	{
		categoryDao.save(category);
	}

	@Override
	public void getCategoryForCode(String code)
	{
		// TODO Auto-generated method stub
		
	}

	public void setCategoryDao(CategoryDao categoryDao)
	{
		this.categoryDao = categoryDao;
	}
	
}
