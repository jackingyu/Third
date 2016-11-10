package com.third.dao.product.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.product.ProductDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductModel;


public class DefaultProductDao extends GenericDAO<ProductModel, String> implements ProductDao
{
	private final static String FIND_BY_CODE_SQL = "from com.third.model.ProductModel p where p.code=?";

	@Override
	public ProductModel getProductForCode(String code)
	{
		List<ProductModel> products = this.find(FIND_BY_CODE_SQL, code);
		return CollectionUtils.isEmpty(products) ? null : products.get(0);
	}





}
