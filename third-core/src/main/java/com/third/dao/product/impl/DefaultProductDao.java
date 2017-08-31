package com.third.dao.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

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


	@Override
	public PaginationSupport getProductList(String productCode, String productTitle, String category,final Integer startIndex,
			final Integer pageSize)
	{
		final StringBuilder sb = new StringBuilder("select p.code,p.producttitle,p.category.name from ProductModel  p ");
		final StringBuilder w = new StringBuilder(" where p.category.code = '"+category+"'");

		List<String> condition = new ArrayList<String>();

		if (StringUtils.isNotBlank(productCode))
		{
			StringBuilder c = new StringBuilder().append("p.code = '").append(productCode).append("'");
			condition.add(c.toString());
		}

		if (StringUtils.isNotBlank(productTitle))
		{
			StringBuilder c = new StringBuilder().append("p.producttitle like '%").append(productTitle).append("%'");
			condition.add(c.toString());
		}

		if (CollectionUtils.isNotEmpty(condition))
		{
			w.append(" and ").append(condition.get(0));
			for (int i = 0; i < condition.size(); i++)
			{
				w.append(" and ").append(condition.get(i));
			}
		}
		
		sb.append(w);

		return findPageByQuery(sb.toString(), pageSize, startIndex);
	}



}
