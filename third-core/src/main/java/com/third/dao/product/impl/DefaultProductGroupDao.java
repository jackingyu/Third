package com.third.dao.product.impl;

import com.third.dao.generic.GenericDAO;
import com.third.dao.product.ProductGroupDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductGroupModel;

public class DefaultProductGroupDao extends GenericDAO<ProductGroupModel, String> implements ProductGroupDao
{
	@Override
	public PaginationSupport getProductGroupList(Integer startIndex, Integer pageSize)
	{
		final StringBuilder sb = new StringBuilder("select p.name  from ProductGroupModel  p ");

		return findPageByQuery(sb.toString(), pageSize, startIndex);
	}
}
