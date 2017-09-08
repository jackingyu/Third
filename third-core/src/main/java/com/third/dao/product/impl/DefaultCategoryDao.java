package com.third.dao.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.third.dao.generic.GenericDAO;
import com.third.dao.product.CategoryDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.CategoryModel;
import com.third.model.UserModel;

public class DefaultCategoryDao extends GenericDAO<CategoryModel, String>
		implements CategoryDao {

	@Override
	public CategoryModel getCategoryForCode(String code)
	{
		DetachedCriteria criteria = DetachedCriteria
				.forClass(CategoryModel.class);

		criteria.add(Restrictions.eq("code", code));

		List<CategoryModel> categories = findByCriteria(criteria);

		return CollectionUtils.isNotEmpty(categories) ? categories.get(0)
				: null;
	}

}
