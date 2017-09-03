package com.third.dao.product.impl;

import java.util.List;

import com.third.dao.generic.GenericDAO;
import com.third.dao.product.ProductGroupDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;

public class DefaultProductGroupDao extends GenericDAO<ProductGroupModel, String> implements ProductGroupDao
{
	@Override
	public List<ProductGroupModel> getProductGroupList()
	{
		return list();
	}
}
