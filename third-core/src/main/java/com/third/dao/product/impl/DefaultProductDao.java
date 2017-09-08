package com.third.dao.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.third.dao.generic.GenericDAO;
import com.third.dao.product.ProductDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.ProductModel;

public class DefaultProductDao extends GenericDAO<ProductModel, String>
		implements ProductDao {
	private Log logger = LogFactory.getLog(getClass());
	private final static String FIND_BY_CODE_SQL = "from com.third.model.ProductModel p where p.code=?";

	@Override
	public ProductModel getProductForCode(String code)
	{
		List<ProductModel> products = this.find(FIND_BY_CODE_SQL, code);
		return CollectionUtils.isEmpty(products) ? null : products.get(0);
	}

	@Override
	public PaginationSupport getProductList(Map<String, String> sp,
			final Integer startIndex, final Integer pageSize)
	{
		final StringBuilder sb = new StringBuilder(
				"select p.code,p.producttitle,p.category.name,p.productGroup.name from ProductModel  p ");
		final StringBuilder w = new StringBuilder(" where p.category.code = '"
				+ getParameterValue(sp, "category") + "'");

		List<String> condition = new ArrayList<String>();

		if (StringUtils.isNotBlank(getParameterValue(sp, "productGroup")))
		{
			StringBuilder c = new StringBuilder().append("p.productGroup = '")
					.append(getParameterValue(sp, "productGroup")).append("'");
			condition.add(c.toString());
		}

		if (StringUtils.isNotBlank(getParameterValue(sp, "productCode")))
		{
			StringBuilder c = new StringBuilder().append("p.code = '")
					.append(getParameterValue(sp, "productCode")).append("'");
			condition.add(c.toString());
		}

		if (StringUtils.isNotBlank(getParameterValue(sp, "productTitle")))
		{
			StringBuilder c = new StringBuilder()
					.append("p.producttitle like '%")
					.append(getParameterValue(sp, "productTitle")).append("%'");
			condition.add(c.toString());
		}

		if (CollectionUtils.isNotEmpty(condition))
		{
			// w.append(" and ").append(condition.get(0));
			for (int i = 0; i < condition.size(); i++)
			{
				w.append(" and ").append(condition.get(i));
			}
		}

		sb.append(w);

		logger.info(sb.toString());
		return findPageByQuery(sb.toString(), pageSize, startIndex);
	}

	@Override
	public PaginationSupport getProductList1(Map<String, String[]> sp,
			final Integer startIndex, final Integer pageSize)
	{
		final StringBuilder sb = new StringBuilder(
				"select p.code,p.producttitle,p.category.name,p.productGroup.name from ProductModel  p ");
		String c1 = getArrayCondtion(sp, "productGroups", "p.productGroup");
		String c2 = getArrayCondtion(sp, "categories", "p.category.code");
		String c3 = getArrayCondtion(sp, "productCode", "p.code");
		String c4 = StringUtils.EMPTY;
		if (sp.containsKey("productTitle") && sp.get("productTitle") != null)
			if (sp.get("productTitle").length >= 1
					&& StringUtils.isNotEmpty(sp.get("productTitle")[0]))
			{
				c4 = new StringBuilder("producttitle like '")
						.append(generateLikeParameter(
								sp.get("productTitle")[0]))
						.append("'").toString();
			}

		List<String> condition = new ArrayList<String>();

		if (StringUtils.isNotEmpty(c3))
			condition.add(c3);
		if (StringUtils.isNotEmpty(c1))
			condition.add(c1);
		if (StringUtils.isNotEmpty(c2))
			condition.add(c2);
		if (StringUtils.isNotEmpty(c4))
			condition.add(c4);

		if (CollectionUtils.isNotEmpty(condition))
		{
			sb.append("where ")
					.append(StringUtils.join(condition.toArray(), " and "));
		}

		return findPageByQuery(sb.toString(), pageSize, startIndex);
	}

}
